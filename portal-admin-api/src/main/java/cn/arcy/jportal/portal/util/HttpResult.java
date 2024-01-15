package cn.arcy.jportal.portal.util;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Builder
@Data
public class HttpResult<T> implements Serializable {

    private static final long serialVersionUID = 7364069549053183238L;

    int code;
    String message;
    T data;

    Map<String, Object> fields = new HashMap<>();

    public static HttpResult<?> Ok(String message, @Nullable Map<String, Object> data)
    {
        return HttpResult.builder().code(HttpStatus.OK.value()).message(message).data(data).build();
    }

    public static HttpResult<?> Ok(String message)
    {
        return Ok(message, null);
    }

    public static HttpResult<?> Unavailable(String message, @Nullable Map<String, Object> data)
    {
        return HttpResult.builder().code(HttpStatus.SERVICE_UNAVAILABLE.value()).message(message).data(data).build();
    }

    public static HttpResult<?> Unavailable(String message)
    {
        return Unavailable(message, null);
    }

    @JsonAnyGetter
    public Map<String, Object> getFields()
    {
        return fields;
    }
}
