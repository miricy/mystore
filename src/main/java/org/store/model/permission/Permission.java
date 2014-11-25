package org.store.model.permission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.core.annotation.Order;

import org.store.generic.persistence.Model;

@Entity
public class Permission extends Model {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Order(value = 1)
	private Integer id;

	@Order(value = 2)
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

}
