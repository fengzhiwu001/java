package com.test;

import java.util.Optional;

//提供定义的新的名称
public class NewMan {

	private Optional<Godness> godness = Optional.empty();
	
	private Godness god;
	
	public Optional<Godness> getGod(){
		return Optional.of(god);
	}

	public NewMan() {
	}

	public NewMan(Optional<Godness> godness) {
		this.godness = godness;
	}

	public Optional<Godness> getGodness() {
		return godness;
	}

	public void setGodness(Optional<Godness> godness) {
		this.godness = godness;
	}

	@Override
	public String toString() {
		return "NewMan [godness=" + godness + "]";
	}

}