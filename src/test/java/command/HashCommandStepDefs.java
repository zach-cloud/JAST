package command;

import generic.TestContext;
import interfaces.IHashService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

public class HashCommandStepDefs {

    private IHashService service;

    @Given("a hash command with mocked services")
    public void a_hash_command_with_mocked_services() {
        this.service = Mockito.mock(IHashService.class);
        TestContext.command = new HashCommand(service);
    }

    @Then("mock hash service should have been executed")
    public void mock_hash_service_should_have_been_executed() {
        Mockito.verify(service, Mockito.times(1)).runHash(Mockito.any());
    }

    @Then("mock hash service should not have been executed")
    public void mock_hash_service_should_not_have_been_executed() {
        Mockito.verify(service, Mockito.times(0)).runHash(Mockito.any());
    }
}
