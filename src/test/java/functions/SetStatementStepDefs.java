package functions;

import nodes.functions.SetStatement;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class SetStatementStepDefs {

    private SetStatement setStatement;

    @When("Set Statement is read")
    public void set_Statement_is_read() {
        this.setStatement = new SetStatement(TestContext.inputScanner, new TreeContext());
    }

    @Then("Set Statement should be:")
    public void set_Statement_should_be(String body) {
        Assert.assertEquals(body, setStatement.toString());
    }

    @Then("Set Statement variable should be {string}")
    public void set_Statement_variable_should_be(String variable) {
        Assert.assertEquals(variable, setStatement.getVariable());
    }

    @Then("Set Statement value should be {string}")
    public void set_Statement_value_should_be(String value) {
        Assert.assertEquals(value, setStatement.getValue());
    }
}
