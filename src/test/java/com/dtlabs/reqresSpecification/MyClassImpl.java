package com.dtlabs.reqresSpecification;

public class MyClassImpl implements MyInterface{

    private MyInterface MyInterface;

    public MyInterface printMe(){
        System.out.println("Print me");
        return MyInterface;
    }
}
