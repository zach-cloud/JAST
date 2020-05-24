package services;

import generic.TestContext;
import interfaces.ISyntaxChecker;
import interfaces.ISyntaxTree;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import tree.SyntaxTree;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

public class SyntaxCheckerServiceStepDefs {

    private ISyntaxChecker syntaxChecker;
    private String result;

    @Given("a new syntax checker")
    public void a_new_syntax_checker() {
        this.syntaxChecker = new SyntaxCheckerService();
    }

    @When("file {string} is checked for errors")
    public void file_is_checked_for_errors(String filePath) {
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
            String inputString = FileUtils.readFileToString(new File(url.getPath()), Charset.defaultCharset());
            TestContext.inputScanner = new Scanner(new File(url.getPath()));
            ISyntaxTree tree = SyntaxTree.readTree(inputString);
            this.result = syntaxChecker.syntaxCheck(tree);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Then("there should be no syntax errors")
    public void there_should_be_no_syntax_errors() {
        for(String line : result.split("\n")) {
            Assert.assertFalse("Error found: " + line, line.contains("Error") || line.contains("error"));
        }
    }

    @Then("there should be syntax errors")
    public void there_should_be_syntax_errors() {
        boolean errorFound = false;
        for(String line : result.split("\n")) {
            if(line.contains("error") || line.contains("Error")) {
                errorFound = true;
            }
        }
        Assert.assertTrue("Error expected, found none: " + result, errorFound);
    }

}
