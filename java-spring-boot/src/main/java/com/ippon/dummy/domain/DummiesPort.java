package com.ippon.dummy.domain;

public interface DummiesPort {
  Dummy create(DummyToCreate toCreate);
  Dummy get(DummyId id);
}
