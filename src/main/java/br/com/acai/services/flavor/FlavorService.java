package br.com.acai.services.flavor;

import java.util.List;
import org.springframework.data.domain.Pageable;
import br.com.acai.dto.flavor.FlavorDTO;

public interface FlavorService {

	List<FlavorDTO> retrieveFlavors(Pageable pageable);

	FlavorDTO createFlavor(FlavorDTO flavorDTO);

	FlavorDTO findById(Integer id);

}
