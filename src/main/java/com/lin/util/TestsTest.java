package com.lin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wyf on 2020/4/3.
 */
public class TestsTest {


    public static void main(String[] args){




     /*   Date date = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //calendar.add(Calendar.YEAR, -1);//当前时间减去一年，即一年前的时间
        calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间
        calendar.getTime();//获取一年前的时间，或者一个月前的时间
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMM");
        System.out.println( sdf.format(calendar.getTime()) );*/


        String str="201912";

        String year=str.substring(0,4);

        year = year.replace("", " ").trim();
        String finalStr=year+"年";


        if("0".equals(str.charAt(4)+"")){
            finalStr+= str.substring(5,6)+"月";
        }else{
            finalStr+= str.substring(4,6)+"月";
        }
        System.out.println(finalStr);
     //   System.out.println( str.substring(5,6));
        // System.out.println( str.substring(0,4));

      //  System.out.println( str.substring(4,4));

    }

}
