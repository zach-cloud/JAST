package functions;

import nodes.functions.FunctionCall;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class FunctionCallStepDefs {

    private FunctionCall functionCall;

    @When("Function Call is read")
    public void function_Call_is_read() {
        this.functionCall = new FunctionCall(TestContext.inputScanner, new TreeContext());
    }

    @Then("Function Call should be:")
    public void function_Call_should_be(String body) {
        Assert.assertEquals(body, functionCall.toString());
    }

}
