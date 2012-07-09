package org.jbehave.eclipse.step;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.PartInitException;
import org.jbehave.eclipse.JBehaveProject;
import org.jbehave.eclipse.JBehaveProjectRegistry;
import org.jbehave.eclipse.parser.Constants;
import org.jbehave.eclipse.parser.StoryPart;
import org.jbehave.eclipse.text.EditorUtils;
import org.jbehave.eclipse.text.UIUtils;
import org.jbehave.eclipse.util.Ref;
import org.jbehave.eclipse.util.Strings;

public class StepJumper {
    
    private JBehaveProject jbehaveProject;
    public StepJumper(JBehaveProject jbehaveProject) {
        super();
        this.jbehaveProject = jbehaveProject;
    }

    public boolean jumpToSelectionDeclaration(final ITextViewer viewer) throws JavaModelException,
    PartInitException {
        
        Point point = viewer.getSelectedRange();
        
        final Ref<StoryPart> found = 
                new StoryPartDocumentUtils(jbehaveProject.getLocalizedStepSupport())
                        .findStoryPartAtOffset(viewer.getDocument(), point.x);
        if (found.isNull()) {
            return false;
        }
        
        final StoryPart part = found.get();
        if (!part.isStepPart())
            return false;
        final String step = part.extractStepSentenceAndRemoveTrailingNewlines();
        
        return jumpToDeclaration(viewer, step);
    }
    
    public boolean jumpToDeclaration(final ITextViewer viewer, final String step) throws JavaModelException,
            PartInitException {
        // configure search
        IProject project = EditorUtils.findProject(viewer);
        if (project == null) {
            UIUtils.show("Step not found", "No project found.");
            return false;
        }
        
        // step can contain comment, make sure there are removed:
        String cleanedStep = Constants.removeComment(step);
        // comment removed: there can be trailing new lines...
        cleanedStep = Strings.removeTrailingNewlines(cleanedStep);
        
        JBehaveProject jbehaveProject = JBehaveProjectRegistry.get().getOrCreateProject(project);
        IJavaElement methodToJump = jbehaveProject.getStepLocator().findMethod(cleanedStep);
        // jump to method
        if (methodToJump != null) {
            JavaUI.openInEditor(methodToJump);
            return true;
        } else {
            UIUtils.show("Step not found", "There is no step matching:\n" + step);
            return false;
        }
    }

    public boolean jumpToMethod(String qualifiedName) throws PartInitException, JavaModelException {
        IJavaElement methodToJump = jbehaveProject.getStepLocator().findMethodByQualifiedName(qualifiedName);
        // jump to method
        if (methodToJump != null) {
            JavaUI.openInEditor(methodToJump);
            return true;
        } else {
            return false;
        }
    }
}
