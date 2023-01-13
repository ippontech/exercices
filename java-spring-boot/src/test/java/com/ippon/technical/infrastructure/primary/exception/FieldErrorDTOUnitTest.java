package com.ippon.technical.infrastructure.primary.exception;

import static org.assertj.core.api.Assertions.assertThat;

import com.ippon.UnitTest;
import org.junit.jupiter.api.Test;

@UnitTest
class FieldErrorDTOUnitTest {

  @Test
  void shouldBuild() {
    FieldErrorDTO fieldErrorDTO = new FieldErrorDTO("dto", "field", "message");

    assertThat(fieldErrorDTO.getObjectName()).isEqualTo("dto");
    assertThat(fieldErrorDTO.getField()).isEqualTo("field");
    assertThat(fieldErrorDTO.getMessage()).isEqualTo("message");
  }
}
