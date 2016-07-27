package com.haiziguo.recipe.balence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.haiziguo.recipe.sort.*;
import com.haiziguo.recipe.util.Define;

public class Algorithm {

	private Params cal_nutrition = new Params();
	private Params cal_nutrition_percent = new Params();	
	
	
	private Params standard = new Params();			//save permanent standard one day
	private Params target = new Params(Define.DEFAULT_TARGET);	//save target % to standard，can be separated set to different values， default is 80% in all
	private Params target_over_shift = new Params(Define.DEFAULT_TARGET_OVER_SHIFT); // define over percent shift using by reduce steps
	private Params gOverTarget = new Params();// save over grams each nutrition over target_over
	private Params target_max_shift = new Params(Define.DEFAULT_TARGET_MAX_SHIFT);// define max percent shift using by add steps
	private Params gReachTarget = new Params();// save left grams to target_max 
	

	private List<Food> food;
	private List<Nutrition> nutrition = new ArrayList<Nutrition>();
	
	private Boolean isFoodInited = false;
	private Boolean isStandardSet = false;
	private Boolean isTargetSet = false;
	private Integer days = 1;
	
	private List<BalanceStep> step = new ArrayList<BalanceStep>();
	
    /**
     * @author zhangy@mywayinfo.com
     * @version 0.0.1
     * @param food 食物列表
     * @see Food()
     * 
     */
	public void InitFoodList(List<Food> food){
		this.food = new ArrayList<Food>(food);
		days = 1;
		Set<Integer> day = new HashSet<Integer>();
		for(Food f:food){
			day.add(f.getDay());
		}
		days = day.size();
		System.out.println("banlance "+days+" days menu!");
		this.isFoodInited = true;
		Nutrition ENERGY = new Nutrition(Define.ENERGY,cal_nutrition_percent.getIndex(Define.ENERGY));
		nutrition.add(ENERGY);
		Nutrition PROTEIN = new Nutrition(Define.PROTEIN,cal_nutrition_percent.getIndex(Define.PROTEIN));
		nutrition.add(PROTEIN);
		Nutrition FAT = new Nutrition(Define.FAT,cal_nutrition_percent.getIndex(Define.FAT));
		nutrition.add(FAT);
		Nutrition CARBOHYDRATE = new Nutrition(Define.CARBOHYDRATE,cal_nutrition_percent.getIndex(Define.CARBOHYDRATE));
		nutrition.add(CARBOHYDRATE);
		Nutrition CA = new Nutrition(Define.CA,cal_nutrition_percent.getIndex(Define.CA));
		nutrition.add(CA);
		Nutrition FE = new Nutrition(Define.FE,cal_nutrition_percent.getIndex(Define.FE));
		nutrition.add(FE);
		Nutrition ZN = new Nutrition(Define.ZN,cal_nutrition_percent.getIndex(Define.ZN));
		nutrition.add(ZN);
		Nutrition VA = new Nutrition(Define.VA,cal_nutrition_percent.getIndex(Define.VA));
		nutrition.add(VA);
		Nutrition VB1 = new Nutrition(Define.VB1,cal_nutrition_percent.getIndex(Define.VB1));
		nutrition.add(VB1);
		Nutrition VB2 = new Nutrition(Define.VB2,cal_nutrition_percent.getIndex(Define.VB2));
		nutrition.add(VB2);
		Nutrition VC = new Nutrition(Define.VC,cal_nutrition_percent.getIndex(Define.VC));
		nutrition.add(VC);
	}
	
	public void printMenu(){
		Collections.sort(food,new SortById());
		for (Food f:food){
			System.out.println(f.toMenu());
		}
	}
    /**
     * @author zhangy@mywayinfo.com
     * @version 0.0.1
     * @param nutrition []  单日营养素标准
     * 
     */
	public void setStandard(Float [] nutrition){
		if(nutrition ==null){
			System.out.println("SetStandard error!nutrition null");
			return;
		}
		if(nutrition.length!=Define.NUM){
			System.out.println("SetStandard error!length < "+Define.NUM);
			return;
		}
		for(Integer index = 0;index < nutrition.length;index++ ){
			standard.setIndex(index, nutrition[index]);
		}
		System.out.println("standard:"+standard.toString());		
		this.isStandardSet = true;
	}
	
	public void calNutritionPerFood(){
		Params temp = new Params();
		for(Food f:food){
			Integer per = 2;
			for(int i = 0;i<Define.NUM;i++){
				temp.setIndex(i,f.getGram()*f.getFoodPart()*f.getIndex(i)/10000);
				if(temp.getIndex(i)<standard.getIndex(i)*target.getIndex(i)){
					per = 2;
				}else{
					per = (int) (temp.getIndex(i)/standard.getIndex(i)*target.getIndex(i))+2;
					System.out.println("index "+i+" set "+f.getFoodName()+" per = "+per);
				}
			}
			f.setReduce_gram(f.getGram()/per);
		}
	}
	
	public Float[] calNutrition(Boolean debug){
		cal_nutrition.setZero();
		for(Food f:food){
			for(int i =0;i<Define.NUM;i++){
				cal_nutrition.setIndexSum(i, f.getIndex(i)*f.getGram()*f.getFoodPart()/10000);
			}
		}
		if(debug){
			System.out.println("menu list cal_nutrition:" + cal_nutrition.toString());
		}
		
		calNutritionPercent(debug);
		return cal_nutrition.toFloatArray();
	}
	
	public Float [] calNutritionPercent(Boolean debug){
		for(int i =0;i<Define.NUM;i++){
			cal_nutrition_percent.setIndex(i, 100*cal_nutrition.getIndex(i)/standard.getIndex(i));
		}
		if(debug){
			System.out.println("menu list cal_nutrition_percent:"+cal_nutrition_percent.toStringPer());
		}

		for(Nutrition n:nutrition){
			n.setPercent(cal_nutrition_percent.getIndex(n.getIndex()));
			n.setPercent2max((target.getIndex(n.getIndex())+target_max_shift.getIndex(n.getIndex()))*100-cal_nutrition_percent.getIndex(n.getIndex()));
		}
		return cal_nutrition_percent.toFloatArray();
	}
	
    /**
     * @author zhangy@mywayinfo.com
     * @version 0.0.1
     * @param target []  设定配平目标百分比
     * 
     */
	public void setBalanceTarget(Float [] target){
		if(target.length!=Define.NUM){
			System.out.println("setBalanceTarget error!length < "+Define.NUM);
			return;
		}
		this.target = new Params(target);
		System.out.println("target:"+this.target.toStringPer100());
		this.isTargetSet = true;
	}
	
    /**
     * @author zhangy@mywayinfo.com
     * @version 0.0.1
     * @param target_max_shift []  设定配平目标可允许达到的最大百分比与目标百分比的差值
     * @see setBalanceTarget
     */
	public void setBalanceTargetMaxOffset(Float [] target_max_shift){
		if(target_max_shift.length!=Define.NUM){
			System.out.println("setBalanceTarget error!length < "+Define.NUM);
			return;
		}
		this.target_max_shift = new Params(target_max_shift);
		this.target_over_shift = this.target_max_shift.plusFloat(0.5f);
		System.out.println("max:"+this.target_max_shift.toStringPer100());
		System.out.println("over:"+this.target_over_shift.toStringPer100());
		this.isTargetSet = true;
	}
	
    /**
     * @author zhangy@mywayinfo.com
     * @version 0.0.1
     * @param index 前向营养素
     * @param param 后项营养素
     * @param order ORDER_ASC单增，ORDER_DESC单减
     * @see Define
     */
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
			if(f.getIndex(index)<Define.FLOAT_ZERO){
				continue;
			}
			if(!f.getIsAdjustable()){
				continue;
			}
			Integer gram = f.getGram() - f.getReduce_gram();
			for (Integer i=0;i<gram;i++){
				f.setGram(f.getGram()-1);
				BalanceStep r = new BalanceStep();
				r.setAddOrReduce(Define.REDUCE_1G);
				r.setFood(f);
				step.add(r);
				//System.out.println(r.toString());
				for(int j =0;j<Define.NUM;j++){
					gOverTarget.setIndexSum(j, -f.getIndex(j)*f.getFoodPart()/10000);
				}
				if(gOverTarget.getIndex(index)<0.0f){
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
		Integer param = 0;
		outter:
		while(standard.getIndex(index)*target.getIndex(index)-cal_nutrition.getIndex(index)>0.0f){
			List<Nutrition> temp = new ArrayList<Nutrition>(nutrition);
			Collections.sort(temp,new SortByPercent2MaxAsc());
			param = temp.get(0).getIndex();
			//System.out.println("add index = " + index + ", param = "+ param);
			sortFood(index,param,Define.ORDER_DESC);
			Iterator<Food> ifood = food.iterator();
			Food f = new Food();
			inner:
			while(ifood.hasNext()){
				f = ifood.next();
				Boolean canAdd = true;
				if(!f.getIsAdjustable()){
					canAdd = false;
				}
				if(f.getIndex(index)<Define.FLOAT_ZERO){
					canAdd = false;
				}
				if(f.getGram()>=f.getAdd_gram()){
					canAdd = false;
				}
				Iterator<Food> efood = except.iterator();
				Food e = new Food();
				while(efood.hasNext()){
					e = efood.next();
					if(f.getFoodId()==e.getFoodId())
						canAdd = false;
				}
				for(int i =0;i<Define.NUM;i++){
					if(gReachTarget.getIndex(i)-f.getIndex(i)/100<0.0f){
						canAdd = false;
					}
				}
				if(canAdd){
					f.setGram(f.getGram()+1);
					BalanceStep r = new BalanceStep();
					r.setAddOrReduce(Define.ADD_1G);
					r.setFood(f);
					step.add(r);
					//System.out.println(r.toString());
					for(int i =0;i<Define.NUM;i++){
						gReachTarget.setIndexSum(i,-f.getIndex(i)*f.getFoodPart()/10000);
					}
					calNutrition(false);
					break inner;
				}
			}
			if(!ifood.hasNext()){
				System.out.println(" index "+index+" no more food add, "+ param+" reach max!");
				break outter;
			}
		}
		if(standard.getIndex(index)*target.getIndex(index)-cal_nutrition.getIndex(index)<0.0f){
			System.out.println(" add index "+index+" end:"+standard.getIndex(index)+"*"+target.getIndex(index)+"<"+cal_nutrition.getIndex(index));
		}else{
			sortFood(param,index,Define.ORDER_DESC);
			Boolean[] feedback = {false,false,false,false,false,false,false,false,false,false,false};
			Boolean isReduced = false;
			Iterator<Food> ifood = food.iterator();
			while(ifood.hasNext()){
				Food f = ifood.next();
				Boolean isExcepted = false;
				if(f.getIndex(param)<Define.FLOAT_ZERO){
					for(Food e:except){
						if(f.getId()==e.getId())
							isExcepted = true;
					}
					if(!isExcepted)
						except.add(f);
					break;
				}
				if((f.getGram()>=f.getReduce_gram()+Define.REDUCE_STEP)&&f.getIsAdjustable()){
					Float[] old_ = calNutrition(false).clone();
					f.setGram(f.getGram()-Define.REDUCE_STEP);
					isReduced = true;
					BalanceStep r = new BalanceStep();
					r.setAddOrReduce(Define.REDUCE_1G);
					r.setFood(f);
					for(Integer i=0;i<Define.REDUCE_STEP;i++){
						step.add(r);
					}
					//System.out.println(r.toString());
					Float[] new_ = calNutrition(false).clone();
					for(int i =0;i<Define.NUM;i++){
						gReachTarget.setIndexSum(i, Define.REDUCE_STEP*f.getIndex(i)*f.getFoodPart()/10000);
						//System.out.println("target:"+standard.getIndex(i)*target.getIndex(index)+":old:"+old_[i]+":new:"+new_[i]);
						if(new_[i]<standard.getIndex(i)*target.getIndex(index)&&old_[i]>standard.getIndex(i)*target.getIndex(index)){
							feedback[i] = true;
						}else{
							feedback[i] = false;
						}
							
					}
					isExcepted = false;
					for(Food e:except){
						if(f.getId()==e.getId())
							isExcepted = true;
					}
					if(!isExcepted)
						except.add(f);
					break;
				}
			}
			if(!isReduced){
				System.out.println("no more food to reduce!for index"+index + ":param "+param);
				return;
			}
			calNutrition(false);
			addFood(index,except);
			for(int i=0;i<Define.NUM;i++){
				if(feedback[i]&&i!=index){
					System.out.println("index "+index+" feed back add index "+i);
					addFood(i,except);
				}
			}
		}		
	}
	
	public void initTargetOver(){
		for(int i=0;i<Define.NUM;i++){
			gOverTarget.setIndex(i, cal_nutrition.getIndex(i)-standard.getIndex(i)*(target.getIndex(i)+target_over_shift.getIndex(i)));
		}
		System.out.println("nutrition over gram:"+gOverTarget.toString());
	}
	
	public void initTargerReach(){
		for (int i=0;i<Define.NUM;i++){
			gReachTarget.setIndex(i, standard.getIndex(i)*(target.getIndex(i)+target_max_shift.getIndex(i))-cal_nutrition.getIndex(i));
		}
		System.out.println("nutrition reach gram:"+gReachTarget.toString());
	}
	
	public void balanceDays(){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		Iterator<BalanceStep> istep = step.iterator();
		while(istep.hasNext()){
			BalanceStep s = istep.next();
			//System.out.println(s.toString());
			Integer id = s.getFood().getId();
			if(map.containsKey(id)){
				Integer value = map.get(id);
				value +=(s.getAddOrReduce()==1)?1:-1;
				map.put(id, value);
			}else{
				map.put(id, (s.getAddOrReduce()==1)?1:-1);
			}
		}
		System.out.println("balance all use "+step.size()+ "steps, involve " +map.size() + " food in list" );
		
		Iterator<Integer> imap = map.keySet().iterator();
		Set<Integer> foodidmap = new HashSet<Integer>();
		while(imap.hasNext()){
			Integer id = imap.next();
			//System.out.println("id = "+ id+",gram = "+ map.get(id));
			Integer foodid = 0;
			List<Integer> lid = new ArrayList<Integer>();
			for(Food f : food){
				if(f.getId()==id){
					foodid = f.getFoodId();
					//System.out.println(f.toMenu());
				}
			}
			if(foodidmap.contains(foodid)){
				continue;
			}
			foodidmap.add(foodid);
			//System.out.println("find same foodid");
			for(Food f : food){
				//System.out.println(f.toMenu());
				if(f.getFoodId().compareTo(foodid)==0){
					lid.add(f.getId());
					//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+f.toMenu());
				}
			}
			Integer offset = 0;
			for(Integer i:lid){
				for(Food f : food){
					if(f.getId()==i){
						if(map.containsKey(i)){
							offset += map.get(i);
						}
					}
				}
			}
			Integer balance = offset/lid.size();
			Integer remain = offset%lid.size();
			
			if(lid.size()>1){
				System.out.println("id "+id + " have same food in list, lid size = "+lid.size()+", offset = "+offset+",every balance add or reduce = "+balance+",remain " + remain);
			}
			for(Integer i:lid){
				for(Food f : food){
					if(f.getId().compareTo(i)==0){
						if(map.containsKey(i)){
							//System.out.println(" balance "+i+" food " + f.getFoodName() + " foodid " + f.getFoodId()+"gram = "+f.getGram()+" old= "+map.get(i)+" balance " +balance );
							f.setGram(f.getGram()-map.get(i)+balance);
							//System.out.println(f.getGram());
						}else{
							//System.out.println(" balance food not modify" + f.getFoodName() + " foodid " + f.getFoodId());
							f.setGram(f.getGram()+balance);
						}
						if(remain>0){
							f.setGram(f.getGram()+1);
							remain--;
						}else if(remain<0){
							f.setGram(f.getGram()-1);
							remain++;
						}
					}
				}
			}
		}
	}
	public List<Food> doBalance(){
		if(!this.isFoodInited||!this.isStandardSet||!this.isTargetSet){
			System.out.println("error! not inited, cannot do balance");
			return null;
		}
		standard.plusDays(days);
		calNutrition(true);
		calNutritionPerFood();
		System.out.println("=====================step 1 reduce==========================");
		initTargetOver();		
		Collections.sort(nutrition,new SortByPercentDesc());
		for(Nutrition n:nutrition){
			if(gOverTarget.getIndex(n.getIndex())>0.0f){
				System.out.println(n.getIndex()+" over "+(target.getIndex(n.getIndex())+target_over_shift.getIndex(n.getIndex()))*100+"%");
				reduceFood(n.getIndex());
			}
		}
		System.out.println("=====================step 1 end=============================");
		calNutrition(true);		
		System.out.println("=====================step 2 add=============================");
		initTargerReach();
		Collections.sort(nutrition,new SortByPercent2MaxDesc());
		for(Nutrition n:nutrition){
			if(standard.getIndex(n.getIndex())*target.getIndex(n.getIndex())-cal_nutrition.getIndex(n.getIndex())>0.0f){
				System.out.println(n.getIndex()+" need reach target");
				addFood(n.getIndex(),new ArrayList<Food>());
			}
		}
		System.out.println("=====================step 2 end=============================");
		calNutrition(true);
		System.out.println("=====================step 3 balance==========================");
		balanceDays();
		System.out.println("=====================step 3 end==========================");
		calNutrition(true);
		return this.food;
	}
}
