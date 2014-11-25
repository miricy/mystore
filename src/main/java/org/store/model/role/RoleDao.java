package org.store.model.role;

import org.springframework.stereotype.Repository;

import org.store.generic.persistence.Dao;

@Repository
public class RoleDao extends Dao<Role> {

	public RoleDao() {
		super(Role.class);
	}

}
