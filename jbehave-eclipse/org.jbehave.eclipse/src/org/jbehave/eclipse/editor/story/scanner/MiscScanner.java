package org.jbehave.eclipse.editor.story.scanner;

import static org.jbehave.eclipse.Keyword.GivenStories;
import static org.jbehave.eclipse.Keyword.Meta;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.text.rules.IToken;
import org.jbehave.eclipse.JBehaveProject;
import org.jbehave.eclipse.Keyword;
import org.jbehave.eclipse.editor.story.StoryPartition;
import org.jbehave.eclipse.editor.text.TextAttributeProvider;
import org.jbehave.eclipse.editor.text.style.TextStyle;
import org.jbehave.eclipse.parser.Constants;
import org.jbehave.eclipse.parser.StoryPart;
import org.jbehave.eclipse.parser.Constants.TokenizerCallback;

public class MiscScanner extends AbstractStoryScanner {
    
    private IToken keywordToken;
    private IToken metaPropertyToken;

    public MiscScanner(JBehaveProject jbehaveProject, TextAttributeProvider textAttributeProvider) {
        super(jbehaveProject, textAttributeProvider);
        initialize();
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        setDefaultToken(newToken(TextStyle.META_DEFAULT));
        keywordToken = newToken(TextStyle.META_KEYWORD);
        metaPropertyToken = newToken(TextStyle.META_KEYWORD);
    }
    
    @Override
    protected boolean isPartAccepted(StoryPart part) {
        Keyword keyword = part.getPreferredKeyword();
        if(StoryPartition.Misc==StoryPartition.partitionOf(keyword)) {
            return true;
        }
        return false;
    }
    
    private boolean handle(StoryPart part, Keyword kw, IToken token, Chain chain) {
        String content = part.getContent();
        String kwString = kw.asString(getLocalizedStepSupport().getLocalizedKeywords());
        if(content.startsWith(kwString)) {
            int length = kwString.length();
            int offset = part.getOffset();
            emit(token, offset, length);
            offset += length;
            
            chain.next(offset, content.substring(length));
            return true;
        }
        return false;
    }

    @Override
    protected void emitPart(StoryPart part) {
        Chain commentAwareChain = commentAwareChain(getDefaultToken());
        if(handle(part, GivenStories, keywordToken, commentAwareChain)
                || handle(part, Meta, keywordToken, metaChain())) {
            // nothing more to do?
        }
        else {
            emitCommentAware(getDefaultToken(), part.getOffset(), part.getContent());
        }
    }

    private Chain metaChain() {
        return new Chain() {
            @Override
            public void next(int offset, String content) {
                parseMetaProperties(offset, content);
            }
        };
    }
    
    private static Pattern metaProperty = Pattern.compile("\\s*@\\s*[^\\s]+");

    protected void parseMetaProperties(final int offset, String content) {
        Constants.splitLine(content, new TokenizerCallback() {
            @Override
            public void token(int startOffset, int endOffset, String line, boolean isDelimiter) {
                if(isDelimiter) {
                    emit(getDefaultToken(), offset + startOffset, line.length());
                    return;
                }
                Matcher matcher = metaProperty.matcher(line);
                if(matcher.find()) {
                    emit(metaPropertyToken, offset + startOffset, matcher.end());
                    emit(getDefaultToken(), offset + startOffset + matcher.end(), line.length()-matcher.end());
                }
                else
                    emit(getDefaultToken(), offset + startOffset, line.length());
            }
        });
    }

}
