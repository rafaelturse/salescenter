package br.com.salescenter.orm.user;

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

import br.com.salescenter.contants.converter.PositionConverter;
import br.com.salescenter.contants.converter.StatusConverter;
import br.com.salescenter.contants.enumeration.PositionEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.orm.permission.UserGroupORM;
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
@Table(name = "tb_user")
public class UserORM implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_userGroup", nullable = false)
	private UserGroupORM userGroup;

	@NotNull
	@Size(max = 20)
	@Column(name = "login", nullable = false, length = 20)
	private String login;

	@NotNull
	@Size(max = 20)
	@Column(name = "password", nullable = false, length = 20)
	private String password;
	
	@Convert(converter = PositionConverter.class)
	@Column(name = "position", nullable = false)
	private PositionEnum position;

	@NotNull
	@Size(max = 6)
	@Column(name = "employeeNumber", nullable = false, length = 6)
	private String employeeNumber;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "theme", nullable = false, length = 50)
	private String theme;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "language", nullable = false, length = 50)
	private String language;
	
	@Convert(converter = StatusConverter.class)
	@Column(name = "status", nullable = false)
	private StatusEnum status;
}
