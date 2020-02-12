package br.com.acai.dto.personalize;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import br.com.acai.entities.Personalize;
import lombok.Data;

@Data
public class PersonalizeDTO {

	private Integer id;
	private String description;
	private Integer preparationTime;

	public static List<PersonalizeDTO> getList(Page<Personalize> page) {

		List<Personalize> list = page.getContent();
		List<PersonalizeDTO> listDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			list.forEach(item -> {
				listDTO.add(new PersonalizeDTO(item));
			});
		}
		return listDTO;
	}

	public PersonalizeDTO(Personalize personalize) {
		this.id = personalize.getId();
		this.description = personalize.getDescription();
		this.preparationTime = personalize.getPreparationTime();
	}

	public PersonalizeDTO() {

	}

}
