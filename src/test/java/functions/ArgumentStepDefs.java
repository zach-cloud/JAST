package functions;

import nodes.functions.Argument;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class ArgumentStepDefs {

    private Argument argument;

    @When("Argument is read")
    public void argument_is_read() {
        this.argument = new Argument(TestContext.inputScanner, new TreeContext());
    }

    @Then("Argument should be:")
    public void argument_should_be(String body) {
        Assert.assertEquals(body, argument.toString());
    }
}
