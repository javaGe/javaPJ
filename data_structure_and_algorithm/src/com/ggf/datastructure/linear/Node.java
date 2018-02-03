package com.ggf.datastructure.linear;

/**
 * @description: 单链表的节点类，一个节点包含了数据域（data）和指针域(next)
 * @author: geguangfu
 * @date：2018/2/2
 * @time：16:40
 * @version: v1.0
 */
public class Node<T> {
    /**
     * 数据域
     */
    public T data;

    /**
     * 指针域，指向下一个节点
     */
    public Node<T> next;

    /**
     * 构造一个节点，data指定数据，next指定后继节点
     * @param data
     * @param next
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * 无参构造方法，构造节点
     */
    public Node() {
        this(null, null);
    }


    /**
     * 返回结点元素值对应的字符串
     */
    @Override
    public String toString() {
        return this.data.toString();
    }

    /**
     * 比较两个结点值是否相等，覆盖Object类的equals(obj)方法
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof Node && this.data.equals(((Node<T>)obj).data);
    }
}

