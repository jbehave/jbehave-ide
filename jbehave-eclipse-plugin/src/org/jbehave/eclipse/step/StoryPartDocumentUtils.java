package org.jbehave.eclipse.step;

import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.jbehave.eclipse.editor.story.StoryDocument;
import org.jbehave.eclipse.parser.StoryParser;
import org.jbehave.eclipse.parser.StoryPart;
import org.jbehave.eclipse.parser.StoryPartCollector;
import org.jbehave.eclipse.parser.StoryPartVisitor;
import org.jbehave.eclipse.util.Ref;

public class StoryPartDocumentUtils {
    
    private LocalizedStepSupport localizedStepSupport;
    public StoryPartDocumentUtils(LocalizedStepSupport localizedStepSupport) {
        super();
        this.localizedStepSupport = localizedStepSupport;
    }

    public List<StoryPart> getStoryParts(IDocument document) {
        StoryPartCollector collector = new StoryPartCollector();
        traverseStoryParts(document, collector);
        return collector.getParts();
    }

    public void traverseStoryParts(IDocument document, StoryPartVisitor visitor) {
        if(document instanceof StoryDocument) {
            ((StoryDocument)document).traverseStoryParts(visitor);
        }
        else {
            new StoryParser(localizedStepSupport).parse(document.get(), visitor);
        }
    }

    public Ref<StoryPart> findStoryPartAtOffset(IDocument document, int offset) {
        if(offset>0)
            offset--; // one search from the character just behind the caret not after
        return findStoryPartAtRegion(document, new Region(offset, 1));
    }
    
    public Ref<StoryPart> findStoryPartAtRegion(IDocument document, final IRegion region) {
        final Ref<StoryPart> ref = Ref.create();
        StoryPartVisitor visitor = new StoryPartVisitor() {
            @Override
            public void visit(StoryPart part) {
                if(part.intersects(region.getOffset(), region.getLength())) {
                    ref.set(part);
                    done();
                }
            }
        };
        traverseStoryParts(document, visitor);
        return ref;
    }

}
