package com.haiziguo.recipe.balence;

public class BalanceParam {
	private Float energy_per;
	private Float protein_per;
	private Float fat_per;
	private Float carbohydrate_per;
	private Float ca_per;
	private Float fe_per;
	private Float zn_per;
	private Float va_per;
	private Float vb1_per;
	private Float vb2_per;
	private Float vc_per;
	
	private final Float min_per = 0.01f;
	private final Float mid_per = 0.05f;
	private final Float max_per = 0.1f;
	
	public String toString(){
		return "standard={"
				+ "energy="+energy_per+",\t"
				+ "protein="+protein_per+",\t"
				+ "fat="+fat_per+",\t"
				+ "carbohydrate="+carbohydrate_per+",\t"
				+ "ca="+ca_per+",\t"
				+ "fe="+fe_per+",\t"
				+ "zn="+zn_per+",\t"
				+ "va="+va_per+",\t"
				+ "vb1="+vb1_per+",\t"
				+ "vb2="+vb2_per+",\t"
				+ "vc="+vc_per+",\t"
				+ "}";
	}
	
	public Float getEnergy_per() {
		return energy_per;
	}
	public void setEnergy_per(Float energy_per) {
		this.energy_per = energy_per;
	}
	public Float getProtein_per() {
		return protein_per;
	}
	public void setProtein_per(Float protein_per) {
		this.protein_per = protein_per;
	}
	public Float getFat_per() {
		return fat_per;
	}
	public void setFat_per(Float fat_per) {
		this.fat_per = fat_per;
	}
	public Float getCarbohydrate_per() {
		return carbohydrate_per;
	}
	public void setCarbohydrate_per(Float carbohydrate_per) {
		this.carbohydrate_per = carbohydrate_per;
	}
	public Float getCa_per() {
		return ca_per;
	}
	public void setCa_per(Float ca_per) {
		this.ca_per = ca_per;
	}
	public Float getFe_per() {
		return fe_per;
	}
	public void setFe_per(Float fe_per) {
		this.fe_per = fe_per;
	}
	public Float getZn_per() {
		return zn_per;
	}
	public void setZn_per(Float zn_per) {
		this.zn_per = zn_per;
	}
	public Float getVa_per() {
		return va_per;
	}
	public void setVa_per(Float va_per) {
		this.va_per = va_per;
	}
	public Float getVb1_per() {
		return vb1_per;
	}
	public void setVb1_per(Float vb1_per) {
		this.vb1_per = vb1_per;
	}
	public Float getVb2_per() {
		return vb2_per;
	}
	public void setVb2_per(Float vb2_per) {
		this.vb2_per = vb2_per;
	}
	public Float getMin_per() {
		return min_per;
	}
	public Float getMid_per() {
		return mid_per;
	}
	public Float getMax_per() {
		return max_per;
	}
	public Float getVc_per() {
		return vc_per;
	}
	public void setVc_per(Float vc_per) {
		this.vc_per = vc_per;
	}
	
}
