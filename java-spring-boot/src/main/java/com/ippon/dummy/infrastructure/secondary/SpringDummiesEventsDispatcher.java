package com.ippon.dummy.infrastructure.secondary;

import com.ippon.dummy.domain.DummiesEventsDispatcher;
import com.ippon.dummy.domain.Dummy;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringDummiesEventsDispatcher implements DummiesEventsDispatcher {

  private final ApplicationEventPublisher publisher;

  public SpringDummiesEventsDispatcher(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  @Override
  public void notifyDummyCreated(Dummy dummy) {
    publisher.publishEvent(dummy);
  }
}
