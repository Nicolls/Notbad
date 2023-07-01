package com.notbad.lib.designpattern;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 适配器模式
 * 将一个类的接口，转换成客户期望的另一个接口，适配器让原本接口不兼容的类可以合作无间
 * <p>
 * <p>
 * 类适配器，
 * <p>
 * 有一个接口A，等待实现，有一个类B，具有类似A接口的功能，现在为了达到不想去写一个A的实现类而是使用现有的B类来做为A接口的实现类，这是就可以使用类适配器了，
 * <p>
 * 新建一个适配器类C，C继承了B类并实现了A接口，这时C就拥有了A和B的功能，也就可以这样写：A a=new C();   ，当然如果接口A和B的方法 都相同，是最好的，如果不同，则在调用A接口的方法时显示去让它去调用B类的方法，就达到了适配的目的。当类中的方法和接口中的方法冲突时，相当于类默认实现了接口中的方法 。
 * <p>
 * 对象适配，
 * <p>
 * 有一个接口A，等待实现，有一个类B，具有可以替代A接口的功能，现在为了让B类的对象来帮接口A去做A的接口功能，使用对象适配，新建 适配器C,C继承于A，并在C的构造方法中传入B的实例对象，这时C持有对象B的实例，当接口A的方法被调用时，则使用对象B的实例方法来完成接口A的功能。
 * <p>
 * 接口适配，
 * <p>
 * 有一个接口A，拥有一些功能方法 ，有一个类B实现了A接口的方法 ，但类B只是想使用接口A中的一两个方法，
 * 如果B直接实现了A就会把A的很多无用方法也都带上，为了简洁则可以使用接口适配，新建一个抽象适配类C，C实现A接口，
 * 并将所有A类不想要的接口方法 都实现，其他想要的方法都不实现，
 * 交由C的子类D来实现;或者是都实现所有方法，然后再由C的子类去复写这些方法，达到不需要把接口方法都实现的目的。
 */
public class AdapterPattern {
    /**
     * 鸭子是一个会飞，又会呱呱叫的动物
     */
    public interface Duck {
        void quack();

        void fly();
    }

    /**
     * 唐老鸭是一种鸭子
     */
    public class TangDuck implements Duck {

        @Override
        public void quack() {
            LogUtils.d("gua gua gua");
        }

        @Override
        public void fly() {
            LogUtils.d("pu pu pu ");
        }
    }

    /**
     * 这个是鸡，它会打鸣，也会飞，但是飞得不高，只是拍打一下翅膀而以
     */
    public interface Chicken {
        void ooo();

        void fly();
    }

    /**
     * 家鸡，是一种鸡
     */
    public class HomeChicken implements Chicken {

        @Override
        public void ooo() {
            LogUtils.d("oooooo");
        }

        @Override
        public void fly() {
            LogUtils.d("pa pa pa ");
        }
    }

    /**
     * 鸡和鸭不能直接交流，所以我们要创建一个适配器，让这只鸡伪装成一只鸭
     */
    public class ChickenAdapter implements Duck {
        private Chicken chicken;

        public ChickenAdapter(Chicken chicken) {
            this.chicken = chicken;
        }

        @Override
        public void quack() {
            this.chicken.ooo();
        }

        @Override
        public void fly() {
            this.chicken.fly();
        }
    }

    /**
     * 这是一个鸭舍
     */
    public class DuckStore {
        private List<Duck> ducks = new CopyOnWriteArrayList<>();

        public void addDuck(Duck duck) {
            if (!ducks.contains(duck)) {
                ducks.add(duck);
            }
        }

        public void removeDuck(Duck duck) {
            ducks.remove(duck);
        }

        public void list() {
            for (Duck duck : ducks) {
                duck.quack();
            }
        }
    }

    public static void main(String[] args) {
        new AdapterPattern().test();
    }

    public void test() {
        DuckStore duckStore = new DuckStore();
        duckStore.addDuck(new TangDuck());
        duckStore.addDuck(new TangDuck());

        Chicken chicken = new HomeChicken();
        // 如果不用适配器，无法把难加到鸭舍里
        // duckStore.addDuck(new HomeChicken());

        // 用适配器后
        Duck duck = new ChickenAdapter(chicken);
        duckStore.addDuck(duck);

        duckStore.list(); // 这时鸭舍里就会出现鸭叫
    }

}
