package org.jbehave.parser;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jbehave.eclipse.Keyword;
import org.jbehave.eclipse.LocalizedStepSupport;
import org.jbehave.eclipse.util.LineParser;
import org.jbehave.util.CharTree;

public class StoryPart {
    private final LocalizedStepSupport localizedStepSupport;
    private final int offset;
    private final String content;
    private Keyword preferredKeyword;
    
    public StoryPart(LocalizedStepSupport localizedStepSupport, int offset, String content) {
        super();
        this.localizedStepSupport = localizedStepSupport;
        this.offset = offset;
        this.content = content;
    }
    
    public int getOffset() {
        return offset;
    }
    
    public int getLength() {
        return content.length();
    }
    
    public String getContentWithoutComment() {
        return Constants.removeComment(content);
    }
    
    public String getContent() {
        return content;
    }

    /**
     * @return
     */
    public String extractStepSentence() {
        return LineParser.extractStepSentence(localizedStepSupport, getContent());
    }
    
    /**
     * @see #isStepPart()
     */
    public String extractStepSentenceAndRemoveTrailingNewlines() {
        return LineParser.extractStepSentenceAndRemoveTrailingNewlines(localizedStepSupport, getContent());
    }
    
    /**
     * @return
     * @see #extractKeyword(CharTree)
     */
    public Keyword getPreferredKeyword() {
        return getPreferredKeyword(defaultTree());
    }
    
    public void setPreferredKeyword(Keyword keyword) {
        this.preferredKeyword = keyword;
    }
    
    public Keyword getPreferredKeyword(CharTree<Keyword> kwTree) {
        if(preferredKeyword==null)
            preferredKeyword = extractKeyword(kwTree);
        return preferredKeyword;
    }
    
    public Keyword extractKeyword() {
        return extractKeyword(defaultTree());
    }
    
    public Keyword extractKeyword(CharTree<Keyword> kwTree) {
        return kwTree.lookup(getContent());
    }
    
    public boolean startsWithKeyword () {
        return startsWithKeyword(defaultTree());
    }

    public boolean startsWithKeyword (CharTree<Keyword> kwTree) {
        return (getPreferredKeyword(kwTree)!=null);
    }
    
    private CharTree<Keyword> defaultTree() {
        return localizedStepSupport.sharedKeywordCharTree();
    }

    /**
     * Same as {@link #getOffset()}
     * @see #getOffset()
     */
    public int getOffsetStart() {
        return getOffset();
    }
    
    public int getOffsetEnd() {
        return getOffset()+getLength();
    }

    public boolean intersects(int offset, int length) {
        int tmin = getOffset();
        int tmax = getOffsetEnd();
        int omin = offset;
        int omax = offset+length;
        return omin<=tmax && tmin<=omax;
    }

    public boolean isStepPart() {
        Keyword keyword = getPreferredKeyword();
        return keyword!=null && keyword.isStep();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.SIMPLE_STYLE).append("locale", localizedStepSupport.getLocale()).append("content", content.replace("\n", "\\n")).toString();
    }
}
