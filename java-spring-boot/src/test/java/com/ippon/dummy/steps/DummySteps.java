package com.ippon.dummy.steps;

import com.ippon.cucumber.CucumberTestContext;
import com.ippon.dummy.infrastructure.primary.RestDummy;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.ippon.cucumber.CommonSteps.*;
import static org.assertj.core.api.Assertions.*;

public class DummySteps {

  @Autowired
  private TestRestTemplate restTemplate;

  public static final String DUMMY_PAYLOAD = """
    {
      "name": "{{name}}"
    }
    """;

  @When("a dummy feature is invoked")
  public void dummyThing() {
    ResponseEntity<Void> response = restTemplate.postForEntity(
      "/api/dummies",
      new HttpEntity<>(DUMMY_PAYLOAD.replace("{{name}}", "dummy name"), jsonHeader()),
      Void.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    CucumberTestContext.setLastDummyId();
  }

  @Then("a dummy behavior is demonstrated")
  public void assertDummyBehavior() {
    ResponseEntity<RestDummy> dummy = restTemplate.getForEntity("/api/dummies/" + CucumberTestContext.getLastDummyId(), RestDummy.class);

    // Either using deserialized Rest object
    assertThat(dummy.getBody().getId()).isNotNull();
    // Or if not deserialized can also be parsed using JSON Path
    assertThat(CucumberTestContext.getElement("$.name")).isEqualTo("dummy name");
  }
}
