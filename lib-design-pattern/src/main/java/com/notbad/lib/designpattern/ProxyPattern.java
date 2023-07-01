package com.notbad.lib.designpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式
 * 有一个接口A，A有一个功能方法method()，有一个类B，B实现了A接口，现在想要实现对method方法管控，比如修改表现，
 * 增加方法之前的一些操作等，并且不应该去修改method方法，这时就可以用上代理模式，
 * 新建一个代理类C，C实现了A接口，C的构造方法传入一个A的实现对象，也就是B对象，在C中的method方法中，
 * 添加要做一事情然后对B对象调用method做管控，这样就实现了一个A的代理。代理模式与装饰模式非常像，但是理念不同，
 * 代理是为了完成对方法的修改管控，而装饰是为了增强功能。代理是动态绑定的，而类是静态绑定的。
 * 因为你可以在对象执行某个方法的时候进行代理，而别的方法不进行代理。
 */
public class ProxyPattern {
    public interface Subject {
        void doWork();
    }

    public class RealSubject implements Subject {

        @Override
        public void doWork() {
            LogUtils.d("real do work");
        }
    }

    public class SubjectProxy implements Subject {
        private Subject realSubject;

        public SubjectProxy(Subject realSubject) {
            this.realSubject = realSubject;
        }

        @Override
        public void doWork() {
            LogUtils.d("now i am proxy");
            this.realSubject.doWork();
        }
    }

    public class MyInvocationHandler implements InvocationHandler {
        public Object realSubject;

        public MyInvocationHandler(Object realSubject) {
            this.realSubject = realSubject;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            LogUtils.d("do other proxy work");
            return method.invoke(realSubject, objects);
        }
    }

    public static void main(String[] args) {
        new ProxyPattern().test();
    }

    public void test() {
        // 自己实现的代理模式
        Subject subject = new RealSubject();
        Subject proxySubject = new SubjectProxy(subject);
        proxySubject.doWork();
        // jdk实现的代理模式
        Subject jdkProxySubject = (Subject) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{Subject.class}, new MyInvocationHandler(subject));
        jdkProxySubject.doWork();
    }

}
