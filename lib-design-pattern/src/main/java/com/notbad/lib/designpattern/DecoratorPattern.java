package com.notbad.lib.designpattern;

/**
 * 装饰者模式，装饰是对现有的类进行增加功能或者修饰，它指的是一种能力增强，或者叠加。
 * 装饰者本身仍然是基于被装饰者的，比如。饮料中的橙汁和冰橙汁和蜜汁冰橙汁的关系，或者是蛋糕中的草莓蛋糕和奶油草莓蛋糕的关系
 */
public class DecoratorPattern {

    /**
     * 饮料接口
     */
    interface Juice {
        void taste();

        int cost();
    }

    /**
     * 橙汁
     */
    public class OrangeJuice implements Juice {

        @Override
        public void taste() {
            LogUtils.d("sweet");
        }

        @Override
        public int cost() {
            return 10;
        }
    }

    /**
     * 冰橙汁
     */
    public class IceOrangeJuice implements Juice {
        private OrangeJuice orangeJuice;

        public IceOrangeJuice(OrangeJuice orangeJuice) {
            this.orangeJuice = orangeJuice;
        }

        @Override
        public void taste() {
            orangeJuice.taste();
            LogUtils.d("and cool");
        }

        @Override
        public int cost() {
            return orangeJuice.cost() + 2;
        }
    }

    /**
     * 蜜汁冰橙汁
     */
    public class HoneyIceOrangeJuice implements Juice {
        public IceOrangeJuice iceOrangeJuice;

        public HoneyIceOrangeJuice(IceOrangeJuice iceOrangeJuice) {
            this.iceOrangeJuice = iceOrangeJuice;
        }

        @Override
        public void taste() {
            LogUtils.d("smell good");
            iceOrangeJuice.taste();
        }

        @Override
        public int cost() {
            return iceOrangeJuice.cost() + 5;
        }
    }

    public static void main(String[] args) {
        new DecoratorPattern().test();
    }

    public void test() {
        // 如果不用装饰者则就会出现三个类都继承于Juice，并且每个类都必须完整的实现所有方法,taste和cost
        // 就会出现冗余

        // 装饰者模式实现，可以继承每个类的本身属性
        Juice iceOrangeJuice = new IceOrangeJuice(new OrangeJuice());
        Juice honeyIceOrangeJuice = new HoneyIceOrangeJuice(new IceOrangeJuice(new OrangeJuice()));
        iceOrangeJuice.taste();
        LogUtils.d("iceOrangeJuice cost " + iceOrangeJuice.cost());

        honeyIceOrangeJuice.taste();
        LogUtils.d("honeyIceOrangeJuice cost " + honeyIceOrangeJuice.cost());
    }


}
