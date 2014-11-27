package org.store;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.store.custom.annotation.form.Form;
import org.store.custom.annotation.form.FormPublic;
import org.store.custom.annotation.form.FormSettings;

public class Menu {
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
}
