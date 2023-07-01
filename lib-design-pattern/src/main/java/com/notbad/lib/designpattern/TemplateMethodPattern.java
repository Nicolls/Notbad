package com.notbad.lib.designpattern;

/**
 * 模板方法模式
 * 两个相似功能的类，但是他们的实现却不同。新建一个抽象类A把它们相同的功能方法抽象出来，
 * 再定义好统一的操作实现，并将不同的实现方法延迟到子类来实现。集合比较接口就用了此模式。
 */
public class TemplateMethodPattern {
    /**
     * 比如泡咖啡，和泡茶，都需要热水，然后把泡好的（咖啡/茶）倒到容器里
     * 所以我们可以把这几个步骤抽出来放到基类中
     * 然后又因为咖啡加糖，茶加柠檬其实都是加辅料，所以我们可以把这个操作抽象成加辅料。
     * 这样准备一个饮品的步骤就是：
     * 1.准备容器，2.热水 3.泡  4，加辅料，5，倒出来
     */
    public abstract class SameBehavior {
        /**
         * 这个就是模板方法，它定义了一个准备饮品的模板步骤
         */
        public final void prepareRecipe() {
            LogUtils.d("prepareRecipe");
            boilWater();
            addOther();
            pourInCup();
        }

        public void boilWater() {
            LogUtils.d("boilWater");
        }

        public void pourInCup() {
            LogUtils.d("pourInCup");
        }

        public abstract void addOther();
    }

    public class Coffee extends SameBehavior {
        @Override
        public void boilWater() {
            super.boilWater();
        }

        @Override
        public void pourInCup() {
            super.pourInCup();
        }

        @Override
        public void addOther() {
            addSugar();
        }

        /**
         * 咖啡有自己的专属方法，加糖
         */
        public void addSugar() {

        }
    }

    public class Tea extends SameBehavior {

        @Override
        public void boilWater() {
            super.boilWater();
        }

        @Override
        public void pourInCup() {
            super.pourInCup();
        }

        @Override
        public void addOther() {
            addLemon();
        }

        /**
         * 茶有自己的处理方法，加柠檬
         */
        public void addLemon() {

        }
    }

    public static void main(String[] args) {
        new TemplateMethodPattern().test();
    }

    public void test() {
        Tea tea = new Tea();
        tea.prepareRecipe();
    }

}
