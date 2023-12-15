package cn.arcy.jportal.portal.util;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
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

    @JsonAnyGetter
    public Map<String, Object> getFields()
    {
        return fields;
    }
}
