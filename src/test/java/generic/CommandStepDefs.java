package generic;

import io.cucumber.java.en.When;

public class CommandStepDefs {

    @When("Line is executed: {string}")
    public void line_is_executed(String cmd) {
        if(TestContext.command.isCommend(cmd)) {
            TestContext.command.execute(cmd);
        }
    }
}
