package services;

import interfaces.IFileWriterService;
import interfaces.IInputParserService;
import interfaces.IOutputService;
import interfaces.ITreeMergeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.InputModel;
import org.junit.Assert;
import org.mockito.Mockito;

public class TreeMergeServiceStepDefs {

    private InputModel model;
    private boolean dedupe;
    private ITreeMergeService mergeService;
    private IFileWriterService writerService;
    private IOutputService outputService;
    private String output;

    @Given("a tree merger with dedupe {string}, tree 1 {string}, tree 2 {string}, and output {string}")
    public void a_tree_merger_with_dedupe_tree_tree_and_output(String dedupe, String tree1, String tree2, String output) {
        if(dedupe.equalsIgnoreCase("true")) {
            this.dedupe = true;
        } else if(dedupe.equalsIgnoreCase("false")) {
            this.dedupe = false;
        } else {
            Assert.fail("Unknown dedupe condition: " + dedupe);
        }
        this.output = output;
        InputParserService parserService = new InputParserService();
        model = parserService.splitInput(IInputParserService.SplitType.MERGE, "merge " + tree1 + " " + tree2 + " " + output);
        this.writerService = Mockito.mock(IFileWriterService.class);
        this.outputService = Mockito.mock(IOutputService.class);
        this.mergeService = new TreeMergeService(outputService, writerService);
    }

    @When("tree merge is executed")
    public void tree_merge_is_executed() {
        mergeService.merge(model, dedupe);
    }

    @Then("tree file should have been written")
    public void tree_file_should_have_been_written() {
        Mockito.verify(writerService, Mockito.times(1)).write(Mockito.any(), Mockito.eq(output));
    }

    @Given("a cheatpack merger with dedupe {string}, tree 1 {string}, activator {string}, and output {string}")
    public void a_jjcp_merger_with_dedupe_tree_activator_and_output(String dedupe, String tree1, String activator, String output) {
        if(dedupe.equalsIgnoreCase("true")) {
            this.dedupe = true;
        } else if(dedupe.equalsIgnoreCase("false")) {
            this.dedupe = false;
        } else {
            Assert.fail("Unknown dedupe condition: " + dedupe);
        }
        this.output = output;
        InputParserService parserService = new InputParserService();
        model = parserService.splitInput(IInputParserService.SplitType.CHEATPACK, "jjcp " + tree1 + " " + activator + " " + output);
        this.writerService = Mockito.mock(IFileWriterService.class);
        this.outputService = Mockito.mock(IOutputService.class);
        this.mergeService = new TreeMergeService(outputService, writerService);
    }

    @When("jjcp merge is executed")
    public void jjcp_merge_is_executed() {
        mergeService.applyJjcp(dedupe, model);
    }

    @When("nzcp merge is executed")
    public void nzcp_merge_is_executed() {
        mergeService.applyNzcp(dedupe, model);
    }
}
