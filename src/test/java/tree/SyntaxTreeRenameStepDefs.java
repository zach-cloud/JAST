package tree;

import interfaces.ISyntaxTree;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SyntaxTreeRenameStepDefs {

    private ISyntaxTree tree;
    private ISyntaxTree renamedTree;

    @Given("Non-renamed script:")
    public void non_renamed_script(String body) {
        this.tree = SyntaxTree.readTree(body);
    }

    @When("Tree is renamed from {string} to {string}")
    public void tree_is_renamed_from_to(String oldVariableName, String newVariableName) {
        renamedTree = tree.renameVariable(oldVariableName, newVariableName);
    }

    @When("Tree is function renamed from {string} to {string}")
    public void tree_is_function_renamed_from_to(String oldFunctionName, String newFunctionName) {
        renamedTree = tree.renameFunction(oldFunctionName, newFunctionName);
    }

    @Then("Renamed script should be:")
    public void renamed_script_should_be(String body) {
        Assert.assertEquals(body.trim(), renamedTree.toString().trim());
    }

}
