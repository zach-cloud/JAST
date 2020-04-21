package wts;

import nodes.wts.WtsStringsFile;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class WtsFileStepDefs {

    private WtsStringsFile wtsFile;

    @When("WTS File is read")
    public void wts_File_is_read() {
        wtsFile = new WtsStringsFile(TestContext.inputScanner, new TreeContext());
    }

    @Then("WTS File should contain {int} strings")
    public void wts_File_should_contain_strings(int count) {
        Assert.assertEquals(count, wtsFile.getStrings().size());
    }

}
