package com.ippon.dummy.application;

import com.ippon.dummy.domain.*;
import org.springframework.stereotype.Component;

@Component
public class DummiesApplicationService {

  private final DummyCreator creator;
  private final DummiesPort port;

  public DummiesApplicationService(DummiesPort port, DummiesEventsDispatcher events) {
    this.port = port;
    creator = new DummyCreator(port, events);
  }

  public Dummy create(DummyToCreate dummy) {
    return creator.create(dummy);
  }

  public Dummy get(DummyId id) {
    return port.get(id);
  }
}
