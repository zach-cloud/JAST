package stringHash;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class StringHashBreakerStepDefs {

    private String hash;
    private String plaintext;

    @Given("the hash to be broken is {string}")
    public void the_hash_to_be_broken_is(String hash) {
        this.hash = hash;
    }

    @When("the hash is broken")
    public void the_hash_is_broken() {
        StringHashBreakerThread thread = new StringHashBreakerThread(hash);
        thread.run();
        this.plaintext = thread.getPlaintext();
    }

    @Then("the plaintext should be {string}")
    public void the_plaintext_should_be(String plaintext) {
        Assert.assertEquals(plaintext, this.plaintext);
    }

}
