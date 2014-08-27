package objectCollection;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class ReflectionStorer extends GeneralStorer {
	public ReflectionStorer(ObjectStorage storage) {
		super(storage);
	}

	public void put(Object key, Object object) throws IOException {

	}

	protected StorageFields getFields(Object object) {
		Class clazz = object.getClass();
		String className = clazz.getName();
		StorageFields fields = new StorageFields(className);
		try {
			Map suffixes = new HashMap();
			do {
				getField(fields, object, clazz, suffixes);
				clazz = clazz.getSuperclass();
			} while (clazz != null);
		} catch (IllegalAccessException ignored) {
		}
		return fields;
	}

	public Object get(Object key) throws IOException, ClassNotFoundException, IllegalAccessException,
	        InstantiationException {
		return null;
	}

	protected Object setFields(RetrievalFields fields) throws ClassNotFoundException, IllegalAccessException,
	        InstantiationException {
		String className = fields.getClassName();
		Class clazz = Class.forName(className);
		Object object = clazz.newInstance();
		Map suffixes = new HashMap();
		do {
			setFields(object, fields, clazz, suffixes);
			clazz = clazz.getSuperclass();
		} while (clazz != null);
		return object;
	}

	private void getFields(StorageFields fields, Object object, Class clazz, Map suffixes)
	        throws IllegalAccessException {
		Field[] classFields = clazz.getDeclaredFields();
		AccessibleObject.setAccessible(classFields, true);
		int n = classFields.length;
		for (int i = 0; i < n; ++i) {
			Field field = classFields[i];
			if (isValid(field)) {
				String name = field.getName();
				Class type = field.getType();
				Object value = field.get(object);
				StringBuffer suffix = (StringBuffer) suffixes.get(name);
				if (suffix == null)
					suffixes.put(name, suffix = new StringBuffer());
				fields.addField(name + suffix, type, value);
				suffix.append('\'');
			}
		}
	}

	private void setFields(Object object, RetrievalFields fields, Class clazz, Map suffixes)
	        throws IllegalAccessException {
		Field[] classFields = clazz.getDeclaredFields();
		AccessibleObject.setAccessible(classFields, true);
		int n = classFields.length;
		for (int i = 0; i < n; ++i) {
			Field field = classFields[i];
			if (isValid(field)) {
				String name = field.getName();
				Class type = field.getType();
				StringBuffer suffix = (StringBuffer) suffixes.get(name);
				if (suffix == null)
					suffixes.put(name, suffix = new StringBuffer());
				Object value = fields.getValue(name + suffix, type);
				field.set(object, value);
				suffix.append('\'');
			}
		}
	}

	private boolean isValid(Field field) {
		int modifiers = field.getModifiers();
		return (!Modifier.isTransient(modifiers) && !Modifier.isStatic(modifiers) && !Modifier.isFinal(modifiers));
	}
}