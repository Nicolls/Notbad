package com.notbad.lib.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录模式
 * 针对于可能需要保存状态恢复状态的操作，原理是建立一个类，此类中包含需要对原类备忘的属性，
 * 利用第三方来实现备忘的新建的恢复。对于原类A有一个value属性可能需要备忘，新建一个备忘录接口B，
 * 该接口是一个空接口，是为了给外部使用，但外部并不知道要备忘的是什么东西，也不能去修改备忘的东西，
 * 在原类A中新建一个内部类C，C实现B接口，并在C中增加一个属性value，value只实现get方法，
 * 并在C的构造函数中传入value完成初始化。原类A提供两个方法，一个是建立一个备忘，建立备忘返回一个备忘接口B的C实现实例，
 * 另一个是恢复备忘，提供一个方法由外部传入一个备忘B,并将其强转为备忘实现C，然后取得value给原类A设置其value值，
 * 完成备忘恢复。新建一个负责保存备忘的类D，D持有一个备忘实例，并向外提供了get,set方法来实现备忘存储的取出。
 * 客户端新建一个原类A的实例，执行备忘创建得到一个B的实例，新建C的实例，并将B存储到C中，原类A做了一些更改后，
 * 想要恢复到初始，从C中取出存储的备忘B，并通过原类A的恢复备忘方法利用备忘B来完成恢复
 */
public class MementoPattern {
    public class Memento {
        private String state;
        public Memento(String state) {
            this.state = state;
        }
        public String getState() {
            return state;
        }
    }

    public class Originator {
        private String state;
        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public Memento saveStateToMemento() {
            return new Memento(state);
        }

        public void getStateFromMemento(Memento Memento) {
            state = Memento.getState();
        }
    }

    public class CareTaker {
        private List<Memento> mementoList = new ArrayList<Memento>();

        public void add(Memento state) {
            mementoList.add(state);
        }

        public Memento get(int index) {
            return mementoList.get(index);
        }
    }

    public static void main(String[] args) {
        new MediatorPattern().test();
    }

    public void test() {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");

        System.out.println("Current State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }

}
