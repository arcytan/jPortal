package cn.arcy.jportal.portal.security;

import cn.arcy.jportal.portal.util.HttpResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.server.resource.BearerTokenErrorCodes;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 处理JWT无授权的验证异常
 */
@Slf4j
public class BearTokenAccessDeniedHandler  implements AccessDeniedHandler {
    private String realmName;

    /**
     * Collect error details from the provided parameters and format according to RFC
     * 6750, specifically {@code error}, {@code error_description}, {@code error_uri}, and
     * {@code scope}.
     * @param request that resulted in an <code>AccessDeniedException</code>
     * @param response so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        Map<String, String> parameters = new LinkedHashMap<>();
        HttpStatus status = HttpStatus.FORBIDDEN;
        if (this.realmName != null) {
            parameters.put("realm", this.realmName);
        }
        if (request.getUserPrincipal() instanceof AbstractOAuth2TokenAuthenticationToken) {
            parameters.put("error", BearerTokenErrorCodes.INSUFFICIENT_SCOPE);
            parameters.put("error_description",
                    "The request requires higher privileges than provided by the access token.");
            parameters.put("error_uri", "https://tools.ietf.org/html/rfc6750#section-3.1");
        }
        log.info("用户无授权：{}", accessDeniedException.toString());
        String wwwAuthenticate = computeWWWAuthenticateHeaderValue(parameters);
        response.addHeader(HttpHeaders.WWW_AUTHENTICATE, wwwAuthenticate);
        response.setStatus(status.value());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        HttpResult<Object> httpResult = HttpResult.builder().code(status.value()).message("用户无授权！").build();
        response.getWriter().println(objectMapper.writeValueAsString(httpResult));
    }

    /**
     * Set the default realm name to use in the bearer token error response
     * @param realmName
     */
    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    private static String computeWWWAuthenticateHeaderValue(Map<String, String> parameters) {
        StringBuilder wwwAuthenticate = new StringBuilder();
        wwwAuthenticate.append("Bearer");
        if (!parameters.isEmpty()) {
            wwwAuthenticate.append(" ");
            int i = 0;
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                wwwAuthenticate.append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
                if (i != parameters.size() - 1) {
                    wwwAuthenticate.append(", ");
                }
                i++;
            }
        }
        return wwwAuthenticate.toString();
    }
}
