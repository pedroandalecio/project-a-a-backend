package br.com.acai.dto.solicitation;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SolicitationFlavorDTO {

	private Integer id;

	public SolicitationFlavorDTO(Integer id) {
		this.id = id;
	}
}
