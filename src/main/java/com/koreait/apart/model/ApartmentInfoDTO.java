package com.koreait.apart.model;

import java.util.List;

public class ApartmentInfoDTO { 
	private int location_cd;
	private List<ApartmentInfoEntity> list;
	
	public ApartmentInfoDTO(int location_cd, List<ApartmentInfoEntity> list) { // 생성자 만들고
		super();
		this.location_cd = location_cd;
		this.list = list;
	}
	
	// getter만 만듦
	public int getLocation_cd() {
		return location_cd;
	}

	public List<ApartmentInfoEntity> getList() {
		return list;
	}
	
	
	
	
}
