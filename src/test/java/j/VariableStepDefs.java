package j;

import nodes.j.Variable;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class VariableStepDefs {

    private Variable variable;

    @When("Variable is read")
    public void variable_is_read() {
        this.variable = new Variable(TestContext.inputScanner, new TreeContext());
    }

    @Then("Variable type should be {string}")
    public void variable_type_should_be(String type) {
        Assert.assertEquals(type, variable.getType());
    }

    @Then("Variable constant should be {string}")
    public void variable_constant_should_be(String constant) {
        if(constant.equalsIgnoreCase("true")) {
            Assert.assertTrue(variable.isConstant());
        } else if(constant.equalsIgnoreCase("false")) {
            Assert.assertFalse(variable.isConstant());
        } else {
            Assert.fail("Invalid flag: " + constant);
        }
    }

    @Then("Variable array should be {string}")
    public void variable_array_should_be(String array) {
        if(array.equalsIgnoreCase("true")) {
            Assert.assertTrue(variable.isArray());
        } else if(array.equalsIgnoreCase("false")) {
            Assert.assertFalse(variable.isArray());
        } else {
            Assert.fail("Invalid flag: " + array);
        }
    }

    @Then("Variable value should be null")
    public void variable_value_should_be_null() {
        Assert.assertNull(variable.getInitialValue());
    }

    @Then("Variable name should be {string}")
    public void variable_name_should_be(String name) {
        Assert.assertEquals(name, variable.getName());
    }

    @Then("Variable value should be {string}")
    public void variable_value_should_be(String value) {
        Assert.assertEquals(value.replace("''", "\""), variable.getInitialValue().toString());
    }

    @Then("Variable should be {string}")
    public void variable_should_be(String toString) {
        Assert.assertEquals(toString.replace("''", "\""), variable.toString());
    }
}
