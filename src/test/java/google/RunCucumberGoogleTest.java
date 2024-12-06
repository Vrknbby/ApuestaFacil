package google;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
			glue = {"google"},
			features = "src/test/resources/google/google.feature",
			tags = {"@BusquedaInput"}
		)
public class RunCucumberGoogleTest {

}
