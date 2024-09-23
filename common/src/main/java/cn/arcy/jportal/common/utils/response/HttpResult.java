package cn.arcy.jportal.common.utils.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Data
public class HttpResult<T> implements Serializable {

    private static final long serialVersionUID = 7364069549053183238L;

    int code;
    String message;
    T data;

    @Builder.Default
    Map<String, Object> fields = new HashMap<>();

    public static HttpResult<?> ok(String message, @Nullable Map<String, Object> data)
    {
        return HttpResult.builder().code(HttpStatus.OK.value()).message(message).data(data).build();
    }

    public static HttpResult<?> ok(String message)
    {
        return ok(message, null);
    }

    public static HttpResult<?> unavailable(String message, @Nullable Map<String, Object> data)
    {
        return HttpResult.builder().code(HttpStatus.SERVICE_UNAVAILABLE.value()).message(message).data(data).build();
    }

    public static HttpResult<?> unavailable(String message)
    {
        return unavailable(message, null);
    }

    /**
     * 按统一的分页格式返回
     * @param page
     * @param message
     * @return
     * @param <T>
     */
    public static <T> HttpResult<List<T>> page(@Nonnull Page<T> page, String message)
    {
        Map<String, Object> fields = Map.of(
                "pageSize", page.getSize(),
                "totalElements", page.getTotalElements(),
                "currentPage", page.getNumber(),
                "totalPages", page.getTotalPages()
        );
        return HttpResult.<List<T>>builder().code(HttpStatus.OK.value()).message(message).fields(fields).data(page.getContent()).build();
    }

    @JsonAnyGetter
    public Map<String, Object> getFields()
    {
        return fields;
    }
}
