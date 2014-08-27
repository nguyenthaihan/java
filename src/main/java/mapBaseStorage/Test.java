package mapBaseStorage;

import java.io.*;
import java.util.*;

import objectCollection.ObjectStorage;
import objectCollection.ObjectStorer;

public class Test {
	public static void main(String[] args) throws Exception {
		Map storageMap = new HashMap();
		ObjectStorage storage = new MapStorage(storageMap);
		ObjectStorer storer = new SerializationStorer(storage);
		storer.put("Helms", new RepublicanSenator("Helms", 16000000000L));
		storer.put("Clinton", new Senator("Clinton", 52, true));
		storer.put("one", new Integer(1));
		System.out.println(storer.get("Helms"));
		System.out.println(storer.get("Clinton"));
		System.out.println(storer.get("one"));
		System.out.println(storageMap);
	}
}

class Senator implements Serializable {
	protected String name;
	protected int age;
	protected boolean expendable;

	public Senator(String name, int age, boolean expendable) {
		this.name = name;
		this.age = age;
		this.expendable = expendable;
	}

	public String toString() {
		return "Senator[name=" + name + ",age=" + age + ",expendable=" + expendable + "]";
	}
}

class RepublicanSenator extends Senator {
	protected long age;

	public RepublicanSenator(String name, long age) {
		super(name, -1, true);
		this.age = age;
	}

	public String toString() {
		return "RepublicanSenator[name=" + name + ",age=" + age + ",expendable=" + expendable + "]";
	}
}
