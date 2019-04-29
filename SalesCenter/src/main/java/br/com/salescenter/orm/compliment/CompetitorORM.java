package br.com.salescenter.orm.compliment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import br.com.salescenter.contants.converter.CompetitorTypeConverter;
import br.com.salescenter.contants.enumeration.CompetitorTypeEnum;
import br.com.salescenter.orm.opportunity.OpportunityORM;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_competitor")
public class CompetitorORM implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Null
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_opportunity", nullable = true)
	private OpportunityORM opportunity;
	
	@NotNull
	@Convert(converter = CompetitorTypeConverter.class)
	@Column(name = "competitorType", nullable = false)
	private CompetitorTypeEnum competitorType;

	@NotNull
	@Size(max = 100)
	@Column(name = "competitor", nullable = false, length = 100)
	private String competitor;
}