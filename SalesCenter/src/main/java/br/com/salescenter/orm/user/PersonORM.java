package br.com.salescenter.orm.user;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import br.com.salescenter.contants.converter.GenderConverter;
import br.com.salescenter.contants.enumeration.GenderEnum;
import br.com.salescenter.orm.base.BaseEntity;
import br.com.salescenter.orm.base.BaseORM;
import br.com.salescenter.orm.compliment.AddressORM;
import br.com.salescenter.orm.compliment.PhoneORM;
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
@Table(name = "tb_person",
		uniqueConstraints = {@UniqueConstraint(name = "uk_person_rg", columnNames = {"rg"}),
				@UniqueConstraint(name = "uk_person_cpf", columnNames = {"cpf"}),
				@UniqueConstraint(name = "uk_person_email", columnNames = {"email"})})
public class PersonORM extends BaseORM implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "person", orphanRemoval = true)
	private List<AddressORM> addresses = new ArrayList<AddressORM>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "person", orphanRemoval = true)
	private List<PhoneORM> phones = new ArrayList<PhoneORM>();

	@NotNull
	@OneToOne
	@JoinColumn(name = "id_user")
	private UserORM user;

	@Null
	@OneToOne
	@JoinColumn(name = "id_topUser")
	private PersonORM topUser;

	@NotNull
	@Convert(converter = GenderConverter.class)
	@Column(name = "gender", nullable = false)
	private GenderEnum gender;

	@NotNull
	@Size(max = 50)
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@NotNull
	@Size(max = 100)
	@Column(name = "lastName", nullable = false, length = 100)
	private String lastName;

	@NotNull
	@Column(name = "birth", nullable = false)
	private LocalDate birth;

	@NotNull
	@Size(max = 9)
	@Column(name = "rg", nullable = false, length = 9)
	private String rg;

	@NotNull
	@Size(max = 11)
	@Column(name = "cpf", nullable = false, length = 11)
	private String cpf;

	@NotNull
	@Size(max = 100)
	@Column(name = "email", nullable = false, length = 100)
	private String email;
}
