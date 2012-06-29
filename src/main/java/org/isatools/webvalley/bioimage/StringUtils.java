package org.isatools.webvalley.bioimage;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 29/06/2012
 *         Time: 17:19
 */
public class StringUtils {

    public static String processString(String stringToProcess) {

        // replace underscore
        String cleanedString = removeValueFromString(stringToProcess, "AreaShape");
        cleanedString = replaceUnderscore(cleanedString);
        cleanedString = splitCamelCase(cleanedString);
        cleanedString = removeDuplicateSpaces(cleanedString);
        return cleanedString.trim();
    }

    private static String removeValueFromString(String stringToProcess, String toRemove) {
        return stringToProcess.replaceAll(toRemove, "");
    }

    private static String replaceUnderscore(String stringToProcess) {
        return stringToProcess.replaceAll("_", " ");
    }

    private static String removeDuplicateSpaces(String stringToProcess) {
        return stringToProcess.replaceAll("\\s+", " ");
    }

    public static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ), " "
        );
    }

}
