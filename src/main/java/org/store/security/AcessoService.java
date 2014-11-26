package org.store.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.store.model.permission.Permission;
import org.store.model.usuario.Usuario;
import org.store.model.usuario.UsuarioDao;

@Component
public class AcessoService implements UserDetailsService {

	@Autowired
	private UsuarioDao usuario;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario account = (Usuario) usuario.findByField("login", username);

    if(account==null) {
			System.out.println("No such user: " + username);
    	throw new UsernameNotFoundException("No such user: " + username);
		}
    else if (account.getRole().isEmpty()) {
			System.out.println("User " + username + " has no authorities");
    	throw new UsernameNotFoundException("User " + username + " has no authorities");
		}

    List<Permission> lista = new ArrayList<Permission>();
    int max = account.getRole().size();
    for(int i=0; i<max; i++) {
    	int max2 = account.getRole().get(i).getPermission().size();
    	for(int j=0; j<max2; j++)
    		lista.add(account.getRole().get(i).getPermission().get(j));
    }

    boolean accountIsEnabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    return new User(account.getLogin(), account.getSenha(), accountIsEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(lista));
	}

	public List<String> getRolesAsList(List<Permission> list) {
	    List <String> rolesAsList = new ArrayList<String>();
	    for(Permission role : list) rolesAsList.add(role.getNome());
	    return rolesAsList;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    for (String role : roles) authorities.add(new SimpleGrantedAuthority(role));
	    return authorities;
	}

	public Collection<? extends GrantedAuthority> getAuthorities(List<Permission> list) {
	    List<GrantedAuthority> authList = getGrantedAuthorities(getRolesAsList(list));
	    return authList;
	}

}
