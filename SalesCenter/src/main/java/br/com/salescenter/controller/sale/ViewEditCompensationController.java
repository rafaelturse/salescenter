package br.com.salescenter.controller.sale;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.orm.sale.CompensationORM;
import br.com.salescenter.orm.user.PersonORM;
import br.com.salescenter.service.CompensationService;
import br.com.salescenter.service.OpportunityService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.MessageUtils;
import br.com.salescenter.utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Named(value = "viewEditCompensationController")
@ManagedBean
@ViewScoped
@Log
public class ViewEditCompensationController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CompensationORM orm;

	@Inject
	CompensationService compensationService;

	@Inject
	OpportunityService opportunityService;

	private BarChartModel sellerCompensationChart;
	private boolean renderedGoalView = true;
	private boolean renderedCommissionView = true;
	private PersonORM seller;
	private StatusEnum[] status = StatusEnum.values();

	private Double january = 0.0;
	private Double february = 0.0;
	private Double march = 0.0;
	private Double april = 0.0;
	private Double may = 0.0;
	private Double june = 0.0;
	private Double july = 0.0;
	private Double august = 0.0;
	private Double september = 0.0;
	private Double october = 0.0;
	private Double november = 0.0;
	private Double december = 0.0;

	private Double chartHeight = 0.0;

	@PostConstruct
	public void init() {
		startORM();
		loadSellerPoints();
		createModels();
	}

	private void loadSellerPoints() {
		january = opportunityService.sellerCompensation(orm.getYear(), 1, orm.getSeller().getId());
		february = opportunityService.sellerCompensation(orm.getYear(), 2, orm.getSeller().getId());
		march = opportunityService.sellerCompensation(orm.getYear(), 3, orm.getSeller().getId());
		april = opportunityService.sellerCompensation(orm.getYear(), 4, orm.getSeller().getId());
		may = opportunityService.sellerCompensation(orm.getYear(), 5, orm.getSeller().getId());
		june = opportunityService.sellerCompensation(orm.getYear(), 6, orm.getSeller().getId());
		july = opportunityService.sellerCompensation(orm.getYear(), 7, orm.getSeller().getId());
		august = opportunityService.sellerCompensation(orm.getYear(), 8, orm.getSeller().getId());
		september = opportunityService.sellerCompensation(orm.getYear(), 9, orm.getSeller().getId());
		october = opportunityService.sellerCompensation(orm.getYear(), 10, orm.getSeller().getId());
		november = opportunityService.sellerCompensation(orm.getYear(), 11, orm.getSeller().getId());
		december = opportunityService.sellerCompensation(orm.getYear(), 12, orm.getSeller().getId());

		chartHeight = january + february + march + april + may + june + july + august + september + october + november
				+ december;
	}

	private void startORM() {
		orm = compensationService.findById(SessionUtils.findByParameter("viewId"));
	}

	private void createModels() {
		setSellerCompensation();
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
		if (!setDefaultData()) { return false; } ;

		return true;
	}

	private boolean prepareToUpdate() {
		if (!setCompliments()) { return false; }

		return true;
	}

	public String cancel() {
		return "search-compensation";
	}

	public String update() {
		if (prepareToUpdate()) {
			compensationService.update(orm);

			MessageUtils.messageInfo(translateEnumValue("message.registerUpdated"));
		}

		return "search-compensation";
	}

	public void setSellerCompensation() {
		int amount = 0;
		Double finalSize = 0.0;
		sellerCompensationChart = initSellerCompensation();

		sellerCompensationChart.setLegendPosition("ne");
		sellerCompensationChart.setAnimate(true);

		amount = orm.getJanuary().intValue();
		amount += orm.getFebruary().intValue();
		amount += orm.getMarch().intValue();
		amount += orm.getApril().intValue();
		amount += orm.getMay().intValue();
		amount += orm.getJune().intValue();
		amount += orm.getJuly().intValue();
		amount += orm.getAugust().intValue();
		amount += orm.getSeptember().intValue();
		amount += orm.getOctober().intValue();
		amount += orm.getNovember().intValue();
		amount += orm.getDecember().intValue();

		if (chartHeight > amount) {
			finalSize = chartHeight;
		} else {
			finalSize = Double.valueOf(amount);
		}

		Axis yAxis = sellerCompensationChart.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(finalSize / 4);
	}

	private BarChartModel initSellerCompensation() {
		BarChartModel model = new BarChartModel();

		ChartSeries goalsSeries = new ChartSeries();
		goalsSeries.setLabel(translateEnumValue("chart.goal"));
		goalsSeries.set(translateEnumValue("chart.month.january"), orm.getJanuary());
		goalsSeries.set(translateEnumValue("chart.month.february"), orm.getFebruary());
		goalsSeries.set(translateEnumValue("chart.month.march"), orm.getMarch());
		goalsSeries.set(translateEnumValue("chart.month.april"), orm.getApril());
		goalsSeries.set(translateEnumValue("chart.month.may"), orm.getMay());
		goalsSeries.set(translateEnumValue("chart.month.june"), orm.getJune());
		goalsSeries.set(translateEnumValue("chart.month.july"), orm.getJuly());
		goalsSeries.set(translateEnumValue("chart.month.august"), orm.getAugust());
		goalsSeries.set(translateEnumValue("chart.month.september"), orm.getSeptember());
		goalsSeries.set(translateEnumValue("chart.month.october"), orm.getOctober());
		goalsSeries.set(translateEnumValue("chart.month.november"), orm.getNovember());
		goalsSeries.set(translateEnumValue("chart.month.december"), orm.getDecember());
		model.addSeries(goalsSeries);

		ChartSeries salerSalesSeries = new ChartSeries();
		salerSalesSeries.setLabel(translateEnumValue("chart.seller"));
		salerSalesSeries.set(translateEnumValue("chart.month.january"), january);
		salerSalesSeries.set(translateEnumValue("chart.month.february"), february);
		salerSalesSeries.set(translateEnumValue("chart.month.march"), march);
		salerSalesSeries.set(translateEnumValue("chart.month.april"), april);
		salerSalesSeries.set(translateEnumValue("chart.month.may"), may);
		salerSalesSeries.set(translateEnumValue("chart.month.june"), june);
		salerSalesSeries.set(translateEnumValue("chart.month.july"), july);
		salerSalesSeries.set(translateEnumValue("chart.month.august"), august);
		salerSalesSeries.set(translateEnumValue("chart.month.september"), september);
		salerSalesSeries.set(translateEnumValue("chart.month.october"), october);
		salerSalesSeries.set(translateEnumValue("chart.month.november"), november);
		salerSalesSeries.set(translateEnumValue("chart.month.december"), december);
		model.addSeries(salerSalesSeries);

		return model;
	}

	public Double commission(int month) {
		int percent = 5;

		switch (month) {
			case 1 :
				return january > orm.getJanuary() ? ((percent * january) / 100) : 0.0;
			case 2 :
				return february > orm.getFebruary() ? ((percent * february) / 100) : 0.0;
			case 3 :
				return march > orm.getMarch() ? ((percent * march) / 100) : 0.0;
			case 4 :
				return april > orm.getApril() ? ((percent * april) / 100) : 0.0;
			case 5 :
				return may > orm.getMay() ? ((percent * may) / 100) : 0.0;
			case 6 :
				return june > orm.getJune() ? ((percent * june) / 100) : 0.0;
			case 7 :
				return july > orm.getJuly() ? ((percent * july) / 100) : 0.0;
			case 8 :
				return august > orm.getAugust() ? ((percent * august) / 100) : 0.0;
			case 9 :
				return september > orm.getSeptember() ? ((percent * september) / 100) : 0.0;
			case 10 :
				return october > orm.getOctober() ? ((percent * october) / 100) : 0.0;
			case 11 :
				return november > orm.getNovember() ? ((percent * november) / 100) : 0.0;
			case 12 :
				return december > orm.getDecember() ? ((percent * december) / 100) : 0.0;
		}

		return 0.0;
	}
}