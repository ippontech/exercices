package com.ippon.dummy.domain;

import com.ippon.error.domain.Assert;

public class DummyToCreate {
  private final String name;

  public DummyToCreate(DummyToCreateBuilder builder) {
    Assert.notBlank("name", builder.name);
    name = builder.name;
  }

  public String getName() {
    return name;
  }

  public static DummyToCreateBuilder builder() {
    return new DummyToCreateBuilder();
  }

  public static class DummyToCreateBuilder {
    private String name;

    public DummyToCreateBuilder name(String name) {
      this.name = name;
      return this;
    }

    public DummyToCreate build() {
      return new DummyToCreate(this);
    }
  }
}
