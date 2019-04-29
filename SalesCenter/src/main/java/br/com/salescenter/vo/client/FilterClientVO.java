package br.com.salescenter.vo.client;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

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
public class FilterClientVO extends BaseFilterVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String social;
	private String fantasy;
	private String cnpj;
	private String segment;
	private String stateRegistration;
	private String site;
}
