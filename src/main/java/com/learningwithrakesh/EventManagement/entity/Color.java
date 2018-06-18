package com.learningwithrakesh.EventManagement.entity;

import javax.persistence.Entity;

/**
 *
 */
@SuppressWarnings("javadoc")
@Entity
public class Color extends BaseDomain {
	private int red;
	private int green;
	private int blue;

	public Color() {
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	@Override
	public String toString() {
		return "Color [red=" + red + ", green=" + green + ", blue=" + blue + "]";
	}

}
