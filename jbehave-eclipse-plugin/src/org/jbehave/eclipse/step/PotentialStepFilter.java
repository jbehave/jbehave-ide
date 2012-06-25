package org.jbehave.eclipse.step;


import fj.F;

final class PotentialStepFilter extends F<PotentialStep, Boolean> {
    private final int maxPrio;

    PotentialStepFilter(int maxPrio) {
        this.maxPrio = maxPrio;
    }

    @Override
    public Boolean f(PotentialStep pStep) {
        return maxPrio == pStep.priority;
    }
}