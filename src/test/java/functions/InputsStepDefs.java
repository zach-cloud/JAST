package functions;

import nodes.functions.Inputs;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class InputsStepDefs {

    private Inputs inputs;

    @When("inputs are read")
    public void inputs_are_read() {
        inputs = new Inputs(TestContext.inputScanner, new TreeContext());
    }

    @Then("Inputs should be:")
    public void inputs_should_be(String body) {
        Assert.assertEquals(body, inputs.toString());
    }

    @Then("Inputs should contain {int} input nodes")
    public void inputs_should_contain_input_nodes(int count) {
        Assert.assertEquals(count, inputs.getInputs().size());
    }
}
