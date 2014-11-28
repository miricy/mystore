package org.store.model.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.store.generic.controller.basicController;

@Controller
@RequestMapping(value = "Usuario")
public class UsuarioController extends basicController<Usuario> {
  public UsuarioController() {
    super(Usuario.class);
  }
}
