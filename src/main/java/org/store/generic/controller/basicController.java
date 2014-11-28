package org.store.generic.controller;

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
import org.store.generic.service.basicService;
import org.store.custom.annotation.form_action.Item;
import org.store.custom.annotation.form_action.Menu;
import org.store.MenuList;

public class basicController<E> {
  @Autowired
  private basicService<E> serv;

  protected Class<?> clazz;

  public basicController(Class<?> clazz) {
    this.clazz = clazz;
  }

  @RequestMapping(value = "cadastra")
  @PreAuthorize("hasPermission(#user, 'cadastra_'+#this.this.name)")
  @Menu(label = "cadastra")
  public String cadastra(Model model) throws Exception {
    model.addAttribute("command", serv.newObject());
    return "private/cadastra";
  }

  @RequestMapping(value = "altera/{id}")
  @PreAuthorize("hasPermission(#user, 'altera_'+#this.this.name)")
  @Item(label = "altera")
  public String altera(Model model, @PathVariable("id") String id) {
    int Id = Integer.valueOf(id).intValue();
    model.addAttribute("command", serv.getObject(Id));
    return "private/altera";
  }

  @RequestMapping(value = "remove/{id}")
  @PreAuthorize("hasPermission(#user, 'remove_'+#this.this.name)")
  @Item(label = "remove")
  public String remove(Model model, @PathVariable("id") String id) {
    int Id = Integer.valueOf(id).intValue();
    model.addAttribute("command", serv.getObject(Id));
    return "private/remove";
  }

  @RequestMapping(value = "index")
  @PreAuthorize("hasPermission(#user, 'listagem_'+#this.this.name)")
  public String index(Model model) {
    model.addAttribute("command", getName());
    model.addAttribute("colunas", MenuList.header(clazz));
    model.addAttribute("menu", MenuList.menu(this.getClass()));
    model.addAttribute("item", MenuList.item(this.getClass()));
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
