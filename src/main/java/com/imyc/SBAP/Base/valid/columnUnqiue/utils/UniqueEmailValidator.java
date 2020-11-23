package com.imyc.SBAP.Base.valid.columnUnqiue.utils;


import com.imyc.SBAP.Base.valid.columnUnqiue.annotation.UniqueEmail;
import com.imyc.SBAP.Http.user.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private UserRepository userRepo;

    @Autowired
    public UniqueEmailValidator(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        if (email != "") {
            return userRepo.findByEmail(email).isEmpty();
        }else{
            return true;
        }

    }
}