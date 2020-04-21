package j;

import nodes.j.FunctionsSection;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class FunctionsSectionStepDefs {

    private FunctionsSection functionsSection;

    @When("Functions Section is read")
    public void functions_Section_is_read() {
        this.functionsSection = new FunctionsSection(TestContext.inputScanner, new TreeContext());
    }

    @Then("Functions Section should contain {int} functions")
    public void functions_Section_should_contain_functions(int count) {
        Assert.assertEquals(count, functionsSection.getFunctions().size());
    }
}
