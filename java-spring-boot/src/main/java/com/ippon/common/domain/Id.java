package com.ippon.common.domain;

import com.ippon.error.domain.Assert;
import java.util.UUID;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class Id {

  private final UUID id;

  protected Id() {
    this.id = UUID.randomUUID();
  }

  protected Id(UUID id) {
    Assert.notNull("id", id);
    this.id = id;
  }

  public UUID get() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Id other)) {
      return false;
    }

    return new EqualsBuilder().append(get(), other.get()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(id).toHashCode();
  }

  @Override
  public String toString() {
    return id.toString();
  }
}
