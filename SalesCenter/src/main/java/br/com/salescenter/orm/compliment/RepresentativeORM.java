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
import javax.validation.constraints.Size;

import br.com.salescenter.contants.converter.GenderConverter;
import br.com.salescenter.contants.enumeration.GenderEnum;
import br.com.salescenter.orm.client.ClientORM;
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
@Table(name = "tb_contact")
public class RepresentativeORM implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client", nullable = false)
	private ClientORM client;

	@NotNull
	@Size(max = 50)
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@NotNull
	@Size(max = 100)
	@Column(name = "lastName", nullable = false, length = 100)
	private String lastName;
	
	@NotNull
	@Convert(converter = GenderConverter.class)
	@Column(name = "gender", nullable = false)
	private GenderEnum gender;
	
	@NotNull
	@Size(max = 11)
	@Column(name = "cpf", nullable = false, length = 11)
	private String cpf;
	
	@NotNull
	@Size(max = 100)
	@Column(name = "position", nullable = false, length = 100)
	private String position;
}
