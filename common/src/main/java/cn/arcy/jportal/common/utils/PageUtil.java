package cn.arcy.jportal.common.utils;

import jakarta.annotation.Nonnull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public final class PageUtil {

    @Nonnull
    public static <T, R> Page<R> toPage(@Nonnull Page<T> from, @Nonnull Class<R> to)
    {
        List<T> fromContent = from.getContent();
        List<R> toContent = new ArrayList<>();
        fromContent.forEach(it -> {
            try {
                R toObj = to.getConstructor().newInstance();
                BeanUtils.copyProperties(it, toObj);
                toContent.add(toObj);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });

        return new PageImpl<>(toContent, from.getPageable(), from.getTotalElements());
    }
}
