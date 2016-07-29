package com.haiziguo.recipe.balence;

public class BalanceStep {
	private Integer addOrReduce = 0;
	private Food	food;
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public Integer getAddOrReduce() {
		return addOrReduce;
	}
	public void setAddOrReduce(Integer addOrReduce) {
		this.addOrReduce = addOrReduce;
	}

	public String toString(){
		if(addOrReduce.compareTo(1)==0){
			return "++++1g "+ food.getFoodName() + " day " +food.getDay() +" type " + food.getType3(); 
		}else{
			return "----1g "+ food.getFoodName() + " day " +food.getDay() +" type " + food.getType3(); 
		}
	}
}
