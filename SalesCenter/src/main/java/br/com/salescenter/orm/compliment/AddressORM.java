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

import br.com.salescenter.contants.converter.StateConverter;
import br.com.salescenter.contants.converter.StreetTypeConverter;
import br.com.salescenter.contants.enumeration.StateEnum;
import br.com.salescenter.contants.enumeration.StreetTypeEnum;
import br.com.salescenter.orm.client.ClientORM;
import br.com.salescenter.orm.user.PersonORM;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_address")
public class AddressORM implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Null
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_person", nullable = true)
	private PersonORM person;
	
	@Null
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client", nullable = true)
	private ClientORM client;

	@NotNull
	@Convert(converter = StreetTypeConverter.class)
	@Column(name = "streetType", nullable = false)
	private StreetTypeEnum streetType;
	
	@NotNull
	@Size(max = 200)
	@Column(name = "street", nullable = false, length = 200)
	private String street;

	@NotNull
	@Size(max = 5)
	@Column(name = "number", nullable = false, length = 5)
	private String number;

	@Size(max = 50)
	@Column(name = "compliment", nullable = true, length = 50)
	private String compliment;

	@NotNull
	@Size(max = 8)
	@Column(name = "cep", nullable = false, length = 8)
	private String cep;

	@NotNull
	@Size(max = 50)
	@Column(name = "neighborhood", nullable = false, length = 50)
	private String neighborhood;

	@NotNull
	@Size(max = 50)
	@Column(name = "city", nullable = false, length = 50)
	private String city;

	@NotNull
	@Convert(converter = StateConverter.class)
	@Column(name = "state", nullable = false)
	private StateEnum state;

	@NotNull
	@Size(max = 20)
	@Column(name = "country", nullable = false, length = 20)
	private String country;
}