package lt.ssva.task.validators;

import java.net.MalformedURLException;
import java.net.URL;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<ValidUrl, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        try {
            new URL(value);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

}