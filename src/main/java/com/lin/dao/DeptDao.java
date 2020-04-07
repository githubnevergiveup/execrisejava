package com.lin.dao;


import com.lin.domain.Dept;
import com.lin.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 功能概要：User的DAO类
 * 
 * @author linbingwen
 * @since 2015年9月28日
 */
public interface DeptDao {

	List<Dept> selectByCareId(Integer careId);
	

	 

}
