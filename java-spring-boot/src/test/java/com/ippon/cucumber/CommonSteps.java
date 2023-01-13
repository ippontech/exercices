package com.ippon.cucumber;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CommonSteps {

  public static HttpHeaders jsonHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    return headers;
  }
}
