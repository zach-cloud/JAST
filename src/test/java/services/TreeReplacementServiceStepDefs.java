package services;

import interfaces.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.InputModel;
import org.junit.Assert;
import org.mockito.Mockito;

public class TreeReplacementServiceStepDefs {

    private InputModel model;
    private ITreeReplaceService replaceService;
    private IFileWriterService writerService;
    private IOutputService outputService;
    private String output;

    @Given("a replacement service for file {string} and old name {string} and new name {string} and output {string}")
    public void a_replacement_service_for_file_and_old_name_and_new_name_and_output(String fileName, String oldName, String newName, String output) {
        this.output = output;
        InputParserService parserService = new InputParserService();
        model = parserService.splitInput(IInputParserService.SplitType.RENAME, "renamevariable " + fileName + " " + oldName + " " + newName + " " + output);
        this.writerService = Mockito.mock(IFileWriterService.class);
        this.outputService = Mockito.mock(IOutputService.class);
        this.replaceService = new TreeReplaceService(outputService, writerService);
    }

    @When("replacement service is executed for variable")
    public void replacement_service_is_executed_for_variable() {
        replaceService.replace(TreeReplaceService.ReplacementType.VARIABLE, model);
    }

    @When("replacement service is executed for function")
    public void replacement_service_is_executed_for_function() {
        replaceService.replace(TreeReplaceService.ReplacementType.FUNCTION, model);
    }

    @When("replacement service is executed for string")
    public void replacement_service_is_executed_for_string() {
        replaceService.replace(TreeReplaceService.ReplacementType.STRING, model);

    }

    @Then("replacement service should have written file")
    public void replacement_service_should_have_written_file() {
        Mockito.verify(writerService, Mockito.times(1)).write(Mockito.any(), Mockito.eq(output));
    }

}
