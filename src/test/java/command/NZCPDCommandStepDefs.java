package command;

import generic.TestContext;
import interfaces.ITreeMergeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.mockito.Mockito;

public class NZCPDCommandStepDefs {

    private ITreeMergeService service;

    @Given("a NZCPD command with mocked services")
    public void a_NZCPD_command_with_mocked_services() {
        this.service = Mockito.mock(ITreeMergeService.class);
        TestContext.command = new NZCPDCommand(service);
    }

    @Then("mock merge service should have been executed for nzcpd")
    public void mock_merge_service_should_have_been_executed() {
        Mockito.verify(service, Mockito.times(1)).applyNzcp(Mockito.eq(true), Mockito.any());
    }

    @Then("mock merge service should not have been executed for nzcpd")
    public void mock_merge_service_should_not_have_been_executed() {
        Mockito.verify(service, Mockito.times(0)).applyNzcp(Mockito.eq(true), Mockito.any());

    }
}
