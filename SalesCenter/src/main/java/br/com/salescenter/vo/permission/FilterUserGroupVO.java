package br.com.salescenter.vo.permission;

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
public class FilterUserGroupVO extends BaseFilterVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
}
