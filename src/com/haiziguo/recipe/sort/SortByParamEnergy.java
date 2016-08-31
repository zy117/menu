package com.haiziguo.recipe.sort;

import java.util.Comparator;

import com.haiziguo.recipe.balence.Food;

public class SortByParamEnergy implements Comparator<Object> {
	
	private Boolean order;
	private Integer param;
	
	public SortByParamEnergy(Boolean order,Integer param){
		this.order = order;
		this.param = param;
	}
	
	 public int compare(Object o1, Object o2) {
		 Food s1 = (Food) o1;
		 Food s2 = (Food) o2;
		 Float f1 = 0.00f;
		 Float f2 =	0.00f;
		 switch(param){
		 case 1:
			 f1 = (s1.getProtein()*4)/(s1.getProtein()*4+s1.getFat()*9+s1.getCarbohydrate()*4);
			 f2 = (s2.getProtein()*4)/(s2.getProtein()*4+s2.getFat()*9+s2.getCarbohydrate()*4);
			 break;
		 case 2:
			 f1 = (s1.getFat()*9)/(s1.getProtein()*4+s1.getFat()*9+s1.getCarbohydrate()*4);
			 f2 = (s2.getFat()*9)/(s2.getProtein()*4+s2.getFat()*9+s2.getCarbohydrate()*4);
			 break;
		 case 3:
			 f1 = (s1.getCarbohydrate()*4)/(s1.getProtein()*4+s1.getFat()*9+s1.getCarbohydrate()*4);
			 f2 = (s2.getCarbohydrate()*4)/(s2.getProtein()*4+s2.getFat()*9+s2.getCarbohydrate()*4);
			 break;
		 default:
			 break;
		 }
		 if(this.order)
			 return f1.compareTo(f2);
		 else
			 return f2.compareTo(f1);
	 }
}
