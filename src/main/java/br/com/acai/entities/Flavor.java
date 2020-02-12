package br.com.acai.entities;

import java.io.Serializable;
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
@Entity(name = "flavor")
@DynamicUpdate
public class Flavor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_flavor")
	@SequenceGenerator(sequenceName = "seq_flavor", allocationSize = 1, name = "seq_flavor")
	private Integer id;

	@Column(nullable = false)
	@Length(min = 1, max = 255)
	private String description;

	@Column(nullable = false, name = "preparation_time")
	private Integer preparationTime;

}
