package br.com.salescenter.controller.dashboard;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import br.com.salescenter.controller.base.BaseCRUD;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "dashboardController")
@ManagedBean
@ViewScoped
public class DashboardController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	private DashboardModel model1;
	private DashboardModel model2;
	private DashboardModel model3;
	private DashboardModel modelSalesAmount;
	private DashboardModel modelPendingForecastCategory;

	@PostConstruct
	public void init() {
		model1();
		model2();
		model3();
	}
	
	public void model1(){
		model1 = new DefaultDashboardModel();
		DashboardColumn column1 = new DefaultDashboardColumn();
		DashboardColumn column2 = new DefaultDashboardColumn();
		DashboardColumn column3 = new DefaultDashboardColumn();

		column1.addWidget("genderPanel");
		column1.addWidget("businessTypePanel");
		column1.addWidget("wonXLostPanel");

		column2.addWidget("foreCastCategoryPanel");
		column2.addWidget("statusOpportunitiesPanel");
		column2.addWidget("reasonPanel");

		column3.addWidget("salesStagePanel");
		column3.addWidget("salesTypePanel");
		column3.addWidget("middleTiketPanel");

		model1.addColumn(column1);
		model1.addColumn(column2);
		model1.addColumn(column3);
	}
	
	public void model2(){
		model2 = new DefaultDashboardModel();
		
		DashboardColumn column1 = new DefaultDashboardColumn();
		
		column1.addWidget("sellerXSalesPanel");
		column1.addWidget("salesAmountPanel");
		column1.addWidget("pendingForecastCategoryPanel");

		model2.addColumn(column1);
	}
	
	public void model3(){
		model3 = new DefaultDashboardModel();
		DashboardColumn column1 = new DefaultDashboardColumn();

		column1.addWidget("sellerCompensationPanel");

		model3.addColumn(column1);
	}
}