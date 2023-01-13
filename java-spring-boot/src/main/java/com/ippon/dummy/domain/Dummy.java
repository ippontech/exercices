package com.ippon.dummy.domain;

import com.ippon.error.domain.Assert;

public class Dummy {

  private final DummyId id;
  private final String name;

  public Dummy(DummyBuilder builder) {
    Assert.notNull("id", builder.id);
    Assert.notBlank("name", builder.name);
    this.id = builder.id;
    this.name = builder.name;
  }

  public String getName() {
    return name;
  }

  public DummyId getId() {
    return id;
  }

  public static DummyBuilder builder() {
    return new DummyBuilder();
  }

  public static class DummyBuilder {
    private DummyId id;
    private String name;

    public DummyBuilder id(DummyId id) {
      this.id = id;
      return this;
    }

    public DummyBuilder name(String name) {
      this.name = name;
      return this;
    }

    public Dummy build() {
      return new Dummy(this);
    }
  }
}
