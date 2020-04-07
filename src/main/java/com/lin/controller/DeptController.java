package com.lin.controller;


import com.alibaba.fastjson.JSON;
import com.lin.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能概要：UserController
 * 
 * @author linbingwen
 * @since  2015年9月28日 
 */
@Controller
public class DeptController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private DeptService deptService;

    /**
     * 分页查询用户信息
     * @author linbingwen
     * @since  2015年10月23日 
     * @param
     * @return
     */
  @RequestMapping(value="/deptList.do", method= RequestMethod.POST)
    @ResponseBody
    public String list(Integer careId) {
		//logger.info("分页查询用户信息列表请求入参：pageNumber{},pageSize{}", pageNumber,pageSize);
		try {
			List<Object> list=deptService.selectByCareId(careId);
			//哈哈哈哈哈
    	    return JSON.toJSONString(list);
    	} catch (Exception e) {
			return responseFail(e.getMessage());
		}
    }
}
