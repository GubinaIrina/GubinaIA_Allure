import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "Cucumber/features",
        glue = "step",
        tags = "@1"
)
public class TestRunner {

}
