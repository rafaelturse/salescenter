package br.com.salescenter.orm.client;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.salescenter.contants.converter.StatusConverter;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.orm.base.BaseORM;
import br.com.salescenter.orm.compliment.AddressORM;
import br.com.salescenter.orm.compliment.EmailORM;
import br.com.salescenter.orm.compliment.PhoneORM;
import br.com.salescenter.orm.compliment.RepresentativeORM;
import br.com.salescenter.orm.compliment.SegmentORM;
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
@Table(name = "tb_client",
		uniqueConstraints = {@UniqueConstraint(name = "uk_client_cnpj", columnNames = {"cnpj"}),
				@UniqueConstraint(name = "uk_client_social", columnNames = {"social"}),
				@UniqueConstraint(name = "uk_client_fantasy", columnNames = {"fantasy"})})
public class ClientORM extends BaseORM implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true)
	private List<AddressORM> addresses = new ArrayList<AddressORM>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true)
	private List<PhoneORM> phones = new ArrayList<PhoneORM>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true)
	private List<EmailORM> emails = new ArrayList<EmailORM>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true)
	private List<RepresentativeORM> representatives = new ArrayList<RepresentativeORM>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true)
	private List<SegmentORM> segments = new ArrayList<SegmentORM>();

	@NotNull
	@Size(max = 255)
	@Column(name = "social", nullable = false, length = 255)
	private String social;

	@NotNull
	@Size(max = 255)
	@Column(name = "fantasy", nullable = false, length = 255)
	private String fantasy;

	@NotNull
	@Size(max = 14)
	@Column(name = "cnpj", nullable = false, length = 14)
	private String cnpj;

	@NotNull
	@Size(max = 50)
	@Column(name = "stateRegistration", nullable = false, length = 50)
	private String stateRegistration;
	
	@NotNull
	@Column(name = "opening", nullable = false)
	private LocalDate opening;

	@NotNull
	@Size(max = 255)
	@Column(name = "site", nullable = false, length = 255)
	private String site;

	@Convert(converter = StatusConverter.class)
	@Column(name = "status", nullable = false)
	private StatusEnum status;
}
