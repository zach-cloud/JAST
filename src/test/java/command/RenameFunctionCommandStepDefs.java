package command;

import generic.TestContext;
import interfaces.ITreeReplaceService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.mockito.Mockito;
import services.TreeReplaceService;

public class RenameFunctionCommandStepDefs {

    private ITreeReplaceService service;

    @Given("a rename function command with mocked services")
    public void a_rename_function_command_with_mocked_services() {
        this.service = Mockito.mock(ITreeReplaceService.class);
        TestContext.command = new RenameFunctionCommand(service);
    }

    @Then("mock replace service should have been executed for function")
    public void mock_replace_service_should_have_been_executed() {
        Mockito.verify(service, Mockito.times(1)).replace(Mockito.eq(TreeReplaceService.ReplacementType.FUNCTION), Mockito.any());
    }

    @Then("mock replace service should not have been executed for function")
    public void mock_replace_service_should_not_have_been_executed() {
        Mockito.verify(service, Mockito.times(0)).replace(Mockito.eq(TreeReplaceService.ReplacementType.FUNCTION), Mockito.any());
    }
}
