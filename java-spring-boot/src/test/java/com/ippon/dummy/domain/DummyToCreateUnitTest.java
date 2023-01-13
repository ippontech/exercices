package com.ippon.dummy.domain;

import com.ippon.UnitTest;
import com.ippon.error.domain.MissingMandatoryValueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@UnitTest
class DummyToCreateUnitTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "\t"})
  void shouldNotBuildWithoutName(String name) {
    assertThatThrownBy(() -> DummyToCreate.builder().name(name).build())
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("name");
  }

  @Test
  void shouldBuildMinimalDummyToCreate() {
    DummyToCreate dummyToCreate = DummyToCreate.builder().name("dummy name").build();

    assertThat(dummyToCreate.getName()).isEqualTo("dummy name");
  }

}
