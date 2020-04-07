package com.lin.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyf on 2020/4/4.
 */
public class DeptTree {




    public Integer getDeptId() {
		return deptId;
	}


	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}


	public Integer getPrantDeptId() {
		return prantDeptId;
	}


	public void setPrantDeptId(Integer prantDeptId) {
		this.prantDeptId = prantDeptId;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public List getList() {
		return list;
	}


	public void setList(List list) {
		this.list = list;
	}


	private Integer deptId;

    private Integer prantDeptId;

    private String deptName;


    private List list=new ArrayList<Object>();

}
