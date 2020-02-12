package br.com.acai.services.personalize.impl;

import static java.util.Objects.isNull;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.acai.dto.personalize.PersonalizeDTO;
import br.com.acai.entities.Personalize;
import br.com.acai.exceptions.PersonalizeException;
import br.com.acai.repositories.PersonalizeRepository;
import br.com.acai.services.personalize.PersonalizeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonalizeServiceImpl implements PersonalizeService {

	@Autowired
	private PersonalizeRepository personalizeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<PersonalizeDTO> retrievePersonalizes(Pageable pageable) {
		log.info("Retrieve Personalizes ");
		Page<Personalize> personalizes = this.personalizeRepository.findAll(pageable);
		log.info("Retrieved {} Personalizes", personalizes.getTotalElements());
		return PersonalizeDTO.getList(personalizes);
	}

	@Override
	public PersonalizeDTO createPersonalize(final PersonalizeDTO personalizeDTO) {
		if (isNull(personalizeDTO)) {
			log.error("personalize is null");
			throw new IllegalArgumentException("personalize is null");
		}
		log.info("Creating personalize {}", personalizeDTO);
		log.info("Saving personalize");

		Personalize personalize = modelMapper.map(personalizeDTO, Personalize.class);

		return modelMapper.map(this.personalizeRepository.save(personalize), PersonalizeDTO.class);
	}

	@Override
	public PersonalizeDTO findById(final Integer personalizeId) {
		if (personalizeId == null) {
			log.error("personalizeId can not be null");
			throw new IllegalArgumentException("personalizeId is null");
		}
		Optional<Personalize> personalize = personalizeRepository.findById(personalizeId);
		if (!personalize.isPresent()) {
			log.warn("Personalize id {}  not found", personalizeId);
			throw new PersonalizeException("Personalize id [ " + personalizeId + " ] not found");
		}

		log.info("Personalize found {} ", personalize.get());

		return modelMapper.map(personalize.get(), PersonalizeDTO.class);
	}

}
