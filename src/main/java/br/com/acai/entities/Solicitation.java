package br.com.acai.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.DynamicUpdate;
import brave.internal.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity(name = "solicitation")
@DynamicUpdate
public class Solicitation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_solicitation")
	@SequenceGenerator(sequenceName = "seq_solicitation", allocationSize = 1, name = "seq_solicitation")
	private Integer id;

	@ManyToOne
	@JoinColumn(nullable = false, name = "size_id")
	private Size size;

	@ManyToOne
	@JoinColumn(nullable = false, name = "flavor_id")
	private Flavor flavor;

	@Column(nullable = false)
	private BigDecimal amount = BigDecimal.ZERO;

	@Column(nullable = false, name = "preparation_time")
	private Integer preparationTime = 0;

	@Nullable
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitation")
	private List<SolicitationPersonalize> personalization;

}
