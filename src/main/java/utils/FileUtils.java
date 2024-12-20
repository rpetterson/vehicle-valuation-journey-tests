package utils;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class FileUtils {

    private static final String REGISTRATION_REGEX = "\\b[A-Z]{2}[0-9]{2}\\s[A-Z]{3}\\b"; // Full format: AA NN AAA

    public static List<String> readValidRegistrations(String filePath) {
        List<String> validRegistrations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains("registration") || line.toLowerCase().contains("registrations")) {
                    // Use regex to extract patterns that match UK registration formats
                    Matcher matcher = Pattern.compile("([A-Z]{2}[0-9]{2}\\s?[A-Z]{3})|([A-Z][0-9]{1,3}\\s?[A-Z]{3})|([A-Z]{3}\\s?[0-9]{1,3}[A-Z])|(([A-Z]{1,3}\\s?[0-9]{1,3}|[0-9]{1,3}\\s?[A-Z]{1,3}))").matcher(line);

                    while (matcher.find()) {
                        String registration = matcher.group().replaceAll("\\s", "").trim(); // Trim spaces
                        if (RegexUtils.isValidRegistration(registration)) {
                            validRegistrations.add(registration);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return validRegistrations;
    }

    // New method to read car details file and return expected details
    public static Map<String, Map<String, String>> readCarDetailsFile(String filePath) {
        Map<String, Map<String, String>> carDetailsMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String registration = parts[0].replaceAll("\\s", "").trim(); // Clean spaces
                    String makeModel = parts[1].trim();
                    String year = parts[2].trim();

                    Map<String, String> details = new HashMap<>();
                    details.put("MAKE_MODEL", makeModel);
                    details.put("YEAR", year);

                    carDetailsMap.put(registration, details);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return carDetailsMap;
    }
}
