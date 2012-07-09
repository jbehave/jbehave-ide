package org.jbehave.eclipse.editor.story.scanner;

import org.eclipse.jface.text.rules.IToken;
import org.jbehave.eclipse.JBehaveProject;
import org.jbehave.eclipse.Keyword;
import org.jbehave.eclipse.editor.story.StoryPartition;
import org.jbehave.eclipse.parser.Constants;
import org.jbehave.eclipse.parser.ContentWithIgnorableEmitter;
import org.jbehave.eclipse.parser.StoryPart;
import org.jbehave.eclipse.text.TextAttributeProvider;
import org.jbehave.eclipse.text.style.TextStyle;

public class ExampleTableScanner extends AbstractStoryScanner {
    
    private IToken keywordToken;

    public ExampleTableScanner(JBehaveProject jbehaveProject, TextAttributeProvider textAttributeProvider) {
        super(jbehaveProject, textAttributeProvider);
        initialize();
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        setDefaultToken(newToken(TextStyle.EXAMPLE_TABLE_DEFAULT));
        keywordToken = newToken(TextStyle.EXAMPLE_TABLE_KEYWORD);
        exampleTableCellToken = newToken(TextStyle.EXAMPLE_TABLE_CELL);
        exampleTableSepToken  = newToken(TextStyle.EXAMPLE_TABLE_SEPARATOR);
    }
    
    @Override
    protected boolean isPartAccepted(StoryPart part) {
        Keyword keyword = part.getPreferredKeyword();
        if(StoryPartition.ExampleTable==StoryPartition.partitionOf(keyword)) {
            return true;
        }
        return false;
    }

    @Override
    protected void emitPart(StoryPart part) {
        String content = part.getContent();
        String kwString = getLocalizedStepSupport().examplesTable(false);
        int offset = part.getOffset();
        
        if(content.startsWith(kwString)) {
            emit(keywordToken, offset, kwString.length());
            offset += kwString.length();
            
            String rawAfterKeyword = content.substring(kwString.length());
            ContentWithIgnorableEmitter emitter = new ContentWithIgnorableEmitter(Constants.commentLineMatcher, rawAfterKeyword);
            String cleanedAfterKeyword = emitter.contentWithoutIgnorables();
            emitTable(emitter, getDefaultToken(), offset, cleanedAfterKeyword);
        }
        else {
            ContentWithIgnorableEmitter emitter = new ContentWithIgnorableEmitter(Constants.commentLineMatcher, content);
            String cleanedContent = emitter.contentWithoutIgnorables();
            emit(emitter, getDefaultToken(), offset, cleanedContent.length());
        }
    }


}
