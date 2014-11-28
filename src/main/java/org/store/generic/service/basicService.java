package org.store.generic.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.store.generic.persistence.Dao;

public class basicService<E> {
  @Autowired
  private Dao<E> dao;

  protected Class<?> clazz;

  public basicService(Class<?> clazz) {
    this.clazz = clazz;
  }

  @PreAuthorize("hasPermission(#user, 'cadastra_'+#this.this.name)")
  public void cadastra(E object) {
    dao.insert(object);
  }

  @PreAuthorize("hasPermission(#user, 'altera_'+#this.this.name)")
  public void altera(E object) {
    dao.update(object);
  }

  @PreAuthorize("hasPermission(#user, 'remove_'+#this.this.name)")
  public void remove(E object) {
    dao.delete(object);
  }

  @PreAuthorize("hasPermission(#user, 'listagem_'+#this.this.name)")
  public List<?> lista() {
    return dao.findAll();
  }

  public E getObject(int id) {
    return dao.findById(id);
  }

  public E newObject() throws Exception {
    return (E) clazz.newInstance();
  }

  public String getName() {
    return clazz.getSimpleName();
  }
}
