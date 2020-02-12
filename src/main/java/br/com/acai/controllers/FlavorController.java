package br.com.acai.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.acai.dto.flavor.FlavorDTO;
import br.com.acai.services.flavor.FlavorService;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FlavorController {

    @Autowired
    private FlavorService flavorService;

    @PostMapping(value = "/v1/flavors")
    public ResponseEntity<FlavorDTO> createFlavor(@RequestBody @Valid final FlavorDTO flavorDTO) {
        log.info("Create Flavor >>> {}", flavorDTO);
        FlavorDTO saved = flavorService.createFlavor(flavorDTO);
        log.info("Flavor saved successfully {}", saved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping(value = "/v1/flavors")
    public ResponseEntity<List<FlavorDTO>> retrieveFlavors(Pageable pageable) {
        log.info("Listing all Flavors.");
        List<FlavorDTO> flavors = flavorService.retrieveFlavors(pageable);
        log.info("Total of Flavors found >>> {}", flavors.size());
        return ResponseEntity.ok(flavors);
    }

    @GetMapping(value = "/v1/flavors/{id}")
    public ResponseEntity<FlavorDTO> retrieveFlavor(@PathVariable("id") Integer id) {
        log.info("Retrieve Flavor id {}  ", id);
        FlavorDTO result = flavorService.findById(id);
        log.info("Flavor found >>> {} ", result);
        return ResponseEntity.ok(result);
    }

}
