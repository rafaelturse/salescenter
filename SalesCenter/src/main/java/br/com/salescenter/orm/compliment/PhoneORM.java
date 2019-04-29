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

import br.com.salescenter.contants.converter.PhoneTypeConverter;
import br.com.salescenter.contants.enumeration.PhoneTypeEnum;
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
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_phone")
public class PhoneORM implements Serializable {

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
	@Convert(converter = PhoneTypeConverter.class)
	@Column(name = "phoneType", nullable = false)
	private PhoneTypeEnum phoneType;

	@NotNull
	@Size(max = 2)
	@Column(name = "ddd", nullable = false, length = 2)
	private String ddd;

	@NotNull
	@Size(max = 11)
	@Column(name = "number", nullable = false, length = 11)
	private String number;
}