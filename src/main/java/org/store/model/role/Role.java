package org.store.model.role;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.core.annotation.Order;

import org.store.generic.persistence.Model;
import org.store.model.permission.Permission;

@Entity
public class Role extends Model {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Order(value = 1)
	private Integer id;

	@Order(value = 2)
	private String nome;

	@ManyToMany
	@JoinTable(name="role_permissions", joinColumns={@JoinColumn(name="fk_role")}, inverseJoinColumns={@JoinColumn(name="fk_permission")})
	@LazyCollection(LazyCollectionOption.FALSE)
	@Order(value = 3)
	private List<Permission> permission;

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

	public List<Permission> getPermission() {
		return permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return nome;
	}

}
