package utils;

public class RegexUtils {
    public static final String CURRENT_FORMAT_REGEX = "^[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}$"; // Post-2001
    public static final String PREFIX_FORMAT_REGEX = "^[A-Z][0-9]{1,3}\\s?[A-Z]{3}$";  // 1983–2001
    public static final String SUFFIX_FORMAT_REGEX = "^[A-Z]{3}\\s?[0-9]{1,3}[A-Z]$";  // 1963–1983
    public static final String DATELESS_FORMAT_REGEX = "^([A-Z]{1,3}\\s?[0-9]{1,3}|[0-9]{1,3}\\s?[A-Z]{1,3})$"; // Pre-1963

    public static boolean isValidRegistration(String registration) {
        return registration.matches(CURRENT_FORMAT_REGEX) ||
                registration.matches(PREFIX_FORMAT_REGEX) ||
                registration.matches(SUFFIX_FORMAT_REGEX) ||
                registration.matches(DATELESS_FORMAT_REGEX);
    }
}
