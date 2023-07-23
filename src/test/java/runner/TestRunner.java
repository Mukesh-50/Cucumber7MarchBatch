package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features = "featureFiles",
				glue = { "stepDefination", "hooks" }, 
				plugin = { "pretty",
						   "html:target/cucumber-reports.html", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
				snippets = SnippetType.CAMELCASE)
public class TestRunner {

}
