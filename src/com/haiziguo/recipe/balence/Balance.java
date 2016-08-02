package com.haiziguo.recipe.balence;

import java.util.List;

public interface Balance {
	
	public void initFoodList(List<Food> food);
	
	public void setStandard(Float [] nutrition);
	
	public void setBalanceTarget(Float [] target);
	
	public void setBalanceTargetMax(Float [] target_max);

	public List<Food> doBalance();
	
	public List<BalanceStep> getSteps();
	
	public void printMenu();
}
