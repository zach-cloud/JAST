package services;

import interfaces.IBlizzardLoaderService;
import interfaces.ISyntaxTree;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class BlizzardLoaderServiceStepDefs {

    private IBlizzardLoaderService loaderService;
    private ISyntaxTree loadedTree;

    @Given("A Blizzard Loader Service")
    public void a_Blizzard_Loader_Service() {
        this.loaderService = new BlizzardLoaderService();
    }

    @When("Common is loaded")
    public void common_is_loaded() {
        this.loadedTree = loaderService.loadCommon();
    }

    @When("Blizzard is loaded")
    public void blizzard_is_loaded() {
        this.loadedTree = loaderService.loadBlizzard();
    }

}
