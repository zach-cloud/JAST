package functions;

import nodes.functions.FunctionDeclaration;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class FunctionDeclarationStepDefs {

    private FunctionDeclaration functionDeclaration;

    @When("Function Declaration is read")
    public void function_Declaration_is_read() {
        this.functionDeclaration = new FunctionDeclaration(TestContext.inputScanner, new TreeContext());
    }

    @Then("Function Declaration should be:")
    public void function_Declaration_should_be(String body) {
        Assert.assertEquals(body, functionDeclaration.toString());
    }

    @Then("Function Declaration should have name {string}")
    public void function_Declaration_should_have_name(String name) {
        Assert.assertEquals(name, functionDeclaration.getName());
    }

    @Then("Function Declaration should have {int} inputs")
    public void function_Declaration_should_have_inputs(int count) {
        Assert.assertEquals(count, functionDeclaration.getInputs().getInputs().size());
    }

    @Then("Function Declaration output should have type {string}")
    public void function_Declaration_output_should_have_type_and_name(String outputType) {
        Assert.assertEquals(outputType, functionDeclaration.getOutput().getType());
    }
}
