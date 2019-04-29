package br.com.salescenter.controller.permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;

import br.com.salescenter.contants.enumeration.ScreenEnum;
import br.com.salescenter.orm.permission.PermissionORM;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Named(value = "permissionController")
@ViewScoped
@Log
public class PermissionController implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<PermissionORM> permissions;

	@PostConstruct
	public void init() {
		permissions = new ArrayList<PermissionORM>();
	}

	public void onNodeSelect(NodeSelectEvent event) {
		try {
			permissions.add(new PermissionORM(null, null, (ScreenEnum) event.getTreeNode().getData(), false, false,
					false, false, false, false));
		} catch (Exception e) {
			log.severe("Error on method onNodeSelect");
			e.printStackTrace();
		}
	}

	public void onNodeUnSelect(NodeUnselectEvent event) {
		try {
			for (PermissionORM i : permissions) {
				if (i.getScreen().equals((ScreenEnum) event.getTreeNode().getData())) {
					permissions.remove(i);
				}
			}
		} catch (Exception e) {
			log.severe("Error on method onNodeUnSelect");
			e.printStackTrace();
		}
	}
}
