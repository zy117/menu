package com.haiziguo.recipe.util;

public enum Meal{
	MEAL_8(1),
	MEAL_10(2),
	MEAL_12(4),
	MEAL_15(8),
	MEAL_18(16);
	private Integer mark;
	private Meal(Integer mark){
		this.mark=mark;
	}
	public Integer getMark(){
		return this.mark;
	}
};