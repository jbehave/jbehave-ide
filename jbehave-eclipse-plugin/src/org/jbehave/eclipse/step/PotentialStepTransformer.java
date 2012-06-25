package org.jbehave.eclipse.step;


import fj.F;

public final class PotentialStepTransformer extends F<PotentialStep, Integer> {

    @Override
    public Integer f(PotentialStep pStep) {
        return pStep.priority;
    }
}