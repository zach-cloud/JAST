package functions;

import nodes.functions.IfStatement;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class IfStatementStepDefs {

    private IfStatement ifStatement;

    @When("If Statement is read")
    public void if_Statement_is_read() {
        ifStatement = new IfStatement(TestContext.inputScanner, new TreeContext());
    }

    @Then("If Statements should be:")
    public void if_Statements_should_be(String body) {
        Assert.assertEquals(body, ifStatement.toString());
    }

    @Then("If Statement should have condition block {string}")
    public void if_Statement_should_have_condition_block(String conditionBlock) {
        Assert.assertEquals(conditionBlock, ifStatement.getCondition().toString());
    }

    @Then("If Statement then block should have {int} statements")
    public void if_Statement_then_block_should_have_statements(int count) {
        Assert.assertEquals(count, ifStatement.getThenStatements().getStatements().size());
    }

    @Then("If Statement else block should have {int} statements")
    public void if_Statement_else_block_should_have_statements(int count) {
        Assert.assertEquals(count, ifStatement.getElseStatements().getStatements().size());
    }

    @Then("If Statement should have {int} elseif statements")
    public void if_Statement_should_have_elseif_statements(int count) {
        Assert.assertEquals(count, ifStatement.getElseifConditions().size());
    }

    @Then("If Statement should have {int} elseif blocks")
    public void if_Statement_should_have_elseif_blocks(int count) {
        Assert.assertEquals(count, ifStatement.getElseifStatements().size());
    }
}
