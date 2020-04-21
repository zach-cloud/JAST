package services;

import generic.TestContext;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class PreprocessFileServiceStepDefs {

    @Then("Preprocessed data should be:")
    public void preprocessed_data_should_be(String body) {
        StringBuilder assembledData = new StringBuilder();
        while(TestContext.inputScanner.hasNextLine()) {
            assembledData.append(TestContext.inputScanner.nextLine()).append("\n");
        }
        assembledData.setLength(assembledData.length()-1);
        Assert.assertEquals(body, assembledData.toString());
    }
}
