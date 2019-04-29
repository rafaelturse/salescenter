package br.com.salescenter.orm.permission;

import java.io.Serializable;
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
import br.com.salescenter.orm.user.UserORM;
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
@Table(name = "tb_userGroup",
		uniqueConstraints = {@UniqueConstraint(name = "uk_userGroup_name", columnNames = {"name"})})
public class UserGroupORM extends BaseORM implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userGroup", orphanRemoval = true)
	private List<PermissionORM> permissions = new ArrayList<PermissionORM>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userGroup", orphanRemoval = true)
	private List<UserORM> users = new ArrayList<UserORM>();

	@NotNull
	@Size(max = 50)
	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Convert(converter = StatusConverter.class)
	@Column(name = "status", nullable = false)
	private StatusEnum status;
}
