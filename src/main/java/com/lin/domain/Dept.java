package com.lin.domain;

import lombok.Data;

/**
 * Created by wyf on 2020/4/4.
 */

public class Dept {


    public Integer getDeptId() {
		return deptId;
	}


	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public Integer getPrantDeptId() {
		return prantDeptId;
	}


	public void setPrantDeptId(Integer prantDeptId) {
		this.prantDeptId = prantDeptId;
	}


	public Integer getCareId() {
		return careId;
	}


	public void setCareId(Integer careId) {
		this.careId = careId;
	}


	private Integer deptId;


    private String deptName;


    private Integer prantDeptId;


    private Integer careId;

    
    
    

}
