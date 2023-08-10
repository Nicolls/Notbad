package com.notbad.lib.algorithm;

public class MyArrayStack {
    private int size;
    private int top;
    private String[] data;
    public MyArrayStack(int size){
        this.size = size;
        data = new String[size];
        top = -1;
    }
    private boolean push(String value){
        if(top==this.size-1){ // 满栈，不操作
            return false;
        }
        // top++，再设置值
        top++;
        data[top]=value;
        return true;
    }
    private String peek(){
        if(top==-1){
            throw new RuntimeException("stack is empty");
        }
        return data[top];
    }

    private String pop(){
        if(top<=-1){
            throw new RuntimeException("stack is empty");
        }
        String d = data[top];
        top--;
        return d;
    }
    private boolean isEmpty(){
        return top==-1;
    }

    private void test(){

        while (!isEmpty()){
            LogUtils.d("s:" + pop());
        }
    }

    public static void main(String[] args) {
        MyArrayStack myStack = new MyArrayStack(6);
        myStack.push("aa");
        myStack.push("bb");
        myStack.push("cc");
        myStack.push("dd");
        myStack.push("ee");
        myStack.test();
    }
}
