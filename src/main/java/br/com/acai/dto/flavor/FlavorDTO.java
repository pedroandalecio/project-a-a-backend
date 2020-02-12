package br.com.acai.dto.flavor;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import br.com.acai.entities.Flavor;
import lombok.Data;

@Data
public class FlavorDTO {

	private Integer id;
	private String description;
	private Integer preparationTime;

	public static List<FlavorDTO> getList(Page<Flavor> page) {

		List<Flavor> list = page.getContent();
		List<FlavorDTO> listDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			list.forEach(item -> {
				listDTO.add(new FlavorDTO(item));
			});
		}
		return listDTO;
	}

	public FlavorDTO(Flavor flavor) {
		this.id = flavor.getId();
		this.description = flavor.getDescription();
		this.preparationTime = flavor.getPreparationTime();
	}

	public FlavorDTO() {

	}

	public FlavorDTO(Integer id) {
		this.id = id;
	}

}
