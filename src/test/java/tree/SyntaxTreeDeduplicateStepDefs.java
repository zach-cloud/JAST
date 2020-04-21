package tree;

import services.RandomNameGeneratorService;
import interfaces.ISyntaxTree;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.mockito.Mockito;

public class SyntaxTreeDeduplicateStepDefs {

    private ISyntaxTree tree;
    private ISyntaxTree resultTree;
    private RandomNameGeneratorService generator;

    @Given("Non-deduplicated script:")
    public void non_deduplicated_script(String body) {
        this.tree = SyntaxTree.readTree(body);
    }

    @Given("mock random name generator set up for {string}")
    public void mock_random_name_generator_set_up_for(String name) {
        generator = Mockito.mock(RandomNameGeneratorService.class);
        Mockito.when(generator.next()).thenReturn(name);
    }

    @When("Tree is deduplicated")
    public void tree_is_deduplicated() {
        resultTree = tree.deduplicate(generator);
    }

    @Then("Deduplicated script should be:")
    public void deduplicated_script_should_be(String body) {
        Assert.assertEquals(body.trim(), resultTree.toString().trim());
    }
}
