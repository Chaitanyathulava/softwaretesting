package org.example.FinalProject.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.example.FinalProject.steps",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner {
}
