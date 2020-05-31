package vjass;

import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nodes.vjass.Scope;
import nodes.vjass.Struct;
import org.junit.Assert;
import tree.TreeContext;

public class StructStepDefs {

    private Struct struct;

    @When("Struct is read")
    public void struct_is_read() {
        struct = new Struct(TestContext.inputScanner, new TreeContext());
    }

    @Then("Struct should be:")
    public void struct_should_be(String body) {
        Assert.assertEquals(body, struct.toString());
    }


}
