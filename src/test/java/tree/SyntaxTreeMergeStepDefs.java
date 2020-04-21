package tree;

import interfaces.ISyntaxTree;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.SyntaxTree;

public class SyntaxTreeMergeStepDefs {

    private ISyntaxTree tree1;
    private ISyntaxTree tree2;
    private ISyntaxTree result;

    @Given("script 1:")
    public void script1(String body) {
        this.tree1 = SyntaxTree.readTree(body);
    }

    @Given("script 2:")
    public void script2(String body) {
        this.tree2 = SyntaxTree.readTree(body);
    }

    @When("scripts are merged")
    public void scripts_are_merged() {
        result = tree1.merge(tree2);
    }

    @Then("merged script should be:")
    public void merged_script_should_be(String body) {
        Assert.assertEquals(body, result.toString());
    }
}
