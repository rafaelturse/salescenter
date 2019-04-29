package br.com.salescenter.controller.permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import br.com.salescenter.contants.enumeration.ScreenEnum;
import br.com.salescenter.contants.enumeration.StatusEnum;
import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.orm.permission.PermissionORM;
import br.com.salescenter.orm.permission.UserGroupORM;
import br.com.salescenter.service.UserGroupService;
import br.com.salescenter.utils.DateTimeUtils;
import br.com.salescenter.utils.MessageUtils;
import br.com.salescenter.utils.SessionUtils;
import br.com.salescenter.utils.ValidationUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Named(value = "viewEditUserGroupController")
@ManagedBean
@ViewScoped
@Log
public class ViewEditUserGroupController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UserGroupORM orm;

	@Inject
	PermissionController permissionController;

	@Inject
	UserGroupService userGroupService;

	private StatusEnum[] status = StatusEnum.values();
	private TreeNode[] selectedNodes;
	private TreeNode root;

	@PostConstruct
	public void init() {
		orm = userGroupService.findById(SessionUtils.findByParameter("viewId"));

		permissionController.setPermissions(orm.getPermissions());
		createTree();
	}

	private void createTree() {
		List<ScreenEnum> screens = new ArrayList<ScreenEnum>();
		root = new CheckboxTreeNode("Root", null);

		for (PermissionORM i : permissionController.getPermissions()) {
			screens.add(i.getScreen());
		}

		for (ScreenEnum i : ScreenEnum.values()) {
			root.getChildren().add(new CheckboxTreeNode(i, root));

			if (screens.contains(i)) {
				root.getChildren().get(root.getChildCount() - 1).setSelected(true);
			}
		}
	}

	private boolean setScreens() {
		try {
			for (PermissionORM i : permissionController.getPermissions()) {
				i.setUserGroup(orm);
			}

			orm.setPermissions(permissionController.getPermissions());
			return true;
		} catch (Exception e) {
			log.severe("Error on method setScreens");
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
		if (!setDefaultData()) { return false; } ;
		if (!setScreens()) { return false; } ;

		return true;
	}

	private boolean validate() {
		if (!ValidationUtils.existsUserGroupName(userGroupService, orm.getId(), orm.getName())) { return false; }

		return true;
	}

	private boolean prepareToUpdate() {
		if ((!validate()) || (!setCompliments())) { return false; }

		return true;
	}

	public String cancel() {
		return "search-user-group";
	}

	public String update() {
		if (prepareToUpdate()) {
			userGroupService.update(orm);

			MessageUtils.messageInfo(translateEnumValue("message.registerUpdated"));
		}

		return "search-user-group";
	}
}