package br.com.acai.services.flavor.impl;

import static java.util.Objects.isNull;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.acai.dto.flavor.FlavorDTO;
import br.com.acai.entities.Flavor;
import br.com.acai.exceptions.FlavorException;
import br.com.acai.repositories.FlavorRepository;
import br.com.acai.services.flavor.FlavorService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlavorServiceImpl implements FlavorService {

	@Autowired
	private FlavorRepository flavorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<FlavorDTO> retrieveFlavors(Pageable pageable) {
		log.info("Retrieve Flavors ");
		Page<Flavor> flavors = this.flavorRepository.findAll(pageable);
		log.info("Retrieved {} Flavors", flavors.getTotalElements());
		return FlavorDTO.getList(flavors);
	}

	@Override
	public FlavorDTO createFlavor(final FlavorDTO flavorDTO) {
		if (isNull(flavorDTO)) {
			log.error("flavor is null");
			throw new IllegalArgumentException("flavor is null");
		}
		log.info("Creating flavor {}", flavorDTO);
		log.info("Saving flavor");

		Flavor flavor = modelMapper.map(flavorDTO, Flavor.class);

		return modelMapper.map(this.flavorRepository.save(flavor), FlavorDTO.class);
	}

	@Override
	public FlavorDTO findById(final Integer flavorId) {
		if (flavorId == null) {
			log.error("flavorId can not be null");
			throw new IllegalArgumentException("flavorId is null");
		}
		Optional<Flavor> flavor = flavorRepository.findById(flavorId);
		if (!flavor.isPresent()) {
			log.warn("Flavor id {}  not found", flavorId);
			throw new FlavorException("Flavor id [ " + flavorId + " ] not found");
		}

		log.info("Flavor found {} ", flavor.get());

		return modelMapper.map(flavor.get(), FlavorDTO.class);
	}

}
