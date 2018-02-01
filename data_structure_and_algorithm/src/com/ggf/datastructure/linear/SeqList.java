package com.ggf.datastructure.linear;

import java.util.Arrays;

/**
 * @description: 具体实行顺序线性表接口List<T>
 * @author: geguangfu
 * @date：2018/2/1
 * @time：15:42
 * @version: v1.0
 */
public class SeqList<T> implements List<T> {

    /**
     * 对象数组
     */
    protected Object[] element;

    /**
     * 顺序表的长度，记载元素的个数
     */
    protected int len;

    /**
     * 无参构造函数，初始化一个长度为64的数组
     */
    public SeqList() {
        this(3);
    }

    /**
     * 有参构造函数，创建一个容量为size的空表
     * @param size
     */
    public SeqList(int size) {
        this.element = new Object[size];
        this.len = 0;
    }

    /**
     * 判断顺序表是否为空，如空返回true
     * @return
     */
    @Override
    public boolean isEmpty() {
        // 如果该顺序表对象长度为0，就是空
        return this.len == 0;
    }

    /**
     * 返回顺序表的长度
     * @return
     */
    @Override
    public int length() {
        return this.len;
    }

    /**
     * 获取下标为i的元素,若i不合法，抛出数组下标越界
     * @param i 下标值
     * @return
     */
    @Override
    public T get(int i) {
        if (i <= 0 && i > this.len) {
            throw new IndexOutOfBoundsException("Index:"+i+",Size:"+this.len);
        }

        return (T)this.element[i];

    }

    /**
     * 设置第i（≥0）个元素值为x(替换某个值)。若i<0或大于表长则抛出序号越界异常；若x==null，不操作
     * @param i 下标值
     * @param x 插入值
     */
    @Override
    public void set(int i, T x) {
        if (i < 0 || i > this.len) {
            throw new IndexOutOfBoundsException("Index:"+i+",Size:"+this.len);
        }else {
            this.element[i] = x;
        }
    }

    /**
     *顺序表的插入操作 插入第i（≥0）个元素值为x。若x==null，不插入。
     * 若i<0，插入x作为第0个元素；若i大于表长，插入x作为最后一个元素
     * @param i
     * @param x
     */
    @Override
    public void insert(int i, T x) {
        // 如果原数组已经满了，进行扩容
        if (this.len == element.length) {
           /* // 创建一个临时的数组
            Object[] temp = this.element;
            //把原来数组长度扩大两倍的长度
            this.element = new Object[temp.length * 2];
*/
           this.element = Arrays.copyOf(this.element, this.element.length * 2);
        }

        // 下标容错
        if (i < 0)
            i = 0;

        if (i > this.len)
            i = this.len;

        // 插入元素，元素往后移动
        for (int j=this.len-1; j>=i; j--) {
            this.element[j+1] = this.element[j];
        }

        this.element[i] = x;
        this.len++;
    }

    @Override
    public void append(T x) {
        insert(this.len, x);
    }

    /**
     * 删除给定下标i对应的值，返回该值
     * @param i
     * @return
     */
    @Override
    public T remove(int i) {
        if (i < 0 || i > this.len) {
            throw new IndexOutOfBoundsException("Index:"+i+",Size:"+this.len);
        }

        // 获取原来位置的值
        T oldValue = (T) this.element[i];

        // 将元素往前移动
        for (int j=i; j<this.len-1; j++) {
            this.element[j] = this.element[j+1];
        }

        // 将最后一个元素置空，交给gc回收
        this.element[this.len-1] = null;
        // 长度减一
        this.len--;

        // 返回删除的值
        return oldValue;
    }

    /**
     * 删除所有的值，将长度置0(顺序表空)
     */
    @Override
    public void removeAll() {
        this.len = 0;
    }

    @Override
    public T search(T key) {
        return null;
    }



    public static void main(String[] args) {
        SeqList<Integer> list = new SeqList<Integer>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.insert(0,4);

        System.out.println(list.length());
        for (int i=0; i<list.length(); i++) {
            System.out.println(list.get(i));
        }

    }
}
