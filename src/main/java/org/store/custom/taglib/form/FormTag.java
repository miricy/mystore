package org.store.custom.taglib.form;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class FormTag extends TagSupport {
  private static final long serialVersionUID = 1L;

  public int doStartTag() {
    JspWriter out = pageContext.getOut();

    try {
      String action = (String) pageContext.findAttribute("action");
      out.println("<form class=\"form\" role=\"form\" method=\"post\" action=\""+action+"\">");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return EVAL_BODY_INCLUDE;
  }

  public int doEndTag() {
    JspWriter out = pageContext.getOut();

    try {
      out.println("</form>");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return EVAL_PAGE;
  }

  public Class<?> classe() {
    return pageContext.findAttribute("command").getClass();
  }

  public List<Field> fields() {
    return Arrays.asList(classe().getDeclaredFields());
  }
}
