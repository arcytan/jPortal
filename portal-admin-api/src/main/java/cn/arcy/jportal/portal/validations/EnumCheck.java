package cn.arcy.jportal.portal.validations;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {EnumCheck.EnumCheckValidator.class})
public @interface EnumCheck {
    String message() default "字段格式错误";

    //分组校验
    Class<?>[] groups() default {};

    Class<?>[] enumClass();

    Class<? extends Payload>[] payload() default {};

    class EnumCheckValidator implements ConstraintValidator<EnumCheck, String> {

        @Override
        public void initialize(EnumCheck constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return false;
        }
    }
}
