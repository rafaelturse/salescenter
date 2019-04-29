package br.com.salescenter.controller.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class IndexManagedBean {

	private StreamedContent imagemEnviada = new DefaultStreamedContent();
	private String imagemTemporaria;
	private CroppedImage croppedImage;
	private boolean exibeBotao = false;

	public void criaArquivo(byte[] bytes, String arquivo) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(arquivo);
			fos.write(bytes);
			fos.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(IndexManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(IndexManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void enviarImagem(FileUploadEvent event) {
		byte[] img = event.getFile().getContents();
		imagemTemporaria = event.getFile().getFileName();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
		String arquivo = scontext
				.getRealPath(File.separator + "resources" + File.separator + "img" + File.separator + imagemTemporaria);
		criaArquivo(img, arquivo);
		setExibeBotao(true);
	}

	public void crop() {
		// setImagemEnviada(new DefaultStreamedContent(new
		// ByteArrayInputStream(croppedImage.getBytes())));
	}
}