package org.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.store.custom.annotation.form.Form;
import org.store.custom.annotation.form.FormPublic;
import org.store.custom.annotation.form.FormSettings;
import org.store.custom.annotation.form_action.Item;
import org.store.custom.annotation.form_action.Menu;
import org.store.custom.annotation.form_control.Input;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MenuList {
  public static List<Class<?>> admin() throws Exception {
    List<Class<?>> lista = new ArrayList<Class<?>>();

    ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
    scanner.addIncludeFilter(new AnnotationTypeFilter(Form.class));
    for (BeanDefinition bd : scanner.findCandidateComponents("org.store.model")) {
      lista.add(Class.forName(bd.getBeanClassName()));
    }

    return lista;
  }

  public static List<Class<?>> index() throws Exception {
    List<Class<?>> lista = new ArrayList<Class<?>>();

    ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
    scanner.addIncludeFilter(new AnnotationTypeFilter(FormPublic.class));
    for (BeanDefinition bd : scanner.findCandidateComponents("org.store.model")) {
      lista.add(Class.forName(bd.getBeanClassName()));
    }

    return lista;
  }

  public static List<Class<?>> settings() throws Exception {
    List<Class<?>> lista = new ArrayList<Class<?>>();

    ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
    scanner.addIncludeFilter(new AnnotationTypeFilter(FormSettings.class));
    for (BeanDefinition bd : scanner.findCandidateComponents("org.store.model")) {
      lista.add(Class.forName(bd.getBeanClassName()));
    }

    return lista;
  }

  public static List<String> header(Class<?> clazz) {
    List<String> ret = new ArrayList<String>();

    List<Field> lista = Arrays.asList(clazz.getDeclaredFields());
    for(int i=0; i<lista.size(); i++) {
      if(lista.get(i).isAnnotationPresent(Input.class))
      ret.add(lista.get(i).getName());
    }

    return ret;
  }

  public static List<String> menu(Class<?> controller) {
    List<String> ret = new ArrayList<String>();

    List<Method> lista = Arrays.asList(controller.getMethods());
    for(int i=0; i<lista.size(); i++) {
      if( lista.get(i).isAnnotationPresent(Menu.class) )
      ret.add(lista.get(i).getAnnotation(Menu.class).label());
    }

    return ret;
  }

  public static List<String> item(Class<?> controller) {
    List<String> ret = new ArrayList<String>();

    List<Method> lista = Arrays.asList(controller.getMethods());
    for(int i=0; i<lista.size(); i++) {
      if( lista.get(i).isAnnotationPresent(Item.class) )
      ret.add(lista.get(i).getAnnotation(Item.class).label());
    }

    return ret;
  }
}
