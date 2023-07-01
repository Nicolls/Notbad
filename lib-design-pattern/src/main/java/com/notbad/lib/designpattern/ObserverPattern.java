package com.notbad.lib.designpattern;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 观察者模式
 * 就是主题提供注册观察者，和取消注册，外部类可以通过这两个接口进注册，然后等待感兴趣的信息回调
 * <p>
 * Jdk提供了观察者的实现
 */
public class ObserverPattern {

// 这是自已写的观察者

    /**
     * 观察者接口
     */
    public interface IObserver {
        void onDataChanged(String data);
    }

    public interface Subject {
        void addObserver(IObserver observer);

        void removeObserver(IObserver observer);
    }

    public class SubjectImpl implements Subject {

        private List<IObserver> observers = new CopyOnWriteArrayList<>();

        @Override
        public void addObserver(IObserver observer) {
            if (!observers.contains(observer)) {
                observers.add(observer);
            }
        }

        @Override
        public void removeObserver(IObserver observer) {
            observers.remove(observer);
        }

        public void dataChanged(String data) {
            for (IObserver observer : observers) {
                observer.onDataChanged(data);
            }
        }
    }

    public class MyObserver implements IObserver {

        public MyObserver(Subject subject) {
            subject.addObserver(this);
        }

        @Override
        public void onDataChanged(String data) {

        }
    }

    public static void main(String[] args) {
        new ObserverPattern().test();
    }

    public void test() {
        // 如果不用观察者模式，那MyObserver就得自己不定时的去查询Subject，获取它的数据。

        // 自己实现
        SubjectImpl subject = new SubjectImpl();
        MyObserver observer = new MyObserver(subject);
        subject.dataChanged("hello data changed");

        // jdk实现
        JdkSubject jdkSubject = new JdkSubject();

        Observer jdkObserver = new JdkObserver();

        jdkSubject.addObserver(jdkObserver);
        jdkSubject.dataChange("yes");
    }

    // 使用JDK实现, 但是它在jdk 9的时候废弃了
    public class JdkSubject extends Observable {
        public void dataChange(String data) {
            setChanged();
            notifyObservers(data);
        }
    }

    public class JdkObserver implements Observer {

        @Override
        public void update(Observable observable, Object o) {
            LogUtils.d("dataChanged " + (String) o);
        }
    }
}
