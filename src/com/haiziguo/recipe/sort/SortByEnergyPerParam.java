package com.haiziguo.recipe.sort;

import java.util.Comparator;

import com.haiziguo.recipe.balence.Food;

public class SortByEnergyPerParam implements Comparator<Object>{
	private Boolean order;
	private Integer param;
	
	public SortByEnergyPerParam(Boolean order,Integer param){
		this.order = order;
		this.param = param;
	}
	
	public int compare(Object o1, Object o2) {
		 Food s1 = (Food) o1;
		 Food s2 = (Food) o2;
		 if(s1.getEnergy()<0.000001f || s2.getEnergy()<0.000001f){
			 	if(order)
			 		return s1.getEnergy().compareTo(s2.getEnergy());
			 	else
			 		return s2.getEnergy().compareTo(s1.getEnergy());
			 }
		 switch(param){
		 	case 1:
		 		if(order)
		 			return ((Float)(s1.getEnergy()/s1.getProtein())).compareTo(((Float)(s2.getEnergy()/s2.getProtein())));
		 		else
		 			return ((Float)(s2.getEnergy()/s2.getProtein())).compareTo(((Float)(s1.getEnergy()/s1.getProtein())));
		 	case 2:
		 		if(order)
		 			return ((Float)(s1.getEnergy()/s1.getFat())).compareTo(((Float)(s2.getEnergy()/s2.getFat())));
		 		else
		 			return ((Float)(s2.getEnergy()/s2.getFat())).compareTo(((Float)(s1.getEnergy()/s1.getFat())));
		 	case 3:
		 		if(order)
		 			return ((Float)(s1.getEnergy()/s1.getCarbohydrate())).compareTo(((Float)(s2.getEnergy()/s2.getCarbohydrate())));
		 		else
		 			return ((Float)(s2.getEnergy()/s2.getCarbohydrate())).compareTo(((Float)(s1.getEnergy()/s1.getCarbohydrate())));
		 	case 4:
		 		if(order)
		 			return ((Float)(s1.getEnergy()/s1.getCa())).compareTo(((Float)(s2.getEnergy()/s2.getCa())));
		 		else
		 			return ((Float)(s2.getEnergy()/s2.getCa())).compareTo(((Float)(s1.getEnergy()/s1.getCa())));
		 	case 5:
		 		if(order)
		 			return ((Float)(s1.getEnergy()/s1.getFe())).compareTo(((Float)(s2.getEnergy()/s2.getFe())));
		 		else
		 			return ((Float)(s2.getEnergy()/s2.getFe())).compareTo(((Float)(s1.getEnergy()/s1.getFe())));
		 	case 6:
		 		if(order)
		 			return ((Float)(s1.getEnergy()/s1.getZn())).compareTo(((Float)(s2.getEnergy()/s2.getZn())));
		 		else
		 			return ((Float)(s2.getEnergy()/s2.getZn())).compareTo(((Float)(s1.getEnergy()/s1.getZn())));
		 	case 7:
		 		if(order)
		 			return ((Float)(s1.getEnergy()/s1.getVa())).compareTo(((Float)(s2.getEnergy()/s2.getVa())));
		 		else
		 			return ((Float)(s2.getEnergy()/s2.getVa())).compareTo(((Float)(s1.getEnergy()/s1.getVa())));
		 	case 8:
		 		if(order)
		 			return ((Float)(s1.getEnergy()/s1.getVb1())).compareTo(((Float)(s2.getEnergy()/s2.getVb1())));
		 		else
		 			return ((Float)(s2.getEnergy()/s2.getVb1())).compareTo(((Float)(s1.getEnergy()/s1.getVb1())));
		 	case 9:
		 		if(order)
		 			return ((Float)(s1.getEnergy()/s1.getVb2())).compareTo(((Float)(s2.getEnergy()/s2.getVb2())));
		 		else
		 			return ((Float)(s2.getEnergy()/s2.getVb2())).compareTo(((Float)(s1.getEnergy()/s1.getVb2())));
		 	case 10:
		 		if(order)
		 			return ((Float)(s1.getEnergy()/s1.getVc())).compareTo(((Float)(s2.getEnergy()/s2.getVc())));
		 		else
		 			return ((Float)(s2.getEnergy()/s2.getVc())).compareTo(((Float)(s1.getEnergy()/s1.getVc())));
		 	case 0:
		 	default:
		 		if(order)
		 			return s1.getEnergy().compareTo(s2.getEnergy());
		 		else
		 			return s2.getEnergy().compareTo(s1.getEnergy());
		 			
		 }
	}
}

