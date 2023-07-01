package com.notbad.lib.designpattern;

/**
 * 适用于需要复用的对象，原型类实现Cloneable接口
 * <p>
 * 场景：
 * 1.将某个单例中的集合对象返回给调用者，但是担心调用者改变这些集合中的对象，则可用原型模式，
 * 通过拷贝实现返回给调用者的只是拷贝的数据，而不是单例中的数据引用。
 * <p>
 * 2.需要比较某个对象在经过某一个流程后，是否发生变化，则可以用原型模式，对原始数据做一个拷贝，
 * 将拷贝或原始数据传递给流程，流程结束后再做比较 。
 * <p>
 * 3.对于某个复杂对象，新创建花销高，并且可以复用某一状态。
 */
public class PrototypePattern {
    public abstract class Shape implements Cloneable {

        private String id;
        protected String type;

        public abstract void draw();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public Object clone() {
            Object object = null;
            try {
                object = super.clone();
            } catch (CloneNotSupportedException e) {
                LogUtils.e("--", e.getMessage());
            }
            return object;
        }
    }
}
