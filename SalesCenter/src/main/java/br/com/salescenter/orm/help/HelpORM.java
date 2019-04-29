package br.com.salescenter.orm.help;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.salescenter.contants.converter.StatusConverter;
import br.com.salescenter.contants.enumeration.StatusEnum;
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
@Table(name = "tb_help")
public class HelpORM implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "inclusion", nullable = false)
	private LocalDate inclusion;

	@NotNull
	@Column(name = "id_user", nullable = false)
	private Long user;

	@NotNull
	@Size(max = 255)
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@NotNull
	@Size(max = 8000)
	@Column(name = "description", nullable = false, length = 8000)
	private String description;

	@Convert(converter = StatusConverter.class)
	@Column(name = "status", nullable = false)
	private StatusEnum status;
}