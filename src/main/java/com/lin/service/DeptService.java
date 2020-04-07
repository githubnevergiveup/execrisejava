package com.lin.service;

import com.lin.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wyf on 2020/4/4.
 */

public interface DeptService {

   public  List<Object> selectByCareId(Integer careId);

}
