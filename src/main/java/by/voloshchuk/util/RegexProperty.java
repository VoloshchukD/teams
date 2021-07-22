package by.voloshchuk.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class RegexProperty {

    public static final String REGEX_EMAIL = "regex.email";

    public static final String REGEX_PASSWORD = "regex.password";

    public static final String REGEX_FIRST = "regex.first";

    public static final String REGEX_LAST = "regex.last";

    public static final String REGEX_COMPANY = "regex.company";

    public static final String REGEX_POSITION = "regex.position";

    public static final String REGEX_EXPERIENCE = "regex.experience";

    public static final String REGEX_SALARY = "regex.salary";

    public static final String REGEX_PRIMARY = "regex.primary";

    public static final String REGEX_SKILLS = "regex.skills";

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("regex", Locale.getDefault());

    public static final String PROPERTY_EMAIL_REGEX = resourceBundle.getString(REGEX_EMAIL);

    public static final String PROPERTY_PASSWORD_REGEX = resourceBundle.getString(REGEX_PASSWORD);

    public static final String PROPERTY_FIRST_REGEX = resourceBundle.getString(REGEX_FIRST);

    public static final String PROPERTY_LAST_REGEX = resourceBundle.getString(REGEX_LAST);

    public static final String PROPERTY_COMPANY_REGEX = resourceBundle.getString(REGEX_COMPANY);

    public static final String PROPERTY_POSITION_REGEX = resourceBundle.getString(REGEX_POSITION);

    public static final String PROPERTY_EXPERIENCE_REGEX = resourceBundle.getString(REGEX_EXPERIENCE);

    public static final String PROPERTY_SALARY_REGEX = resourceBundle.getString(REGEX_SALARY);

    public static final String PROPERTY_PRIMARY_REGEX = resourceBundle.getString(REGEX_PRIMARY);

    public static final String PROPERTY_SKILLS_REGEX = resourceBundle.getString(REGEX_SKILLS);

}
