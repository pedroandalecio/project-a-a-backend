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
@Entity(name = "size")
@DynamicUpdate
public class Size implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_size")
	@SequenceGenerator(sequenceName = "seq_size", allocationSize = 1, name = "seq_size")
	private Integer id;

	@Column(nullable = false)
	@Length(min = 5, max = 255)
	private String description;

	@Column(nullable = false)
	private BigDecimal price = BigDecimal.ZERO;

	@Column(nullable = false, name = "preparation_time")
	private Integer preparationTime = 0;
}
