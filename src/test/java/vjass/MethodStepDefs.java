package vjass;

import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nodes.vjass.Method;
import org.junit.Assert;
import tree.TreeContext;

public class MethodStepDefs {

    private Method method;

    @When("Method is read")
    public void method_is_read() {
        method = new Method(TestContext.inputScanner, new TreeContext());
    }

    @Then("Method should be:")
    public void method_should_be(String body) {
        Assert.assertEquals(body, method.toString());
    }


}
