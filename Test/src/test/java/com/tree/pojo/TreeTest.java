package com.tree.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

public class TreeTest {
	public List<Node> generateData() {
		List<Node> trees = new ArrayList<>();
		Node tree0 = new Node(0, -1, "中国");
		Node tree1 = new Node(1, 0, "河南省");
		Node tree2 = new Node(2, 0, "山东省");
		Node tree3 = new Node(3, 0, "山西省");
		Node tree4 = new Node(4, 0, "河北省");
		Node tree5 = new Node(5, 0, "湖南省");
		Node tree6 = new Node(6, 1, "郑州市");
		Node tree7 = new Node(7, 1, "洛阳市");
		Node tree8 = new Node(8, 1, "许昌市");

		Node tree9 = new Node(9, 6, "金水区");
		Node tree10 = new Node(10, 6, "二七区");
		Node tree11 = new Node(11, 6, "中原区");

		Node tree12 = new Node(12, 7, "洛龙区");
		Node tree13 = new Node(13, 7, "西工区");
		Node tree14 = new Node(14, 7, "老城区");

		/*
		 * tree11.setChildren(Arrays.asList(new Tree[] {tree111,tree112,tree113}));
		 * tree12.setChildren(Arrays.asList(new Tree[] {tree121,tree122,tree123}));
		 * tree1.setChildren(Arrays.asList(new Tree[] {tree11,tree12,tree13}));
		 */
		trees.add(tree0);
		trees.add(tree1);
		trees.add(tree2);
		trees.add(tree3);
		trees.add(tree4);
		trees.add(tree5);
		trees.add(tree6);
		trees.add(tree7);
		trees.add(tree8);
		trees.add(tree9);
		trees.add(tree10);
		trees.add(tree11);
		trees.add(tree12);
		trees.add(tree13);
		trees.add(tree14);
		return trees;
	}

	// 同步tree，获取整个tree
	@Test
	public void testAll() {
		List<Node> trees = generateData();
		// 节点列表（散列表，用于临时存储节点对象）
		HashMap<Integer, Node> nodeList = new HashMap();
		for (Node tree : trees) {
			nodeList.put(tree.getId(), tree);
		}
		// 根节点
		Node root = null;
		Set<Entry<Integer, Node>> entrySet = nodeList.entrySet();
		Iterator<Entry<Integer, Node>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Node node = iterator.next().getValue();
			if (node.getPid() == -1) {
				root = node;
			} else {
				nodeList.get(node.getPid()).addChild(node);
			}
		}
		System.out.println(root.toString());
	}

	// 异步tree，根据id查询下一级的各个子节点
	@Test
	public void testa() {
		Scanner scanner = new Scanner(System.in);
		int id = scanner.nextInt();
		List<Node> trees = generateData();
		// 节点列表（散列表，用于临时存储节点对象）
		HashMap<Integer, Node> nodeList = new HashMap();
		for (Node tree : trees) {
			if (tree.getId() == id || tree.getPid() == id) {
				nodeList.put(tree.getId(), tree);
			}
		}
		// 根节点
		Node root = null;
		Set<Entry<Integer, Node>> entrySet = nodeList.entrySet();
		Iterator<Entry<Integer, Node>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Node node = iterator.next().getValue();
			if (node.getId() == id) {
				root = node;
			} else {
				nodeList.get(node.getPid()).addChild(node);
			}
		}
		System.out.println(root.toString());
	}
}
