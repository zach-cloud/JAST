package vjass;

import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nodes.j.GlobalsSection;
import nodes.vjass.Library;
import org.junit.Assert;
import tree.TreeContext;

public class LibraryStepDefs {

    private Library library;

    @When("Library is read")
    public void library_is_read() {
        library = new Library(TestContext.inputScanner, new TreeContext());
    }

    @Then("Library should be:")
    public void library_should_be(String body) {
        Assert.assertEquals(body, library.toString());
    }


}
