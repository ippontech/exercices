package com.ippon.dummy.infrastructure.secondary;

import com.ippon.dummy.domain.DummiesPort;
import com.ippon.dummy.domain.Dummy;
import com.ippon.dummy.domain.DummyId;
import com.ippon.dummy.domain.DummyToCreate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DummiesAdapter implements DummiesPort {

  private final Collection<Dummy> dummies = new ArrayList<>();

  @Override
  public Dummy create(DummyToCreate toCreate) {
    Dummy dummy = Dummy.builder().id(new DummyId()).name(toCreate.getName()).build();
    dummies.add(dummy);
    return dummy;
  }

  @Override
  public Dummy get(DummyId id) {
    return dummies.stream().filter(dummy -> dummy.getId().get().equals(id.get())).findFirst().orElseThrow(IllegalArgumentException::new);
  }
}
