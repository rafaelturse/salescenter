package br.com.salescenter.controller.opportunity;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
import br.com.salescenter.orm.client.ClientORM;
import br.com.salescenter.orm.compliment.CompetitorORM;
import br.com.salescenter.orm.compliment.InvolvedTeamORM;
import br.com.salescenter.orm.compliment.ProductORM;
import br.com.salescenter.orm.opportunity.OpportunityORM;
import br.com.salescenter.orm.user.PersonORM;
import br.com.salescenter.service.OpportunityService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.MessageUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Named(value = "insertOpportunityController")
@RequestScoped
@Log
public class InsertOpportunityController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String SEARCH = "search-opportunity";

	@Inject
	OpportunityORM orm;

	@Inject
	CompetitorController competitorController;

	@Inject
	InvolvedTeamController involvedTeamController;

	@Inject
	ProductController productController;

	@Inject
	OpportunityService opportunityService;

	private BusinessTypeEnum[] bussinessTypes = BusinessTypeEnum.values();
	private ForecastCategoryEnum[] forecastCategories = ForecastCategoryEnum.values();
	private ReasonEnum[] reasons = ReasonEnum.values();
	private SalesStageEnum[] salesStages = SalesStageEnum.values();
	private SalesTypeEnum[] salesTypes = SalesTypeEnum.values();
	private StatusEnum[] status = StatusEnum.values();

	private ClientORM client;
	private PersonORM involved;
	private PersonORM seller;

	@PostConstruct
	public void init() {
		orm.setClient(new ClientORM());
		orm.setSeller(new PersonORM());

		orm.setStatus(StatusEnum.ATIVO);
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
			orm.setClient(client);
			orm.setSeller(seller);

			orm.setInclusion(DateTimeUtils.today());
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

	private boolean prepareToSave() {
		prepareHistory();
		if (!setCompliments()) { return false; }

		return true;
	}

	private void prepareHistory() {
		orm.setHistory(opportunityService.getHistoryHead().concat(" ").concat(orm.getHistory().concat(" || ")));
	}

	public String cancel() {
		return SEARCH;
	}

	private void reinitFields() {
		orm = new OpportunityORM();
		competitorController.init();
		involvedTeamController.init();
		productController.init();

		client = new ClientORM();
		involved = new PersonORM();
		seller = new PersonORM();
	}

	public boolean save() {
		if (prepareToSave()) {
			opportunityService.save(orm);
			createLog(ScreenEnum.OPPORTUNITY, orm.getId());

			MessageUtils.messageInfo(translateEnumValue("message.registerCreated"));

			reinitFields();

			return true;
		}

		return false;
	}
}
