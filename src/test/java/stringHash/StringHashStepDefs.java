package stringHash;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class StringHashStepDefs {

    private String plaintext;
    private String hash;

    @Given("the plaintext {string}")
    public void thePlaintext(String text) {
        plaintext = text;
    }

    @When("the string is hashed")
    public void theStringIsHashed() {
        hash = SStrHash2.hash(plaintext);
    }

    @Then("the hash is {string}")
    public void theHashIs(String realHash) {
        Assert.assertEquals("Expected hash: " + realHash + " but was actually " + hash, realHash, hash);
    }
}
