package br.com.salescenter.vo.user;

import java.io.Serializable;

import br.com.salescenter.contants.enumeration.GenderEnum;
import br.com.salescenter.orm.user.UserORM;
import br.com.salescenter.vo.base.BaseVO;
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
public class PersonVO extends BaseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String lastName;
	private String rg;
	private String cpf;
	private GenderEnum gender;
	private String email;
	private UserORM user;
}
