package functions;

import nodes.functions.Input;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class InputStepDefs {

    private Input input;

    @When("input is read")
    public void input_is_read() {
        this.input = new Input(TestContext.inputScanner, new TreeContext());
    }

    @Then("Input should be:")
    public void input_should_be(String body) {
        Assert.assertEquals(body, input.toString());
    }

    @Then("Input type should be {string}")
    public void input_type_should_be(String type) {
        Assert.assertEquals(type, input.getType());
    }

    @Then("Input name should be {string}")
    public void input_name_should_be(String name) {
        Assert.assertEquals(name, input.getName());
    }
}
