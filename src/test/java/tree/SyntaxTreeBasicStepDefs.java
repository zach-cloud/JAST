package tree;

import generic.TestContext;
import interfaces.ISyntaxTree;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

public class SyntaxTreeBasicStepDefs {

    private ISyntaxTree tree;
    private Exception exception;

    @Given("tree file {string}")
    public void tree_file(String filePath) {
        exception = null;
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
            String inputString = FileUtils.readFileToString(new File(url.getPath()), Charset.defaultCharset());
            TestContext.inputScanner = new Scanner(new File(url.getPath()));
            this.tree = SyntaxTree.readTree(inputString);
        } catch (Exception ex) {
            this.exception = ex;
        }
    }

    @Then("there should be no syntax error")
    public void there_should_be_no_syntax_error() {
        if(exception != null) {
            exception.printStackTrace();
        }
        Assert.assertNull("Syntax error found", exception);
    }

    @Then("there should be a syntax error")
    public void there_should_be_a_syntax_error() {
        Assert.assertNotNull("Syntax error not found", exception);
    }
}
