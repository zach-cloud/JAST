package command;

import generic.TestContext;
import interfaces.IExitProgramService;
import interfaces.IOutputService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.mockito.Mockito;

public class QuitCommandStepDefs {

    private IExitProgramService exitProgramService;

    @Given("a quit command with mocked services")
    public void a_quit_command_with_mocked_services() {
        this.exitProgramService = Mockito.mock(IExitProgramService.class);
        TestContext.command = new QuitCommand(exitProgramService);
    }

    @Then("mock quit service should have been executed")
    public void mock_quit_help_service_should_have_been_executed() {
        Mockito.verify(exitProgramService, Mockito.times(1)).exit();
    }

    @Then("mock quit service should not have been executed")
    public void mock_quit_help_service_should_not_have_been_executed() {
        Mockito.verify(exitProgramService, Mockito.times(0)).exit();
    }
}
