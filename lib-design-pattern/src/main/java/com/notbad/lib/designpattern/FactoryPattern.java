package com.notbad.lib.designpattern;

/**
 * 定义了一个创建对象的接口
 * 但由子类决定要实例化的类是哪一个，工厂方法让类把实例化推迟到子类
 */
public class FactoryPattern {
    public abstract class Pizza {
        abstract void cut();

        abstract void box();

        abstract String taste();
    }

    public class GreekPizza extends Pizza {

        @Override
        void cut() {

        }

        @Override
        void box() {

        }

        @Override
        String taste() {
            return "greek";
        }
    }

    public class CheesePizza extends Pizza {

        @Override
        void cut() {

        }

        @Override
        void box() {

        }

        @Override
        String taste() {
            return "cheese";
        }
    }

    /**
     * 简单工厂方法
     */
    public class SimplePizzaFactory {
        public Pizza createPizza(String type) {
            if ("greek".equals(type)) {
                return new GreekPizza();
            } else if ("cheese".equals(type)) {
                return new CheesePizza();
            }
            return null;
        }
    }

    /**
     * 抽象工厂模式，工厂只负责生产pizza，pizza打包啥的最后是由pizza公司来执行的。
     */
    public abstract class PizzaFactory {
        abstract Pizza createPizza(String type);
    }

    /**
     * 芝加哥pizza工厂
     */
    public class ChicagoPizzaFactory extends PizzaFactory {

        public class ChicagoGreekPizza extends GreekPizza {
            @Override
            String taste() {
                return super.taste() + " chicago";
            }
        }

        @Override
        Pizza createPizza(String type) {
            if ("greek".equals(type)) {
                return new ChicagoGreekPizza();
            } else if ("cheese".equals(type)) {
                return new CheesePizza();
            }
            return null;
        }
    }

    /**
     * 加利福尼亚pizza工厂
     */
    public class CaliforniaPizzaFactory extends PizzaFactory {
        public class CaliforniaCheesePizza extends CheesePizza {
            @Override
            String taste() {
                return super.taste() + " california";
            }
        }

        @Override
        Pizza createPizza(String type) {
            if ("greek".equals(type)) {
                return new GreekPizza();
            } else if ("cheese".equals(type)) {
                return new CaliforniaCheesePizza();
            }
            return null;
        }
    }

    /**
     * Pizza生产公司,
     * 如果把这个PizzaStore也做成抽象（接口），里面对pizza的打包等操作，全部由子类来指定，这样就变成了抽象工厂模式，
     * 思路和工厂方法一样，不再赘述
     */
    public class PizzaStore {

        /**
         * 抽象工厂方法
         */
        private PizzaFactory pizzaFactory;

        public PizzaStore(PizzaFactory pizzaFactory) {
            this.pizzaFactory = pizzaFactory;
        }

        public Pizza orderPizza(String type) {
            Pizza pizza;

            // 如果不用工厂方法实现就变成在这里做if判断，无法扩展
            if ("greek".equals(type)) {
                pizza = new GreekPizza();
            } else if ("cheese".equals(type)) {
                pizza = new CheesePizza();
            }

            // 简单工厂方法
            pizza = new SimplePizzaFactory().createPizza(type);

            // 实现抽象工厂方法来实现
            pizza = pizzaFactory.createPizza(type);

            pizza.cut();
            pizza.box();
            return pizza;
        }
    }

    public static void main(String[] args) {
        new FactoryPattern().test();
    }

    public void test() {
        PizzaStore pizzaStore = new PizzaStore(new ChicagoPizzaFactory());
        Pizza pizza = pizzaStore.orderPizza("cheese");
        PizzaStore californiaStore = new PizzaStore(new CaliforniaPizzaFactory());
        Pizza pizza2 = californiaStore.orderPizza("cheese");
    }


}
