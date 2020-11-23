package com.imyc.SBAP.Base.valid.combinedNotNull.annotation;


import com.imyc.SBAP.Base.valid.combinedNotNull.utils.CombinedNotNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CombinedNotNullValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CombinedNotNull {
    String message() default "{dto.combined.not.null.error}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // Fields to validate against null.
    String[] fields() default {};
}