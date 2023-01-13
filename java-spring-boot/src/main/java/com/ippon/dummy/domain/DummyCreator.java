package com.ippon.dummy.domain;

public class DummyCreator {
  private final DummiesPort port;
  private final DummiesEventsDispatcher events;

  public DummyCreator(DummiesPort port, DummiesEventsDispatcher events) {
    this.port = port;
    this.events = events;
  }

  public Dummy create(DummyToCreate dummyToCreate) {
    Dummy dummy = port.create(dummyToCreate);
    events.notifyDummyCreated(dummy);
    return dummy;
  }
}
