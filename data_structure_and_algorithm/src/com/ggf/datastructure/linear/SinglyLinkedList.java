package com.ggf.datastructure.linear;

/**
 * @description: 线性的单链表实现
 * @author: geguangfu
 * @date：2018/2/2
 * @time：17:01
 * @version: v1.0
 */
public class SinglyLinkedList<T> {
    /**
     * 根据链表的特性，必须包含一个头节点
     */
    public Node<T> head;

    /**
     * 默认的构造方法，创建一个空的单链表
     */
    public SinglyLinkedList() {
        // 创建头结点，data和next都是null
        this.head = new Node<T>();
    }


    /**
     * 由指定数组中的多个对象构造单链表。采用尾插入构造单链表
     * 每次由前一个节点指向后一个节点
     * @param element
     */
    public SinglyLinkedList(T[] element) {
        // 初始化一个头节点
        this();

        // rear节点每次指向最后一个节点
        Node<T> rear = this.head;

        for (int i=0; i<element.length; i++) {
            // 一开始它是头节点，然后指向第一个节点
            rear.next = new Node<T>(element[i], null);
            // 将
            rear = rear.next;
        }
    }
}
