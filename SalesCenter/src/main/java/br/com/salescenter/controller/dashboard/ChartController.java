package br.com.salescenter.controller.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import br.com.salescenter.contants.enumeration.BusinessTypeEnum;
import br.com.salescenter.contants.enumeration.ForecastCategoryEnum;
import br.com.salescenter.contants.enumeration.GenderEnum;
import br.com.salescenter.contants.enumeration.ReasonEnum;
import br.com.salescenter.contants.enumeration.SalesStageEnum;
import br.com.salescenter.contants.enumeration.SalesTypeEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.service.OpportunityService;
import br.com.salescenter.service.PersonService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.vo.opportunity.PendingForecastCategoryVO;
import br.com.salescenter.vo.opportunity.SalesAmountVO;
import br.com.salescenter.vo.opportunity.SellerXSalesVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "chartController")
@ManagedBean
@ViewScoped
public class ChartController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	OpportunityService opportunityService;

	@Inject
	PersonService personService;

	int year = DateTimeUtils.currentYear();
	int yearSalesAmount = DateTimeUtils.currentYear();

	// bar - opportunity
	private BarChartModel salesAmountChart;
	private BarChartModel sellerXSalesChart;
	// bubble - opportunity
	private BubbleChartModel pendingForecastCategoryChart = new BubbleChartModel();
	// Pie - user
	private PieChartModel genderChart = new PieChartModel();
	// Pie - opportunity
	private PieChartModel businessTypeChart = new PieChartModel();
	private PieChartModel forecastCategoryChart = new PieChartModel();
	private PieChartModel middleTiketChart = new PieChartModel();
	private PieChartModel reasonChart = new PieChartModel();
	private PieChartModel salesTypeChart = new PieChartModel();
	private PieChartModel salesStageChart = new PieChartModel();
	private PieChartModel statusOpportunitiesChart = new PieChartModel();
	private PieChartModel wonXLostChart = new PieChartModel();

	@PostConstruct
	public void init() {
		createPieModels();
		createBarModels();
		createBubbleModels();
	}

	private void createPieModels() {
		setGenderChart();
		setBusinessTypeChart();
		setForecastCategoryChart();
		setReasonChart();
		setSalesStageChart();
		setSalesTypeChart();
		setStatusOpportunitiesChart();
		setWonXLost();
		setMiddleTiket();
	}

	private void createBarModels() {
		setSalesAmount();
		setSellerXSales();
	}

	private void createBubbleModels() {
		setPendingForecastCategory();
	}

	private void pieCharConfig(PieChartModel pieChart, String position, boolean fill, boolean labels, int diameter,
			boolean shadow) {
		pieChart.setLegendPosition(position);
		pieChart.setFill(fill);
		pieChart.setShowDataLabels(labels);
		pieChart.setDiameter(diameter);
		pieChart.setShadow(shadow);
	}

	private void setGenderChart() {
		pieCharConfig(genderChart, "e", true, true, 230, true);

		for (GenderEnum i : GenderEnum.values()) {
			genderChart.set(translateEnumValue(i.getDescription()), personService.amountPersonByGender(i));
		}
	}

	private void setBusinessTypeChart() {
		pieCharConfig(businessTypeChart, "e", true, true, 230, true);

		for (BusinessTypeEnum i : BusinessTypeEnum.values()) {
			businessTypeChart.set(translateEnumValue(i.getDescription()), opportunityService.amountBusinessType(i));
		}
	}

	private void setForecastCategoryChart() {
		pieCharConfig(forecastCategoryChart, "e", true, true, 230, true);

		for (ForecastCategoryEnum i : ForecastCategoryEnum.values()) {
			forecastCategoryChart.set(translateEnumValue(i.getDescription()),
					opportunityService.amountForecastCategory(i));
		}
	}

	private void setReasonChart() {
		pieCharConfig(reasonChart, "s", true, true, 140, true);

		reasonChart.setLegendCols(2);

		for (ReasonEnum i : ReasonEnum.values()) {
			reasonChart.set(translateEnumValue(i.getDescription()), opportunityService.amountReason(i));
		}
	}

	private void setSalesStageChart() {
		pieCharConfig(salesStageChart, "e", true, true, 150, true);

		for (SalesStageEnum i : SalesStageEnum.values()) {
			salesStageChart.set(translateEnumValue(i.getDescription()), opportunityService.amountSalesStage(i));
		}
	}

	private void setSalesTypeChart() {
		pieCharConfig(salesTypeChart, "e", true, true, 230, true);

		for (SalesTypeEnum i : SalesTypeEnum.values()) {
			salesTypeChart.set(translateEnumValue(i.getDescription()), opportunityService.amountSalesType(i));
		}
	}

	private void setStatusOpportunitiesChart() {
		pieCharConfig(statusOpportunitiesChart, "e", true, true, 230, true);

		for (StatusEnum i : StatusEnum.values()) {
			statusOpportunitiesChart.set(translateEnumValue(i.getDescription()),
					opportunityService.amountStatusOpportunities(i));
		}
	}

	private void setWonXLost() {
		pieCharConfig(wonXLostChart, "e", true, true, 230, true);

		wonXLostChart.set(translateEnumValue(ForecastCategoryEnum.WON.getDescription()),
				opportunityService.amountWonXLost(ForecastCategoryEnum.WON));
		wonXLostChart.set(translateEnumValue(ForecastCategoryEnum.OMMITED.getDescription()),
				opportunityService.amountWonXLost(ForecastCategoryEnum.OMMITED));
	}

	private void setMiddleTiket() {
		int notWon = 0;
		
		pieCharConfig(middleTiketChart, "e", true, true, 230, true);
		
		for (ForecastCategoryEnum i : ForecastCategoryEnum.values()) {
			if (ForecastCategoryEnum.WON != i){
				notWon += opportunityService.middleTiket(i);				
			}
		}

		middleTiketChart.set(translateEnumValue("label.other"), notWon);
		middleTiketChart.set(translateEnumValue(ForecastCategoryEnum.WON.getDescription()), opportunityService.amountWonXLost(ForecastCategoryEnum.WON));
	}

	public void setSalesAmount() {
		int y = 0;

		List<SalesAmountVO> salesAmount = opportunityService.salesAmount(yearSalesAmount);
		salesAmountChart = initSalesAmountModel(salesAmount);
		salesAmountChart.setAnimate(true);

		for (SalesAmountVO i : salesAmount) {
			int value = 0;

			if (i.getAmount().contains(".")) {
				value = Integer.valueOf(i.getAmount().substring(0, i.getAmount().lastIndexOf(".")));
			} else {
				value = Integer.valueOf(i.getAmount());
			}

			if (value > y) {
				y = value;
			}
		}

		Axis yAxis = salesAmountChart.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(y + ((y * 20) / 100));
	}

	private BarChartModel initSalesAmountModel(final List<SalesAmountVO> salesAmount) {
		BarChartModel model = new BarChartModel();
		ChartSeries series = new ChartSeries();

		for (SalesAmountVO i : salesAmount) {
			series.set(i.getMonth(), Double.valueOf(i.getAmount()));
		}

		model.addSeries(series);
		series = new ChartSeries();

		return model;
	}

	public void setSellerXSales() {
		int y = 0;

		List<SellerXSalesVO> sellersXSales = opportunityService.sellerXSales(year);
		sellerXSalesChart = initSellerXSalesModel(sellersXSales);

		sellerXSalesChart.setLegendPosition("ne");
		sellerXSalesChart.setAnimate(true);

		for (SellerXSalesVO i : sellersXSales) {
			int value = 0;

			if (i.getAmount().contains(".")) {
				value = Integer.valueOf(i.getAmount().substring(0, i.getAmount().lastIndexOf(".")));
			} else {
				value = Integer.valueOf(i.getAmount());
			}

			if (value > y) {
				y = value;
			}
		}

		Axis yAxis = sellerXSalesChart.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(y + ((y * 20) / 100));
	}

	private BarChartModel initSellerXSalesModel(final List<SellerXSalesVO> sellersXSales) {
		BarChartModel model = new BarChartModel();
		List<String> uniqueSellers = new ArrayList<String>();
		ChartSeries series = new ChartSeries();

		for (SellerXSalesVO i : sellersXSales) {

			if (!uniqueSellers.contains(i.getSeller())) {
				uniqueSellers.add(i.getSeller());
			}
		}

		for (String uniqueSeller : uniqueSellers) {
			series.setLabel(uniqueSeller);

			for (SellerXSalesVO sellerXSales : sellersXSales) {
				if (sellerXSales.getSeller().equals(uniqueSeller)) {
					series.set(sellerXSales.getMonth(), Double.valueOf(sellerXSales.getAmount()));
				}
			}

			model.addSeries(series);
			series = new ChartSeries();
		}

		return model;
	}

	public BubbleChartModel setPendingForecastCategory() {
		BubbleChartModel model = new BubbleChartModel();

		List<PendingForecastCategoryVO> pendingForecastCategory = opportunityService.pendingForecastCategory();
		pendingForecastCategoryChart = initPendingForecastCategoryModel(pendingForecastCategory);

		model.getAxis(AxisType.X).setLabel("QTD");
		Axis yAxis = model.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(1000);
		yAxis.setLabel("Pending Days");

		return model;
	}

	private BubbleChartModel initPendingForecastCategoryModel(
			final List<PendingForecastCategoryVO> pendingForecastCategory) {
		BubbleChartModel model = new BubbleChartModel();

		for (PendingForecastCategoryVO i : pendingForecastCategory) {
			model.add(new BubbleChartSeries(translateEnumValue(i.getForecastCategory().getDescription()), i.getQtd(),
					i.getPending(), i.getSize()));
		}

		return model;
	}
}