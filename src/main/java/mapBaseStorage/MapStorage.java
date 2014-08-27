package mapBaseStorage;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

import objectCollection.ObjectStorage;
import objectCollection.RetrievalFields;
import objectCollection.StorageFields;

public class MapStorage implements ObjectStorage {
	// private Map<K, V> storageMap;
	private Map storageMap = new HashMap();

	private static String classNameKey = "@class";

	public MapStorage(Map storageMap) {
		this.storageMap = storageMap;
	}

	public void put(Object key, StorageFields object) {
		if (object == null) {
			storageMap.remove(key);
			return;
		}
		Map objectMap = new HashMap();
		String className = object.getClassName();
		objectMap.put(classNameKey, className);
		Iterator<String> fields = object.getFieldNames();
		while (fields.hasNext()) {
			Object field = fields.next();
			Object value = object.getValue(field);
			if (value != null)
				objectMap.put(field, value);
			else
				objectMap.put(field, objectMap);
		}
		storageMap.put(key, objectMap);
	}

	public RetrievalFields get(Object key) {
		Map objectMap = (Map) storageMap.get(key);
		if (objectMap == null)
			return null;
		String className = (String) objectMap.get(classNameKey);
		RetrievalFields object = new RetrievalFields(className);
		Iterator fields = objectMap.keySet().iterator();
		while (fields.hasNext()) {
			String field = (String) fields.next();
			Object value = objectMap.get(field);
			if (value == objectMap)
				value = null;
			object.addField(field, value);
		}
		return object;
	}
}