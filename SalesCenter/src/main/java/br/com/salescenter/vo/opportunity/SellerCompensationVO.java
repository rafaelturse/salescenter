package br.com.salescenter.vo.opportunity;

import java.io.Serializable;

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
public class SellerCompensationVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String seller;
	private String amount;
	private int month;
}