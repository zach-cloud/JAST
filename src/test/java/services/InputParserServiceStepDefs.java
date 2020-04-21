package services;

import interfaces.IInputParserService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.InputModel;
import org.junit.Assert;
import services.InputParserService;

public class InputParserServiceStepDefs {

    private String inputLine;
    private InputModel model;

    @Given("Input to be parsed {string}")
    public void input_to_be_parsed(String input) {
        this.inputLine = input;
    }

    @When("Merge input is parsed")
    public void merge_input_is_parsed() {
        IInputParserService parserService = new InputParserService();
        this.model = parserService.splitInput(IInputParserService.SplitType.MERGE, inputLine);
    }

    @When("Cheatpack input is parsed")
    public void cheatpack_input_is_parsed() {
        IInputParserService parserService = new InputParserService();
        this.model = parserService.splitInput(IInputParserService.SplitType.CHEATPACK, inputLine);
    }

    @When("Rename input is parsed")
    public void rename_input_is_parsed() {
        IInputParserService parserService = new InputParserService();
        this.model = parserService.splitInput(IInputParserService.SplitType.RENAME, inputLine);
    }

    @When("Hash input is parsed")
    public void hash_input_is_parsed() {
        IInputParserService parserService = new InputParserService();
        this.model = parserService.splitInput(IInputParserService.SplitType.HASH, inputLine);
    }

    @When("Hashbreak input is parsed")
    public void hashbreak_input_is_parsed() {
        IInputParserService parserService = new InputParserService();
        this.model = parserService.splitInput(IInputParserService.SplitType.HASHBREAK, inputLine);
    }

    @Then("Tree1 should exist")
    public void tree1_should_exist() {
        Assert.assertNotNull(this.model.getTree1());
    }

    @Then("Tree2 should exist")
    public void tree2_should_exist() {
        Assert.assertNotNull(this.model.getTree2());
    }

    @Then("Output path should be {string}")
    public void output_path_should_be(String output) {
        Assert.assertEquals(output, this.model.getOutputPath());
    }

    @Then("Activator should be {string}")
    public void activator_should_be(String act) {
        Assert.assertEquals(act, model.getActivator());
    }

    @Then("New Name should be {string}")
    public void new_Name_should_be(String name) {
        Assert.assertEquals(name, model.getNewName());
    }

    @Then("Old Name should be {string}")
    public void old_Name_should_be(String name) {
        Assert.assertEquals(name, model.getOldName());
    }

    @Then("Plaintext should be {string}")
    public void plaintext_should_be(String text) {
        Assert.assertEquals(text, model.getPlaintext());
    }

    @Then("Hash should be {string}")
    public void hash_should_be(String hash) {
        Assert.assertEquals(hash, model.getHash());
    }
}
