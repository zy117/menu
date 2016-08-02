package com.haiziguo.recipe.balence;

import java.util.HashMap;
import java.util.Map;
import com.haiziguo.recipe.util.Define;

public class Type {
	private Map<Integer,TypeValue> values;
	
	private class TypeValue{
		private Float max = Float.MAX_VALUE;
		private Float min = 0.0f;
		private Float cur = 0.0f;

		public Float getMax() {
			return max;
		}
		public void setMax(Float max) {
			this.max = max;
		}
		public Float getMin() {
			return min;
		}
		public void setMin(Float min) {
			this.min = min;
		}

		public Float getCur() {
			return cur;
		}

		public void setCur(Float cur) {
			this.cur = cur;
		}
	}
	
	public Type(){
		this.values = new HashMap<Integer,TypeValue>();
		this.values.put(Define.FOOD_CEREAL, new TypeValue());
		setTypeMin(Define.FOOD_CEREAL,85f);
		setTypeMax(Define.FOOD_CEREAL,150f);
		this.values.put(Define.FOOD_BEANS, new TypeValue());
		setTypeMin(Define.FOOD_BEANS,5f);
		setTypeMax(Define.FOOD_BEANS,15f);
		this.values.put(Define.FOOD_VEGETABLE, new TypeValue());
		setTypeMin(Define.FOOD_VEGETABLE,200f);
		setTypeMax(Define.FOOD_VEGETABLE,300f);
		this.values.put(Define.FOOD_FRUIT, new TypeValue());
		setTypeMin(Define.FOOD_FRUIT,100f);
		setTypeMax(Define.FOOD_FRUIT,150f);
		this.values.put(Define.FOOD_MEAT, new TypeValue());
		setTypeMin(Define.FOOD_MEAT,30f);
		setTypeMax(Define.FOOD_MEAT,80f);
		this.values.put(Define.FOOD_EGGS, new TypeValue());
		setTypeMin(Define.FOOD_EGGS,20f);
		setTypeMax(Define.FOOD_EGGS,25f);
		this.values.put(Define.FOOD_MILK, new TypeValue());
		setTypeMin(Define.FOOD_MILK,350f);
		setTypeMax(Define.FOOD_MILK,500f);
		this.values.put(Define.FOOD_HEAT, new TypeValue());
		this.values.put(Define.FOOD_CAKE, new TypeValue());
		this.values.put(Define.FOOD_OTHERS, new TypeValue());
	}
	
	public String toString(){
		return "谷类\t"+this.values.get(Define.FOOD_CEREAL).getCur()+"g, \t["+this.values.get(Define.FOOD_CEREAL).getMin()+","+this.values.get(Define.FOOD_CEREAL).getMax()+"]"+"\n"
				+"豆类\t"+this.values.get(Define.FOOD_BEANS).getCur()+"g, \t["+this.values.get(Define.FOOD_BEANS).getMin()+","+this.values.get(Define.FOOD_BEANS).getMax()+"]"+"\n"
				+"蔬菜\t"+this.values.get(Define.FOOD_VEGETABLE).getCur()+"g, \t["+this.values.get(Define.FOOD_VEGETABLE).getMin()+","+this.values.get(Define.FOOD_VEGETABLE).getMax()+"]"+"\n"
				+"水果\t"+this.values.get(Define.FOOD_FRUIT).getCur()+"g,\t ["+this.values.get(Define.FOOD_FRUIT).getMin()+","+this.values.get(Define.FOOD_FRUIT).getMax()+"]"+"\n"
				+"禽类水产\t"+this.values.get(Define.FOOD_MEAT).getCur()+"g, \t["+this.values.get(Define.FOOD_MEAT).getMin()+","+this.values.get(Define.FOOD_MEAT).getMax()+"]"+"\n"
				+"蛋类\t"+this.values.get(Define.FOOD_EGGS).getCur()+"g, \t["+this.values.get(Define.FOOD_EGGS).getMin()+","+this.values.get(Define.FOOD_EGGS).getMax()+"]"+"\n"
				+"乳制品\t"+this.values.get(Define.FOOD_MILK).getCur()+"g, \t["+this.values.get(Define.FOOD_MILK).getMin()+","+this.values.get(Define.FOOD_MILK).getMax()+"]"+"\n"
				;
	}
	
	public void resetCur(){
		for(Integer i:values.keySet()){
			this.values.get(i).setCur(0.0f);
		}
	}
	
	public Boolean isTypeOverMaxAdd(Integer type,Float value){
		if((this.values.get(type).getCur()+value)>this.values.get(type).getMax())
			return true;
		return false;
	}
	
	public Boolean isTypeUnderMinReduce(Integer type,Float value){
		if((this.values.get(type).getCur()-value)<this.values.get(type).getMin())
			return true;
		return false;
	}
	
	public Boolean isTypeOverMax(Integer type){
		if(this.values.get(type).getCur()>this.values.get(type).getMax())
			return true;
		return false;
	}
	
	public Boolean isTypeUnderMin(Integer type){
		if(this.values.get(type).getCur()<this.values.get(type).getMin())
			return true;
		return false;
	}
	
	public Float getTypeMax(Integer type){
		return 	this.values.get(type).getMax();	
	}
	public Float getTypeMin(Integer type){
		return 	this.values.get(type).getMin();
	}
	public Float getTypeCur(Integer type){
		return 	this.values.get(type).getCur();
	}
	public void setTypeMax(Integer type,Float value){
		this.values.get(type).setMax(value);		
	}
	public void setTypeMin(Integer type,Float value){
		this.values.get(type).setMin(value);		
	}
	public void setTypeCur(Integer type,Float value){
		this.values.get(type).setCur(value);		
	}
	
	public void setTypeCurSelfAdd(Integer type,Float value){
		this.values.get(type).setCur(getTypeCur(type)+value);		
	}
	
	public void plusDaysAndMeals(Integer days,Float meals){
		Integer plus = days;
		Float percent = meals;
		if(plus<1){
			plus = 1;
		}
		if(percent<Define.FLOAT_ZERO){
			percent = 0.0f;
		}else if(percent>1){
			percent = 1.0f;
		}
		for(Integer i:values.keySet()){
			this.values.get(i).setMax(this.values.get(i).getMax()*plus*percent);
			this.values.get(i).setMin(this.values.get(i).getMin()*plus*percent);
		}
	}
	
	
	
}
