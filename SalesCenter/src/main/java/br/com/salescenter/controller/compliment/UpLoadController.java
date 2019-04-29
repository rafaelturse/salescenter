package br.com.salescenter.controller.compliment;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.orm.compliment.UploadORM;
import br.com.salescenter.service.UploadService;
import br.com.salescenter.utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Named(value = "upLoadController")
@ManagedBean
@ViewScoped
@Log
public class UpLoadController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UploadORM orm;

	@Inject
	UploadService upLoadService;

	private List<UploadORM> opportunityFiles;
	private List<UploadedFile> uploadedFiles;
	private UploadedFile file;

	@PostConstruct
	public void init() {
		uploadedFiles = new ArrayList<UploadedFile>();
	}

	public void upload(FileUploadEvent event) {
		 uploadedFiles.add(event.getFile());

		try {
			orm.setOpportunity(SessionUtils.recoverHashNumber());
			orm.setFile(IOUtils.toByteArray(event.getFile().getInputstream()));
		} catch (IOException e) {
			log.severe("Error on method upload");
			e.printStackTrace();
		}

		upLoadService.upLoad(orm);

		orm = new UploadORM();
	}
}
