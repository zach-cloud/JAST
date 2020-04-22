package rawcode;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.File;

public class RawcodeBinaryReaderStepDefs {

    private ObjectsStructure structure;
    private RawcodeBinaryReader reader;
    private File inputFile;

    @Given("Input object file {string}")
    public void input_object_file(String file) {
        this.inputFile = new File(file);
        reader = new RawcodeBinaryReader();
    }

    @When("W3T file is read")
    public void w3t_file_is_read() {
        this.structure = reader.readObject(inputFile);
    }

    @Then("there should be {int} modified objects and {int} new objects")
    public void there_should_be_modified_objects_and_new_objects(int modified, int newObjects) {
        Assert.assertEquals(modified, structure.getModifiedObjects().size());
        Assert.assertEquals(newObjects, structure.getNewObjects().size());
    }
}
