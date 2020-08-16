package helper;

import interfaces.ISyntaxTree;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import template.TemplateLoader;
import tree.SyntaxTree;

public class TemplateLoaderStepDefs {

    private ISyntaxTree loadedTree;

    @When("{string} is loaded")
    public void is_loaded(String cheatpackName) {
        if(TemplateLoader.canLoadTemplateByName(cheatpackName)) {
            try {
                loadedTree = SyntaxTree.readTree(TemplateLoader.loadTemplateByName(cheatpackName));
            } catch (Exception ex) {
                loadedTree = null;
                Assert.fail("Attempted and failed to load: " + cheatpackName);
            }
        } else {
            loadedTree = null;
        }
    }

    @Then("Cheatpack is loaded successfully")
    public void cheatpack_is_loaded_successfully() {
        Assert.assertNotNull(loadedTree);
    }

    @Then("Cheatpack is not loaded successfully")
    public void cheatpack_is_not_loaded_successfully() {
        Assert.assertNull(loadedTree);
    }

}
