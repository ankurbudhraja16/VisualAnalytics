package com.model;

public class Data {

	int count;
	int weaponFirearms;
	int weaponExplosives;
	int weaponChemicals;
	int weaponIncendiary;
	int weaponBiological;
	int others;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getWeaponFirearms() {
		return weaponFirearms;
	}
	public void setWeaponFirearms(int weaponFirearms) {
		this.weaponFirearms = weaponFirearms;
	}
	public int getWeaponExplosives() {
		return weaponExplosives;
	}
	public void setWeaponExplosives(int weaponExplosives) {
		this.weaponExplosives = weaponExplosives;
	}
	public int getWeaponChemicals() {
		return weaponChemicals;
	}
	public void setWeaponChemicals(int weaponChemicals) {
		this.weaponChemicals = weaponChemicals;
	}
	public int getWeaponIncendiary() {
		return weaponIncendiary;
	}
	public void setWeaponIncendiary(int weaponIncendiary) {
		this.weaponIncendiary = weaponIncendiary;
	}
	public int getWeaponBiological() {
		return weaponBiological;
	}
	public void setWeaponBiological(int weaponBiological) {
		this.weaponBiological = weaponBiological;
	}
	public int getOthers() {
		return others;
	}
	public void setOthers(int others) {
		this.others = others;
	}
	@Override
	public String toString() {
		return "Data [count=" + count + ", weaponFirearms=" + weaponFirearms + ", weaponExplosives=" + weaponExplosives
				+ ", weaponChemicals=" + weaponChemicals + ", weaponIncendiary=" + weaponIncendiary
				+ ", weaponBiological=" + weaponBiological + ", others=" + others + "]";
	}
	public Data() {
		super();
	}
	
	
	
	
}
