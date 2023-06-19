package com.notbad.hotfix;

import com.notbad.lib.common.LogUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class ProxyHandler {
    private static final String TAG = "ProxyHandler";

    /**
     * 动物的接口
     */
    public interface Animal {
        void speak();

        void come();
    }

    /**
     * 狗
     */
    public static class Dog implements Animal {
        @Override
        public void speak() {
            LogUtils.d("Dog", hashCode() + "-wang wang wang");
        }

        @Override
        public void come() {
            LogUtils.d("Dog", hashCode() + "-dog coming");
        }
    }

    /**
     * 蛇
     */
    public static class Snake implements Animal {
        @Override
        public void speak() {
            System.out.println("si si si");
        }

        @Override
        public void come() {
            System.out.println("snake coming");
        }
    }


    /**
     * 人，默认人的宠物是一只狗
     */
    public static class Person {
        /**
         * 人默认有一只宠物狗
         */
        private Animal pet;

        public Person() {
            pet = new Dog();
        }

        public void petSpeak() {
            pet.speak();
        }

        public void petCome() {
            pet.come();
        }


    }

    /**
     * 代理处理接口实现
     */
    public static class AnimalInvocationHandler implements InvocationHandler {
        private static final String TAG = "AnimalInvocationHandler";
        /**
         * 被代理的对象
         */
        private Object animal;

        public AnimalInvocationHandler(Object animal) {
            this.animal = animal;
        }

        /**
         * @param proxy  被代理的对象,
         * @param method 代理对象正在执行的方法
         * @param args   执行这个方法所传入的参数
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // proxy，这个就是我们前面Proxy.newProxyInstance出来的。不能用它来调用方法做，否则会无限的去回调它这里面的invoke方法
            if (method.getName().equals("speak")) {
                // 我们希望在第一个动物说话的时候先介绍一下它
                LogUtils.d(TAG, "我来了");
            }
            return method.invoke(this.animal, args);
        }
    }

    /**
     * 代理
     */
    public static void handle(Person person) {
        try {
            Class<?> cls = Person.class;
            Field petField = cls.getDeclaredField("pet");
            petField.setAccessible(true);
            Object pet = petField.get(person);
            InvocationHandler invocationHandler = new AnimalInvocationHandler(pet);
            Animal animal = (Animal) Proxy.newProxyInstance(Animal.class.getClassLoader(),
                    new Class<?>[]{Animal.class}, invocationHandler);
            petField.set(person, animal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * public class MainActivity {
 *     private ProxyHandler.Person person= new ProxyHandler.Person();
 *     public void onTestPerson(View view) {
 *         // 第一次点击的时候，只会打印 "wang wang wang"
 *         person.petSpeak();
 *         person.petCome();
 *     }
 *
 *     public void onProxyHandle(View view) {
 *         // 当我们执行下面的方法后，再执行onTestPerson
 *         // 就会多打印一个："我来了"
 *         ProxyHandler.handle(person);
 *     }
 * }
 *
 */