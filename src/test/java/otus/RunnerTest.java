package otus;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@SelectPackages("otus.steps")
@ConfigurationParameter(
    key = GLUE_PROPERTY_NAME,
    value = "otus.steps"
)
public class RunnerTests {
}
