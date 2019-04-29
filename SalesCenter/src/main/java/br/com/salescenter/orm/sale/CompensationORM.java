package br.com.salescenter.orm.sale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.salescenter.contants.converter.StatusConverter;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.orm.base.BaseORM;
import br.com.salescenter.orm.user.PersonORM;
import br.com.salescenter.utils.DateTimeUtils;
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
@Table(name = "tb_compensation")
public class CompensationORM extends BaseORM implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@OneToOne
	@JoinColumn(name = "seller")
	private PersonORM seller;

	@NotNull
	@Size(max = 255)
	@Column(name = "year", nullable = false, length = 255)
	private int year = DateTimeUtils.currentYear();

	@NotNull
	@Column(name = "january", nullable = false)
	private Double january = 0.0;

	@NotNull
	@Column(name = "february", nullable = false)
	private Double february = 0.0;

	@NotNull
	@Column(name = "march", nullable = false)
	private Double march = 0.0;

	@NotNull
	@Column(name = "april", nullable = false)
	private Double april = 0.0;

	@NotNull
	@Column(name = "may", nullable = false)
	private Double may = 0.0;

	@NotNull
	@Column(name = "june", nullable = false)
	private Double june = 0.0;

	@NotNull
	@Column(name = "july", nullable = false)
	private Double july = 0.0;

	@NotNull
	@Column(name = "august", nullable = false)
	private Double august = 0.0;

	@NotNull
	@Column(name = "september", nullable = false)
	private Double september = 0.0;

	@NotNull
	@Column(name = "october", nullable = false)
	private Double october = 0.0;

	@NotNull
	@Column(name = "november", nullable = false)
	private Double november = 0.0;

	@NotNull
	@Column(name = "december", nullable = false)
	private Double december = 0.0;

	@Convert(converter = StatusConverter.class)
	@Column(name = "status", nullable = false)
	private StatusEnum status;
}
