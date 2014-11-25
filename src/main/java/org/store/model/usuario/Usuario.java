package org.store.model.usuario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import org.store.model.role.Role;

@Entity
public class Usuario extends Model {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Order(value = 1)
	private Integer id;

	@Order(value = 2)
	private String login;

	@Order(value = 3)
	private String senha;

	@Order(value = 4)
	private String nome;

	@Order(value = 5)
	private String sobrenome;

	@Order(value = 6)
	private String email;

	@ManyToMany
	@JoinTable(name="role_members", joinColumns={@JoinColumn(name="fk_user")}, inverseJoinColumns={@JoinColumn(name="fk_role")})
	@LazyCollection(LazyCollectionOption.FALSE)
	@Order(value = 7)
	private List<Role> role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(senha.getBytes(), 0, senha.length());
		BigInteger hash = new BigInteger(1, m.digest());
		this.senha = hash.toString(16);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return nome + " " + sobrenome;
	}

}
