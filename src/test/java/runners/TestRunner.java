package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {
                "json:target/jsonReports/cucumber-report.json"

        },
        features = "src/test/resources/features",
        glue = "stepDefinitions"
        //tags = "@AddPlace"
)
public class TestRunner {
}
