package command;

import generic.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import mpq.MpqEditor;
import org.junit.Assert;
import org.mockito.Mockito;

public class ExtractCommandStepDefs {

    private MpqEditor editor;

    @Given("An extract command with mocked services")
    public void an_extract_command_with_mocked_services() {
        this.editor = Mockito.mock(MpqEditor.class);
        TestContext.command = new ExtractCommand(editor);
    }

    @Then("Mock mpq extract service should have been executed")
    public void mock_mpq_extract_service_should_have_been_executed() {
        Mockito.verify(editor, Mockito.times(1)).extractFiles(Mockito.any());
    }

    @Then("Mock mpq extract service should not have been executed")
    public void mock_mpq_extract_service_should_not_have_been_executed() {
        Mockito.verify(editor, Mockito.times(0)).extractFiles(Mockito.any());
    }

}
