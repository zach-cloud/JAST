package services;

import interfaces.IGuiOptimizerService;
import interfaces.ISyntaxTree;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.SyntaxTree;

public class GuiOptimizerServiceStepDefs {

    private IGuiOptimizerService optimizerService;
    private ISyntaxTree tree;
    private ISyntaxTree convertedTree;

    @Given("GUI tree data:")
    public void gui_tree_data(String body) {
        this.optimizerService = new GuiOptimizerService();
        this.tree = SyntaxTree.readTree(body);
    }

    @When("GUI code is optimized")
    public void gui_code_is_optimized() {
        convertedTree = optimizerService.optimize(tree);
    }

    @Then("Converted tree data should be:")
    public void converted_tree_data_should_be(String body) {
        Assert.assertEquals(body, convertedTree.toString());
    }
}
