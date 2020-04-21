package command;

import generic.TestContext;
import interfaces.IHashBreakService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

public class HashBreakCommandStepDefs {

    private IHashBreakService service;

    @Given("a hash breaker command with mocked services")
    public void a_hash_breaker_command_with_mocked_services() {
        this.service = Mockito.mock(IHashBreakService.class);
        TestContext.command = new HashBreakCommand(service);
    }

    @Then("mock hash breaker service should have been executed")
    public void mock_hash_breaker_service_should_have_been_executed() {
        Mockito.verify(service, Mockito.times(1)).setHash(Mockito.any());
        Mockito.verify(service, Mockito.times(1)).runBreak();
    }

    @Then("mock hash breaker service should not have been executed")
    public void mock_hash_breaker_service_should_not_have_been_executed() {
        Mockito.verify(service, Mockito.times(0)).setHash(Mockito.any());
        Mockito.verify(service, Mockito.times(0)).runBreak();
    }
}
