package services;

import interfaces.ISyntaxTree;
import interfaces.IUnhexService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.SyntaxTree;

public class UnhexServiceStepDefs {

    private ISyntaxTree tree;
    private IUnhexService unhexService;

    @Given("Unhex data:")
    public void unhex_data(String body) {
        tree = SyntaxTree.readTree(body);
    }

    @When("Code is unhexed")
    public void code_is_unhexed() {
        this.unhexService = new UnhexService();
        unhexService.unhex(tree);
    }

    @Then("Unhexed code should be:")
    public void unhexed_code_should_be(String body) {
        Assert.assertEquals(body, tree.toString());
    }
}
