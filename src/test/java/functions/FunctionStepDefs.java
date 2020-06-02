package functions;

import nodes.functions.Function;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class FunctionStepDefs {

    private Function function;

    @When("Function is read")
    public void function_is_read() {
        this.function = new Function(TestContext.inputScanner, new TreeContext());
    }

    @Then("Function should be:")
    public void function_should_be(String body) {
        Assert.assertEquals(body, function.toString());
    }

    @Then("Function header should be {string}")
    public void function_header_should_be(String header) {
        Assert.assertEquals(header, function.getFunctionDeclaration().toString());
    }

    @Then("Function should have {int} call statements")
    public void function_should_have_call_statements(int callStatements) {
        Assert.assertEquals(callStatements, function.getStatements().getCallStatements().size());
    }

    @Then("Function should have {int} loops")
    public void function_should_have_loops(int loops) {
        Assert.assertEquals(loops, function.getStatements().getLoopStatements().size());
    }

    @Then("Function should have {int} if statements")
    public void function_should_have_if_statements(int ifs) {
        Assert.assertEquals(ifs, function.getStatements().getIfStatements().size());
    }

    @Then("Function should have {int} variable statements")
    public void function_should_have_variable_statements(int sets) {
        Assert.assertEquals(sets, function.getStatements().getSetStatements().size());
    }

    @Then("Function should have {int} locals")
    public void function_should_have_locals(int locals) {
        Assert.assertEquals(locals, function.getStatements().getLocalStatements().size());
    }

}
