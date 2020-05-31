package vjass;

import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nodes.vjass.Scope;
import org.junit.Assert;
import tree.TreeContext;

public class ScopeStepDefs {

    private Scope scope;

    @When("Scope is read")
    public void scope_is_read() {
        scope = new Scope(TestContext.inputScanner, new TreeContext());
    }

    @Then("Scope should be:")
    public void scope_should_be(String body) {
        Assert.assertEquals(body, scope.toString());
    }


}
