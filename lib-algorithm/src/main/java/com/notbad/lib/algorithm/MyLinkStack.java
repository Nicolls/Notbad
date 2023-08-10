package com.notbad.lib.algorithm;

public class MyLinkStack {

    public class LinkNode{
        public String value;
        public LinkNode next;
        public LinkNode(String value){
            this.value = value;
        }
    }

    private LinkNode head;

    private void push(String value){
        LinkNode insertNode = new LinkNode(value);
        insertNode.next = head;
        head = insertNode;
    }

    private String peek(){
        if(isEmpty()){
            throw new RuntimeException("empty");
        }
        return head.value;
    }

    private String pop(){
        if(isEmpty()){
            throw new RuntimeException("empty");
        }
        String v = head.value;
        head = head.next;
        return v;
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
        new MyLinkStack().test();
    }
}
