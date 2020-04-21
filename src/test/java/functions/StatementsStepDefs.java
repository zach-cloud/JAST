package functions;

import nodes.functions.Statements;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class StatementsStepDefs {

    private Statements statements;

    @When("Statements are read")
    public void statements_are_read() {
        this.statements = new Statements(TestContext.inputScanner, new TreeContext());
    }

    @Then("Statements should be:")
    public void statements_should_be(String body) {
        Assert.assertEquals(body, statements.toString());
    }

    @Then("Statements should have {int} statements")
    public void statements_should_have_statements(int count) {
        Assert.assertEquals(count, statements.getStatements().size());
    }
}
