package br.com.acai.dto.solicitation;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SolicitationSizeDTO {

	private Integer id;

	public SolicitationSizeDTO(Integer id) {
		this.id = id;
	}
}
