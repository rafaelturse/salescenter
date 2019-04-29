package br.com.salescenter.orm.compliment;

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
import javax.validation.constraints.Null;

import br.com.salescenter.contants.converter.SegmentConverter;
import br.com.salescenter.contants.enumeration.SegmentEnum;
import br.com.salescenter.orm.client.ClientORM;
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
@Table(name = "tb_segment")
public class SegmentORM implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Null
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client", nullable = true)
	private ClientORM client;

	@NotNull
	@Convert(converter = SegmentConverter.class)
	@Column(name = "segment", nullable = false)
	private SegmentEnum segment;
}