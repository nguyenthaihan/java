package objectCollection;

import java.io.*;
import java.util.*;

public abstract class GeneralStorer implements ObjectStorer {
	protected GeneralStorer(ObjectStorage storage) {

	}

	public void put(Object key, Object object) throws IOException {
		
	}

	public Object get(Object key) throws IOException, ClassNotFoundException, IllegalAccessException,
	        InstantiationException {
		return null;
	}

	protected abstract StorageFields getFields(Object object) throws IOException;

	protected abstract Object setFields(RetrievalFields object) throws IOException, ClassNotFoundException,
	        IllegalAccessException, InstantiationException;
}