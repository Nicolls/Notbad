package com.notbad.lib.designpattern;

/**
 * 策略模式
 * 定义了算法族，分别封装起来，让它们之间可以互相交换，此模式让算法的变化独立于使用算法的客户。
 * 其实就是把属于类属性的不变化的部分仍然放在类中，把那些会变化的逻辑都抽出来当成接口，然后主类中持有这些接口
 * 并使用这些接口的功能方法。
 * 如果在构造的时候把这些接口当成参数传进来，就是面向接口编程
 */
public class StrategyPattern {

    /**
     * 将可能会变化的行为抽象成接口，例如飞行。
     * 有一些鸭子飞得快，有些飞得慢，而有些是不会飞行的，如果将fly放到Duck基类中，那些不会飞行的鸭子即使不会飞，也得继承飞
     */
    public interface Fly {
        void fly();
    }

    public class QuickFly implements Fly {

        @Override
        public void fly() {
            LogUtils.d("fly quickly");
        }
    }

    public class SlowFly implements Fly {

        @Override
        public void fly() {
            LogUtils.d("fly slowly");
        }
    }

    public class UnFly implements Fly {

        @Override
        public void fly() {
            LogUtils.d("can not fly");
        }
    }

    public abstract class Duck {
        private String name;

        public Duck(String name) {
            this.name = name;
        }

        /**
         * 基类留下公用的，不怎么变化的接口
         */
        public void swim() {
            LogUtils.d(name + " swim");
        }

        public abstract void performFly();
    }

    public class TianErDuck extends Duck {
        private Fly fly;

        public TianErDuck(String name, Fly fly) {
            super(name);
            this.fly = fly;
        }

        /**
         * 通过接口实现可变的行为
         */
        @Override
        public void performFly() {
            fly.fly();
        }
    }

    public class DangDuck extends Duck {
        private Fly fly;

        public DangDuck(String name, Fly fly) {
            super(name);
            this.fly = fly;
        }

        /**
         * 通过接口实现可变的行为
         */
        @Override
        public void performFly() {
            fly.fly();
        }
    }

    public class UglyDuck extends Duck {
        //        private Fly fly;  // 丑小鸭不会飞，也就不需要传fly进来
        public UglyDuck(String name) {
            super(name);
        }

        @Override
        public void performFly() {
        }
    }

    public static void main(String[] args) {
        new StrategyPattern().test();
    }

    public void test() {
        // 如果不用策略模式，那么你的fly飞行行为就会做为Duck的一个基类方法，而丑小鸭显示不需要这个方法。但因为写在基类里
        // 它被强制实现了

        Fly quickFly = new QuickFly();
        Fly slowFly = new SlowFly();
        Duck tianErDuck = new TianErDuck("天鹅", quickFly);
        Duck tangDuck = new DangDuck("唐老鸭", slowFly);
        Duck uglyDuck = new UglyDuck("丑小鸭");
        tianErDuck.performFly();
        tangDuck.performFly();
        uglyDuck.performFly();
    }

}
