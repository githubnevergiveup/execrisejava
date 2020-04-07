package com.lin.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyf on 2020/4/4.
 */
@Data
public class DeptTree {




    private Integer deptId;

    private Integer prantDeptId;

    private String deptName;


    private List list=new ArrayList<Object>();

}
