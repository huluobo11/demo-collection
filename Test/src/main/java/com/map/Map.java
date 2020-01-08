package com.map;

import java.util.Set;

/**
 * @date 2017年10月18日下午3:51:07
 * @Description: TODO
 * @authorAdministrator
 */
public interface Map<K, V> {

	/**
	 * @Description: map的size
	 * @return int
	 */
	int size();

	/**
	 * 是否包含某个key
	 * 
	 * @Description: TODO
	 * @param key
	 * @return boolean
	 */
	boolean containsKey(Object key);

	/**
	 * 是否为空
	 * 
	 * @Description: TODO
	 * @return boolean
	 */
	boolean isEmpty();

	/**
	 * 去掉某个元素
	 * 
	 * @Description: TODO
	 * @param key
	 * @return V
	 */
	V remove(Object key);

	/**
	 * 得到set
	 * 
	 * @Description: TODO
	 * @return Set<Map.Entry<K,V>>
	 */
	Set<Map.Entry<K, V>> entrySet();

	/**
	 * 根据key得到value
	 * 
	 * @Description: TODO
	 * @param key
	 * @return V
	 */
	V get(Object key);

	/**
	 * put
	 * 
	 * @Description: TODO
	 * @param key
	 * @param value
	 * @return V
	 */
	V put(K key, V value);

	/**
	 * 
	 * @date 2017年10月18日下午4:08:23
	 * @Description: TODO
	 * @authorAdministrator
	 */
	interface Entry<K, V> {
		/**
		 * 是否相同
		 * 
		 * @Description: TODO
		 * @param obj
		 * @return boolean
		 */
		boolean equals(Object obj);

		/**
		 * 取出key
		 * 
		 * @Description: TODO
		 * @return K
		 */
		K getKey();

		/**
		 * 取出value
		 * 
		 * @Description: TODO
		 * @return V
		 */
		V getValue();

		/**
		 * hash值
		 * 
		 * @Description: TODO
		 * @return int
		 */
		int hashCode();

		/**
		 * 设置值
		 * 
		 * @Description: TODO
		 * @param value
		 * @return V
		 */
		V setValue(V value);
	}

	/**
	 * 清空map
	 * @Description: TODO void
	 */
	void clear();

	/**
	 * key组成的set
	 * @Description: TODO
	 * @return Set<K>
	 */
	Set<K> keySet();
}
