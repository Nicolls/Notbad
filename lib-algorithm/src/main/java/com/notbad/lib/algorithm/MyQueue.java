package com.notbad.lib.algorithm;

public class MyQueue {


    public class LinkNode{
        public String value;
        public LinkNode next;
        public LinkNode(String value){
            this.value = value;
        }
    }

    private LinkNode head;
    private LinkNode tail;

    private void push (String s){
        LinkNode insertNode = new LinkNode(s);
        if(head==null){
            head = insertNode;
            tail = insertNode;
        } else {
            tail.next = insertNode;
            tail = insertNode;
        }
    }

    private String peek(){
        if(isEmpty()){
            throw new RuntimeException("queue empty");
        }
        return head.value;
    }

    private String pop(){
        if(isEmpty()){
            throw new RuntimeException("queue empty");
        }
        String value = head.value;
        head = head.next;
        return value;
    }

    private boolean isEmpty(){
        return head==null;
    }


    private void test(){
        push("aa");
        push("bb");
        push("cc");
        push("dd");
        push("ee");

        while (!isEmpty()){
            LogUtils.d("s:" + pop());
        }
    }

    public static void main(String[] args) {
        new MyQueue().test();
    }
}
