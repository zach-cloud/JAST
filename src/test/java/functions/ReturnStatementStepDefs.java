package functions;

import nodes.functions.ReturnStatement;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class ReturnStatementStepDefs {

    private ReturnStatement returnStatement;

    @When("Return statement is read")
    public void return_statement_is_read() {
        this.returnStatement = new ReturnStatement(TestContext.inputScanner, new TreeContext());
    }

    @Then("Return statement should be:")
    public void return_statement_should_be(String body) {
        Assert.assertEquals(body, returnStatement.toString());
    }

    @Then("Return statement body should be: {string}")
    public void return_statement_body_should_be(String body) {
        Assert.assertEquals(body, returnStatement.getReturnBody());
    }

}
