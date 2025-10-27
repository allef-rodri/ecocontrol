import io.cucumber.junit.platform.engine.Cucumber;
import io.cucumber.junit.platform.engine.ConfigurationParameter;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Cucumber
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps,config")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,summary")
public class CucumberTest {
}
