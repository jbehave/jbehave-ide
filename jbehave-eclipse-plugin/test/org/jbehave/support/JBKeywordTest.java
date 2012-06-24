package org.jbehave.support;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.jbehave.support.JBKeyword.And;
import static org.jbehave.support.JBKeyword.AsA;
import static org.jbehave.support.JBKeyword.ExamplesTable;
import static org.jbehave.support.JBKeyword.ExamplesTableHeaderSeparator;
import static org.jbehave.support.JBKeyword.ExamplesTableIgnorableSeparator;
import static org.jbehave.support.JBKeyword.ExamplesTableRow;
import static org.jbehave.support.JBKeyword.ExamplesTableValueSeparator;
import static org.jbehave.support.JBKeyword.Given;
import static org.jbehave.support.JBKeyword.GivenStories;
import static org.jbehave.support.JBKeyword.IWantTo;
import static org.jbehave.support.JBKeyword.Ignorable;
import static org.jbehave.support.JBKeyword.InOrderTo;
import static org.jbehave.support.JBKeyword.Meta;
import static org.jbehave.support.JBKeyword.MetaProperty;
import static org.jbehave.support.JBKeyword.Narrative;
import static org.jbehave.support.JBKeyword.Scenario;
import static org.jbehave.support.JBKeyword.Then;
import static org.jbehave.support.JBKeyword.When;

import java.util.Locale;

import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.junit.Test;

public class JBKeywordTest {

    @Test
    public void asString() {
        Keywords keywords = new LocalizedKeywords(Locale.US);
        assertThat(Meta.asString(keywords), equalTo("Meta:"));
        assertThat(MetaProperty.asString(keywords), equalTo("@"));
        assertThat(Narrative.asString(keywords), equalTo("Narrative:"));
        assertThat(InOrderTo.asString(keywords), equalTo("In order to"));
        assertThat(AsA.asString(keywords), equalTo("As a"));
        assertThat(IWantTo.asString(keywords), equalTo("I want to"));
        assertThat(Scenario.asString(keywords), equalTo("Scenario:"));
        assertThat(GivenStories.asString(keywords), equalTo("GivenStories:"));
        assertThat(ExamplesTable.asString(keywords), equalTo("Examples:"));
        assertThat(ExamplesTableRow.asString(keywords), equalTo("Example:"));
        assertThat(ExamplesTableHeaderSeparator.asString(keywords), equalTo("|"));
        assertThat(ExamplesTableValueSeparator.asString(keywords), equalTo("|"));
        assertThat(ExamplesTableIgnorableSeparator.asString(keywords), equalTo("|--"));
        assertThat(Given.asString(keywords), equalTo("Given"));
        assertThat(When.asString(keywords), equalTo("When"));
        assertThat(Then.asString(keywords), equalTo("Then"));
        assertThat(And.asString(keywords), equalTo("And"));
        assertThat(Ignorable.asString(keywords), equalTo("!--"));
    }
}
