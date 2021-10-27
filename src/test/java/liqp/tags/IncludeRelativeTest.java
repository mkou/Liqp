package liqp.tags;

import liqp.ParseSettings;
import liqp.Template;
import liqp.exceptions.VariableNotExistException;
import liqp.parser.Flavor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IncludeRelativeTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testFilterNotAvailableForLiquid() {
        thrown.expectMessage("The tag 'include_relative' is not registered.");
        thrown.expect(isA(RuntimeException.class));
        Template template = Template.parse("{% include_relative include_file.txt %}", liquid());
        template.render();
    }

    
    @Test
    public void testFilterAvailableForJekyll() {
        // todo: if running from variable in ruby script what is taken as a base?
        // 1) working dir?
        // 2) script folder location?
        // 3) java case: .class vs .jar
        Template template = Template.parse("{% include_relative snippets/header.html %}", jekyll());
        String render = template.render();
        assertEquals("HEADER\n", render);
    }

    public ParseSettings jekyll() {
        return new ParseSettings.Builder().withFlavor(Flavor.JEKYLL).build();
    }

    public ParseSettings liquid() {
        return new ParseSettings.Builder().withFlavor(Flavor.LIQUID).build();
    }

}
