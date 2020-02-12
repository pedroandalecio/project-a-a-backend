package br.com.acai.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.acai.dto.size.SizeDTO;
import br.com.acai.services.size.SizeService;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @PostMapping(value = "/v1/sizes")
    public ResponseEntity<SizeDTO> createSize(@RequestBody @Valid final SizeDTO sizeDTO) {
        log.info("Create Size >>> {}", sizeDTO);
        SizeDTO saved = sizeService.createSize(sizeDTO);
        log.info("Size saved successfully {}", saved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping(value = "/v1/sizes")
    public ResponseEntity<List<SizeDTO>> retrieveSizes(Pageable pageable) {
        log.info("Listing all Sizes.");
        List<SizeDTO> sizes = sizeService.retrieveSizes(pageable);
        log.info("Total of Sizes found >>> {}", sizes.size());
        return ResponseEntity.ok(sizes);
    }

    @GetMapping(value = "/v1/sizes/{id}")
    public ResponseEntity<SizeDTO> retrieveSize(@PathVariable("id") Integer id) {
        log.info("Retrieve Size id {}  ", id);
        SizeDTO result = sizeService.findById(id);
        log.info("Size found >>> {} ", result);
        return ResponseEntity.ok(result);
    }

}
