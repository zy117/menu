package com.haiziguo.recipe.balence;

public class Standard {
	private Float energy;
	private Float protein;
	private Float fat;
	private Float carbohydrate;
	private Float ca;
	private Float fe;
	private Float zn;
	private Float va;
	private Float vb1;
	private Float vb2;
	private Float vc;
	
	public String toString(){
		return "standard={"
				+ "energy="+energy+",\t"
				+ "protein="+protein+",\t"
				+ "fat="+fat+",\t"
				+ "carbohydrate="+carbohydrate+",\t"
				+ "ca="+ca+",\t"
				+ "fe="+fe+",\t"
				+ "zn="+zn+",\t"
				+ "va="+va+",\t"
				+ "vb1="+vb1+",\t"
				+ "vb2="+vb2+",\t"
				+ "vc="+vb2+",\t"
				+ "}";
	}
	
	public Float getIndex(Integer index){
		switch(index){
		case 1:
			return protein;
		case 2:
			return fat;
		case 3:
			return carbohydrate;
		case 4:
			return ca;
		case 5:
			return fe;
		case 6:
			return zn;
		case 7:
			return va;
		case 8:
			return vb1;
		case 9:
			return vb2;
		case 10:
			return vc;
		case 0:
		default:
			return energy;
		}
	}
	public Float getEnergy() {
		return energy;
	}
	public void setEnergy(Float energy) {
		this.energy = energy;
	}
	public Float getProtein() {
		return protein;
	}
	public void setProtein(Float protein) {
		this.protein = protein;
	}
	public Float getFat() {
		return fat;
	}
	public void setFat(Float fat) {
		this.fat = fat;
	}
	public Float getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(Float carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public Float getCa() {
		return ca;
	}
	public void setCa(Float ca) {
		this.ca = ca;
	}
	public Float getFe() {
		return fe;
	}
	public void setFe(Float fe) {
		this.fe = fe;
	}
	public Float getZn() {
		return zn;
	}
	public void setZn(Float zn) {
		this.zn = zn;
	}
	public Float getVa() {
		return va;
	}
	public void setVa(Float va) {
		this.va = va;
	}
	public Float getVb1() {
		return vb1;
	}
	public void setVb1(Float vb1) {
		this.vb1 = vb1;
	}
	public Float getVb2() {
		return vb2;
	}
	public void setVb2(Float vb2) {
		this.vb2 = vb2;
	}

	public Float getVc() {
		return vc;
	}

	public void setVc(Float vc) {
		this.vc = vc;
	}
}
