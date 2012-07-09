package org.jbehave.eclipse.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.jbehave.eclipse.util.Strings.removeTrailingNewlines;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.jbehave.eclipse.util.Strings;
import org.junit.Test;

public class StringsTest {
    @Test
    public void tet_removeTrailingNewline () {
        assertEquals("a", removeTrailingNewlines("a"));
        assertEquals("a", removeTrailingNewlines("a\r\n"));
        assertEquals("a", removeTrailingNewlines("a\n"));
        assertEquals("a", removeTrailingNewlines("a\n\n"));
        assertEquals("a", removeTrailingNewlines("a\r"));
        assertEquals("a", removeTrailingNewlines("a\r\r"));
        assertEquals("a\nb", removeTrailingNewlines("a\nb\n"));
    }
    
    private static <T> void assertEquals(T expected, T actual) {
        assertThat(actual, equalTo(expected));
    }
    
    @Test
    public void getSubLineUntilOffset() throws IOException {
        String text = IOUtils.toString(getClass().getResourceAsStream("/data/tfdm_update-1.story"));
        String line = Strings.substringUntilOffset(text, 25);
        assertThat(line, equalTo("Given an inactive direct "));
    }
    
    @Test
    public void removeLeftSpaces() {
        assertThat(Strings.removeLeadingSpaces("   a  "), equalTo("a  "));
        assertThat(Strings.removeLeadingSpaces("   abc"), equalTo("abc"));
        assertThat(Strings.removeLeadingSpaces("   a c"), equalTo("a c"));
        assertThat(Strings.removeLeadingSpaces("      "), equalTo(""));
        assertThat(Strings.removeLeadingSpaces(""), equalTo(""));
        assertThat(Strings.removeLeadingSpaces(null), equalTo(null));
        assertThat(Strings.removeLeadingSpaces("  \n"), equalTo("\n"));
        
    }

}

