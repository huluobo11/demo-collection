package com.tree.pojo;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private int id;
	private int pid;
	private String text;
	private List<Node> children;

	public void addChild(Node node) {
		if (children != null) {
			children.add(node);
		} else {
			children = new ArrayList<>();
			children.add(node);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Tree [id=" + id + ", pid=" + pid + ", text=" + text + ", children=" + children + "]";
	}

	public Node(int id, int pid, String text) {
		super();
		this.id = id;
		this.pid = pid;
		this.text = text;
	}

}
