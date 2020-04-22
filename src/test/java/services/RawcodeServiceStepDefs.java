package services;

import interfaces.IRawcodeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import rawcode.ObjectsStructure;
import rawcode.RawcodeBinaryReader;

import java.io.File;

public class RawcodeServiceStepDefs {

    private ObjectsStructure structure;
    private RawcodeBinaryReader reader;
    private IRawcodeService rawcodeService;
    private File inputFile;
    private String rawcodeText;

    @Given("Rawcode file: {string}")
    public void rawcode_file(String file) {
        this.inputFile = new File(file);
        reader = new RawcodeBinaryReader();
        this.rawcodeService = new RawcodeService();
    }

    @When("Rawcodes are parsed and read")
    public void rawcodes_are_parsed_and_read() {
        this.structure = reader.readObject(inputFile);
        this.rawcodeText = rawcodeService.makeRawcodesFrom(structure);
    }

    @Then("Rawcodes should be:")
    public void rawcodes_should_be(String body) {
        Assert.assertEquals(body, rawcodeText);
    }


}
