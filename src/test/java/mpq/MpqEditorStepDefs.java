package mpq;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.mockito.Mockito;
import systems.crigges.jmpq3.JMpqEditor;

import java.io.File;

public class MpqEditorStepDefs {

    private JMpqEditor jMpqEditor;
    private MpqEditor editor;

    @Given("A mock MPQ editor with file {string}")
    public void a_mock_MPQ_editor_with_file(String filename) {
        this.jMpqEditor = Mockito.mock(JMpqEditor.class);
        Mockito.when(jMpqEditor.hasFile(filename)).thenReturn(true);
        editor = new MpqEditor(jMpqEditor);
    }

    @When("MPQ is extracted")
    public void mpq_is_extracted() {
        editor.extractFiles(new File("out"));
    }

    @Then("File {string} should have been exported")
    public void file_should_have_been_exported(String filename) throws Exception {
        Mockito.verify(jMpqEditor, Mockito.times(1)).extractFile(Mockito.eq(filename), Mockito.any(File.class));
    }

    @When("MPQ is imported from directory {string}")
    public void mpq_is_imported_from_directory(String directory) {
        editor.packFiles(new File(directory));
    }

    @Then("File {string} should have been imported")
    public void file_should_have_been_imported(String filename) throws Exception {
        Mockito.verify(jMpqEditor, Mockito.times(1)).insertFile(
                Mockito.eq(filename),
                Mockito.any(File.class),
                Mockito.eq(false)
        );
    }
}
