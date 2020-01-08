package com.map;

import java.util.Arrays;
import java.util.Set;


/**
 * @date 2017年10月18日下午4:00:58
 * @Description: 模拟HashMap类
 * @authorAdministrator
 */
public class SmallMap<K, V> implements Map<K, V> {
	transient int size;

	transient Entry<K, V>[] table;

	static class Entry<K, V> implements Map.Entry<K, V> {
		final K key;// 键
		V value;// 值
		Entry<K, V> next;// 下一个元素、
		int hash;// Hash值

		/**
		 * 构造方法
		 * 
		 * @param h
		 * @param k
		 * @param v
		 * @param n
		 */
		Entry(int h, K k, V v, Entry<K, V> n) {
			value = v;
			next = n;
			key = k;
			hash = h;
		}

		@Override
		public final K getKey() {

			return key;
		}

		@Override
		public final V getValue() {

			return value;
		}

		@Override
		public final V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}
	}

	public SmallMap() {
		table = (Entry<K, V>[]) new Entry<?, ?>[16];
	}

	public SmallMap(int size) {
		table = (Entry<K, V>[]) new Entry<?, ?>[size];
	}

	/**
	 * @Description: 取得表的容量
	 * @return int
	 */
	int capacity() {
		return table.length;
	}

	final int hash(Object k) {
		return k.hashCode();
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean containsKey(Object key) {
		//如果siz为0，返回false。
		if (size == 0) {
			return false;
		}
		//计算key的hash值，根据hash值和table的长度，计算出该key应该放置的位置。
		//从那个位置上取下Entry对象，进行判断。
		int hash = (key == null) ? 0 : hash(key);
		for (Entry<K, V> e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
			Object k;
			if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
				return e != null;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	@Override
	public V remove(Object key) {

		return null;
	}

	// get时，先hash，然后找到它在数组中 的位置。再取出 value
	@Override
	public V get(Object key) {
		int hash = hash(key);
		int index = indexFor(hash, table.length);

		for (Entry<K, V> e = table[index]; e != null; e = e.next) {
			Object k;
			if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
				return e.value;
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		// 计算key的hash值
		int hash = hash(key);
		// 根据hash值找到存放位置
		int i = indexFor(hash, table.length);
		for (Entry<K, V> e = table[i]; e != null; e = e.next) {
			Object k;
			// 哈希码相同并且对象相同时 说明key已经存在。
			if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
				V oldValue = e.value;
				e.value = value;
				return oldValue;
			}

		}
		// key不存在时，加入新元素
		// modCount++; 对hashmap修改的次数加上1.
		addEntry(hash, key, value, i);
		return null;
	}

	/**
	 * @Description:
	 * @param hash
	 * @param key
	 * @param value
	 * @param i
	 *            void
	 */
	private void addEntry(int hash, K key, V value, int i) {
		// 如果hashmap中的元素个数超过数组大小*loadFactor时，就会进行数组扩容
		// 数组大小默认为16，loadFactor的默认值为0.75
		if ((size >= table.length * 0.75) && (null != table[i])) {
			resize(2 * table.length);
		}
		createEntry(hash, key, value, i);
	}

	/**
	 * @Description: TODO
	 * @param hash
	 * @param key
	 * @param value
	 * @param i
	 *            void
	 */
	private void createEntry(int hash, K key, V value, int i) {
		Entry<K, V> e = table[i];
		table[i] = new Entry<>(hash, key, value, e);
		size++;

	}

	/**
	 * @Description: 数组扩容
	 * @param i
	 *            void
	 */
	private void resize(int newCapacity) {
		Entry[] newTable = new Entry[newCapacity];
		// initHashSeedAsNeeded(newCapacity)方法是判断是否需要对原数组元素进行重新hash。
		transfer(newTable, true);
		table = newTable;

	}

	/**
	 * @Description: 此方法是将原来数组中的元素重新计算每个元素在新数组的位置（可能要先hash）后，放入新数组中的对应 位置。
	 * @param newTable
	 * @param b
	 *            void
	 */
	private void transfer(Entry[] newTable, boolean rehash) {
		int newCapacity = newTable.length;
		for (Entry<K, V> e : table) {
			while (null != e) {
				Entry<K, V> next = e.next;
				if (rehash) {
					e.hash = null == e.key ? 0 : hash(e.key);
				}
				int i = indexFor(e.hash, newCapacity);
				e.next = newTable[i];
				newTable[i] = e;
				e = next;
			}
		}

	}

	/**
	 * @Description: 根据key的hash值和table的长度确定其所在的位置，
	 * @param hash
	 * @param length
	 * @return int
	 */
	private int indexFor(int hash, int length) {
		return hash & (length - 1);
	}

	@Override
	public void clear() {
		// 先把值全部设置为null，再把size设置为0.
		Arrays.fill(table, null);
		size = 0;
	}

	@Override
	public Set<K> keySet() {

		return null;
	}

	@Override
	public Set<com.map.Map.Entry<K, V>> entrySet() {

		return null;
	}

}
