package com.haiziguo.recipe.balence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.haiziguo.recipe.sort.*;
import com.haiziguo.recipe.util.Define;
import com.haiziguo.recipe.util.Logger;

public class Algorithm implements Balance{

	private Logger logger = new Logger();
	private Params cal_nutrition = new Params();
	private Params cal_nutrition_percent = new Params();	
	
	private Integer max_retry = 1;
	
	private Params standard = new Params();			//save permanent standard one day
	private Params target = new Params(Define.DEFAULT_TARGET);	//save target % to standard，can be separated set to different values， default is 80% in all
	private Params target_max = new Params(Define.DEFAULT_TARGET_MAX);
	private Params target_over_shift = new Params(Define.DEFAULT_TARGET_OVER_SHIFT); // define over percent shift using by reduce steps
	private Params gOverTarget = new Params();// save over grams each nutrition over target_over
	private Params target_max_shift = new Params(Define.DEFAULT_TARGET_MAX_SHIFT);// define max percent shift using by add steps
	private Params gReachTarget = new Params();// save left grams to target_max 
	

	private List<Food> food;
	private Map<Integer, Food> foodmap;
	private List<Nutrition> nutrition = new ArrayList<Nutrition>();
	private Type type = new Type();
	
	private Boolean isFoodInited = false;
	private Boolean isStandardSet = false;
	private Boolean isTargetSet = false;
	private Integer days = 1;
	private Integer meals = 3;
	
	private List<BalanceStep> step = new ArrayList<BalanceStep>();
	
	private Float checkhighproteinprecent = Define.ENERGY_HIGH_PER;
	private Float protein_per = Define.ENERGY_PROTEIN_UP;
	private Float fat_per = Define.ENERGY_FAT_UP;
	private Float carbohydrate_per = Define.ENERGY_CARBOHYDRATE_UP;
	
    /**
     * @author zhangy@mywayinfo.com
     * @version 0.0.1
     * @param food 食物列表
     * @see Food()
     * 
     */
	public void initFoodList(List<Food> food){
		this.food = new ArrayList<Food>(food);
		foodmap = new HashMap<Integer, Food>();
		days = 1;
		meals = 3;
		Set<Integer> day = new HashSet<Integer>();
		Set<Integer> meal = new HashSet<Integer>();
		for(Food f:food){
			foodmap.put(f.getId(), f);
			day.add(f.getDay());
			meal.add(f.getMeal());
		}
		days = day.size();
		meals = meal.size();
		logger.info("banlance "+days+" days "+meals+" meals/day menu!");
		type.plusDaysAndMeals(days, 1.0f);
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
		logger.info(type.toString());
	}
		
	public void printMenu(){
		Collections.sort(food,new SortById());
		for (Food f:food){
			logger.info(f.toMenu());
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
			logger.error("SetStandard error!nutrition null");
			return;
		}
		if(nutrition.length!=Define.NUM){
			logger.error("SetStandard error!length < "+Define.NUM);
			return;
		}
		for(Integer index = 0;index < nutrition.length;index++ ){
			standard.setIndex(index, nutrition[index]);
		}
		logger.info("standard:"+standard.toString());

		Float energy_all = standard.getIndex(Define.PROTEIN)*4+standard.getIndex(Define.FAT)*9+standard.getIndex(Define.CARBOHYDRATE)*4;
		Float protein_per = standard.getIndex(Define.PROTEIN)*4/energy_all;
		Float fat_per = standard.getIndex(Define.FAT)*9/energy_all;
		Float carbohydrate_per = standard.getIndex(Define.CARBOHYDRATE)*4/energy_all;
		if(protein_per.compareTo(Define.ENERGY_PROTEIN_UP)>0||protein_per.compareTo(Define.ENERGY_PROTEIN_DOWN)<0){
			logger.warn("WARNING:  STANDARD PROTEIN      IS NOT GOOD: protein:" + protein_per*100+"%, fat:"+fat_per*100+"%, carbohydrate:"+carbohydrate_per*100+"%");
		}
		if(fat_per.compareTo(Define.ENERGY_FAT_UP)>0||fat_per.compareTo(Define.ENERGY_FAT_DOWN)<0){
			logger.warn("WARNING:  STANDARD FAT          IS NOT GOOD: protein:" + protein_per*100+"%, fat:"+fat_per*100+"%, carbohydrate:"+carbohydrate_per*100+"%");
		}
		if(carbohydrate_per.compareTo(Define.ENERGY_CARBOHYDRATE_UP)>0||carbohydrate_per.compareTo(Define.ENERGY_CARBOHYDRATE_DOWN)<0){
			logger.warn("WARNING:  STANDARD CARBOHYDRATE IS NOT GOOD: protein:" + protein_per*100+"%, fat:"+fat_per*100+"%, carbohydrate:"+carbohydrate_per*100+"%");
		}
		
		this.isStandardSet = true;
	}
	
	private void calNutritionPerFood(){
		Params temp = new Params();
		for(Food f:food){
			Integer per = 2;
			for(int i = 0;i<Define.NUM;i++){
				temp.setIndex(i,f.getGram()*f.getFoodPart()*f.getIndex(i)/10000);
				if(temp.getIndex(i)<standard.getIndex(i)*target.getIndex(i)){
					per = Math.max(2,per);
				}else{
					per = (int) (temp.getIndex(i)/(standard.getIndex(i)*target.getIndex(i)));
					per += 3;
					logger.info("index "+i+" set "+f.getFoodName()+" per = "+per);
					f.setAdd_gram(f.getGram());
				}
			}
			f.setReduce_gram(f.getGram()/per);
		}
	}
	
	private Float[] calNutrition(Boolean debug){
		cal_nutrition.setZero();
		type.resetCur();
		Float good_protein = 0.0f;
		for(Food f:food){
			for(int i =0;i<Define.NUM;i++){
				cal_nutrition.setIndexSum(i, f.getIndex(i)*f.getGram()*f.getFoodPart()/10000);
			}
			for(Integer i:Define.ENERGY_HIGH){
				if(f.getType3().compareTo(i)==0){
					good_protein += f.getGram()*f.getFoodPart()*f.getProtein()/10000;
				}
			}
			if(Define.FOOD_NULL<f.getType1()&&f.getType1()<Define.FOOD_INVALID){
				type.setTypeCurSelfAdd(f.getType1(), ((float)(f.getGram()*f.getFoodPart()/100)));
			}
		}
		if(debug){
			logger.info("menu list cal_nutrition:" + cal_nutrition.toString());
			logger.info(type.toString());
		}
		Float energy_all = cal_nutrition.getIndex(Define.PROTEIN)*4+cal_nutrition.getIndex(Define.FAT)*9+cal_nutrition.getIndex(Define.CARBOHYDRATE)*4;
		this.protein_per = cal_nutrition.getIndex(Define.PROTEIN)*4/energy_all;
		this.fat_per = cal_nutrition.getIndex(Define.FAT)*9/energy_all;
		this.carbohydrate_per = cal_nutrition.getIndex(Define.CARBOHYDRATE)*4/energy_all;
		if(debug){
			logger.info("energy_all  :" + energy_all);
			logger.info("protein     :" + this.protein_per*100+"%");
			logger.info("fat         :" + this.fat_per*100+"%");
			logger.info("carbohydrate:" + this.carbohydrate_per*100+"%");
		}
		checkhighproteinprecent = good_protein/cal_nutrition.getIndex(Define.PROTEIN);
		if(debug){
			logger.info("high protein:"+checkhighproteinprecent*100+"%");
		}
		calNutritionPercent(debug);
		return cal_nutrition.toFloatArray();
	}
	
	private Float [] calNutritionPercent(Boolean debug){
		for(int i =0;i<Define.NUM;i++){
			cal_nutrition_percent.setIndex(i, 100*cal_nutrition.getIndex(i)/standard.getIndex(i));
		}
		if(debug){
			logger.info("menu list cal_nutrition_percent:"+cal_nutrition_percent.toStringPer());
		}

		for(Nutrition n:nutrition){
			n.setPercent(cal_nutrition_percent.getIndex(n.getIndex()));
			n.setPercent2target(target.getIndex(n.getIndex())*100 - cal_nutrition_percent.getIndex(n.getIndex()));
			n.setPercent2over(cal_nutrition_percent.getIndex(n.getIndex())-(target.getIndex(n.getIndex())+target_over_shift.getIndex(n.getIndex()))*100);
			n.setPercent2max((target.getIndex(n.getIndex())+target_max_shift.getIndex(n.getIndex()))*100-cal_nutrition_percent.getIndex(n.getIndex()));
		}
		return cal_nutrition_percent.toFloatArray();
	}
	
	public Boolean isNutritionMeetTarget(){
		for(Nutrition n:nutrition){
			if(n.getPercent2target()>0.0f)
				return false;
			if(n.getPercent2max()<0.0f)
				return false;
		}
		return true;
	}
	
    /**
     * @author zhangy@mywayinfo.com
     * @version 0.0.1
     * @param target []  设定配平目标百分比
     * 
     */
	public void setBalanceTarget(Float [] target){
		if(target.length!=Define.NUM){
			logger.error("setBalanceTarget error!length < "+Define.NUM);
			return;
		}
		this.target = new Params(target);
		logger.info("target:"+this.target.toStringPer100());
		this.isTargetSet = true;
	}
	
    /**
     * @author zhangy@mywayinfo.com
     * @version 0.0.1
     * @param target_max[]  设定配平目标可允许达到的最大百分比与目标百分比的差值
     * @see setBalanceTarget
     */
	public void setBalanceTargetMax(Float [] target_max){
		if(target_max.length!=Define.NUM){
			logger.error("setBalanceTarget error!length < "+Define.NUM);
			return;
		}
		this.target_max = new Params(target_max);
		this.target_max_shift = this.target_max.minusParams(this.target);
		this.target_over_shift = this.target_max_shift.plusFloat(0.5f);
		logger.info("max_shift:"+this.target_max_shift.toStringPer100());
		logger.info("over_shift:"+this.target_over_shift.toStringPer100());
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
	private void sortFood(Integer index, Integer param, Boolean order){
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
			case 11:
				Collections.sort(food,new SortByVePerParam(order,param));
				break;
			case 12:
				Collections.sort(food,new SortByNaPerParam(order,param));
				break;
			default:
				break;
		}
	}
	
	
	private void reduceFood(Integer index){
		List<Nutrition> temp = new ArrayList<Nutrition>(nutrition);
		Collections.sort(temp,new SortByPercent2OverAsc());
		Integer param = temp.get(0).getIndex();
		logger.info("reduce index = " + index + ", param = "+ param);
		boolean isEnd = false;
		sortFood(index,param,Define.ORDER_DESC);
		for(Food f:food){
			if(f.getIndex(index)<Define.FLOAT_ZERO){
				continue;
			}
			if(!f.getIsAdjustable()){
				continue;
			}
			if(checkhighproteinprecent<Define.ENERGY_HIGH_PER){
				for(Integer i:Define.ENERGY_HIGH){
					if(f.getType3().compareTo(i)==0){
						continue;
					}
				}
			}
			Integer gram = f.getGram() - f.getReduce_gram();
			for (Integer i=0;i<gram;i++){
				f.setGram(f.getGram()-1);
				BalanceStep r = new BalanceStep();
				r.setAddOrReduce(Define.REDUCE_1G);
				r.setFood(f);
				r.setProcess(1);
				step.add(r);
				logger.debug(r.toString());
				for(int j =0;j<Define.NUM;j++){
					gOverTarget.setIndexSum(j, -f.getIndex(j)*f.getFoodPart()/10000);
				}
				if(gOverTarget.getIndex(index)<0.0f){
					logger.info("reduce index "+index+" end!");
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
	
	private void readd(){
		Collections.sort(nutrition,new SortByPercent2TargetDesc());
		for(Nutrition n:nutrition){
			if(n.getPercent2target()>0.0f){
				Integer index = n.getIndex();
				logger.info("need add "+n.getIndex()+" to meet target "+target.getIndex(n.getIndex()));
				outter:
					while(n.getPercent2target()>0.0f){
						List<Nutrition> temp = new ArrayList<Nutrition>(nutrition);
						Collections.sort(temp,new SortByPercent2TargetAsc());
						Integer param = temp.get(0).getIndex();
						sortFood(n.getIndex(),param,Define.ORDER_DESC);
						Iterator<Food> ifood = food.iterator();
						Food f = new Food();
						Boolean isAdded = false;
						
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
								if(checkhighproteinprecent<Define.ENERGY_HIGH_PER){
									for(Integer i:Define.ENERGY_LOW){
										if(f.getType3().compareTo(i)==0){
											canAdd = false;
										}
									}
								}
								if(canAdd){
									isAdded = true;
									f.setGram(f.getGram()+1);
									BalanceStep r = new BalanceStep();
									r.setAddOrReduce(Define.ADD_1G);
									r.setFood(f);
									r.setProcess(5);
									step.add(r);
									logger.debug(r.toString());
									calNutrition(false);
									break inner;
								}
							}
						if(!isAdded){
							logger.warn("no food to add "+ index);
							break outter;
						}
					}
				calNutrition(true);
			}
		}	
	}
	private void addFood(Integer index, List<Food> except){
		if(except.size() == food.size()){
			logger.info("except full no more food for add");
			return;
		}
		Integer param = 0;
		Boolean needReduce = false;
		outter:
		while(standard.getIndex(index)*(target.getIndex(index))>cal_nutrition.getIndex(index)){
			List<Nutrition> temp = new ArrayList<Nutrition>(nutrition);
			Collections.sort(temp,new SortByPercent2MaxAsc());
			param = temp.get(0).getIndex();
			logger.debug("add index = " + index + ", param = "+ param);
			sortFood(index,param,Define.ORDER_DESC);
			Iterator<Food> ifood = food.iterator();
			Food f = new Food();
			Boolean isAdded = false;

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
				if(checkhighproteinprecent<Define.ENERGY_HIGH_PER){
					for(Integer i:Define.ENERGY_LOW){
						if(f.getType3().compareTo(i)==0){
							canAdd = false;
						}
					}
				}
				Iterator<Food> efood = except.iterator();
				Food e = new Food();
				while(efood.hasNext()){
					e = efood.next();
					if(f.getFoodId()==e.getFoodId())
						canAdd = false;
				}
				for(int i =0;i<Define.NUM;i++){
					if(i!=index&&gReachTarget.getIndex(i)<f.getIndex(i)*f.getFoodPart()/10000){
						needReduce = true;
						param = i;
						canAdd = false;
					}
				}
				if(canAdd){
					f.setGram(f.getGram()+1);
					isAdded = true;
					BalanceStep r = new BalanceStep();
					r.setAddOrReduce(Define.ADD_1G);
					r.setFood(f);
					r.setProcess(2);
					step.add(r);
					logger.debug(r.toString());
					for(int i =0;i<Define.NUM;i++){
						gReachTarget.setIndexSum(i,-f.getIndex(i)*f.getFoodPart()/10000);
					}
					calNutrition(false);
					break inner;
				}
			}
			if(!isAdded){
				logger.info(" index "+index+" no more food add, "+ param+" need reduce = "+needReduce);
				break outter;
			}
		}
		if(standard.getIndex(index)*target.getIndex(index)<cal_nutrition.getIndex(index)){
			logger.info(" add index "+index+" end:"+standard.getIndex(index)+"*"+target.getIndex(index)+"<"+cal_nutrition.getIndex(index));
		}else if(needReduce){
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
					continue;
				}
				if(checkhighproteinprecent<Define.ENERGY_HIGH_PER){
					for(Integer i:Define.ENERGY_HIGH){
						if(f.getType3().compareTo(i)==0){
							for(Food e:except){
								if(f.getId()==e.getId())
									isExcepted = true;
							}
							if(!isExcepted)
								except.add(f);
							continue;
						}
					}
				}
				if((f.getGram()>=f.getReduce_gram()+Define.REDUCE_STEP)&&f.getIsAdjustable()){
					Float[] old_ = calNutrition(false).clone();
					f.setGram(f.getGram()-Define.REDUCE_STEP);
					isReduced = true;
					BalanceStep r = new BalanceStep();
					r.setAddOrReduce(Define.REDUCE_1G);
					r.setFood(f);
					r.setProcess(2);
					for(Integer i=0;i<Define.REDUCE_STEP;i++){
						step.add(r);
					}
					logger.debug(r.toString());
					Float[] new_ = calNutrition(false).clone();
					for(int i =0;i<Define.NUM;i++){
						gReachTarget.setIndexSum(i, Define.REDUCE_STEP*f.getIndex(i)*f.getFoodPart()/10000);
						logger.debug("target:"+standard.getIndex(i)*target.getIndex(index)+":old:"+old_[i]+":new:"+new_[i]);
						if(new_[i]<standard.getIndex(i)*target.getIndex(i)&&old_[i]>standard.getIndex(i)*target.getIndex(i)){
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
				logger.info("no more food to reduce!for index"+index + ":param "+param);
				return;
			}
			calNutrition(false);
			addFood(index,except);
			for(int i=0;i<Define.NUM;i++){
				if(feedback[i]&&i!=index){
					logger.info("index "+index+" feed back add index "+i);
					addFood(i,except);
				}
			}
		}else{
			logger.info("no more food add,try again");
		}
	}
	
	private void initTargetOver(){
		for(int i=0;i<Define.NUM;i++){
			gOverTarget.setIndex(i, cal_nutrition.getIndex(i)-standard.getIndex(i)*(target.getIndex(i)+target_over_shift.getIndex(i)));
		}
		logger.info("nutrition over gram:"+gOverTarget.toString());
	}
	
	private void initTargerReach(){
		for (int i=0;i<Define.NUM;i++){
				gReachTarget.setIndex(i, standard.getIndex(i)*(target.getIndex(i)+target_max_shift.getIndex(i)-Define.ENERGY_BALANCE_REMAIN)-cal_nutrition.getIndex(i));
		}
		logger.info("nutrition reach gram:"+gReachTarget.toString());
	}
	private void reinitTargerReach(){
		for (int i=0;i<Define.NUM;i++){
				gReachTarget.setIndexSum(i, standard.getIndex(i)*Define.ENERGY_BALANCE_REMAIN);// for energy balance
		}
		logger.info("nutrition rereach gram:"+gReachTarget.toString());
	}
	
	private void balanceDays(){
		/*
		 *	map     HashMap<Integer,Integer>存放菜谱调整克数的map
		 *	key:    菜谱id
		 *	value:  此id在此前总共的调整克数
		 */
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		Iterator<BalanceStep> istep = step.iterator();
		/*
		 * 	dayOffset   按天统计调整克数
		 * 	key:		day
		 * 	value:		当日调整克数
		 */
		Map<Integer,Integer> dayOffset = new HashMap<Integer,Integer>();
		while(istep.hasNext()){
			BalanceStep s = istep.next();
			logger.debug(s.toString());
			Integer id = s.getFood().getId();
			Integer day = s.getFood().getDay();
			if(map.containsKey(id)){
				Integer value = map.get(id);
				value +=(s.getAddOrReduce()==1)?1:-1;
				map.put(id, value);
			}else{
				map.put(id, (s.getAddOrReduce()==1)?1:-1);
			}
			if(dayOffset.containsKey(day)){
				Integer value = map.get(id);
				value +=(s.getAddOrReduce()==1)?1:-1;
				dayOffset.put(day, value);
			}else{
				dayOffset.put(day,(s.getAddOrReduce()==1)?1:-1);
			}
			
		}
		logger.info("balance all use "+step.size()+ "steps, involve " +map.size() + " food in list" );
		logger.info("gram per day:");
		for(Integer d:dayOffset.keySet()){
			logger.info(" day "+ d+" : "+ dayOffset.get(d)+"g");
		}
		Iterator<Integer> imap = map.keySet().iterator();
		/*
		 * foodidset   存放食物id
		 */
		Set<Integer> foodidset = new HashSet<Integer>();
		while(imap.hasNext()){
			Integer id = imap.next();
			logger.debug("id = "+ id+",gram = "+ map.get(id));
			Integer foodid = 0;
			/*
			 * lid		HashMap<Integer,Integer>()
			 * key:		与当前食谱的foodid相同的食谱id
			 * value:	lgram为上一次累计调整过的克数
			 */
			Map<Integer,Integer> lid = new HashMap<Integer,Integer>();
			for(Food f : food){
				if(f.getId()==id){
					foodid = f.getFoodId();
					logger.debug(f.toMenu());
				}
			}
			if(foodidset.contains(foodid)){
				logger.debug("find same foodid:"+foodid+" id = "+id);
				continue;
			}
			foodidset.add(foodid);
			
			for(Food f : food){
				if(f.getFoodId().compareTo(foodid)==0){
					lid.put(f.getId(),0);
					logger.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!"+f.toMenu());
				}
			}
			/*
			 * offset	foodid一共需要均衡的克数
			 */
			Integer offset = 0;
			for(Integer i:lid.keySet()){
				for(Food f : food){
					if(f.getId()==i){
						if(map.containsKey(i)){
							offset += map.get(i);
						}
					}
				}
			}
			logger.debug("foodid in foodlist total ="+lid.size()+" , change grams "+offset);
			while(Math.abs(offset)>0){
				if(lid.size()==0){
					logger.error("check no foodlist?but offset remains..."+offset);
					break;
				}
				/*
				 * balance	本次平均调整克数
				 * remain	本次预计剩余克数
				 */
				Integer balance = offset/lid.size();
				Integer remain = offset%lid.size();
				if(lid.size()>1){
					logger.info("id "+id + " have same food in list, lid size = "+lid.size()+", offset = "+offset+",every balance add or reduce = "+balance+",remain " + remain);
				}
				/*
				 * rid 	存放下次无法继续调整，被移除的食谱id
				 */
				List<Integer> rid = new ArrayList<Integer>();
				for(Integer i:lid.keySet()){
					Integer lgram = lid.get(i);
					logger.debug("i = "+ i+" value = "+ lgram);
					Food f = foodmap.get(i);
					if(f==null){
						logger.error("food  "+i+" can not find!");
						continue;
					}
					/*
					 * oldg:	平衡前克数
					 * 
					 */
					Integer oldg = f.getGram();
					if(map.containsKey(i)){//食谱有配平记录
						/*
						 * 	bgram:	需要平衡到的克数：计算方式
						 * 			如果lgram为0，说明为首次，还原原始克数oldg-map.get(i)再加上balance克数；
						 * 			否则，直接加上balance克数
						 */
						Integer bgram = (lgram.compareTo(0)==0)?(f.getGram()-map.get(i)+balance):f.getGram()+balance;
						logger.debug(" balance "+i+" food " + f.getFoodName() + " foodid " + f.getFoodId()+" gram = "+f.getGram()+" old= "+map.get(i)+" balance " +balance +" bgram = "+bgram + " lgram = "+lgram);
						if(f.getReduce_gram()<=bgram&&f.getAdd_gram()>=bgram){
								f.setGram(bgram);
								offset-=balance;
								lid.put(i, balance+lgram);//将balance累加进去
						}else if(f.getReduce_gram()>bgram){
								if(lgram.compareTo(0)==0)
									offset-=(f.getReduce_gram() - (f.getGram()-map.get(i)));
								else
									offset-= f.getReduce_gram() - f.getGram();
								f.setGram(f.getReduce_gram());
								rid.add(i);
						}else{
								if(lgram.compareTo(0)==0)
									offset-=(f.getAdd_gram() - (f.getGram()-map.get(i)));
								else
									offset-=f.getAdd_gram()-f.getGram();
								f.setGram(f.getAdd_gram());
								rid.add(i);
						}
						logger.debug(f.getGram()+" offset :"+offset);
					}else{//食谱保持原始克数
						logger.debug(" balance food not modify" + f.getFoodName() + " foodid " + f.getFoodId());
						Integer bgram =f.getGram()+balance;
						if(f.getReduce_gram()<=bgram&&f.getAdd_gram()>=bgram){
							f.setGram(bgram);
							offset-=balance;
							lid.put(i, balance+lgram);
						}else if(f.getReduce_gram()>bgram){
							rid.add(i);
							offset-=f.getReduce_gram()-f.getGram();
							f.setGram(f.getReduce_gram());	
						}else{
							rid.add(i);
							offset-=f.getAdd_gram()-f.getGram();
							f.setGram(f.getAdd_gram());
						}
						logger.debug(f.getGram()+" offset :"+offset);
					}
					if(balance.compareTo(0)==0||lid.size()==1){
						if(remain>0){
							if(f.getGram()+1 <= f.getAdd_gram()){
								f.setGram(f.getGram()+1);
								remain--;
								offset--;
								lid.put(i, 1+lgram);
							}
						}else if(remain<0){
							if(f.getGram()-1 >= f.getReduce_gram()){
								f.setGram(f.getGram()-1);
								remain++;
								offset++;
								lid.put(i, -1+lgram);
							}
						}
					}
					Integer newg = f.getGram();
					BalanceStep r = new BalanceStep();
					r.setFood(f);
					r.setProcess(4);
					if(newg>oldg){
						logger.debug("add "+ (newg-oldg));
						r.setAddOrReduce(Define.ADD_1G);
					}else if(newg<oldg){
						logger.debug("reduce" +(newg-oldg));
						r.setAddOrReduce(Define.REDUCE_1G);
					}
					for(int n=0;n<Math.abs(newg-oldg);n++){
						step.add(r);
					}
					
					Integer oldoffset = dayOffset.get(f.getDay());
					if(oldoffset==null){
						oldoffset = 0;
					}
					dayOffset.put(f.getDay(), oldoffset+newg-oldg);				
				}//end for(Integer i:lid.keySet())
				if(rid.size()>0){
					for(Integer r:rid){
						lid.remove(r);
						logger.debug("lid remove "+r);
					}
				}
			}//end while
		}
		logger.info("after balance gram per day:");
		for(Integer d:dayOffset.keySet()){
			logger.info(" day "+ d+" : "+ dayOffset.get(d)+"g");
		}
	}
	public List<Food> doBalance(){
		if(!this.isFoodInited||!this.isStandardSet||!this.isTargetSet){
			logger.error("error! not inited, cannot do balance");
			return null;
		}
		Date t1 = new Date();
		standard.plusDays(days);
		calNutrition(true);
		if(isNutritionMeetTarget()){
			logger.info("no need to balance");
			return this.food;
		}
		logger.info("=====================step 1 reduce==========================");
		calNutritionPerFood();
		initTargetOver();		
		Collections.sort(nutrition,new SortByPercent2OverDesc());
		for(Nutrition n:nutrition){
			if(gOverTarget.getIndex(n.getIndex())>0.0f){
				logger.info(n.getIndex()+" over "+(target.getIndex(n.getIndex())+target_over_shift.getIndex(n.getIndex()))*100+"%");
				reduceFood(n.getIndex());
			}
		}
		logger.info("=====================step 1 end=============================");
		calNutrition(true);		
		Date t2 = new Date();
		Integer n1=step.size();

		logger.info("=====================step 2 add=============================");
		initTargerReach();
		Collections.sort(nutrition,new SortByPercent2TargetDesc());
		for(Nutrition n:nutrition){
			if(standard.getIndex(n.getIndex())*target.getIndex(n.getIndex())>cal_nutrition.getIndex(n.getIndex())){
				logger.info(n.getIndex()+" need reach target");
				addFood(n.getIndex(),new ArrayList<Food>());
			}
		}
		logger.info("=====================step 2 end=============================");
		calNutrition(true);
		//printMenu();
		Date t3 = new Date();
		Integer n2=step.size();
		
		logger.info("=====================step 3 energy cost==========================");
		reinitTargerReach();
		logger.info("protein     :" + this.protein_per*100+"%");
		logger.info("fat         :" + this.fat_per*100+"%");
		logger.info("carbohydrate:" + this.carbohydrate_per*100+"%");
		balanceEnergy(new ArrayList<Food>());
		logger.info("protein     :" + this.protein_per*100+"%");
		logger.info("fat         :" + this.fat_per*100+"%");
		logger.info("carbohydrate:" + this.carbohydrate_per*100+"%");
		logger.info("=====================step 3 end==========================");
		calNutrition(true);
		Date t4 = new Date();
		Integer n3=step.size();

		logger.info("=====================step 4 balance==========================");
		balanceDays();
		logger.info("=====================step 4 end==========================");
		calNutrition(true);
		Date t5 = new Date();
		Integer n4=step.size();
		logger.info("step 1 total "+ n1 + " steps  cost=" +(t2.getTime()-t1.getTime())+"ms");
		logger.info("step 2 total "+ (n2-n1) + " steps  cost=" +(t3.getTime()-t2.getTime())+"ms");
		logger.info("step 3 total "+ (n3-n2) + " steps  cost=" +(t4.getTime()-t3.getTime())+"ms");
		logger.info("step 4 total "+ (n4-n3) + " steps  cost=" +(t5.getTime()-t4.getTime())+"ms");
		if(isNutritionMeetTarget()){
			logger.info("done");
		}else{
			logger.warn("balance end! not meet target!");
			readd();
			balanceDays();
			calNutrition(true);
		}
		return this.food;
	}
	
	private void balanceReduce(Integer index,List<Food> except){
		Collections.sort(food,new SortByParamEnergy(Define.ORDER_DESC,index));
		Boolean isReduced = false;
		Boolean[] feedback = {false,false,false,false,false,false,false,false,false,false,false};
		for(Food f:food){
			Boolean canReduce = true;
			if(!f.getIsAdjustable()){
				canReduce = false;
				continue;
			}
			if(f.getIndex(index)<Define.FLOAT_ZERO){
				canReduce = false;
				continue;
			}
			if(f.getGram()<=f.getReduce_gram()){
				canReduce = false;
				continue;
			}
			if(index==Define.PROTEIN){
				Float all = (cal_nutrition.getProtein()-f.getProtein()*f.getFoodPart()/10000)*4
						+(cal_nutrition.getFat()-f.getFat()*f.getFoodPart()/10000)*9
						+(cal_nutrition.getCarbohydrate()-f.getCarbohydrate()*f.getFoodPart()/10000)*4;
				Float fp = (cal_nutrition.getProtein()-f.getProtein()*f.getFoodPart()/10000)*4/all;
				if(fp>protein_per){
					canReduce= false;
					continue;
				}
			}else if(index==Define.FAT){
				Float all = (cal_nutrition.getProtein()-f.getProtein()*f.getFoodPart()/10000)*4
						+(cal_nutrition.getFat()-f.getFat()*f.getFoodPart()/10000)*9
						+(cal_nutrition.getCarbohydrate()-f.getCarbohydrate()*f.getFoodPart()/10000)*4;
				Float fp = (cal_nutrition.getFat()-f.getFat()*f.getFoodPart()/10000)*9/all;
				if(fp>fat_per){
					canReduce= false;
					continue;
				}
			}else if(index==Define.CARBOHYDRATE){
				Float all = (cal_nutrition.getProtein()-f.getProtein()*f.getFoodPart()/10000)*4
						+(cal_nutrition.getFat()-f.getFat()*f.getFoodPart()/10000)*9
						+(cal_nutrition.getCarbohydrate()-f.getCarbohydrate()*f.getFoodPart()/10000)*4;
				Float fp = (cal_nutrition.getCarbohydrate()-f.getCarbohydrate()*f.getFoodPart()/10000)*4/all;
				if(fp>carbohydrate_per){
					canReduce= false;
					continue;
				}
			}
			if(checkhighproteinprecent<Define.ENERGY_HIGH_PER){
				for(Integer i:Define.ENERGY_HIGH){
					if(f.getType3().compareTo(i)==0){
						canReduce = false;
						continue;
					}
				}
			}
			if(canReduce){
				Float[] old_ = calNutrition(false).clone();
				f.setGram(f.getGram()-1);
				isReduced = true;
				BalanceStep r = new BalanceStep();
				r.setAddOrReduce(Define.REDUCE_1G);
				r.setFood(f);
				r.setProcess(3);
				step.add(r);
				logger.debug(r.toString());
				Boolean isExcepted=false;
				for(Food e:except){
					if(f.getId()==e.getId())
						isExcepted = true;
				}
				if(!isExcepted)
					except.add(f);
				Float[] new_ = calNutrition(false).clone();
				for(int i =0;i<Define.NUM;i++){
					gReachTarget.setIndexSum(i,f.getIndex(i)*f.getFoodPart()/10000);
					logger.debug("target:"+standard.getIndex(i)*target.getIndex(index)+":old:"+old_[i]+":new:"+new_[i]);
					if(new_[i]<standard.getIndex(i)*target.getIndex(i)&&old_[i]>standard.getIndex(i)*target.getIndex(i)){
						feedback[i] = true;
					}else{
						feedback[i] = false;
					}
						
				}
				break;
			}
		}
		if(isReduced){
			for(int i=0;i<Define.NUM;i++){
				if(feedback[i]&&i!=index){
					logger.info("index "+index+" feed back add index "+i);
					addFood(i,except);
				}
			}
			for(int i=0;i<Define.NUM;i++){
				if(cal_nutrition.getIndex(i)<standard.getIndex(i)*target.getIndex(i)){
					logger.info("add feedback fail");
					return;
				}
			}
			balanceEnergy(except);
		}else{
			logger.info("no food cannot reduce index"+index);
		}
	}
	
	private void balanceAdd(Integer index,List<Food> except){
		Collections.sort(food,new SortByParamEnergy(Define.ORDER_DESC,index));
		Boolean isAdded = false;
		for(Food f:food){
			Boolean canAdd = true;
			if(!f.getIsAdjustable()){
				canAdd = false;
				continue;
			}
			if(f.getIndex(index)<Define.FLOAT_ZERO){
				canAdd = false;
				continue;
			}
			if(f.getGram()>=f.getAdd_gram()){
				canAdd = false;
				continue;
			}
			Iterator<Food> efood = except.iterator();
			Food e = new Food();
			while(efood.hasNext()){
				e = efood.next();
				if(f.getFoodId()==e.getFoodId()){
					canAdd = false;
					continue;
				}
			}
			if(index==Define.PROTEIN){
				Float all = (cal_nutrition.getProtein()+f.getProtein()*f.getFoodPart()/10000)*4
						+(cal_nutrition.getFat()+f.getFat()*f.getFoodPart()/10000)*9
						+(cal_nutrition.getCarbohydrate()+f.getCarbohydrate()*f.getFoodPart()/10000)*4;
				Float fp = (cal_nutrition.getProtein()+f.getProtein()*f.getFoodPart()/10000)*4/all;
				if(fp<protein_per){
					canAdd= false;
					continue;
				}
			}else if(index==Define.FAT){
				Float all = (cal_nutrition.getProtein()+f.getProtein()*f.getFoodPart()/10000)*4
						+(cal_nutrition.getFat()+f.getFat()*f.getFoodPart()/10000)*9
						+(cal_nutrition.getCarbohydrate()+f.getCarbohydrate()*f.getFoodPart()/10000)*4;
				Float fp = (cal_nutrition.getFat()+f.getFat()*f.getFoodPart()/10000)*9/all;
				if(fp<fat_per){
					canAdd= false;
					continue;
				}
			}else if(index==Define.CARBOHYDRATE){
				Float all = (cal_nutrition.getProtein()+f.getProtein()*f.getFoodPart()/10000)*4
						+(cal_nutrition.getFat()+f.getFat()*f.getFoodPart()/10000)*9
						+(cal_nutrition.getCarbohydrate()+f.getCarbohydrate()*f.getFoodPart()/10000)*4;
				Float fp = (cal_nutrition.getCarbohydrate()+f.getCarbohydrate()*f.getFoodPart()/10000)*4/all;
				if(fp<carbohydrate_per){
					canAdd= false;
					continue;
				}
			}
			if(checkhighproteinprecent<Define.ENERGY_HIGH_PER){
				for(Integer i:Define.ENERGY_LOW){
					if(f.getType3().compareTo(i)==0){
						canAdd = false;
						continue;
					}
				}
			}
			for(int i =0;i<Define.NUM;i++){
				if(gReachTarget.getIndex(i)<f.getIndex(i)*f.getFoodPart()/10000){
					canAdd = false;
					continue;
				}
			}
			if(canAdd){
				f.setGram(f.getGram()+1);
				isAdded = true;
				BalanceStep r = new BalanceStep();
				r.setAddOrReduce(Define.ADD_1G);
				r.setFood(f);
				r.setProcess(3);
				step.add(r);
				logger.debug(r.toString());
				for(int i =0;i<Define.NUM;i++){
					gReachTarget.setIndexSum(i,-f.getIndex(i)*f.getFoodPart()/10000);
				}
				calNutrition(false);
				break;
			}
		}
		if(isAdded){
			balanceEnergy(except);
		}else{
			logger.info("no food cannot add index "+index);
		}
	}
	
	private void balanceEnergy(List<Food> except) {
		if(this.protein_per>Define.ENERGY_PROTEIN_DOWN&&this.protein_per<Define.ENERGY_PROTEIN_UP
				&&this.fat_per>Define.ENERGY_FAT_DOWN&&this.fat_per<Define.ENERGY_FAT_UP
				&&this.carbohydrate_per>Define.ENERGY_CARBOHYDRATE_DOWN&&this.carbohydrate_per<Define.ENERGY_CARBOHYDRATE_UP){
			return;
		}
		if(except.size()==food.size()){
			logger.info("no more food");
			return;
		}
		
		if(this.protein_per>Define.ENERGY_PROTEIN_UP){
			if(this.fat_per>Define.ENERGY_FAT_UP){
				if(this.carbohydrate_per>Define.ENERGY_CARBOHYDRATE_UP){
					logger.error("1 need check ");
				}else if(this.carbohydrate_per<Define.ENERGY_CARBOHYDRATE_DOWN){
					logger.debug("add CARBOHYDRATE");
					balanceAdd(Define.CARBOHYDRATE,except);
				}else{
					logger.debug("add CARBOHYDRATE");
					balanceAdd(Define.CARBOHYDRATE,except);
				}
			}else if(this.fat_per<Define.ENERGY_FAT_DOWN){
				if(this.carbohydrate_per>Define.ENERGY_CARBOHYDRATE_UP){
					logger.debug("add FAT");
					balanceAdd(Define.FAT,except);
				}else if(this.carbohydrate_per<Define.ENERGY_CARBOHYDRATE_DOWN){
					logger.debug("reduce PROTEIN");
					balanceReduce(Define.PROTEIN,except);
				}else{
					logger.debug("add FAT");
					balanceAdd(Define.FAT,except);
				}
			}else{
				if(this.carbohydrate_per>Define.ENERGY_CARBOHYDRATE_UP){
					logger.debug("add FAT");
					balanceAdd(Define.FAT,except);
				}else if(this.carbohydrate_per<Define.ENERGY_CARBOHYDRATE_DOWN){
					logger.debug("add CARBOHYDRATE");
					balanceAdd(Define.CARBOHYDRATE,except);
				}else{
					logger.debug("reduce PROTEIN");
					balanceReduce(Define.PROTEIN,except);
				}
			}

		}else if(this.protein_per<Define.ENERGY_PROTEIN_DOWN){
			if(this.fat_per>Define.ENERGY_FAT_UP){
				if(this.carbohydrate_per>Define.ENERGY_CARBOHYDRATE_UP){
					logger.debug("add PROTEIN");
					balanceAdd(Define.PROTEIN,except);
				}else if(this.carbohydrate_per<Define.ENERGY_CARBOHYDRATE_DOWN){
					logger.debug("reduce FAT");
					balanceReduce(Define.FAT,except);
				}else{
					logger.debug("add PROTEIN");
					balanceAdd(Define.PROTEIN,except);
				}
			}else if(this.fat_per<Define.ENERGY_FAT_DOWN){
				if(this.carbohydrate_per>Define.ENERGY_CARBOHYDRATE_UP){
					logger.debug("reduce CARBOHYDRATE");
					balanceReduce(Define.CARBOHYDRATE,except);
				}else if(this.carbohydrate_per<Define.ENERGY_CARBOHYDRATE_DOWN){
					logger.error("need check ");
				}else{
					logger.debug("reduce CARBOHYDRATE");
					balanceReduce(Define.CARBOHYDRATE,except);
				}
			}else{
				if(this.carbohydrate_per>Define.ENERGY_CARBOHYDRATE_UP){
					logger.debug("add PROTEIN");
					balanceAdd(Define.PROTEIN,except);
				}else if(this.carbohydrate_per<Define.ENERGY_CARBOHYDRATE_DOWN){
					logger.debug("reduce FAT");
					balanceReduce(Define.FAT,except);
				}else{
					logger.debug("add PROTEIN");
					balanceAdd(Define.PROTEIN,except);
				}
			}
		}else{
			if(this.fat_per>Define.ENERGY_FAT_UP){
				if(this.carbohydrate_per>Define.ENERGY_CARBOHYDRATE_UP){
					logger.debug("add PROTEIN");
					balanceAdd(Define.PROTEIN,except);
				}else if(this.carbohydrate_per<Define.ENERGY_CARBOHYDRATE_DOWN){
					logger.debug("add CARBOHYDRATE");
					balanceAdd(Define.CARBOHYDRATE,except);
				}else{
					logger.debug("reduce FAT");
					balanceReduce(Define.FAT,except);
				}
			}else if(this.fat_per<Define.ENERGY_FAT_DOWN){
				if(this.carbohydrate_per>Define.ENERGY_CARBOHYDRATE_UP){
					logger.debug("add FAT");
					balanceAdd(Define.FAT,except);
				}else if(this.carbohydrate_per<Define.ENERGY_CARBOHYDRATE_DOWN){
					logger.debug("reduce PROTEIN");
					balanceReduce(Define.PROTEIN,except);
				}else{
					logger.debug("add FAT");
					balanceAdd(Define.FAT,except);
				}
			}else{
				if(this.carbohydrate_per>Define.ENERGY_CARBOHYDRATE_UP){
					logger.debug("reduce CARBOHYDRATE");
					balanceReduce(Define.CARBOHYDRATE,except);
				}else if(this.carbohydrate_per<Define.ENERGY_CARBOHYDRATE_DOWN){
					logger.debug("add CARBOHYDRATE");
					balanceAdd(Define.CARBOHYDRATE,except);
				}else{
					return;
				}
			}
		}
		return;
	}

	public List<BalanceStep> getSteps(){
		return this.step;
	}
	
	public List<Object> getStepsMap(){
		List<Object> l = new ArrayList<Object>();
		Map<Integer,Integer> m = new HashMap<Integer,Integer>();
		Iterator<BalanceStep> i = this.step.iterator();
		while(i.hasNext()){
			BalanceStep s = i.next();
			m.put(s.getAddOrReduce(), s.getFood().getFoodId());
			l.add(m);
			m.clear();
		}
		return l;
	}
	
	public Map<Integer,Integer> getResultsMap(){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		Collections.sort(food,new SortById());
		for (Food f:food){
			map.put(f.getId(), f.getGram());
		}
		return map;
	}
	
	public void setLogLevel(Integer level){
		logger.setLogLevel(level);
	}
	
	public void logOnOff(Boolean on){
		logger.setLogOn(on);
	}

	public Integer getMax_retry() {
		return max_retry;
	}

	public void setMax_retry(Integer max_retry) {
		this.max_retry = max_retry;
	}
}
