package liqp.filters;

import liqp.Template;
import liqp.TemplateContext;
import org.antlr.v4.runtime.RecognitionException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DowncaseTest {

    @Test
    public void applyTest() throws RecognitionException {

        String[][] tests = {
                {"{{ '' | downcase }}", ""},
                {"{{ nil | downcase }}", ""},
                {"{{ 'Abc' | downcase }}", "abc"},
                {"{{ 'abc' | downcase }}", "abc"},
        };

        for (String[] test : tests) {

            Template template = Template.parse(test[0]);
            String rendered = String.valueOf(template.render());

            assertThat(rendered, is(test[1]));
        }
    }

    /*
	 * def test_downcase
     *   assert_equal 'testing', @filters.downcase("Testing")
     *   assert_equal '', @filters.downcase(nil)
     * end
	 */
    @Test
    public void applyOriginalTest() {

        final Filter filter = Filter.getFilter("downcase");
        TemplateContext templateContext = new TemplateContext();
        assertThat(filter.apply("Testing", templateContext), is((Object)"testing"));
        assertThat(filter.apply(null, templateContext), is((Object)""));
    }
}
