package com.example.validation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VietnameseEmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface VietnameseEmail {
    String message() default "Email phải thuộc các tên miền được chấp nhận (.edu.vn, gmail.com, yahoo.com, hotmail.com, outlook.com)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}