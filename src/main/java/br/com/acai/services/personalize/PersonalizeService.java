package br.com.acai.services.personalize;

import java.util.List;
import org.springframework.data.domain.Pageable;

import br.com.acai.dto.personalize.PersonalizeDTO;

public interface PersonalizeService {

	List<PersonalizeDTO> retrievePersonalizes(Pageable pageable);

	PersonalizeDTO createPersonalize(PersonalizeDTO flavorDTO);

	PersonalizeDTO findById(Integer id);

}
