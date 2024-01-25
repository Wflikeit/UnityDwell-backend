package UnityDwell.com.UnityDwell.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CharTNValidator implements ConstraintValidator<CharTN, Character> {
    @Override
    public boolean isValid(Character value, ConstraintValidatorContext context) {
        return value == null || (value == 'T' || value == 'N');
    }
}
