package services;

import interfaces.IHashService;
import interfaces.IOutputService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.InputModel;
import org.mockito.Mockito;

public class HashServiceStepDefs {

    private IOutputService outputService;
    private IHashService hashService;

    @Given("a hash service")
    public void a_hash_service() {
        this.outputService = Mockito.mock(IOutputService.class);
        this.hashService = new HashService(outputService);
    }

    @When("hash service is executed")
    public void hash_service_is_executed() {
        InputModel model = new InputModel();
        model.setPlaintext("aaa");
        this.hashService.runHash(model);
    }

    @Then("the hash result should have been displayed")
    public void the_hash_result_should_have_been_displayed() {
        Mockito.verify(outputService, Mockito.atLeastOnce()).print(Mockito.any());
    }
}
