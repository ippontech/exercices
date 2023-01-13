package com.ippon.dummy.infrastructure.primary;

import com.ippon.dummy.domain.DummyToCreate;

class RestDummyToCreate {
  private String name;

  public RestDummyToCreate() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DummyToCreate toDomain() {
    return DummyToCreate.builder().name(name).build();
  }
}
