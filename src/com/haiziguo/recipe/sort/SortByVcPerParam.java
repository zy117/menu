package com.haiziguo.recipe.sort;

import java.util.Comparator;

import com.haiziguo.recipe.balence.Food;

public class SortByVcPerParam implements Comparator<Object>{
	private Boolean order;
	private Integer param;
	
	public SortByVcPerParam(Boolean order,Integer param){
		this.order = order;
		this.param = param;
	}
	
	public int compare(Object o1, Object o2) {
		 Food s1 = (Food) o1;
		 Food s2 = (Food) o2;
		 if(s1.getVc()<0.000001f || s2.getVc()<0.000001f){
			 	if(order)
			 		return s1.getVc().compareTo(s2.getVc());
			 	else
			 		return s2.getVc().compareTo(s1.getVc());
			 }
		 switch(param){
		 	case 0:
		 		if(order)
		 			return ((Float)(s1.getVc()/s1.getEnergy())).compareTo(((Float)(s2.getVc()/s2.getEnergy())));
		 		else
		 			return ((Float)(s2.getVc()/s2.getEnergy())).compareTo(((Float)(s1.getVc()/s1.getEnergy())));
		 	case 1:
		 		if(order)
		 			return ((Float)(s1.getVc()/s1.getProtein())).compareTo(((Float)(s2.getVc()/s2.getProtein())));
		 		else
		 			return ((Float)(s2.getVc()/s2.getProtein())).compareTo(((Float)(s1.getVc()/s1.getProtein())));
		 	case 2:
		 		if(order)
		 			return ((Float)(s1.getVc()/s1.getFat())).compareTo(((Float)(s2.getVc()/s2.getFat())));
		 		else
		 			return ((Float)(s2.getVc()/s2.getFat())).compareTo(((Float)(s1.getVc()/s1.getFat())));
		 	case 3:
		 		if(order)
		 			return ((Float)(s1.getVc()/s1.getCarbohydrate())).compareTo(((Float)(s2.getVc()/s2.getCarbohydrate())));
		 		else
		 			return ((Float)(s2.getVc()/s2.getCarbohydrate())).compareTo(((Float)(s1.getVc()/s1.getCarbohydrate())));
		 	case 4:
		 		if(order)
		 			return ((Float)(s1.getVc()/s1.getCa())).compareTo(((Float)(s2.getVc()/s2.getCa())));
		 		else
		 			return ((Float)(s2.getVc()/s2.getCa())).compareTo(((Float)(s1.getVc()/s1.getCa())));
		 	case 5:
		 		if(order)
		 			return ((Float)(s1.getVc()/s1.getFe())).compareTo(((Float)(s2.getVc()/s2.getFe())));
		 		else
		 			return ((Float)(s2.getVc()/s2.getFe())).compareTo(((Float)(s1.getVc()/s1.getFe())));
		 	case 6:
		 		if(order)
		 			return ((Float)(s1.getVc()/s1.getZn())).compareTo(((Float)(s2.getVc()/s2.getZn())));
		 		else
		 			return ((Float)(s2.getVc()/s2.getZn())).compareTo(((Float)(s1.getVc()/s1.getZn())));
		 	case 7:
		 		if(order)
		 			return ((Float)(s1.getVc()/s1.getVa())).compareTo(((Float)(s2.getVc()/s2.getVa())));
		 		else
		 			return ((Float)(s2.getVc()/s2.getVa())).compareTo(((Float)(s1.getVc()/s1.getVa())));
		 	case 8:
		 		if(order)
		 			return ((Float)(s1.getVc()/s1.getVb1())).compareTo(((Float)(s2.getVc()/s2.getVb1())));
		 		else
		 			return ((Float)(s2.getVc()/s2.getVb1())).compareTo(((Float)(s1.getVc()/s1.getVb1())));
		 	case 9:
		 		if(order)
		 			return ((Float)(s1.getVc()/s1.getVb2())).compareTo(((Float)(s2.getVc()/s2.getVb2())));
		 		else
		 			return ((Float)(s2.getVc()/s2.getVb2())).compareTo(((Float)(s1.getVc()/s1.getVb2())));
		 	case 10:
		 	default:
		 		if(order)
		 			return s1.getVc().compareTo(s2.getVc());
		 		else
		 			return s2.getVc().compareTo(s1.getVc());
		 			
		 }
	}
}

