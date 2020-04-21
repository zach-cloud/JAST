package command;

import generic.TestContext;
import interfaces.IOutputService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.mockito.Mockito;

public class HelpCommandStepDefs {

    private IOutputService outputService;

    @Given("a help command with mocked services")
    public void a_help_command_with_mocked_services() {
        this.outputService = Mockito.mock(IOutputService.class);
        TestContext.command = new HelpCommand();
        TestContext.command.setOutputService(outputService);
    }

    @Then("mock output help service should have been executed")
    public void mock_output_help_service_should_have_been_executed() {
        Mockito.verify(outputService, Mockito.atLeastOnce()).print(Mockito.any());
    }

    @Then("mock output help service should not have been executed")
    public void mock_output_help_service_should_not_have_been_executed() {
        Mockito.verify(outputService, Mockito.times(0)).print(Mockito.any());
    }
}
