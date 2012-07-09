package org.jbehave.eclipse.editor.step;

import static org.jbehave.eclipse.util.StringEnhancer.enhanceString;
import static org.jbehave.eclipse.util.Strings.array;

import org.jbehave.core.steps.StepType;
import org.jbehave.eclipse.util.StringEnhancer;
import org.jbehave.eclipse.util.Strings;

public class StepSupport {

	public static boolean isTheStartIgnoringCaseOfStep(
			LocalizedStepSupport localizedStepSupport, String step) {
		return enhanceString(step).isTheStartIgnoringCaseOfOneOf(//
				localizedStepSupport.given(true), //
				localizedStepSupport.when(true), //
				localizedStepSupport.then(true), //
				localizedStepSupport.and(true));
	}

	public static boolean isStepIgnoringCase(
			LocalizedStepSupport localizedStepSupport, String step) {
		return enhanceString(step).startsIgnoringCaseWithOneOf(//
				localizedStepSupport.given(true), //
				localizedStepSupport.when(true), //
				localizedStepSupport.then(true), //
				localizedStepSupport.and(true));
	}

	public static boolean isStepType(LocalizedStepSupport localizedStepSupport,
			String step) {
		return enhanceString(step).equalsToOneOf(//
				localizedStepSupport.given(true), //
				localizedStepSupport.when(true), //
				localizedStepSupport.then(true), //
				localizedStepSupport.and(true));
	}

	public static boolean isStepAndType(
			LocalizedStepSupport localizedStepSupport, String step) {
		return enhanceString(step).startsIgnoringCaseWithOneOf(
				localizedStepSupport.and(true));
	}

	public static int stepKeywordIndex(
			LocalizedStepSupport localizedStepSupport, String step) {
		StringEnhancer enhanced = enhanceString(step);
		for (String prefix : array(//
				localizedStepSupport.given(true), //
				localizedStepSupport.when(true), //
				localizedStepSupport.then(true), //
				localizedStepSupport.and(true))) {
			if (enhanced.startsIgnoringCaseWith(prefix)) {
				return prefix.length();
			}
		}
		return 0;
	}

	/**
	 * Remove the step keyword from the given line. <strong>In case of multiline
	 * step</strong> you may prefer to use the
	 * {@link #extractStepSentenceAndRemoveTrailingNewlines(String)}
	 * alternative.
	 * 
	 * @param step
	 * @return
	 */
	public static String stepWithoutKeyword(
			LocalizedStepSupport localizedStepSupport, String step) {
		return step.substring(stepKeywordIndex(localizedStepSupport, step));
	}

	public static String stepWithoutKeywordAndTrailingNewlines(
			LocalizedStepSupport localizedStepSupport, String step) {
		return Strings.removeTrailingNewlines(stepWithoutKeyword(
				localizedStepSupport, step));
	}

	public static String stepType(LocalizedStepSupport localizedStepSupport,
			String step) {
		StringEnhancer enhanced = enhanceString(step);
		if (enhanced.startsIgnoringCaseWith(localizedStepSupport.when(true)))
			return StepType.WHEN.name();
		else if (enhanced.startsIgnoringCaseWith(localizedStepSupport
				.given(true)))
			return StepType.GIVEN.name();
		else if (enhanced.startsIgnoringCaseWith(localizedStepSupport
				.then(true)))
			return StepType.THEN.name();
		return null;
	}
}
