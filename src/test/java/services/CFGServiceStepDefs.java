package services;

import interfaces.ICFGService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class CFGServiceStepDefs {

    private ICFGService cfgService;
    private Map<String,String> cfgData;

    @Given("A cfg file {string} with contents:")
    public void a_cfg_file_with_contents(String path, String body) throws Exception {
        PrintWriter writer = new PrintWriter(new File(path));
        writer.println(body);
        writer.flush();
        writer.close();
    }

    @When("The cfg file {string} is read")
    public void the_cfg_file_is_read(String path) {
        this.cfgService = new CFGService();
        this.cfgData = cfgService.readConfigFile(path);
    }

    @Then("The cfg map should have {int} key value pair")
    public void the_cfg_map_should_have_key_value_pair(int count) {
        Assert.assertEquals(count, cfgData.size());
    }

    @Given("No cfg file at {string}")
    public void no_cfg_file_at(String filePath) {
        File file = new File(filePath);
        if(file.exists()) {
            file.delete();
        }
        file.mkdirs();
        file.delete();
    }

    @When("The cfg file {string} is written with key {string} and value {string}")
    public void the_cfg_file_is_written_with_key_and_value(String path, String key, String value) {
        this.cfgService = new CFGService();
        Map<String,String> writtenMap = new HashMap<>();
        writtenMap.put(key, value);
        this.cfgService.writeConfigFile(path, writtenMap);
    }

    @Then("The contents of {string} should be:")
    public void the_contents_of_should_be(String path, String body) throws Exception {
        String fileContents = FileUtils.readFileToString(new File(path), Charset.defaultCharset());
        Assert.assertEquals(body.trim(), fileContents.trim());
    }
}
