package generic;

import interfaces.IPreprocessFileService;
import services.PreprocessFileService;
import io.cucumber.java.en.Given;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class AstNodeStepDefs {

    @Given("input data:")
    public void input_data(String inputString) {
        TestContext.inputScanner = new Scanner(inputString);
        IPreprocessFileService preprocessor = new PreprocessFileService();
        TestContext.inputScanner = preprocessor.preprocessFile(TestContext.inputScanner);
    }

    @Given("input file: {string}")
    public void input_file(String filePath) throws Exception {
        URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
        TestContext.inputScanner = new Scanner(new File(url.getPath()));
        IPreprocessFileService preprocessor = new PreprocessFileService();
        TestContext.inputScanner = preprocessor.preprocessFile(TestContext.inputScanner);
    }

    @Given("input data without preprocessing:")
    public void input_data_without_preprocessing(String inputString) {
        TestContext.inputScanner = new Scanner(inputString);
    }
}
