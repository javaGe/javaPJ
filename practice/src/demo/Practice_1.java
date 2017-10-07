package demo;

/**
 * Created by geguangfu on 2017/10/8.
 */
public class Practice_1 {
    private int num;
    public void show() {
        //public int num; 局部变量不可以添加修饰词
        System.out.println("practice_1.show()....");
    }

    public static void main(String[] args) {
        P p = new M();
        M m = (M)p;
        p.show();
        m.show();
    }
}

class P {
    public void show(){
        System.out.println("p.show()....");
    }
}

class M extends P {
    public void show() {
        System.out.println("m.show()....");
    }

    public void show2() {
        System.out.println("show2().....");
    }
}