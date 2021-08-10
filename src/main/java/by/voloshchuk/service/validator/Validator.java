package by.voloshchuk.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Validator<T> {

    boolean validateCreateData(T entity);

    boolean validateUpdateData(T entity);

    default boolean validateString(String target, String patternString) {
        boolean valid = false;
        if (target != null) {
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(target);
            valid = matcher.matches();
        }
        return valid;
    }

}
