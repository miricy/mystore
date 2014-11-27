package org.store.custom.taglib.form_control;

import java.lang.reflect.Field;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.spring.config.annotation.form_control.Checkbox;
import com.spring.config.taglib.form.FormTag;

public class RadiobuttonTag extends TagSupport {
  private static final long serialVersionUID = 1L;

  public int doStartTag() {
    JspWriter out = pageContext.getOut();

    try {
      out.println("<field-box>");
      out.println("   <label class=\"radio\">");
      out.println("      <input type=\"radio\" name=\""+name()+"\" id=\""+name()+"\" value=\""+value()+"\">"+label());
      out.println("   </label>");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return EVAL_BODY_INCLUDE;
  }

  public int doEndTag() {
    JspWriter out = pageContext.getOut();

    try {
      out.println("</field-box>");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return EVAL_PAGE;
  }

  public int index() {
    return (Integer) pageContext.findAttribute("index");
  }

  public Field field() {
    FormTag tag = (FormTag) findAncestorWithClass(this, FormTag.class);
    return tag.fields().get(index());
  }

  public String label() {
    return field().getAnnotation(Checkbox.class).label();
  }

  public String name() {
    return field().getName();
  }

  public Object value() throws Exception {
    Object object = pageContext.findAttribute("command");
    return object.getClass().getMethod("get"+caps(field().getName())).invoke(object);
  }

  private String caps(String string) {
    char[] charArray = string.toCharArray();
    charArray[0] = Character.toUpperCase(charArray[0]);
    String Key = new String(charArray);
    return Key;
  }
}
