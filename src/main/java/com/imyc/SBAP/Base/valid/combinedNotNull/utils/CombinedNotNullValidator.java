package com.imyc.SBAP.Base.valid.combinedNotNull.utils;

import com.imyc.SBAP.Base.valid.combinedNotNull.annotation.CombinedNotNull;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CombinedNotNullValidator implements ConstraintValidator<CombinedNotNull, Object> {

    private String[] fields;

    @Override
    public void initialize(final CombinedNotNull combinedNotNull) {
        fields = combinedNotNull.fields();
    }

    @Override
    public boolean isValid(final Object value, ConstraintValidatorContext context) {
        final BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);

        List<String> nonNullFieldsContainer = new ArrayList<>();
        for (final String f : fields) {
            Object fieldValue = beanWrapper.getPropertyValue(f);
            if (fieldValue != "") {
                nonNullFieldsContainer.add(f);
            }
        }

        if (nonNullFieldsContainer.isEmpty()) {
            return false;
        }else{
            return true;
        }
    }
}
