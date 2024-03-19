package cn.arcy.jportal.portal.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {

    public static PasswordEncoder getEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public static String encode(String rawPassword)
    {
        return getEncoder().encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword)
    {
        return getEncoder().matches(rawPassword, encodedPassword);
    }
}
