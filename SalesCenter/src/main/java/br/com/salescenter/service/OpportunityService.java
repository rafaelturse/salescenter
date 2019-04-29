package br.com.salescenter.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.salescenter.contants.enumeration.BusinessTypeEnum;
import br.com.salescenter.contants.enumeration.ForecastCategoryEnum;
import br.com.salescenter.contants.enumeration.ReasonEnum;
import br.com.salescenter.contants.enumeration.SalesStageEnum;
import br.com.salescenter.contants.enumeration.SalesTypeEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.dao.OpportunityDAO;
import br.com.salescenter.orm.opportunity.OpportunityORM;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.SessionUtils;
import br.com.salescenter.vo.opportunity.FilterOpportunityVO;
import br.com.salescenter.vo.opportunity.OpportunityVO;
import br.com.salescenter.vo.opportunity.PendingForecastCategoryVO;
import br.com.salescenter.vo.opportunity.SalesAmountVO;
import br.com.salescenter.vo.opportunity.SellerCompensationVO;
import br.com.salescenter.vo.opportunity.SellerXSalesVO;

@RequestScoped
public class OpportunityService {

	@Inject
	private OpportunityDAO opportunityDAO;

	public void save(final OpportunityORM orm) {
		opportunityDAO.save(orm);
	}

	public void update(final OpportunityORM orm) {
		opportunityDAO.update(orm);
	}

	public OpportunityORM findById(final long id) {
		return opportunityDAO.findById(id);
	}

	public int rowFiltered() {
		return opportunityDAO.rowFiltered();
	}

	public List<OpportunityVO> filtered(final FilterOpportunityVO filter) {
		return opportunityDAO.filtered(filter);
	}

	public Long amountBusinessType(final BusinessTypeEnum businessType) {
		return opportunityDAO.amountBusinessType(businessType);
	}

	public Long amountForecastCategory(final ForecastCategoryEnum forecastCategory) {
		return opportunityDAO.amountForecastCategory(forecastCategory);
	}

	public Long amountReason(final ReasonEnum reason) {
		return opportunityDAO.amountReason(reason);
	}

	public Long amountSalesStage(final SalesStageEnum salesStage) {
		return opportunityDAO.amountSalesStage(salesStage);
	}

	public Long amountSalesType(final SalesTypeEnum salesType) {
		return opportunityDAO.amountSalesType(salesType);
	}

	public Long amountStatusOpportunities(final StatusEnum status) {
		return opportunityDAO.amountStatusOpportunities(status);
	}

	public Long amountWonXLost(final ForecastCategoryEnum forecastCategory) {
		return opportunityDAO.amountWonXLost(forecastCategory);
	}

	public Long middleTiket(final ForecastCategoryEnum forecastCategory) {
		return opportunityDAO.middleTiket(forecastCategory);
	}

	public List<SellerXSalesVO> sellerXSales(final int year) {
		return opportunityDAO.sellerXSales(year);
	}

	public List<SalesAmountVO> salesAmount(final int year) {
		return opportunityDAO.salesAmount(year);
	}

	public List<PendingForecastCategoryVO> pendingForecastCategory() {
		return opportunityDAO.pendingForecastCategory();
	}

	public Double sellerCompensation(final int year, final int month, final Long idPerson) {
		List<SellerCompensationVO> i = opportunityDAO.sellerCompensation(year, month, idPerson);

		if (i.size() <= 0) { return 0.0; }

		String amount = i.get(0).getAmount();

		if (amount.isEmpty()) {
			return 0.0;
		} else {
			return Double.valueOf(amount);
		}
	}

	public String getHistoryHead() {
		StringBuilder head = new StringBuilder();

		head.append("");
		head.append(SessionUtils.recoverAuthenticatedUser().getName());
		head.append("");
		head.append(SessionUtils.recoverAuthenticatedUser().getLastName());
		head.append(" - ");
		head.append(DateTimeUtils.now());
		head.append(": ");

		return head.toString();
	}
}
