package br.com.acai.dto.size;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import br.com.acai.entities.Size;
import lombok.Data;

@Data
public class SizeDTO {

	private Integer id;
	private String description;
	private BigDecimal price;
	private Integer preparationTime;

	public static List<SizeDTO> getList(Page<Size> page) {

		List<Size> list = page.getContent();
		List<SizeDTO> listDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			list.forEach(item -> {
				listDTO.add(new SizeDTO(item));
			});
		}
		return listDTO;
	}

	public SizeDTO(Size size) {
		this.id = size.getId();
		this.description = size.getDescription();
		this.price = size.getPrice();
		this.preparationTime = size.getPreparationTime();
	}

	public SizeDTO() {

	}

	public SizeDTO(Integer id) {
		this.id = id;
	}

}
