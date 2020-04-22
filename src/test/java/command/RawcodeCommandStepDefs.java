package command;

import generic.TestContext;
import interfaces.IFileWriterService;
import interfaces.IGuiOptimizerService;
import interfaces.IRawcodeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.mockito.Mockito;

public class RawcodeCommandStepDefs {

    private IRawcodeService rawcodeService;
    private IFileWriterService writerService;

    @Given("a rawcode generate command with mocked services")
    public void a_rawcode_generate_command_with_mocked_services() {
        this.rawcodeService = Mockito.mock(IRawcodeService.class);
        this.writerService = Mockito.mock(IFileWriterService.class);
        TestContext.command = new RawcodeCommand(rawcodeService);
        TestContext.command.setWriterService(this.writerService);
    }

    @Then("mock rawcode generator service should have been executed")
    public void mock_rawcode_generator_service_should_have_been_executed() {
        Mockito.verify(rawcodeService, Mockito.times(1)).makeRawcodesFrom(Mockito.any(String.class));
        Mockito.verify(writerService, Mockito.times(1)).writeString(Mockito.any(), Mockito.any());
    }

    @Then("mock rawcode generator service should not have been executed")
    public void mock_rawcode_generator_service_should_not_have_been_executed() {
        Mockito.verify(rawcodeService, Mockito.times(0)).makeRawcodesFrom(Mockito.any(String.class));
        Mockito.verify(writerService, Mockito.times(0)).writeString(Mockito.any(), Mockito.any());
    }
}
