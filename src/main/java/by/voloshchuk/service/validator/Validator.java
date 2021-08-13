package by.voloshchuk.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Validator<T> {

    /**
     * Validate data required for creation.
     *
     * @param entity - entity to be validated
     * @return boolean result of validation
     */
    boolean validateCreateData(T entity);

    /**
     * Validate data required for updating.
     *
     * @param entity - entity to be validated
     * @return boolean result of validation
     */
    boolean validateUpdateData(T entity);

    /**
     * Validate target string according to pattern.
     *
     * @param target        - string to be validated
     * @param patternString - pattern to be compared to
     * @return boolean result of validation
     */
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
