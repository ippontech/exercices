package com.ippon.dummy.domain;

import com.ippon.common.domain.Id;

import java.util.UUID;

public class DummyId extends Id {
  public DummyId(UUID id) {
    super(id);
  }
  public DummyId() {
    super();
  }
}
