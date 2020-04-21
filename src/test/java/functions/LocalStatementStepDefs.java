package functions;

import nodes.functions.LocalStatement;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class LocalStatementStepDefs {

    private LocalStatement localStatement;

    @When("Local statement is read")
    public void local_statement_is_read() {
        this.localStatement = new LocalStatement(TestContext.inputScanner, new TreeContext());
    }

    @Then("Local statement should be:")
    public void local_statement_should_be(String body) {
        Assert.assertEquals(body, localStatement.toString());
    }

    @Then("Local statement type should be {string}")
    public void local_statement_type_should_be(String type) {
        Assert.assertEquals(type, localStatement.getType());
    }

    @Then("Local statement array should be {string}")
    public void local_statement_array_should_be(String array) {
        if(array.equals("true")) {
            Assert.assertTrue(localStatement.isArray());
        } else if(array.equals("false")) {
            Assert.assertFalse(localStatement.isArray());
        } else {
            Assert.fail("Not valid flag (array): " + array);
        }
    }

    @Then("Local statement initial value should be {string}")
    public void local_statement_initial_value_should_be(String value) {
        Assert.assertEquals(value, localStatement.getInitialValue().toString());
    }

    @Then("Local statement initial value should be null")
    public void local_statement_initial_value_should_be_null() {
        Assert.assertNull(localStatement.getInitialValue());
    }

    @Then("Local statement name should be {string}")
    public void local_statement_name_should_be(String name) {
        Assert.assertEquals(name, localStatement.getName());
    }
}
