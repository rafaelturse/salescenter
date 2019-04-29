package br.com.salescenter.orm.permission;

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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Null;

import br.com.salescenter.contants.converter.ScreenConverter;
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
@Table(name = "tb_permission", uniqueConstraints = {
		@UniqueConstraint(name = "uk_permission_userGroup", columnNames = {"id_userGRoup", "screen"})})
public class PermissionORM implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Null
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_userGroup", nullable = true)
	private UserGroupORM userGroup;

	@Null
	@Convert(converter = ScreenConverter.class)
	@Column(name = "screen", nullable = true)
	private ScreenEnum screen;

	@Null
	@Column(name = "action_create", nullable = true)
	private Boolean create = false;

	@Null
	@Column(name = "action_delete", nullable = true)
	private Boolean delete = false;

	@Null
	@Column(name = "action_read", nullable = true)
	private Boolean read = false;

	@Null
	@Column(name = "action_update", nullable = true)
	private Boolean update = false;

	@Null
	@Column(name = "action_enable", nullable = true)
	private Boolean enable = false;

	@Null
	@Column(name = "action_disable", nullable = true)
	private Boolean disable = false;

	public PermissionORM(final UserGroupORM userGroup, final ScreenEnum screen) {
		this.userGroup = userGroup;
		this.screen = screen;
	}
}
