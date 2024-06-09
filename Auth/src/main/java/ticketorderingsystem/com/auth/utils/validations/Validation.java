package ticketorderingsystem.com.auth.utils.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    public static boolean isNicknameValid(String nickname) {
        return !nickname.trim().isEmpty();
    }

    public static boolean isEmailValid(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email.trim());
        return matcher.matches();
    }

    public static boolean isPasswordValid(String password) {
        Matcher matcher = PASSWORD_PATTERN.matcher(password.trim());
        return matcher.matches();
    }
}
