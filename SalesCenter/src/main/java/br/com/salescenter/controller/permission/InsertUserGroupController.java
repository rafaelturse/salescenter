package br.com.salescenter.controller.permission;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
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
import br.com.salescenter.utils.ValidationUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Named(value = "insertUserGroupController")
@RequestScoped
@Log
public class InsertUserGroupController extends BaseCRUD implements Serializable {

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
		createTree();
	}

	private void createTree() {
		root = new CheckboxTreeNode("Root", null);

		for (ScreenEnum i : ScreenEnum.values()) {
			root.getChildren().add(new CheckboxTreeNode(i, root));
		}
	}

	private boolean validate() {
		if (!ValidationUtils.existsUserGroupName(userGroupService, orm.getName())) { return false; }

		return true;
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
		if (!setDefaultData()) { return false; } ;
		if (!setScreens()) { return false; } ;

		return true;
	}

	private boolean prepareToSave() {
		if ((!validate()) || (!setCompliments())) { return false; }

		return true;
	}

	private void reinitFields() {
		orm = new UserGroupORM();
	}

	public List<UserGroupORM> searchUserGroup(String value) {
		return userGroupService.searchUserGroup(value);
	}

	public String cancel() {
		return "search-user-group";
	}

	public boolean save() {
		if (prepareToSave()) {
			userGroupService.save(orm);

			MessageUtils.messageInfo(translateEnumValue("message.registerCreated"));

			reinitFields();

			return true;
		}

		return false;
	}
}
