package br.com.salescenter.vo.compensation;

import java.io.Serializable;

import br.com.salescenter.orm.user.PersonORM;
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
public class CompensationVO extends BaseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private PersonORM seller;
}
