package command;

import generic.TestContext;
import interfaces.ITreeMergeService;
import interfaces.ITreeReplaceService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.mockito.Mockito;
import services.TreeReplaceService;

public class RenameVariableCommandStepDefs {

    private ITreeReplaceService service;

    @Given("a rename variable command with mocked services")
    public void a_rename_variable_command_with_mocked_services() {
        this.service = Mockito.mock(ITreeReplaceService.class);
        TestContext.command = new RenameVariableCommand(service);
    }

    @Then("mock replace service should have been executed for variable")
    public void mock_replace_service_should_have_been_executed() {
        Mockito.verify(service, Mockito.times(1)).replace(Mockito.eq(TreeReplaceService.ReplacementType.VARIABLE), Mockito.any());
    }

    @Then("mock replace service should not have been executed for variable")
    public void mock_replace_service_should_not_have_been_executed() {
        Mockito.verify(service, Mockito.times(0)).replace(Mockito.eq(TreeReplaceService.ReplacementType.VARIABLE), Mockito.any());
    }
}
