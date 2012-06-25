package org.jbehave.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.jbehave.eclipse.Keyword.Given;
import static org.jbehave.eclipse.Keyword.InOrderTo;
import static org.jbehave.eclipse.Keyword.Narrative;

import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.eclipse.Keyword;
import org.junit.Before;
import org.junit.Test;

public class CharTreeTest {
    
    private CharTree<Keyword> cn;

    @Before
    public void setUp () {
        LocalizedKeywords keywords = new LocalizedKeywords();
        cn = new CharTree<Keyword>('/', null);
        for(Keyword kw : Keyword.values())
            cn.push(kw.asString(keywords), kw);
        
    }
        
    @Test
    public void lookup() {
        assertEquals(Given, cn.lookup("Given"));
        assertEquals(Narrative, cn.lookup("Narrative:"));
        assertEquals(Given, cn.lookup("Given a user named \"Bob\""));
        assertEquals(InOrderTo, cn.lookup("In order to be more communicative"));
    }
    
    private void assertEquals(Keyword expected, Keyword actual) {
        assertThat(actual, equalTo(expected));
    }


    @Test
    public void lookup_missing() {
        assertEquals(null, cn.lookup("Gaven"));
        assertEquals(null, cn.lookup("\n"));
    }

}
