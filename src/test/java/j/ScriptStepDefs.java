package j;

import nodes.j.Script;
import generic.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tree.TreeContext;

public class ScriptStepDefs {

    private Script script;

    @When("J File is read")
    public void j_File_is_read() {
        this.script = new Script(TestContext.inputScanner, new TreeContext());
    }

    @Then("J File should contain {int} variables")
    public void j_File_should_contain_variables(int variables) {
        Assert.assertEquals(variables, script.getGlobalsSection().getGlobalVariables().size());
    }

    @Then("J File should contain {int} functions")
    public void j_File_should_contain_functions(int functions) {
        Assert.assertEquals(functions, script.getFunctionsSection().getFunctions().size());
    }

}
