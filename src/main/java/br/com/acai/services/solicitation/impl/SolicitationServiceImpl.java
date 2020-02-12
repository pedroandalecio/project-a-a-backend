package br.com.acai.services.solicitation.impl;

import static java.util.Objects.isNull;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.acai.dto.solicitation.CreateSolicitationDTO;
import br.com.acai.dto.solicitation.SolicitationDTO;
import br.com.acai.entities.Flavor;
import br.com.acai.entities.Personalize;
import br.com.acai.entities.Size;
import br.com.acai.entities.Solicitation;
import br.com.acai.exceptions.SolicitationException;
import br.com.acai.repositories.FlavorRepository;
import br.com.acai.repositories.PersonalizeRepository;
import br.com.acai.repositories.SolicitationPersonalizeRepository;
import br.com.acai.repositories.SizeRepository;
import br.com.acai.repositories.SolicitationRepository;
import br.com.acai.services.solicitation.SolicitationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SolicitationServiceImpl implements SolicitationService {

	@Autowired
	private SolicitationRepository solicitationRepository;

	@Autowired
	private SizeRepository sizeRepository;

	@Autowired
	private FlavorRepository flavorRepository;

	@Autowired
	private PersonalizeRepository personalizeRepository;

	@Autowired
	private SolicitationPersonalizeRepository solicitationPersonalizeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<SolicitationDTO> retrieveSolicitations(Pageable pageable) {
		log.info("Retrieve Solicitations ");
		Page<Solicitation> solicitations = this.solicitationRepository.findAll(pageable);
		log.info("Retrieved {} Solicitations", solicitations.getTotalElements());
		return SolicitationDTO.getList(solicitations);
	}

	@Override
	public CreateSolicitationDTO createSolicitation(final CreateSolicitationDTO solicitationDTO) {
		if (isNull(solicitationDTO)) {
			log.error("solicitation is null");
			throw new IllegalArgumentException("solicitation is null");
		}

		log.info("Creating solicitation {}", solicitationDTO);
		log.info("Saving solicitation");

		Solicitation solicitation = modelMapper.map(solicitationDTO, Solicitation.class);

		if (solicitationDTO.getFlavor() == null) {
			log.error("Select a flavor for the açaí.");
			throw new IllegalArgumentException("Select a flavor for the açaí.");
		}

		Optional<Flavor> flavor = this.flavorRepository.findById(solicitationDTO.getFlavor().getId());
		if (flavor.isPresent()) {
			solicitation.setFlavor(flavor.get());
			solicitation.setPreparationTime(solicitation.getPreparationTime() + flavor.get().getPreparationTime());
		}

		if (solicitationDTO.getSize() == null) {
			log.error("Select a size for the açaí.");
			throw new IllegalArgumentException("Select a size for the açaí.");
		}

		Optional<Size> size = this.sizeRepository.findById(solicitationDTO.getSize().getId());
		if (size.isPresent()) {
			solicitation.setSize(size.get());
			solicitation.setPreparationTime(solicitation.getPreparationTime() + size.get().getPreparationTime());
			solicitation.setAmount(solicitation.getAmount().add(size.get().getPrice()));
		}

		if (solicitation.getPersonalization() != null) {
			solicitation.getPersonalization().forEach(personalization -> {

				Optional<Personalize> optional = getPersonalize(personalization.getId());
				if (optional.isPresent()) {
					solicitation.setPreparationTime(solicitation.getPreparationTime() + optional.get().getPreparationTime());
					solicitation.setAmount(solicitation.getAmount().add(optional.get().getPrice()));
					personalization.setSolicitation(solicitation);
					personalization.setPersonalize(optional.get());
				}

			});
		}

		this.solicitationRepository.save(solicitation);

		if (solicitation.getPersonalization() != null) {
			this.solicitationPersonalizeRepository.saveAll(solicitation.getPersonalization());
		}

		return modelMapper.map(solicitation, CreateSolicitationDTO.class);
	}

	@Override
	public SolicitationDTO findById(final Integer solicitationId) {
		if (solicitationId == null) {
			log.error("solicitationId can not be null");
			throw new IllegalArgumentException("solicitationId is null");
		}
		Optional<Solicitation> solicitation = solicitationRepository.findById(solicitationId);
		if (!solicitation.isPresent()) {
			log.warn("Solicitation id {}  not found", solicitationId);
			throw new SolicitationException("Solicitation id [ " + solicitationId + " ] not found");
		}

		log.info("Solicitation found {} ", solicitation.get());

		return modelMapper.map(solicitation.get(), SolicitationDTO.class);
	}

	private Optional<Personalize> getPersonalize(Integer id) {
		return this.personalizeRepository.findById(id);
	}

}
