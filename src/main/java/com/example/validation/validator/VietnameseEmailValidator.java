package com.example.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class VietnameseEmailValidator implements ConstraintValidator<VietnameseEmail, String> {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );
    
    private static final String[] VIETNAMESE_DOMAINS = {
        "gmail.com", "yahoo.com", "hotmail.com", "outlook.com",
        "fpt.edu.vn", "hust.edu.vn", "uit.edu.vn", "hcmut.edu.vn"
    };

    @Override
    public void initialize(VietnameseEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return false;
        }
        
        String domain = email.substring(email.lastIndexOf("@") + 1);
        for (String allowedDomain : VIETNAMESE_DOMAINS) {
            if (domain.equalsIgnoreCase(allowedDomain)) {
                return true;
            }
        }
        
        return false;
    }
}