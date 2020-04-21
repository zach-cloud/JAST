package functions;

import nodes.functions.ExitWhenStatement;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class ExitWhenStatementStepDefs {

    private ExitWhenStatement exitWhenStatement;

    @When("Exitwhen statement is read")
    public void exitwhen_statement_is_read() {
        exitWhenStatement = new ExitWhenStatement(TestContext.inputScanner, new TreeContext());
    }

    @Then("Exitwhen statement should be:")
    public void exitwhen_statement_should_be(String body) {
        Assert.assertEquals(body, exitWhenStatement.toString());
    }

    @Then("Exitwhen condition should be {string}")
    public void exitwhen_condition_should_be(String condition) {
        Assert.assertEquals(condition, exitWhenStatement.getExitwhenCondition().toString());
    }
}
