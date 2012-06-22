package org.jbehave.eclipse.editor.story.completion;

import org.eclipse.jface.text.templates.SimpleTemplateVariableResolver;
import org.eclipse.jface.text.templates.TemplateContext;
import org.jbehave.eclipse.LocalizedStepSupport;
import org.jbehave.support.JBKeyword;

public class LocalizedKeywordResolver extends SimpleTemplateVariableResolver {

    private final JBKeyword keyword;

    public LocalizedKeywordResolver(JBKeyword keyword) {
        super(keyword.name()/* type */, keyword.asString() /* descriptions */);
        this.keyword = keyword;
    }

    @Override
    protected String resolve(TemplateContext context) {
        if (!(context instanceof JBehaveTemplateContext)) {
            return "<???>";
        }
        JBehaveTemplateContext jbContext = (JBehaveTemplateContext) context;
        LocalizedStepSupport localizedStepSupport = jbContext.getProject().getLocalizedStepSupport();
        return keyword.asString(localizedStepSupport.getLocalizedKeywords());
    }
}
