package com.ekdev.rental_system.controller;

import com.ekdev.rental_system.assembler.CottageDtoAssembler;
import com.ekdev.rental_system.dto.CottageDtoRequest;
import com.ekdev.rental_system.dto.CottageDtoResponse;
import com.ekdev.rental_system.model.Cottage;
import com.ekdev.rental_system.service.CottageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cottages")
public class CottageController {

    private final CottageService cottageService;

    private final PagedResourcesAssembler<Cottage> pagedResourcesAssembler;

    private final CottageDtoAssembler cottageDtoAssembler;

    @Autowired
    public CottageController(CottageService cottageService,
                             PagedResourcesAssembler<?> genericAssembler,
                             CottageDtoAssembler cottageDtoAssembler) {
        this.cottageDtoAssembler = cottageDtoAssembler;
        this.cottageService = cottageService;
        @SuppressWarnings("unchecked")
        PagedResourcesAssembler<Cottage> castedAssembler = (PagedResourcesAssembler<Cottage>) genericAssembler;
        this.pagedResourcesAssembler = castedAssembler;
    }

    @GetMapping
    public PagedModel<CottageDtoResponse> getAllCottages(
            @PageableDefault(size = 10, sort = "number") Pageable pageable) {
        Page<Cottage> cottages = cottageService.getAllCottages(pageable);
        return pagedResourcesAssembler.toModel(cottages, cottageDtoAssembler);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CottageDtoResponse> getCottageById(@PathVariable Long id) {
        Cottage cottage = cottageService.getCottageById(id);
        CottageDtoResponse dto = cottageDtoAssembler.toModel(cottage);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CottageDtoResponse> createCottage(@Valid @RequestBody CottageDtoRequest cottageDtoRequest) {
        Cottage cottage = cottageService.createCottage(cottageDtoRequest);
        CottageDtoResponse dto = cottageDtoAssembler.toModel(cottage);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cottage.getId())
                .toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCottage(@PathVariable Long id) {
        cottageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CottageDtoResponse> updateCottage (@Valid @RequestBody CottageDtoRequest cottageDtoRequest, @PathVariable Long id) {
        Cottage cottage = cottageService.updateCottage(id, cottageDtoRequest);
        CottageDtoResponse cottageDtoResponse = cottageDtoAssembler.toModel(cottage);
        return ResponseEntity.ok(cottageDtoResponse);
    }
}
