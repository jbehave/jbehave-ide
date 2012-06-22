package org.jbehave.eclipse.util;

import org.jbehave.eclipse.PotentialStep;
import org.jbehave.eclipse.jface.JDTUtils;
import org.jbehave.util.HasHTMLComment;

public class WeightedCandidateStep implements Comparable<WeightedCandidateStep>, HasHTMLComment {
    public final PotentialStep potentialStep;
    public final float weight;
    public WeightedCandidateStep(PotentialStep potentialStep, float weight) {
        super();
        this.potentialStep = potentialStep;
        this.weight = weight;
    }
    @Override
    public int compareTo(WeightedCandidateStep o) {
        return (weight>o.weight)?1:-1;
    }
    
    private String htmlComment;
    @Override
    public String getHTMLComment() {
        if(htmlComment==null) {
            try {
                htmlComment = JDTUtils.getJavadocOf(potentialStep.method);
            } catch (Exception e) {
                htmlComment = "No documentation found";
            }
        }
        return htmlComment;
    }
}