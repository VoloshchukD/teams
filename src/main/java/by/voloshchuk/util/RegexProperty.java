package by.voloshchuk.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Constants with regular expression values.
 *
 * @author Daniil Voloshchuk
 */
public class RegexProperty {

    private static final String REGEX_EMAIL = "regex.email";

    private static final String REGEX_PASSWORD = "regex.password";

    private static final String REGEX_FIRST = "regex.forename";

    private static final String REGEX_LAST = "regex.surname";

    private static final String REGEX_COMPANY = "regex.company";

    private static final String REGEX_POSITION = "regex.position";

    private static final String REGEX_EXPERIENCE = "regex.experience";

    private static final String REGEX_SALARY = "regex.salary";

    private static final String REGEX_PRIMARY = "regex.primary";

    private static final String REGEX_SKILLS = "regex.skills";

    private static final String REGEX_PROJECT_NAME = "regex.project.name";

    private static final String REGEX_PROJECT_DESCRIPTION = "regex.project.description";

    private static final String REGEX_TECHNICAL_TASK_NAME = "regex.technical-task.name";

    private static final String REGEX_TECHNICAL_TASK_OVERVIEW = "regex.technical-task.overview";

    private static final String REGEX_TECHNICAL_TASK_DEADLINE = "regex.technical-task.deadline";

    private static final String REGEX_BILL_AMOUNT = "regex.bill.amount";

    private static final String REGEX_BILL_INFORMATION = "regex.bill.information";

    private static final String NUMBER = "regex.number";

    private static final String REGEX_REQUIREMENT_QUALIFICATION = "regex.requirement.qualification";

    private static final String REGEX_REQUIREMENT_PRIMARY_SKILL = "regex.requirement.primary-skill";

    private static final String REGEX_PAYMENT_HOLDER = "regex.payment.holder";

    private static final String REGEX_PAYMENT_MM = "regex.payment.mm";

    private static final String REGEX_PAYMENT_YY = "regex.payment.yy";

    private static final String REGEX_PAYMENT_NUMBER = "regex.payment.number";

    private static final String REGEX_PAYMENT_CVC = "regex.payment.cvc";

    private static final String RESOURCE_NAME = "regex";

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, Locale.getDefault());

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

    public static final String PROPERTY_PROJECT_NAME_REGEX = resourceBundle.getString(REGEX_PROJECT_NAME);

    public static final String PROPERTY_PROJECT_DESCRIPTION_REGEX = resourceBundle.getString(
            REGEX_PROJECT_DESCRIPTION);

    public static final String PROPERTY_TECHNICAL_TASK_NAME_REGEX = resourceBundle.getString(
            REGEX_TECHNICAL_TASK_NAME);

    public static final String PROPERTY_TECHNICAL_TASK_OVERVIEW_REGEX = resourceBundle.getString(
            REGEX_TECHNICAL_TASK_OVERVIEW);

    public static final String PROPERTY_TECHNICAL_TASK_DEADLINE_REGEX = resourceBundle.getString(
            REGEX_TECHNICAL_TASK_DEADLINE);

    public static final String PROPERTY_BILL_AMOUNT_REGEX = resourceBundle.getString(REGEX_BILL_AMOUNT);

    public static final String PROPERTY_BILL_INFORMATION_REGEX = resourceBundle.getString(REGEX_BILL_INFORMATION);

    public static final String PROPERTY_NUMBER_REGEX = resourceBundle.getString(NUMBER);

    public static final String PROPERTY_REQUIREMENT_QUALIFICATION_REGEX = resourceBundle.getString(
            REGEX_REQUIREMENT_QUALIFICATION);

    public static final String PROPERTY_REQUIREMENT_PRIMARY_REGEX = resourceBundle.getString(
            REGEX_REQUIREMENT_PRIMARY_SKILL);

    public static final String PROPERTY_PAYMENT_HOLDER_REGEX = resourceBundle.getString(
            REGEX_PAYMENT_HOLDER);

    public static final String PROPERTY_PAYMENT_MM_REGEX = resourceBundle.getString(
            REGEX_PAYMENT_MM);

    public static final String PROPERTY_PAYMENT_YY_REGEX = resourceBundle.getString(
            REGEX_PAYMENT_YY);

    public static final String PROPERTY_PAYMENT_NUMBER_REGEX = resourceBundle.getString(
            REGEX_PAYMENT_NUMBER);

    public static final String PROPERTY_PAYMENT_CVC_REGEX = resourceBundle.getString(
            REGEX_PAYMENT_CVC);

}
