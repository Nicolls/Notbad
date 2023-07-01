package com.notbad.lib.designpattern;

/**
 * 中介者模式
 * <p>
 * 主要处理有相互的复杂同事关系，有一个工作的接口A，定义了一系列的工作，有多个同事类B,C,D，
 * 同事类B在处理自己的工作之前需要C的支持，
 * 处理完自己的工作后就到D去处理D工作，这就出现很多相互的关系，这时新建一个中介类M，
 * 向外提供一个将同事类添加到自己持有的通过名称来映射的同事列表方法 和同事的一些需要使用的工作方法。
 * 所有的同事类都持有中介类的实例，当同事类需要其他同事的协作时，则通过自己持有的中介，
 * 传入想要协助的同事名称 让中介去帮它做让指定人协作的事情。
 * 中介类收到命令就会从自己持有的同事列表中取出对应的同事来完成协作。
 */
public class MediatorPattern {
    public abstract class Worker {
        protected Mediator mediator;

        public Worker(Mediator mediator) {
            this.mediator = mediator;
        }

        abstract void doMyWork();
    }

    public class A extends Worker {

        public A(Mediator mediator) {
            super(mediator);
            mediator.setA(this);
        }

        @Override
        public void doMyWork() {
            LogUtils.d("i am A , doing my work");
            mediator.letBdoWork();
        }
    }

    public class B extends Worker {
        public B(Mediator mediator) {
            super(mediator);
            mediator.setB(this);
        }

        @Override
        public void doMyWork() {
            LogUtils.d("i am B , doing my work");
            mediator.letCdoWork();
        }
    }

    public class C extends Worker {
        public C(Mediator mediator) {
            super(mediator);
            mediator.setC(this);
        }

        @Override
        public void doMyWork() {
            LogUtils.d("i am C , doing my work");
        }
    }

    public class Mediator {
        private Worker a;
        private Worker b;
        private Worker c;

        public void setA(Worker a) {
            this.a = a;
        }

        public void setB(Worker b) {
            this.b = b;
        }

        public void setC(Worker c) {
            this.c = c;
        }

        public void letAdoWork() {
            LogUtils.d("let A do work");
            a.doMyWork();
        }

        public void letBdoWork() {
            LogUtils.d("let B do work");
            b.doMyWork();
        }

        public void letCdoWork() {
            LogUtils.d("let C do work");
            c.doMyWork();
        }
    }

    public static void main(String[] args) {
        new MediatorPattern().test();
    }

    public void test() {
        Mediator mediator = new Mediator();
        A a = new A(mediator);
        B b = new B(mediator);
        C c = new C(mediator);
        a.doMyWork();
    }

}
