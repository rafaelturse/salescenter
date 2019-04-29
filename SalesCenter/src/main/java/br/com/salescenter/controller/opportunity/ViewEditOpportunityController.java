package br.com.salescenter.controller.opportunity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.salescenter.contants.enumeration.BusinessTypeEnum;
import br.com.salescenter.contants.enumeration.ForecastCategoryEnum;
import br.com.salescenter.contants.enumeration.ReasonEnum;
import br.com.salescenter.contants.enumeration.SalesStageEnum;
import br.com.salescenter.contants.enumeration.SalesTypeEnum;
import br.com.salescenter.contants.enumeration.ScreenEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.controller.compliment.CompetitorController;
import br.com.salescenter.controller.compliment.InvolvedTeamController;
import br.com.salescenter.controller.compliment.ProductController;
import br.com.salescenter.controller.compliment.UpLoadController;
import br.com.salescenter.orm.client.ClientORM;
import br.com.salescenter.orm.compliment.CompetitorORM;
import br.com.salescenter.orm.compliment.InvolvedTeamORM;
import br.com.salescenter.orm.compliment.ProductORM;
import br.com.salescenter.orm.compliment.UploadORM;
import br.com.salescenter.orm.opportunity.OpportunityORM;
import br.com.salescenter.orm.permission.LogORM;
import br.com.salescenter.orm.user.PersonORM;
import br.com.salescenter.service.LogService;
import br.com.salescenter.service.OpportunityService;
import br.com.salescenter.service.UploadService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.MessageUtils;
import br.com.salescenter.utils.SessionUtils;
import br.com.salescenter.utils.TextUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Named(value = "viewEditOpportunityController")
@ManagedBean
@ViewScoped
@Log
public class ViewEditOpportunityController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UploadORM upLoadORM;

	@Inject
	CompetitorController competitorController;

	@Inject
	InvolvedTeamController involvedTeamController;

	@Inject
	ProductController productController;

	@Inject
	UpLoadController upLoadController;

	@Inject
	OpportunityService opportunityService;

	@Inject
	UploadService uploadService;

	@Inject
	LogService logService;

	private OpportunityORM orm;
	private SalesTypeEnum mirrorSalesType;
	private Double mirrorAmount;

	private BusinessTypeEnum[] bussinessTypes = BusinessTypeEnum.values();
	private ForecastCategoryEnum[] forecastCategories = ForecastCategoryEnum.values();
	private ReasonEnum[] reasons = ReasonEnum.values();
	private SalesStageEnum[] salesStages = SalesStageEnum.values();
	private SalesTypeEnum[] salesTypes = SalesTypeEnum.values();
	private StatusEnum[] status = StatusEnum.values();

	private boolean renderedCompetitorView = true;
	private boolean renderedInvolvedView = true;
	private boolean renderedProductView = true;
	private ClientORM client;
	private PersonORM involved;
	private PersonORM seller;
	private String currentHistory;
	private String history;

	private List<UploadORM> opportunityFiles;
	private List<UploadedFile> uploadedFiles;
	private UploadedFile file;
	private StreamedContent downloadFile;

	@PostConstruct
	public void init() {
		startORM();
		startHistory();
		findUploadedFiles();

		setORMs();

		uploadedFiles = new ArrayList<UploadedFile>();
	}

	private void startORM() {
		orm = opportunityService.findById(SessionUtils.findByParameter("viewId"));

		mirrorAmount = orm.getAmount();
		mirrorSalesType = orm.getSalesType();
	}

	private void startHistory() {
		history = orm.getHistory();
	}

	public void findUploadedFiles() {
		opportunityFiles = uploadService.findUploadedFiles(orm.getId());
	}

	private void setORMs() {
		competitorController.setOrms(orm.getCompetitors());
		involvedTeamController.setOrms(orm.getInvolveds());
		productController.setOrms(orm.getProducts());
	}

	private boolean setCompetitors() {
		try {
			for (CompetitorORM i : competitorController.getOrms()) {
				i.setOpportunity(orm);
			}

			orm.setCompetitors(competitorController.getOrms());
			return true;
		} catch (Exception e) {
			log.severe("Error on method setCompetitors");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setInvolveds() {
		try {
			for (InvolvedTeamORM i : involvedTeamController.getOrms()) {
				i.setOpportunity(orm);
			}

			orm.setInvolveds(involvedTeamController.getOrms());
			return true;
		} catch (Exception e) {
			log.severe("Error on method setInvolveds");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setProducts() {
		try {
			for (ProductORM i : productController.getOrms()) {
				i.setOpportunity(orm);
			}

			orm.setProducts(productController.getOrms());
			return true;
		} catch (Exception e) {
			log.severe("Error on method setProducts");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setDefaultData() {
		try {
			orm.setLastUpdate(DateTimeUtils.today());

			return true;
		} catch (Exception e) {
			log.severe("Error on method setDefaultData");
			e.printStackTrace();
		}

		return false;
	}

	private boolean setCompliments() {
		if (!setCompetitors()) { return false; } ;
		if (!setInvolveds()) { return false; } ;
		if (!setProducts()) { return false; } ;
		if (!setDefaultData()) { return false; } ;

		return true;
	}

	private boolean prepareToUpdate() {
		if (!setCompliments()) { return false; }

		return true;
	}

	private void doLog() {
		if (!mirrorAmount.equals(orm.getAmount())) {
			updateLog(ScreenEnum.OPPORTUNITY, orm.getId(), "AMOUNT", mirrorAmount.toString(),
					orm.getAmount().toString());
		}

		if (!mirrorSalesType.equals(orm.getSalesType())) {
			updateLog(ScreenEnum.OPPORTUNITY, orm.getId(), "SALES TYPE", mirrorSalesType.toString(),
					orm.getSalesType().toString());
		}
	}

	public String cancel() {
		return "search-opportunity";
	}

	public String closeDateFormated() {
		return DateTimeUtils.formatDate(orm.getCloseDate());
	}

	public void storeHistory() {
		StringBuilder sbHistory = new StringBuilder();

		sbHistory.append(history);
		sbHistory.append(opportunityService.getHistoryHead());
		sbHistory.append(currentHistory);
		sbHistory.append(" || ");

		orm.setHistory(sbHistory.toString());
	}

	public String update() {
		if (prepareToUpdate()) {
			opportunityService.update(orm);

			doLog();

			MessageUtils.messageInfo(translateEnumValue("message.registerUpdated"));
		}

		return "search-opportunity";
	}

	public void remove(final long id) {
		uploadService.removeById(id);

		findUploadedFiles();
	}

	public void download(final long id) {
		byte[] bytes;
		InputStream stream;
		String contentType;
		UploadORM download;

		download = uploadService.findById(id);

		contentType = TextUtils.fileName(download.getFileName()) + "/"
				+ TextUtils.fileExtension(download.getFileName());

		bytes = download.getFile();
		bytes.getClass().getResourceAsStream("file");

		stream = new ByteArrayInputStream(bytes);

		downloadFile = new DefaultStreamedContent(stream, contentType, download.getFileName());
	}

	public void upload(FileUploadEvent event) {
		try {
			upLoadORM.setOpportunity(orm.getId());
			upLoadORM.setUser(SessionUtils.recoverAuthenticatedUser().getId());
			upLoadORM.setInclusion(DateTimeUtils.today());
			upLoadORM.setFileName(event.getFile().getFileName());
			upLoadORM.setSize(event.getFile().getSize());
			upLoadORM.setFile(IOUtils.toByteArray(event.getFile().getInputstream()));
		} catch (IOException e) {
			MessageUtils.messageError(translateEnumValue("message.upload.fail"));
			log.severe("Error on method upload");
			e.printStackTrace();
		}

		uploadService.upLoad(upLoadORM);

		upLoadORM = new UploadORM();

		findUploadedFiles();
	}

	public List<LogORM> logs() {
		return logService.listAll(ScreenEnum.OPPORTUNITY, orm.getId());
	}
}