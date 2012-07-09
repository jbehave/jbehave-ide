package org.jbehave.eclipse.editor.text;

import org.junit.Test;

public class HtmlStateMachineTest {

    @Test
    public void canParseContent() throws Exception {
        String content = "<b>Ambiguous steps</b>" +
                "<ul>" +
                    "<li>when an agent $clicks on the button<br/>(<code>Agent#clicks</code>)</li>" +
                    "<li>when an agent $enters (<code>Agent#enters</code>)</li>" +
                    "</ul>";
        new HtmlStateMachine().parse("<root>"+content+"</root>");
    }
}
