package com.notbad.lib.designpattern;

/**
 * 外观模式
 * 外观模式（Facade Pattern）隐藏系统的复杂性，并向客户端提供了一个客户端可以访问系统的接口。
 * 这种类型的设计模式属于结构型模式，它向现有的系统添加一个接口，来隐藏系统的复杂性。
 * 这种模式涉及到一个单一的类，该类提供了客户端请求的简化方法和对现有系统类方法的委托调用。
 */
public class FacadePattern {
    public interface Shape {
        void draw();
    }

    public class Rectangle implements Shape {

        @Override
        public void draw() {
            System.out.println("Rectangle::draw()");
        }
    }

    public class Square implements Shape {

        @Override
        public void draw() {
            System.out.println("Square::draw()");
        }
    }

    public class Circle implements Shape {

        @Override
        public void draw() {
            System.out.println("Circle::draw()");
        }
    }

    /**
     * 这个就是我们的外观类，用来定义一下统一的内部接口访问类
     */
    public class ShapeFacade {
        private Shape circle;
        private Shape rectangle;
        private Shape square;

        public ShapeFacade() {
            circle = new Circle();
            rectangle = new Rectangle();
            square = new Square();
        }

        public void drawCircle() {
            circle.draw();
        }

        public void drawRectangle() {
            rectangle.draw();
        }

        public void drawSquare() {
            square.draw();
        }
    }

    public static void main(String[] args) {
        new FacadePattern().test();
    }

    public void test() {
        ShapeFacade shapeMaker = new ShapeFacade();
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }

}
