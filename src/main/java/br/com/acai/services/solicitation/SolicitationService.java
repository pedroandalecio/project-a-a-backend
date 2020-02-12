package br.com.acai.services.solicitation;

import java.util.List;
import org.springframework.data.domain.Pageable;

import br.com.acai.dto.solicitation.CreateSolicitationDTO;
import br.com.acai.dto.solicitation.SolicitationDTO;

public interface SolicitationService {

	List<SolicitationDTO> retrieveSolicitations(Pageable pageable);

	CreateSolicitationDTO createSolicitation(CreateSolicitationDTO solicitationDTO);

	SolicitationDTO findById(Integer id);

}
