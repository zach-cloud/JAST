package functions;

import nodes.functions.Output;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class OutputStepDefs {

    private Output output;

    @When("Output is read")
    public void output_is_read() {
        this.output = new Output(TestContext.inputScanner, new TreeContext());
    }

    @Then("Output should be:")
    public void output_should_be(String body) {
        Assert.assertEquals(body, output.toString());
    }

    @Then("Output type should be {string}")
    public void output_type_should_be(String type) {
        Assert.assertEquals(type, output.getType());
    }

}
