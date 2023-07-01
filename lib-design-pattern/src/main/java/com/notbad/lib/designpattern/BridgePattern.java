package com.notbad.lib.designpattern;

/**
 * 桥接模式
 * <p>
 * 桥接模式是为了解决一个通用的逻辑具有多个维度的变化问题，像巴士跟小车都能在公路上跑，也都能在高速路上跑。总的逻辑是车在路上跑，
 * 但是车会有多种车，路也可能有多种路，这时使用桥接模式来适应这种变化 。
 * 新建抽象变化类A，它的实现B和C，新建抽象变化类D，它的实现E和F，A实现了两个变化类的逻辑关系方法，并持有一个D的实例，
 * 并提供get 和set方法来动态的变化实例D。实例化A后，用get ,set方法改变D的子类型来实现状态变化。桥接模式更像是，
 * 车不变，而路却在变。
 */
public class BridgePattern {
    public interface Card {
        String getName();
    }

    public class Moto implements Card {

        @Override
        public String getName() {
            return "Moto";
        }
    }

    public class Track implements Card {

        @Override
        public String getName() {
            return "Track";
        }
    }

    public abstract class Road {
        protected Card card;

        public Road(Card card) {
            this.card = card;
        }

        public abstract void run();
    }

    public class HighRoad extends Road {

        public HighRoad(Card card) {
            super(card);
        }

        @Override
        public void run() {
            LogUtils.d(card.getName() + " run on high road");
        }
    }

    public class NormalRoad extends Road {

        public NormalRoad(Card card) {
            super(card);
        }

        @Override
        public void run() {
            LogUtils.d(card.getName() + " run on normal road");
        }
    }

    public static void main(String[] args) {
        new BridgePattern().test();
    }

    public void test() {
        Card card = new Moto();
        Road road = new HighRoad(card);
        road.run();
    }

}
