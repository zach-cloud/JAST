package command;

import generic.TestContext;
import interfaces.ITreeMergeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.mockito.Mockito;
import settings.Settings;

public class NZCPCommandStepDefs {

    private ITreeMergeService service;

    @Given("a NZCP command with mocked services")
    public void a_NZCP_command_with_mocked_services() {
        this.service = Mockito.mock(ITreeMergeService.class);
        TestContext.command = new NZCPCommand(service);
    }

    @Then("mock merge service should have been executed for nzcp")
    public void mock_merge_service_should_have_been_executed() {
        if (Settings.CHEATING_ENABLED) {
            Mockito.verify(service, Mockito.times(1)).applyNzcp(Mockito.eq(false), Mockito.any());
        }
    }

    @Then("mock merge service should not have been executed for nzcp")
    public void mock_merge_service_should_not_have_been_executed() {
        Mockito.verify(service, Mockito.times(0)).applyNzcp(Mockito.eq(false), Mockito.any());

    }
}
