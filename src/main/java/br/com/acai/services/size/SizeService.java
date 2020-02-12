package br.com.acai.services.size;

import java.util.List;
import org.springframework.data.domain.Pageable;
import br.com.acai.dto.size.SizeDTO;

public interface SizeService {

	List<SizeDTO> retrieveSizes(Pageable pageable);

	SizeDTO createSize(SizeDTO sizeDTO);

	SizeDTO findById(Integer id);

}
