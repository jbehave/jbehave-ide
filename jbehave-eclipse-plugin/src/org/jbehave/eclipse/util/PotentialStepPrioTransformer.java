package org.jbehave.eclipse.util;

import org.jbehave.eclipse.PotentialStep;

import fj.F;

public final class PotentialStepPrioTransformer extends F<PotentialStep, Integer> {

    @Override
    public Integer f(PotentialStep pStep) {
        return pStep.priority;
    }
}