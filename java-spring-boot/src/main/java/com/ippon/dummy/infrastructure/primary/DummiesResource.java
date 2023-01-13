package com.ippon.dummy.infrastructure.primary;

import com.ippon.dummy.application.DummiesApplicationService;
import com.ippon.dummy.domain.DummyId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/dummies")
class DummiesResource {

  private final DummiesApplicationService service;

  DummiesResource(DummiesApplicationService service) {
    this.service = service;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Create dummy", description = "Creates a wonderful dummy")
  public RestDummy create(@RequestBody RestDummyToCreate dummy) {
    return RestDummy.from(service.create(dummy.toDomain()));
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get a dummy", description = "Get dummy information for given dummy id")
  public RestDummy get(@Schema(description = "Identifier of the dummy") @PathVariable("id") UUID id) {
    return RestDummy.from(service.get(new DummyId(id)));
  }
}
