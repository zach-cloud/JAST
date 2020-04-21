package command;

import generic.TestContext;
import interfaces.ITreeMergeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.mockito.Mockito;

public class JJCPCommandStepDefs {

    private ITreeMergeService service;

    @Given("a JJCP command with mocked services")
    public void a_JJCP_command_with_mocked_services() {
        this.service = Mockito.mock(ITreeMergeService.class);
        TestContext.command = new JJCPCommand(service);
    }

    @Then("mock merge service should have been executed for jjcp")
    public void mock_merge_service_should_have_been_executed() {
        Mockito.verify(service, Mockito.times(1)).applyJjcp(Mockito.eq(false), Mockito.any());
    }

    @Then("mock merge service should not have been executed for jjcp")
    public void mock_merge_service_should_not_have_been_executed() {
        Mockito.verify(service, Mockito.times(0)).applyJjcp(Mockito.eq(false), Mockito.any());

    }
}
