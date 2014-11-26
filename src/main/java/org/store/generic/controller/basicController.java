package org.hello.generic.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hello.generic.service.basicService;

public class basicController<E> {
  @Autowired
  private basicService<E> serv;

  protected Class<?> clazz;

  public basicController(Class<?> clazz) {
    this.clazz = clazz;
  }

  @RequestMapping(value = "cadastra")
  @PreAuthorize("hasPermission(#user, 'cadastra_'+#this.this.name)")
  public String cadastra(Model model) {
    return "private/cadastra";
  }

  @RequestMapping(value = "altera/{id}")
  @PreAuthorize("hasPermission(#user, 'altera_'+#this.this.name)")
  public String altera(Model model, @PathVariable("id") String id) {
    return "private/altera";
  }

  @RequestMapping(value = "remove/{id}")
  @PreAuthorize("hasPermission(#user, 'remove_'+#this.this.name)")
  public String remove(Model model, @PathVariable("id") String id) {
    return "private/remove";
  }

  @RequestMapping(value = "index")
  @PreAuthorize("hasPermission(#user, 'listagem_'+#this.this.name)")
  public String index(Model model) {
    return "private/index";
  }

  @RequestMapping(value = "cadastra", method=RequestMethod.POST)
  public void cadastra(@ModelAttribute("object") E object, BindingResult result) {
    serv.cadastra(object);
  }

  @RequestMapping(value = "altera", method=RequestMethod.POST)
  public void altera(@ModelAttribute("object") E object, BindingResult result) {
    serv.altera(object);
  }

  @RequestMapping(value = "remove", method=RequestMethod.POST)
  public void remove(@ModelAttribute("object") E object, BindingResult result) {
    serv.remove(object);
  }

  @RequestMapping(value = "lista.json")
  @ResponseBody
  public String lista() throws JsonProcessingException {
    String json = "";

    ObjectMapper mapper = new ObjectMapper();
    json = mapper.writeValueAsString(serv.lista());

    return json;
  }

  public String getName() {
    return clazz.getSimpleName();
  }
}
