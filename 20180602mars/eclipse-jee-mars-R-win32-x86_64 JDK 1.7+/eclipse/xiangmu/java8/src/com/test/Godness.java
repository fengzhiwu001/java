package com.test;
/**
 *Å®Éú
 */
public class Godness {

	private String name;//Ãû×Ö

	public Godness() {
	}

	public Godness(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Godness [name=" + name + "]";
	}

}