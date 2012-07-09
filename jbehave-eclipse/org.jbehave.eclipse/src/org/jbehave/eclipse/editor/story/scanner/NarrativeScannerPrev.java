package org.jbehave.eclipse.editor.story.scanner;

import static org.jbehave.eclipse.editor.story.scanner.rule.Rules.lineStartsWithRule;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.jbehave.eclipse.JBehaveProject;
import org.jbehave.eclipse.editor.step.LocalizedStepSupport;
import org.jbehave.eclipse.editor.text.TextAttributeProvider;
import org.jbehave.eclipse.editor.text.style.TextStyle;

public class NarrativeScannerPrev extends RuleBasedScanner {

    private final LocalizedStepSupport localizedStepSupport;
    private final TextAttributeProvider textAttributeProvider;

    public NarrativeScannerPrev(JBehaveProject jbehaveProject, TextAttributeProvider textAttributeProvider) {
        this.localizedStepSupport = jbehaveProject.getLocalizedStepSupport();
        this.textAttributeProvider = textAttributeProvider;
        initialize();
        textAttributeProvider.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                initialize();
            }
        });
    }
    
    private void initialize() {
        TextAttribute textAttribute = textAttributeProvider.get(TextStyle.NARRATIVE_DEFAULT);
        setDefaultReturnToken(new Token(textAttribute));
        
        textAttribute = textAttributeProvider.get(TextStyle.NARRATIVE_KEYWORD);
        IToken keyword = new Token(textAttribute);
        
        IRule[] rules = new IRule[] {
                lineStartsWithRule(localizedStepSupport.narrative(false), " ", keyword), //
                lineStartsWithRule(localizedStepSupport.inOrderTo(false), " ", keyword), //
                lineStartsWithRule(localizedStepSupport.asA(false), " ", keyword), //
                lineStartsWithRule(localizedStepSupport.iWantTo(false), " ", keyword) //
        };
        setRules(rules);
    }
}
