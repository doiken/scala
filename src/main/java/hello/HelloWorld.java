package hello;

import lib.TestLib;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("hoge");
        System.out.println(new TestLib().getBar());

        if (new TestLib().foo()) {
            System.out.println("foo");
        } else {
            System.out.println("bar");
        }
    }
}
