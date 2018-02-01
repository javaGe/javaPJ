package com.ggf.datastructure.linear;

/**
 * @description: 顺序线性表，描述线性表的抽象数据类型，泛型 <T>表示数据元素的数据类型
 * @author: geguangfu
 * @date：2018/2/1
 * @time：14:30
 * @version: v1.0
 */
public interface List<T> {
    /**
     * 判断线性表是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 返回线性表的长度
     * @return
     */
    int length();

    /**
     * 返回第i(i>=0)个下标的元素
     * @param i 下标值
     * @return
     */
    T get(int i);

    /**
     * 设置下标为i的值为x
     * @param i 下标值
     * @param x 插入值
     */
    void set(int i, T x);

    /**
     * 在下标为i的位置插入x值
     * @param i
     * @param x
     */
    void insert(int i, T x);

    /**
     * 在线性表末尾插入数据x
     * @param x
     */
    void append(T x);

    /**
     * 删除第i个下标的值，返回被删除的元素
     * @param i
     * @return
     */
    T remove(int i);

    /**
     * 删除所有的元素
     */
    void removeAll();

    /**
     * 查找， 返回首次出现的关键字为key的元素
     * @param key
     * @return
     */
    T search(T key);
}
