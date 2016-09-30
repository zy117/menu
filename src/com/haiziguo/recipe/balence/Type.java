package com.haiziguo.recipe.balence;

import java.util.HashMap;
import java.util.Map;

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
}
