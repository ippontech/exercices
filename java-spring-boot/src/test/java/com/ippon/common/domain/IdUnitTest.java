package com.ippon.common.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.ippon.error.domain.MissingMandatoryValueException;
import com.ippon.UnitTest;

import java.util.UUID;
import org.junit.jupiter.api.Test;

@UnitTest
public abstract class IdUnitTest<T> {

  public static final UUID ID = UUID.fromString("5f38741a-f3ce-495f-adbf-407463f91b69");

  @Test
  void shouldNotCreateWithoutId() {
    assertThatThrownBy(this::nullId).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("id");
  }

  @Test
  void shouldCreateWithoutId() {
    assertThat(defaultRandomId().get()).isNotNull();
  }

  @Test
  void shouldCreate() {
    assertThat(defaultId().get()).isEqualTo(ID);
  }

  @Test
  void shouldNotBeEqualAnotherType() {
    assertThat(defaultId().equals("id")).isFalse();
  }

  @Test
  void shouldNotBeEqualToIdWithAnotherId() {
    assertThat(defaultId().equals(defaultRandomId())).isFalse();
  }

  @Test
  void shouldBeEqualWhenIsSameId() {
    assertThat(defaultId().equals(defaultId())).isTrue();
  }

  @Test
  void shouldNotBeEqualWhenOneIdNull() {
    assertThat(defaultId().equals(null)).isFalse();
  }

  @Test
  void shouldBeEqual() {
    Id id = defaultId();
    assertThat(id).isEqualTo(id);
  }

  @Test
  void shouldNotBeEqualWhenForOneNull() {
    assertThat(defaultId()).isNotEqualTo(null);
  }

  @Test
  void shouldGetSameHashCodeWithSameId() {
    assertThat(defaultId()).hasSameHashCodeAs(defaultId());
  }

  @Test
  void shouldGetDifferentHashCodeWithDifferentId() {
    assertThat(defaultId()).doesNotHaveSameHashCodeAs(defaultRandomId());
  }

  @Test
  void shouldGetString() {
    assertThat(defaultId()).hasToString(ID.toString());
  }

  protected abstract Id nullId();

  protected abstract Id defaultRandomId();

  protected abstract Id defaultId();
}
