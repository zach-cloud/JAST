package functions;

import nodes.functions.CallStatement;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class CallStatementStepDefs {

    private CallStatement callStatement;

    @When("Call Statement is read")
    public void call_Statement_is_read() {
        callStatement = new CallStatement(TestContext.inputScanner, new TreeContext());
    }

    @Then("Call Statement should be:")
    public void call_Statement_should_be(String callStatementText) {
        Assert.assertEquals(callStatementText, callStatement.toString());
    }

}
