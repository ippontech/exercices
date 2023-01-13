package com.ippon.cucumber;

import com.ippon.DummyApp;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.client.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@CucumberContextConfiguration
@ActiveProfiles("test")
@SpringBootTest(classes = { DummyApp.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
public class CucumberConfiguration {

  @Autowired
  private TestRestTemplate rest;

  @Before
  public void resetTestContext() {
    CucumberTestContext.reset();
  }

  @Before
  public void loadInterceptors() {
    ClientHttpRequestFactory requestFactory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());

    RestTemplate template = rest.getRestTemplate();
    template.setRequestFactory(requestFactory);
    template.setInterceptors(Arrays.asList(mockedCsrfTokenInterceptor(), saveLastResultInterceptor()));
    template.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
  }

  private ClientHttpRequestInterceptor mockedCsrfTokenInterceptor() {
    return (request, body, execution) -> {
      request.getHeaders().add("mocked-csrf-token", "MockedToken");

      return execution.execute(request, body);
    };
  }

  private ClientHttpRequestInterceptor saveLastResultInterceptor() {
    return (request, body, execution) -> {
      ClientHttpResponse response = execution.execute(request, body);

      CucumberTestContext.addResponse(request, response, execution, body);

      return response;
    };
  }
}
