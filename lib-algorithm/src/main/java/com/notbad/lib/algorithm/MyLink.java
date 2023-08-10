package com.notbad.lib.algorithm;

public class MyLink {

    public class LinkNode {
        public int value;
        public LinkNode(int value){
            this.value = value;
        }
        public LinkNode next;
        public LinkNode pre;
    }

    private LinkNode head;


    private void test(){
        head = new LinkNode(0);

        LinkNode firstLink = new LinkNode(1);

        LinkNode secondLink = new LinkNode(2);

        LinkNode threeLink = new LinkNode(3);

        LinkNode fourLink = new LinkNode(4);

        head.pre = null;
        head.next = firstLink;

        firstLink.pre = head;
        firstLink.next = secondLink;
        secondLink.pre = firstLink;
        secondLink.next = threeLink;
        threeLink.pre = secondLink;
        threeLink.next = fourLink;
        fourLink.pre = threeLink;
        fourLink.next = null;

        bianli();
        LogUtils.d("insert start");
        insert();
        bianli();

    }

    private void bianli(){
        // 遍历
        LinkNode link = head;
        while (link!=null){
            LogUtils.d("out:" + link.value);
            link = link.next;
        }
    }

    private void insert(){
        // 插入一个数
        int insertIndex=2;
        LinkNode insertNode = new LinkNode(100);
        int index=0;
        LinkNode linktemp = head;
        LinkNode pre=null;
        while (linktemp!=null){
            if(index==insertIndex){
                if(pre!=null){
                    pre.next = insertNode;
                }
                insertNode.next = linktemp;
            }
            pre = linktemp;
            linktemp = linktemp.next;
            index++;
        }
    }

    public static void main(String[] args) {
        new MyLink().test();
    }
}
