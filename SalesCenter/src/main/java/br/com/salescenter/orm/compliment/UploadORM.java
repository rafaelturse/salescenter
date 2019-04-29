package br.com.salescenter.orm.compliment;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

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
@Table(name = "tb_upload")
public class UploadORM implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "inclusion", nullable = false)
	private LocalDate inclusion;

	@Null
	@Column(name = "id_opportunity", nullable = true)
	private Long opportunity;

	@NotNull
	@Column(name = "id_user", nullable = false)
	private Long user;

	@NotNull
	@Column(name = "size", nullable = false)
	private Long size;

	@NotNull
	@Size(max = 255)
	@Column(name = "fileName", nullable = false, length = 255)
	private String fileName;

	@NotNull
	@Column(name = "file", nullable = false, columnDefinition = "LONGBLOB")
	private byte[] file;
}