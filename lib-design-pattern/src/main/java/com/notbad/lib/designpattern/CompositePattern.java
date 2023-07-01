package com.notbad.lib.designpattern;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 组合模式
 * 一个统一的节点接口，子节点实现节点接口，父节点也实现节点接口并且持有子节点对象列表。
 * ViewGroup和View就使用了这种模式
 */
public class CompositePattern {
    public interface Node {

    }

    public class Child implements Node {
        private Node parent;

        public Child(Node parent) {
            this.parent = parent;
        }
    }

    public class Parent implements Node {
        private List<Node> childList = new CopyOnWriteArrayList<>();

    }

}
