package com.lin.service;

import com.lin.dao.DeptDao;
import com.lin.domain.Dept;
import com.lin.domain.User;
import com.lin.util.DeptTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wyf on 2020/4/4.
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;


    @Override
    public List<Object> selectByCareId(Integer careId) {


        List<Dept> deptList=deptDao.selectByCareId(careId);
        //寻找父节点
        List<Object> list=new ArrayList<Object>();

        for(Dept dept:deptList){
            //根据父节点判断  父节点为0
            if(dept.getPrantDeptId()==0){
                //说明是父节点
                //根据父节点去递归找子节点
               DeptTree deptTree=new DeptTree();
               deptTree.setDeptId(dept.getDeptId());
                deptTree.setPrantDeptId(dept.getPrantDeptId());
                deptTree.setDeptName(dept.getDeptName());
                //循环找子节点
                deptTree.setList(findChildren(deptList,dept.getDeptId()));
                list.add(deptTree);
            }

        }
        return list;
    }


    public List<Object> findChildren(List<Dept> list,Integer prantDeptId){

          List<Object> list1=new ArrayList<Object>();

            for(Dept dept : list){

                if(dept.getPrantDeptId()==prantDeptId){

                    DeptTree deptTree=new DeptTree();
                    deptTree.setDeptName(dept.getDeptName());
                    deptTree.setDeptId(dept.getDeptId());
                    deptTree.setPrantDeptId(prantDeptId);
                    //递归
                    deptTree.setList(findChildren(list,dept.getDeptId()));


                    list1.add(deptTree);
                }

            }


    return list1;
    }



}
