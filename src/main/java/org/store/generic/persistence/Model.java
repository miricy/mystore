package org.store.generic.persistence;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class Model {
	public abstract String toString();

	public List<String> getFields() {
		Field fields[] = this.getClass().getDeclaredFields();
		String lista[] = new String[fields.length];

		for(int i=0; i<fields.length; i++)
			lista[i] = fields[i].getName();

		return Arrays.asList(lista);
	}

	public Object getValue(int position) {
		List<Method> all_methods = Arrays.asList(this.getClass().getMethods());
		List<Method> getter_methods = new ArrayList<Method>();

		for(int i=0; i<all_methods.size(); i++) {
			if(all_methods.get(i).getName().substring(0, 3).equals("get"))
			getter_methods.add(all_methods.get(i));
		}

		try {
			return getter_methods.get(position).invoke(this);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setValue(int position, Object value) {
		List<Method> all_methods = Arrays.asList(this.getClass().getMethods());
		List<Method> setter_methods = new ArrayList<Method>();

		for(int i=0; i<all_methods.size(); i++) {
			if(all_methods.get(i).getName().substring(0, 3).equals("set"))
			setter_methods.add(all_methods.get(i));
		}

		try {
			setter_methods.get(position).invoke(this, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
