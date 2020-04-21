package services;

import interfaces.IReturnInlinerService;
import interfaces.ISyntaxTree;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.SyntaxTree;

public class ReturnInlinerServiceStepDefs {

    private IReturnInlinerService inlinerService;
    private ISyntaxTree originalTree;
    private ISyntaxTree inlinedTree;

    @Given("Inliner data:")
    public void inliner_data(String body) {
        this.originalTree = SyntaxTree.readTree(body);
        this.inlinerService = new ReturnInlinerService();
    }

    @When("All functions are inlined")
    public void all_functions_are_inlined() {
        inlinedTree = inlinerService.inlineAll(originalTree);
    }

    @Then("The inlined code should be:")
    public void the_inlined_code_should_be(String body) {
        Assert.assertEquals(body, inlinedTree.toString());
    }
}
