package command;

import generic.TestContext;
import interfaces.IFileWriterService;
import interfaces.IGuiOptimizerService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.mockito.Mockito;

public class OptimizeGuiCommandStepDefs {

    private IGuiOptimizerService optimizerService;
    private IFileWriterService writerService;

    @Given("an optimize gui command with mocked services")
    public void an_optimize_gui_command_with_mocked_services() {
        this.optimizerService = Mockito.mock(IGuiOptimizerService.class);
        this.writerService = Mockito.mock(IFileWriterService.class);
        TestContext.command = new OptimizeGuiCommand(optimizerService, writerService);
    }

    @Then("mock optimizer service should have been executed")
    public void mock_optimizer_service_should_have_been_executed() {
        Mockito.verify(optimizerService, Mockito.times(1)).runOptimize(Mockito.any());
        Mockito.verify(writerService, Mockito.times(1)).write(Mockito.any(), Mockito.any());
    }

    @Then("mock optimizer service should not have been executed")
    public void mock_optimizer_service_should_not_have_been_executed() {
        Mockito.verify(optimizerService, Mockito.times(0)).runOptimize(Mockito.any());
        Mockito.verify(writerService, Mockito.times(0)).write(Mockito.any(), Mockito.any());
    }

}
