package wts;

import nodes.wts.WtsString;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class WtsStringStepDefs {

    private WtsString wtsString;

    @When("WTS String is read")
    public void wts_String_is_read() {
        wtsString = new WtsString(TestContext.inputScanner, new TreeContext());
    }

    @Then("WTS String key should be {string}")
    public void wts_String_key_should_be(String key) {
        Assert.assertEquals(key, wtsString.getKey());
    }

    @Then("WTS String value should be {string}")
    public void wts_String_value_should_be(String value) {
        value = value.replace("\\n", "\n");
        Assert.assertEquals(value, wtsString.getValue());
    }

    @Then("WTS String comment should be {string}")
    public void wts_String_comment_should_be(String comment) {
        Assert.assertEquals(comment, wtsString.getComment());
    }

    @Then("WTS String should be:")
    public void wts_String_should_be(String toString) {
        Assert.assertEquals(toString, wtsString.toString());
    }

}
