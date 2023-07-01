package com.notbad.lib.designpattern;

/**
 * 单例模式
 */
public class SingletonPattern {
    /**
     * 饿汉
     */
    public static class SingletonA {
        private static SingletonA singleton = new SingletonA();

        private SingletonA() {
        }

        public static SingletonA getInstance() {
            return singleton;
        }
    }

    /**
     * 懒汉，但性能不好
     */
    public static class SingletonB {
        private static SingletonB singleton;

        private SingletonB() {
        }

        public synchronized static SingletonB getInstance() {
            if (singleton == null) {
                singleton = new SingletonB();
            }
            return singleton;
        }
    }

    /**
     * 懒汉，双重校验锁
     */
    public static class SingletonC {
        private static volatile SingletonC singleton;

        private SingletonC() {
        }

        public static SingletonC getInstance() {
            if (singleton == null) {
                synchronized (SingletonC.class) {
                    if (singleton == null) {
                        singleton = new SingletonC();
                    }
                }
            }
            return singleton;
        }
    }

    /**
     * 懒汉，静态内部类
     */
    public static class SingletonD {
        public static class SingletonHolder {
            private static SingletonD singleton = new SingletonD();
        }

        private SingletonD() {
        }

        public static SingletonD getInstance() {
            return SingletonHolder.singleton;
        }
    }

    /**
     * 枚举实现,可防止反射
     */
    public enum SingletonF {
        INSTANCE;

        public void logic() {

        }
    }
}
