package org.jbehave.eclipse.jface;

public class TemplateUtils {

    public static String templatizeVariables(String content) {
        return content.replaceAll("\\$([a-zA-Z0-9]+)", "\\${$1}");
    }
}
