package com.ippon.dummy.infrastructure.primary;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.ippon.dummy.domain.Dummy;
import com.ippon.dummy.domain.DummyId;

import java.util.UUID;

@JsonDeserialize(builder = RestDummy.RestDummyBuilder.class)
public class RestDummy {
  private final UUID id;
  private final String name;

  public RestDummy(RestDummyBuilder builder) {
    id = builder.id;
    name = builder.name;
  }

  public static RestDummy from(Dummy dummy) {
    return RestDummy.builder().id(dummy.getId().get()).name(dummy.getName()).build();
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  private static RestDummyBuilder builder() {
    return new RestDummyBuilder();
  }

  @JsonPOJOBuilder(withPrefix = "")
  static class RestDummyBuilder {
    private UUID id;
    private String name;

    public RestDummyBuilder id(UUID id) {
      this.id = id;
      return this;
    }

    public RestDummyBuilder name(String name) {
      this.name = name;
      return this;
    }

    public RestDummy build() {
      return new RestDummy(this);
    }
  }
}
