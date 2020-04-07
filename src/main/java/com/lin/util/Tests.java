package com.lin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by wyf on 2020/4/1.
 */
public class Tests {


    public static void main(String[] args){

        StringBuffer stringBuffer= new StringBuffer();

        new Thread();

        String str="";

        Double doube=0.0;

        Integer integer=new Integer(1);

        int i=1;

        ArrayList list=new ArrayList<Integer>();

        //底层实现，创建一个新迭代器
        list.iterator();

        //底层实现 会在当前size基础+1，去看看需不需要扩容或初始化，初始化为10，数据变换次数增加，如果最小值小于数组的长度则需要扩容；扩容逻辑：
        //新数组为原来数组的1.5倍，把原来的数组复制到新数组中。 在数组末尾添加新元素，size加一。
        list.add("");
        //底层实现 remove对象  判断如果对象为空，循环数组寻找元素为null的，数据变换次数加一，移除指定元素，把该元素之后的元素向前移
        list.remove("");
        //数据变换次数加一，循环数组 都置为null，size=0
        list.clear();

        list.remove(1);

        CopyOnWriteArrayList copyOnWriteArrayList=new CopyOnWriteArrayList<Integer>();
        //CopyOnWriteArrayList的遍历会创建一个迭代器 把原有的元素复制到新迭代器上面，遍历新迭代器；所以不能在遍历的时候进行增删，会直接抛异常
        copyOnWriteArrayList.iterator();
        //进行add操作会先获得锁，并且进行上锁，然后会拿到原来的数组长度并且加1，然后复制，把新元素放进去，替换原来的数组，再finally中解锁
        copyOnWriteArrayList.add(1);
        //获得锁，进行上锁，根据传过来的索引去计算是否是数组中最后一位元素，如果是则复制数组并且下面减一；
        // 如果不是则复制原数组从0开始到删除索引的位置；再复制从索引开始的位置到结束的位置 然后把两个数组拼一起；
        copyOnWriteArrayList.remove(1);
        //获得数组，比较对象获得数组元素下标，如果下标大于0 则执行remove操作，获得锁，上锁
        copyOnWriteArrayList.remove("");


        HashMap<String,Object> hashMap=new HashMap<String,Object>();

        hashMap.put("","");

        hashMap.get("");

        Hashtable<String,Object> hashtable=new Hashtable<String,Object>();
        // put 底层实现 判断value是否为空，为空则抛异常；获取hash表，根据key获取hashcode；去除最高位然后跟hash表长度取余获得索引
        //根据索引去找链表，如果存在，for循环为数组+链表形式，寻找当前索引的链表中有没有相同的key和相同的hash，有就覆盖；没有则进行添加
        //首先结构变更次数增加，，如果该链表的总条数》=阙值，则需要扩容；扩容的方法内容为 说明扩容方案 为原来的两倍+1；如果新的长度-最大长度》0则判断旧长度—最大值是否等于0
        //等于则直接返回；否则最大长度等于扩容长度；创建新扩容后的数据结构；结构变更次数增加；重新计算阙值，新链表等于原来的链表；将原链表的数据循环放到新链表中；然后获取key的hashcode，获取索引的位置:去最高位和链表长度取余；扩容结束。
        //需要对本次新增的元素重新计算索引位置，把新元素添加到新数组中，位于entry的头部；总条数增加。
        hashtable.put("","");

        //get方法底层 获取key的hashcode 然后计算索引位置:去最高位和链表长度取余，根据索引获得entry集合，循环比较key和key的hash如果都相同则返回相应的value，否则返回空。
        hashtable.get("");

        //获取ket的hashcode，计算索引位置:去最高位后和链表长度取余，根据索引获得entry集合，循环集合，寻找key和key的hashcode都相同的值，结构变换次数增加
        //删除节点
        hashtable.remove("");


        ConcurrentHashMap<String,Object> concurrentHashMap=new ConcurrentHashMap<String,Object>();

        concurrentHashMap.put("","");


    }
}
