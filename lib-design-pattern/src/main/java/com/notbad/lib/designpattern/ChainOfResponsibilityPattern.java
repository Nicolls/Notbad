package com.notbad.lib.designpattern;

/**
 * 责任链模式
 * <p>
 * 当发出一个请求，需要经过一条流程，这个流程中应该有一个对象来处理这个请求，
 * 如果它不能处理就把这个请求转交给下一个流程来处理。
 * 新建一个流程抽象类A，用来代表处理的流程，抽象类A持有下一个流程的实例，
 * 并提供一个方法来设置下一个流程的实例，抽象类A还提供一个处理的抽象方法。
 * 新建流程子类B，C，客户端实例B和C，并给B设置它的下一个处理流程为C，当有多个流程时，处理方法一样。
 * 这样就形成了一条链，当请求走到此流程的处理函数时，如果当前流程能处理则处理，
 * 不能处理则转交给它持有的下一个流程来处理，如果没有持有下一个流程又不能处理则请求结束。
 */
public class ChainOfResponsibilityPattern {

    /**
     * 下面以请求给员工报销的申请
     */
    public interface Handler {
        boolean handleRequest(int money);

        void setNextHandler(Handler handler);
    }

    public class Leader implements Handler {
        private Handler nextHandler;

        @Override
        public boolean handleRequest(int money) {
            if (money < 100) {
                LogUtils.d("Leader approve");
                return true;
            } else if (nextHandler != null) {
                LogUtils.d("Leader agree");
                return nextHandler.handleRequest(money);
            } else {
                LogUtils.d("Leader some thing wrong");
                return false;
            }
        }

        @Override
        public void setNextHandler(Handler handler) {
            this.nextHandler = handler;
        }
    }

    public class Manager implements Handler {
        private Handler nextHandler;

        @Override
        public boolean handleRequest(int money) {
            LogUtils.d("Manager approve");
            return true;
        }

        @Override
        public void setNextHandler(Handler handler) {
            this.nextHandler = handler;
        }
    }

    public class CheckSystem {

        private Handler firstHandler;

        public CheckSystem() {
            Handler leader = new Leader();
            Handler manager = new Manager();
            leader.setNextHandler(manager);
            firstHandler = leader;
        }

        public void check(int money) {
            boolean handle = firstHandler.handleRequest(money);
            if (handle) {
                LogUtils.d("money come");
            }
        }
    }

    public static void main(String[] args) {
        new ChainOfResponsibilityPattern().test();
    }

    public void test() {
        CheckSystem checkSystem = new CheckSystem();
        checkSystem.check(130);
    }

}
