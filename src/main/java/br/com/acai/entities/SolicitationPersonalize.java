package br.com.acai.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.DynamicUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity(name = "solicitation_personalize")
@DynamicUpdate
public class SolicitationPersonalize implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_solicitation_personalize")
	@SequenceGenerator(sequenceName = "seq_solicitation_personalize", allocationSize = 1, name = "seq_solicitation_personalize")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "solicitation_id")
	private Solicitation solicitation;

	@ManyToOne
	@JoinColumn(name = "personalize_id")
	private Personalize personalize;

}
