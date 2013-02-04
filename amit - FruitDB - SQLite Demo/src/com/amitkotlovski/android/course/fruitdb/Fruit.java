package com.amitkotlovski.android.course.fruitdb;


public class Fruit {
	private int mId;
	private String mName;
	private String mTaste;
	private double mWeight;

	public Fruit(int id, String name, String taste, double weight) {
		mId = id;
		mName = name;
		mTaste = taste;
		mWeight = weight;
	}

	public Fruit(String name, String taste, double weight) {
		this(0, name, taste, weight);
	}

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		mId = id;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getTaste() {
		return mTaste;
	}

	public void setTaste(String taste) {
		mTaste = taste;
	}

	public double getWeight() {
		return mWeight;
	}

	public void setWeight(double weight) {
		mWeight = weight;
	}

}
