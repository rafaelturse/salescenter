package br.com.salescenter.vo.user;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

import br.com.salescenter.contants.enumeration.GenderEnum;
import br.com.salescenter.contants.enumeration.PositionEnum;
import br.com.salescenter.vo.base.BaseFilterVO;
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
@RequestScoped
public class FilterPersonVO extends BaseFilterVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String lastName;
	private String rg;
	private String cpf;
	private GenderEnum gender;
	private String email;
	private String login;
	private PositionEnum position;
	private String employeeNumber;
}
