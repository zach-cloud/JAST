package services;

import interfaces.IGuiCompressorService;
import interfaces.ISyntaxTree;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nodes.functions.Function;
import org.junit.Assert;
import tree.SyntaxTree;
import tree.TreeContext;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class GuiCompressorServiceStepDefs {

    private IGuiCompressorService optimizationFinderService;
    private ISyntaxTree tree;
    private List<Function> foundOptimizations;
    private Function toOptimize;
    private Function newFunction;

    @Given("optimization data:")
    public void optimization_data(String body) {
        this.optimizationFinderService = new GuiCompressorService();
        this.tree = SyntaxTree.readTree(body);
    }

    @Given("optimization file: {string}")
    public void optimization_file(String fileName) {
        this.optimizationFinderService = new GuiCompressorService();
        this.tree = SyntaxTree.readTree(new File(fileName));
    }

    @When("data is searched for optimizable functions")
    public void data_is_searched_for_optimizable_functions() {
        foundOptimizations = optimizationFinderService.getOptimizableFunctionsBasedOnIf(tree);
    }

    @Then("there should be {int} function in the list of optimizable functions")
    public void there_should_be_function_in_the_list_of_optimizable_functions(int num) {
        Assert.assertEquals(num, foundOptimizations.size());
    }

    @Given("optimization function:")
    public void optimization_function(String body) {
        this.optimizationFinderService = new GuiCompressorService();
        this.toOptimize = new Function(new Scanner(body), new TreeContext());
    }

    @When("Funtion is optimized")
    public void funtion_is_optimized() {
        newFunction = optimizationFinderService.optimize(toOptimize);
    }

    @Then("Optimized function should be:")
    public void optimized_function_should_be(String body) {
        Assert.assertEquals(body, newFunction.toString());
    }

}
