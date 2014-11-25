package org.store.model.permission;

import org.springframework.stereotype.Repository;

import org.store.generic.persistence.Dao;

@Repository
public class PermissionDao extends Dao<Permission> {

	public PermissionDao() {
		super(Permission.class);
	}

}
