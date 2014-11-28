package org.store.model.usuario;

import org.springframework.stereotype.Service;
import org.store.generic.service.basicService;

@Service
public class UsuarioService extends basicService<Usuario> {
  public UsuarioService() {
    super(Usuario.class);
  }
}
