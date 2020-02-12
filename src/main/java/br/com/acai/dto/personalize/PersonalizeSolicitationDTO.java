package br.com.acai.dto.personalize;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import br.com.acai.entities.SolicitationPersonalize;
import lombok.Data;

@Data
public class PersonalizeSolicitationDTO {

	private Integer id;
	private String description;
	private Integer preparationTime;

	public static List<PersonalizeSolicitationDTO> getList(Page<SolicitationPersonalize> page) {

		List<SolicitationPersonalize> list = page.getContent();
		List<PersonalizeSolicitationDTO> listDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			list.forEach(item -> {
				listDTO.add(new PersonalizeSolicitationDTO(item));
			});
		}
		return listDTO;
	}

	public PersonalizeSolicitationDTO(SolicitationPersonalize item) {
		this.id = item.getId();
		this.description = item.getPersonalize().getDescription();
		this.preparationTime = item.getPersonalize().getPreparationTime();
	}

	public PersonalizeSolicitationDTO() {

	}

}
