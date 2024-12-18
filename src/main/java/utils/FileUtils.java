package utils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileUtils {

    public static List<String> readValidRegistrations(String filePath) {
        List<String> validRegistrations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains("registration")) {
                    validRegistrations.addAll(Arrays.stream(line.split("[,\\s]+"))
                            .map(token -> token.replaceAll("\\s", "").trim())
                            .filter(RegexUtils::isValidRegistration)
                            .collect(Collectors.toList()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return validRegistrations;
    }

    public static Map<String, Map<String, String>> readCarDetailsFile(String filePath) {
        Map<String, Map<String, String>> carDetails = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("VARIANT_REG")) { // Skip header
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        Map<String, String> details = new HashMap<>();
                        details.put("MAKE_MODEL", parts[1]);
                        details.put("YEAR", parts[2]);
                        carDetails.put(parts[0].trim(), details);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carDetails;
    }
}
