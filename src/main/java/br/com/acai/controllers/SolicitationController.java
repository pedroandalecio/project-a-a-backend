package br.com.acai.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.acai.dto.solicitation.CreateSolicitationDTO;
import br.com.acai.dto.solicitation.SolicitationDTO;
import br.com.acai.services.solicitation.SolicitationService;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SolicitationController {

    @Autowired
    private SolicitationService solicitationService;

    @PostMapping(value = "/v1/solicitations")
    public ResponseEntity<CreateSolicitationDTO> createSolicitation(@RequestBody @Valid final CreateSolicitationDTO solicitationDTO) {
        log.info("Create Solicitation >>> {}", solicitationDTO);
        CreateSolicitationDTO saved = solicitationService.createSolicitation(solicitationDTO);
        log.info("Solicitation saved successfully {}", saved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping(value = "/v1/solicitations")
    public ResponseEntity<List<SolicitationDTO>> retrieveSolicitations(Pageable pageable) {
        log.info("Listing all Solicitations.");
        List<SolicitationDTO> solicitations = solicitationService.retrieveSolicitations(pageable);
        log.info("Total of Solicitations found >>> {}", solicitations.size());
        return ResponseEntity.ok(solicitations);
    }

    @GetMapping(value = "/v1/solicitations/{id}")
    public ResponseEntity<SolicitationDTO> retrieveSolicitation(@PathVariable("id") Integer id) {
        log.info("Retrieve Solicitation id {}  ", id);
        SolicitationDTO result = solicitationService.findById(id);
        log.info("Solicitation found >>> {} ", result);
        return ResponseEntity.ok(result);
    }

}
