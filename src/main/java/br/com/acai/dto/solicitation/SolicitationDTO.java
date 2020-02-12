package br.com.acai.dto.solicitation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import br.com.acai.dto.flavor.FlavorDTO;
import br.com.acai.dto.personalize.PersonalizeSolicitationDTO;
import br.com.acai.dto.size.SizeDTO;
import br.com.acai.entities.Solicitation;
import lombok.Data;

@Data
public class SolicitationDTO {

	private Integer id;
	private SizeDTO size;
	private FlavorDTO flavor;
	private BigDecimal amount;
	private Integer preparationTime;
	private List<PersonalizeSolicitationDTO> personalization;

	public static List<SolicitationDTO> getList(Page<Solicitation> page) {

		List<Solicitation> list = page.getContent();
		List<SolicitationDTO> listDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			list.forEach(item -> {
				listDTO.add(new SolicitationDTO(item));
			});
		}
		return listDTO;
	}

	public SolicitationDTO() {

	}

	public SolicitationDTO(Solicitation solicitation) {
		this.id = solicitation.getId();
		if (solicitation.getSize() != null) {
			this.size = new SizeDTO(solicitation.getSize());
		}
		if (solicitation.getFlavor() != null) {
			this.flavor = new FlavorDTO(solicitation.getFlavor());
		}
		this.amount = solicitation.getAmount();
		this.preparationTime = solicitation.getPreparationTime();
		this.personalization = PersonalizeSolicitationDTO.getList(new PageImpl<>(solicitation.getPersonalization()));
	}

}
