package br.com.salescenter.orm.permission;

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

import br.com.salescenter.contants.converter.ActionConverter;
import br.com.salescenter.contants.converter.ScreenConverter;
import br.com.salescenter.contants.enumeration.ActionEnum;
import br.com.salescenter.contants.enumeration.ScreenEnum;
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
@Table(name = "tb_log")
public class LogORM implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "inclusion", nullable = false)
	private LocalDate inclusion;

	@NotNull
	@Convert(converter = ScreenConverter.class)
	@Column(name = "screen", nullable = false)
	private ScreenEnum screen;

	@NotNull
	@Column(name = "reference", nullable = false)
	private Long reference;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "field", length = 255, nullable = false)
	private String field;

	@NotNull
	@Column(name = "id_user", nullable = false)
	private Long user;

	@NotNull
	@Convert(converter = ActionConverter.class)
	@Column(name = "action", nullable = false)
	private ActionEnum action;

	@NotNull
	@Size(max = 255)
	@Column(name = "oldValue", length = 255, nullable = false)
	private String oldValue;

	@NotNull
	@Size(max = 255)
	@Column(name = "newValue", length = 255, nullable = false)
	private String newValue;
}
