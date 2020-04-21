package cli;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Scanner;

public class JASTStepDefs {

    private Scanner mockScanner;

    @Given("scanner data:")
    public void scanner_data(String body) {
        this.mockScanner = new Scanner(body);
    }

    @When("JAST is executed")
    public void jast_is_executed() {
        JAST jast = new JAST();
        jast.setScanner(mockScanner);
        jast.run();
    }

    @Then("exit state should be true")
    public void exit_state_should_be_true() {
        Assert.assertTrue(JASTState.quitDesired);
    }
}
