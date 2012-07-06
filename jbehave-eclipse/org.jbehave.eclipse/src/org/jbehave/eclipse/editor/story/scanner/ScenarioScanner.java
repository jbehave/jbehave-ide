package org.jbehave.eclipse.editor.story.scanner;

import org.eclipse.jface.text.rules.IToken;
import org.jbehave.eclipse.JBehaveProject;
import org.jbehave.eclipse.Keyword;
import org.jbehave.eclipse.jface.TextAttributeProvider;
import org.jbehave.eclipse.parser.StoryPart;
import org.jbehave.eclipse.textstyle.TextStyle;

public class ScenarioScanner extends AbstractStoryPartBasedScanner {
    
    private IToken keywordToken;

    public ScenarioScanner(JBehaveProject jbehaveProject, TextAttributeProvider textAttributeProvider) {
        super(jbehaveProject, textAttributeProvider);
        initialize();
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        setDefaultToken(newToken(TextStyle.SCENARIO_DEFAULT));
        keywordToken = newToken(TextStyle.SCENARIO_KEYWORD);
    }
    
    @Override
    protected boolean isPartAccepted(StoryPart part) {
        Keyword keyword = part.getPreferredKeyword();
        if(keyword==Keyword.Scenario || keyword.isComment()) {
            return true;
        }
        return false;
    }

    @Override
    protected void emitPart(StoryPart part) {
        String content = part.getContent();
        String kwString = getLocalizedStepSupport().lScenario(false);
        int offset = part.getOffset();
        
        if(content.startsWith(kwString)) {
            emit(keywordToken, offset, kwString.length());
            offset += kwString.length();
            emitCommentAware(getDefaultToken(), offset, content.substring(kwString.length()));
        }
        else {
            emitCommentAware(getDefaultToken(), offset, content);
        }
    }
}
