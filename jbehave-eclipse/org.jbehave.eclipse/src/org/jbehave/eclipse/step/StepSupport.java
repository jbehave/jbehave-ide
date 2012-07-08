package org.jbehave.eclipse.step;

import static org.jbehave.eclipse.util.StringEnhancer.enhanceString;
import static org.jbehave.eclipse.util.Strings.s;

import org.jbehave.core.steps.StepType;
import org.jbehave.eclipse.util.StringEnhancer;
import org.jbehave.eclipse.util.Strings;

public class StepSupport {
    
    public static boolean isTheStartIgnoringCaseOfStep(LocalizedStepSupport localizedStepSupport, String line) {
        return enhanceString(line).isTheStartIgnoringCaseOfOneOf(//
                localizedStepSupport.given(true), //
                localizedStepSupport.when(true), //
                localizedStepSupport.then(true), //
                localizedStepSupport.and(true));
    }

    public static boolean isStepIgnoringCase(LocalizedStepSupport localizedStepSupport, String line) {
        return enhanceString(line).startsIgnoringCaseWithOneOf(//
                localizedStepSupport.given(true), //
                localizedStepSupport.when(true), //
                localizedStepSupport.then(true), //
                localizedStepSupport.and(true));
    }
    
    public static boolean isStepType(LocalizedStepSupport localizedStepSupport, String line) {
        return enhanceString(line).equalsToOneOf(//
                localizedStepSupport.given(true), //
                localizedStepSupport.when(true), //
                localizedStepSupport.then(true), //
                localizedStepSupport.and(true));
    }
    
    public static boolean isStepAndType(LocalizedStepSupport localizedStepSupport, String line) {
        return enhanceString(line).startsIgnoringCaseWithOneOf(localizedStepSupport.and(true));
    }

    
    public static int stepSentenceIndex(LocalizedStepSupport localizedStepSupport, String line) {
        StringEnhancer enhanced = enhanceString(line);
        for(String prefix : s(//
                localizedStepSupport.given(true), //
                localizedStepSupport.when(true), //
                localizedStepSupport.then(true), //
                localizedStepSupport.and(true))) {
            if(enhanced.startsIgnoringCaseWith(prefix))
                return prefix.length();
        }
        return 0;
    }
    
    /**
     * Remove the step keyword from the given line.
     * <strong>In case of multiline step</strong> you may prefer to use the
     * {@link #extractStepSentenceAndRemoveTrailingNewlines(String)} alternative.
     * @param line
     * @return
     */
    public static String extractStepSentence(LocalizedStepSupport localizedStepSupport, String line) {
        int indexOf = stepSentenceIndex(localizedStepSupport, line);
        return line.substring(indexOf);
    }
    
    public static String extractStepSentenceAndRemoveTrailingNewlines(LocalizedStepSupport localizedStepSupport, String text) {
        return Strings.removeTrailingNewlines(extractStepSentence(localizedStepSupport, text));
    }

    public static String stepType(LocalizedStepSupport localizedStepSupport, String stepLine) {
        StringEnhancer enhanced = enhanceString(stepLine);
        if(enhanced.startsIgnoringCaseWith(localizedStepSupport.when(true)))
            return StepType.WHEN.name();
        else if(enhanced.startsIgnoringCaseWith(localizedStepSupport.given(true)))
            return StepType.GIVEN.name();
        else if(enhanced.startsIgnoringCaseWith(localizedStepSupport.then(true)))
            return StepType.THEN.name();
        return null;
    }
}
