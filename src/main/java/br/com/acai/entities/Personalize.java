package br.com.acai.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity(name = "personalize")
@DynamicUpdate
public class Personalize implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_personalize")
	@SequenceGenerator(sequenceName = "seq_personalize", allocationSize = 1, name = "seq_personalize")
	private Integer id;

	@Column(nullable = false)
	@Length(min = 5, max = 255)
	private String description;

	private BigDecimal price = BigDecimal.ZERO;

	@Column(nullable = false, name = "preparation_time")
	private Integer preparationTime = 0;

}
