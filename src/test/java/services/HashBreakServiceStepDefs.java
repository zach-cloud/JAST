package services;

import interfaces.IHashBreakService;
import interfaces.IOutputService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.InputModel;
import org.mockito.Mockito;

public class HashBreakServiceStepDefs {

    private IOutputService outputService;
    private IHashBreakService hashBreakService;

    @Given("a hash breaker service")
    public void a_hash_breaker_service() {
        this.outputService = Mockito.mock(IOutputService.class);
        this.hashBreakService = new HashBreakService(outputService);
    }

    @When("hash breaker service is executed")
    public void hash_breaker_service_is_executed() {
        InputModel model = new InputModel();
        model.setHash("-244676034");
        this.hashBreakService.setHash(model);
        this.hashBreakService.runBreak();
    }

    @Then("the result should have been displayed")
    public void the_result_should_have_been_displayed() {
        Mockito.verify(outputService, Mockito.atLeastOnce()).print(Mockito.any());
    }
}
