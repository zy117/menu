package com.haiziguo.recipe.balence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.haiziguo.recipe.sort.*;
import com.haiziguo.recipe.util.Define;

public class Algorithm {

	private Float [] cal_nutrition = {0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f};
	private Float [] cal_nutrition_percent = {0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f};	
	
	private Float [] target = {0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f};
	
	private Float [] target_over = {0.2f,0.2f,0.2f,0.2f,1.2f,1.2f,1.2f,1.2f,1.2f,1.2f,1.2f};
	private Float [] target_max = {0.4f,0.4f,0.4f,0.4f,2.5f,2.5f,2.5f,2.5f,2.5f,2.5f,2.5f};
	
	private Float [] gOverTarget10Percent = {0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f};
	private Float [] gReachTarget20Percent = {0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f};
	
	private Standard standard = new Standard();
	private List<Food> food;
	private List<Nutrition> nutrition = new ArrayList<Nutrition>();
	
	private Boolean isFoodInited = false;
	private Boolean isStandardSet = false;
	private Boolean isTargetSet = false;
	
	public void InitFoodList(List<Food> f){
		this.setFood(new ArrayList<Food>(f));
		this.isFoodInited = true;
		Nutrition ENERGY = new Nutrition(Define.ENERGY,cal_nutrition_percent[Define.ENERGY]);
		nutrition.add(ENERGY);
		Nutrition PROTEIN = new Nutrition(Define.PROTEIN,cal_nutrition_percent[Define.PROTEIN]);
		nutrition.add(PROTEIN);
		Nutrition FAT = new Nutrition(Define.FAT,cal_nutrition_percent[Define.FAT]);
		nutrition.add(FAT);
		Nutrition CARBOHYDRATE = new Nutrition(Define.CARBOHYDRATE,cal_nutrition_percent[Define.CARBOHYDRATE]);
		nutrition.add(CARBOHYDRATE);
		Nutrition CA = new Nutrition(Define.CA,cal_nutrition_percent[Define.CA]);
		nutrition.add(CA);
		Nutrition FE = new Nutrition(Define.FE,cal_nutrition_percent[Define.FE]);
		nutrition.add(FE);
		Nutrition ZN = new Nutrition(Define.ZN,cal_nutrition_percent[Define.ZN]);
		nutrition.add(ZN);
		Nutrition VA = new Nutrition(Define.VA,cal_nutrition_percent[Define.VA]);
		nutrition.add(VA);
		Nutrition VB1 = new Nutrition(Define.VB1,cal_nutrition_percent[Define.VB1]);
		nutrition.add(VB1);
		Nutrition VB2 = new Nutrition(Define.VB2,cal_nutrition_percent[Define.VB2]);
		nutrition.add(VB2);
		Nutrition VC = new Nutrition(Define.VC,cal_nutrition_percent[Define.VC]);
		nutrition.add(VC);
	}
	
	public void setStandard(Float [] nutrition){
		if(nutrition.length!=Define.NUM){
			System.out.println("SetStandard error!length < "+Define.NUM);
			return;
		}
		standard.setEnergy(nutrition[0]);
		standard.setProtein(nutrition[1]);
		standard.setFat(nutrition[2]);
		standard.setCarbohydrate(nutrition[3]);
		standard.setCa(nutrition[4]);
		standard.setFe(nutrition[5]);
		standard.setZn(nutrition[6]);
		standard.setVa(nutrition[7]);
		standard.setVb1(nutrition[8]);
		standard.setVb2(nutrition[9]);
		standard.setVc(nutrition[10]);
		System.out.println(standard.toString());		
		this.isStandardSet = true;
	}

	public List<Food> getFood() {
		return food;
	}

	public void setFood(List<Food> food) {
		this.food = food;
	}
	
	public void printMenu(){
		Collections.sort(food,new SortByMeal());
		for (Food f:food){
			System.out.println(f.toMenu());
		}
	}
	
	public Float[] calNutrition(Boolean debug){
		for (int i=0;i<Define.NUM;i++){
			cal_nutrition[i] = 0.0f;
		}
		for(Food f:food){
			for(int i =0;i<Define.NUM;i++){
				cal_nutrition[i]+=f.getIndex(i)*f.getGram()/100;
			}
		}
		if(debug){
			System.out.println("menu list cal_nutrition [enegy = "+cal_nutrition[0]+",\t"
					+"protein = "+cal_nutrition[1]+",\t"
					+"fat = "+cal_nutrition[2]+",\t"
					+"carbohydrate = "+cal_nutrition[3]+",\t"
					+"ca = "+cal_nutrition[4]+",\t"
					+"fe = "+cal_nutrition[5]+",\t"
					+"zn = "+cal_nutrition[6]+",\t"
					+"va = "+cal_nutrition[7]+",\t"
					+"vb1 = "+cal_nutrition[8]+",\t"
					+"vb2 = "+cal_nutrition[9]+",\t"
					+"vc = "+cal_nutrition[10]+"]");
		}
		
		calNutritionPercent(debug);
		return cal_nutrition;
	}
	
	public Float [] calNutritionPercent(Boolean debug){
		for(int i =0;i<Define.NUM;i++){
			cal_nutrition_percent[i]=100*cal_nutrition[i]/standard.getIndex(i);
		}
		if(debug){
			System.out.println("menu list percent:"+cal_nutrition_percent[0]+"%,\t"
					   +cal_nutrition_percent[1]+"%,\t"
					   +cal_nutrition_percent[2]+"%,\t"
					   +cal_nutrition_percent[3]+"%,\t"
					   +cal_nutrition_percent[4]+"%,\t"
					   +cal_nutrition_percent[5]+"%,\t"
					   +cal_nutrition_percent[6]+"%,\t"
					   +cal_nutrition_percent[7]+"%,\t"
					   +cal_nutrition_percent[8]+"%,\t"
					   +cal_nutrition_percent[9]+"%,\t"
					   +cal_nutrition_percent[10]+"%");
		}

		for(Nutrition n:nutrition){
			n.setPercent(cal_nutrition_percent[n.getIndex()]);
			n.setPercent2max(target[n.getIndex()]*target_max[n.getIndex()]*100-cal_nutrition_percent[n.getIndex()]);
		}
		return cal_nutrition_percent;
	}
	
	public void setBalanceTarget(Float [] target){
		if(target.length!=Define.NUM){
			System.out.println("setBalanceTarget error!length < "+Define.NUM);
			return;
		}
		this.target = target;
		this.isTargetSet = true;
	}
	
	public void sortFood(Integer index, Integer param, Boolean order){
		switch(index){
			case 0:
				Collections.sort(food,new SortByEnergyPerParam(order,param));
				break;
			case 1:
				Collections.sort(food,new SortByProteinPerParam(order,param));
				break;
			case 2:
				Collections.sort(food,new SortByFatPerParam(order,param));
				break;
			case 3:
				Collections.sort(food,new SortByCarbohydratePerParam(order,param));
				break;
			case 4:
				Collections.sort(food,new SortByCaPerParam(order,param));
				break;
			case 5:
				Collections.sort(food,new SortByFePerParam(order,param));
				break;
			case 6:
				Collections.sort(food,new SortByZnPerParam(order,param));
				break;
			case 7:
				Collections.sort(food,new SortByVaPerParam(order,param));
				break;
			case 8:
				Collections.sort(food,new SortByVb1PerParam(order,param));
				break;
			case 9:
				Collections.sort(food,new SortByVb2PerParam(order,param));
				break;
			case 10:
				Collections.sort(food,new SortByVcPerParam(order,param));
				break;
			default:
				break;
		}
	}
	
	
	public void reduceFood(Integer index){
		List<Nutrition> temp = new ArrayList<Nutrition>(nutrition);
		Collections.sort(temp,new SortByPercentAsc());
		Integer param = temp.get(0).getIndex();
		System.out.println("reduce index = " + index + ", param = "+ param);
		boolean isEnd = false;
		sortFood(index,param,Define.ORDER_DESC);
		for(Food f:food){
			Integer gram = f.getGram() - f.getReduce_gram();
			for (Integer i=0;i<gram;i++){
				System.out.println("--------reduce 1g "+f.getFoodName());
				f.setGram(f.getGram()-1);
				for(int j =0;j<Define.NUM;j++){
					gOverTarget10Percent[j]-=f.getIndex(j)/100;
				}
				if(gOverTarget10Percent[index]<0.0f){
					System.out.println("reduce index "+index+" end!");
					isEnd = true;
					break;
				}
			}
			if(isEnd){
				break;
			}
		}
		calNutrition(false);
	}
	
	public void addFood(Integer index, List<Food> except){
		if(except.size() == food.size()){
			System.out.println(" no more food for add");
			return;
		}
		if(except.size() > 0){
			for(Food e:except){
				System.out.println("except "+ e.getFoodName());
			}
		}
		Integer param = 0;
		while(standard.getIndex(index)*target[index]-cal_nutrition[index]>0.0f){
			List<Nutrition> temp = new ArrayList<Nutrition>(nutrition);
			Collections.sort(temp,new SortByPercent2MaxAsc());
			param = temp.get(0).getIndex();
			//System.out.println("add index = " + index + ", param = "+ param);
			sortFood(index,param,Define.ORDER_DESC);
			Iterator<Food> ifood = food.iterator();
			while(ifood.hasNext()){
				Food f = ifood.next();
				Boolean canAdd = true;
				for(Food e:except){
					if(f.getFoodId()==e.getFoodId())
						canAdd = false;
				}
				for(int i =0;i<Define.NUM;i++){
					if(gReachTarget20Percent[i]-f.getIndex(i)/100<0.0f){
						canAdd = false;
					}
				}
				if(f.getGram()>=f.getAdd_gram()){
					canAdd = false;
				}
				if(canAdd){
					System.out.println("++++++++add 1g "+f.getFoodName());
					f.setGram(f.getGram()+1);
					for(int i =0;i<Define.NUM;i++){
						gReachTarget20Percent[i]-=f.getIndex(i)/100;
					}
					calNutrition(false);
					break;
				}
			}
			if(!ifood.hasNext()){
				System.out.println(" no more food end, "+ param+" reach max!");
				break;
			}
		}
		if(standard.getIndex(index)*target[index]-cal_nutrition[index]<0.0f){
			System.out.println(" add index "+index+" end");
		}else{
			sortFood(param,index,Define.ORDER_DESC);
			for(Food f:food){
				if(f.getGram()>f.getReduce_gram()){
					System.out.println("--------reduce 1g "+f.getFoodName());
					f.setGram(f.getGram()-1);
					for(int i =0;i<Define.NUM;i++){
						gReachTarget20Percent[i]+=f.getIndex(i)/100;
					}
					Boolean isExcepted = false;
					for(Food e:except){
						if(f.getFoodId()==e.getFoodId())
							isExcepted = true;
					}
					if(!isExcepted)
						except.add(f);
					break;
				}
			}
			calNutrition(false);
			addFood(index,except);
		}		
	}
	
	public void initTargetOver(){
		for(int i=0;i<Define.NUM;i++){
			gOverTarget10Percent[i] = cal_nutrition[i]-standard.getIndex(i)*(target[i]+target_over[i]);
		}
		System.out.println("nutrition over gram:"+gOverTarget10Percent[0]+",\t"
				   +gOverTarget10Percent[1]+",\t"
				   +gOverTarget10Percent[2]+",\t"
				   +gOverTarget10Percent[3]+",\t"
				   +gOverTarget10Percent[4]+",\t"
				   +gOverTarget10Percent[5]+",\t"
				   +gOverTarget10Percent[6]+",\t"
				   +gOverTarget10Percent[7]+",\t"
				   +gOverTarget10Percent[8]+",\t"
				   +gOverTarget10Percent[9]+",\t"
				   +gOverTarget10Percent[10]);
	}
	
	public void initTargerReach(){
		for (int i=0;i<Define.NUM;i++){
			gReachTarget20Percent[i] = standard.getIndex(i)*(target[i]+target_max[i])-cal_nutrition[i];
		}
		System.out.println("nutrition reach gram:"+gReachTarget20Percent[0]+",\t"
				   +gReachTarget20Percent[1]+",\t"
				   +gReachTarget20Percent[2]+",\t"
				   +gReachTarget20Percent[3]+",\t"
				   +gReachTarget20Percent[4]+",\t"
				   +gReachTarget20Percent[5]+",\t"
				   +gReachTarget20Percent[6]+",\t"
				   +gReachTarget20Percent[7]+",\t"
				   +gReachTarget20Percent[8]+",\t"
				   +gReachTarget20Percent[9]+",\t"
				   +gReachTarget20Percent[10]);
	}
	
	public List<Food> doBalance(){
		if(!this.isFoodInited||!this.isStandardSet||!this.isTargetSet){
			System.out.println("error! not inited, cannot do balance");
			return null;
		}
		calNutrition(true);
		System.out.println("=====================step 1 reduce==========================");
		initTargetOver();		
		Collections.sort(nutrition,new SortByPercentDesc());
		for(Nutrition n:nutrition){
			if(gOverTarget10Percent[n.getIndex()]>0.0f){
				System.out.println(n.getIndex()+" over "+(target[n.getIndex()]+target_over[n.getIndex()])*100+"%");
				reduceFood(n.getIndex());
			}
		}
		System.out.println("=====================step 1 end=============================");
		calNutrition(true);		
		System.out.println("=====================step 2 add=============================");
		initTargerReach();
		Collections.sort(nutrition,new SortByPercent2MaxDesc());
		for(Nutrition n:nutrition){
			if(standard.getIndex(n.getIndex())*target[n.getIndex()]-cal_nutrition[n.getIndex()]>0.0f){
				System.out.println(n.getIndex()+" need reach target");
				addFood(n.getIndex(),new ArrayList<Food>());
			}
		}
		System.out.println("=====================step 2 end=============================");
		calNutrition(true);
		return this.food;
	}
}
