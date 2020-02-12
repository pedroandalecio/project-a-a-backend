package br.com.acai.services.size.impl;

import static java.util.Objects.isNull;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.acai.dto.size.SizeDTO;
import br.com.acai.entities.Size;
import br.com.acai.exceptions.SizeException;
import br.com.acai.repositories.SizeRepository;
import br.com.acai.services.size.SizeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SizeServiceImpl implements SizeService {

	@Autowired
	private SizeRepository sizeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<SizeDTO> retrieveSizes(Pageable pageable) {
		log.info("Retrieve Sizes ");
		Page<Size> sizes = this.sizeRepository.findAll(pageable);
		log.info("Retrieved {} Sizes", sizes.getTotalElements());
		return SizeDTO.getList(sizes);
	}

	@Override
	public SizeDTO createSize(final SizeDTO sizeDTO) {
		if (isNull(sizeDTO)) {
			log.error("size is null");
			throw new IllegalArgumentException("size is null");
		}
		log.info("Creating size {}", sizeDTO);
		log.info("Saving size");

		Size size = modelMapper.map(sizeDTO, Size.class);

		return modelMapper.map(this.sizeRepository.save(size), SizeDTO.class);
	}

	@Override
	public SizeDTO findById(final Integer sizeId) {
		if (sizeId == null) {
			log.error("sizeId can not be null");
			throw new IllegalArgumentException("sizeId is null");
		}
		Optional<Size> size = sizeRepository.findById(sizeId);
		if (!size.isPresent()) {
			log.warn("Size id {}  not found", sizeId);
			throw new SizeException("Size id [ " + sizeId + " ] not found");
		}

		log.info("Size found {} ", size.get());

		return modelMapper.map(size.get(), SizeDTO.class);
	}

}
