package command;

import generic.TestContext;
import interfaces.ITreeMergeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.mockito.Mockito;

public class MergeDedupeCommandStepDefs {

    private ITreeMergeService service;

    @Given("a merge dedupe command with mocked services")
    public void a_JJCP_command_with_mocked_services() {
        this.service = Mockito.mock(ITreeMergeService.class);
        TestContext.command = new MergeDedupCommand(service);
    }

    @Then("mock merge service should have been executed for merge dedupe")
    public void mock_merge_service_should_have_been_executed() {
        Mockito.verify(service, Mockito.times(1)).merge(Mockito.any(), Mockito.eq(true));
    }

    @Then("mock merge service should not have been executed for merge dedupe")
    public void mock_merge_service_should_not_have_been_executed() {
        Mockito.verify(service, Mockito.times(0)).merge(Mockito.any(), Mockito.eq(true));

    }
}
