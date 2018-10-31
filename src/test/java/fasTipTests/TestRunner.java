package fasTipTests;

/**
 * TestRunner.java
 * Purpose: Cucumber Test Runner class. Contains execution configurations
 * which features are to be included in a run.
 *
 * @author Haris Saleem
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/",
        plugin = { "pretty", "html:target/htmlreport"})

public class TestRunner {

}