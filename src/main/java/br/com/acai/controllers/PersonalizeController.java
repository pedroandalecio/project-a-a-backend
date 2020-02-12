package br.com.acai.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.acai.dto.personalize.PersonalizeDTO;
import br.com.acai.services.personalize.PersonalizeService;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PersonalizeController {

    @Autowired
    private PersonalizeService personalizeService;

    @PostMapping(value = "/v1/personalizes")
    public ResponseEntity<PersonalizeDTO> createPersonalize(@RequestBody @Valid final PersonalizeDTO personalizeDTO) {
        log.info("Create Personalize >>> {}", personalizeDTO);
        PersonalizeDTO saved = personalizeService.createPersonalize(personalizeDTO);
        log.info("Personalize saved successfully {}", saved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping(value = "/v1/personalizes")
    public ResponseEntity<List<PersonalizeDTO>> retrievePersonalizes(Pageable pageable) {
        log.info("Listing all Personalizes.");
        List<PersonalizeDTO> personalizes = personalizeService.retrievePersonalizes(pageable);
        log.info("Total of Personalizes found >>> {}", personalizes.size());
        return ResponseEntity.ok(personalizes);
    }

    @GetMapping(value = "/v1/personalizes/{id}")
    public ResponseEntity<PersonalizeDTO> retrievePersonalize(@PathVariable("id") Integer id) {
        log.info("Retrieve Personalize id {}  ", id);
        PersonalizeDTO result = personalizeService.findById(id);
        log.info("Personalize found >>> {} ", result);
        return ResponseEntity.ok(result);
    }

}
