package br.com.acai.dto.solicitation;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.acai.dto.flavor.FlavorSolicitationDTO;
import br.com.acai.dto.personalize.CreatePersonalizeSolicitationDTO;
import br.com.acai.dto.size.SizeSolicitationDTO;
import lombok.Data;

@Data
public class CreateSolicitationDTO {

	@JsonIgnore
	private Integer id;
	private SizeSolicitationDTO size;
	private FlavorSolicitationDTO flavor;
	private List<CreatePersonalizeSolicitationDTO> personalization;

}
