package com.otus;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@SelectClasspathResource("com/otus")
@ConfigurationParameter(
    key = GLUE_PROPERTY_NAME,
    value = "com/otus"
)
public class RunnerTest {
}
