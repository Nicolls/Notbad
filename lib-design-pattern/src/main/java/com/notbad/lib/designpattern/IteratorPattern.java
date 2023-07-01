package com.notbad.lib.designpattern;

/**
 * 迭代器模式
 * 对于不同的数据集合，建立统一的迭代数据的方法 。
 * 新建迭代接口A，新建类B和C实现A接口，分别用于不同的数据的迭代，这些接口持有数据实例。完成统一数据迭代。
 */
public class IteratorPattern {
    public interface Iterator {
        public boolean hasNext();

        public Object next();
    }

    public interface Container {
        public Iterator getIterator();
    }

    public class NameRepository implements Container {
        public String[] names = {"Robert", "John", "Julie", "Lora"};

        @Override
        public Iterator getIterator() {
            return new NameIterator();
        }

        private class NameIterator implements Iterator {

            int index;

            @Override
            public boolean hasNext() {
                if (index < names.length) {
                    return true;
                }
                return false;
            }

            @Override
            public Object next() {
                if (this.hasNext()) {
                    return names[index++];
                }
                return null;
            }
        }
    }

    public static void main(String[] args) {
        new IteratorPattern().test();
    }

    public void test() {
        NameRepository namesRepository = new NameRepository();

        for (Iterator iter = namesRepository.getIterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            System.out.println("Name : " + name);
        }
    }
}
