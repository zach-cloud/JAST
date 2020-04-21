package j;

import nodes.j.GlobalsSection;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class GlobalsSectionStepDefs {

    private GlobalsSection globalsSection;

    @When("Globals section is read")
    public void globals_section_is_read() {
        globalsSection = new GlobalsSection(TestContext.inputScanner, new TreeContext());
    }

    @Then("Globals section should be:")
    public void globals_section_should_be(String toString) {
        Assert.assertEquals(toString, globalsSection.toString());
    }

    @Then("Globals section should have {int} variables")
    public void globals_section_should_have_variables(int count) {
        Assert.assertEquals(count, globalsSection.getGlobalVariables().size());
    }
}
