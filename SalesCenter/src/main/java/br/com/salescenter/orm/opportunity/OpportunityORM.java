package br.com.salescenter.orm.opportunity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import br.com.salescenter.contants.converter.BusinessTypeConverter;
import br.com.salescenter.contants.converter.ForecastCategoryConverter;
import br.com.salescenter.contants.converter.ReasonConverter;
import br.com.salescenter.contants.converter.SalesStageConverter;
import br.com.salescenter.contants.converter.SalesTypeConverter;
import br.com.salescenter.contants.converter.StatusConverter;
import br.com.salescenter.contants.enumeration.BusinessTypeEnum;
import br.com.salescenter.contants.enumeration.ForecastCategoryEnum;
import br.com.salescenter.contants.enumeration.ReasonEnum;
import br.com.salescenter.contants.enumeration.SalesStageEnum;
import br.com.salescenter.contants.enumeration.SalesTypeEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.orm.base.BaseEntity;
import br.com.salescenter.orm.base.BaseORM;
import br.com.salescenter.orm.client.ClientORM;
import br.com.salescenter.orm.compliment.CompetitorORM;
import br.com.salescenter.orm.compliment.InvolvedTeamORM;
import br.com.salescenter.orm.compliment.ProductORM;
import br.com.salescenter.orm.user.PersonORM;
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
@Table(name = "tb_opportunity")
public class OpportunityORM extends BaseORM implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "opportunity", orphanRemoval = true)
	private List<CompetitorORM> competitors = new ArrayList<CompetitorORM>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "opportunity", orphanRemoval = true)
	private List<InvolvedTeamORM> Involveds = new ArrayList<InvolvedTeamORM>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "opportunity", orphanRemoval = true)
	private List<ProductORM> products = new ArrayList<ProductORM>();

	@Null
	@OneToOne
	@JoinColumn(name = "id_client")
	private ClientORM client;

	@Null
	@OneToOne
	@JoinColumn(name = "id_person")
	private PersonORM seller;

	@NotNull
	@Size(max = 50)
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Null
	@Size(max = 1000)
	@Column(name = "description", nullable = true, length = 1000)
	private String description;
	
	@NotNull
	@Column(name = "history", nullable = false, columnDefinition = "LONGTEXT")
	private String history;

	@Null
	@Size(max = 255)
	@Column(name = "manufacturerRegistration", nullable = true, length = 255)
	private String manufacturerRegistration;

	@Null
	@Column(name = "closeDate", nullable = true)
	private LocalDate closeDate;

	@NotNull
	@Convert(converter = BusinessTypeConverter.class)
	@Column(name = "businessType", nullable = false)
	private BusinessTypeEnum businessType;

	@NotNull
	@Convert(converter = SalesStageConverter.class)
	@Column(name = "salesStage", nullable = false)
	private SalesStageEnum salesStage;

	@NotNull
	@Convert(converter = SalesTypeConverter.class)
	@Column(name = "salesType", nullable = false)
	private SalesTypeEnum salesType;

	@NotNull
	@Convert(converter = ForecastCategoryConverter.class)
	@Column(name = "forecastCategory", nullable = false)
	private ForecastCategoryEnum forecastCategory;

	@Convert(converter = ReasonConverter.class)
	@Column(name = "reason", nullable = false)
	private ReasonEnum reason;

	@NotNull
	@Column(name = "amount", nullable = false)
	private Double amount;

	@Convert(converter = StatusConverter.class)
	@Column(name = "status", nullable = false)
	private StatusEnum status;
}