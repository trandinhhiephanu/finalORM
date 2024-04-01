package fa.training.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountNumberValidator implements ConstraintValidator<AccountNumber, String> {

    @Override
    public void initialize(AccountNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String accountNumber, ConstraintValidatorContext constraintValidatorContext) {
        return accountNumber != null && accountNumber.matches("\\d{10}");
    }
}