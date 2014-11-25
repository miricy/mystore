package org.store.model.usuario;

import org.springframework.stereotype.Repository;

import org.store.generic.persistence.Dao;

@Repository
public class UsuarioDao extends Dao<Usuario> {

	public UsuarioDao() {
		super(Usuario.class);
	}

}
