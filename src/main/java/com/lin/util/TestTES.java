package com.lin.util;

/**
 * Created by wyf on 2020/4/3.
 */
public class TestTES {



    public static void main(String[] args){

        int[] array = {1, 2, 3, 4, 5};
        int[] targetArr = new int[array.length];

        System.arraycopy(array,0,targetArr,0,3);

        //System.out.println(targetArr.toString());

        for(int i=0;i<targetArr.length;i++){
            System.out.println(targetArr[i]);
        }

    }


}
