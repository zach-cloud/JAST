package functions;

import nodes.functions.LoopStatement;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class LoopStatementStepDefs {

    private LoopStatement loopStatement;

    @When("Loop statement is read")
    public void loop_statement_is_read() {
        this.loopStatement = new LoopStatement(TestContext.inputScanner, new TreeContext());
    }

    @Then("Loop Statement should be:")
    public void loop_Statement_should_be(String body) {
        Assert.assertEquals(body, loopStatement.toString());
    }

    @Then("Loop exit condition block should be {string}")
    public void loop_exit_condition_block_should_be(String exitCondition) {
        Assert.assertEquals(exitCondition, loopStatement.getExitCondition().toString());
    }

    @Then("Loop should have {int} statements")
    public void loop_should_have_statements(int count) {
        Assert.assertEquals(count, loopStatement.getStatements().getStatements().size());
    }
}
