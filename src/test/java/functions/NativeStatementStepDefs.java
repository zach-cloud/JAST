package functions;

import nodes.functions.NativeFunction;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class NativeStatementStepDefs {

    private NativeFunction nativeFunction;

    @When("Native Statement is read")
    public void native_Statement_is_read() {
        this.nativeFunction = new NativeFunction(TestContext.inputScanner, new TreeContext());
    }

    @Then("Native Statement should be:")
    public void native_Statement_should_be(String body) {
        Assert.assertEquals(body, nativeFunction.toString());
    }
}
