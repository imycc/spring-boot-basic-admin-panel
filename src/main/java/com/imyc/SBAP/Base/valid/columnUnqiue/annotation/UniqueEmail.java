package com.imyc.SBAP.Base.valid.columnUnqiue.annotation;

import com.imyc.SBAP.Base.valid.columnUnqiue.utils.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "{dto.email.error}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}