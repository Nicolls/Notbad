package com.notbad.lib.designpattern;

/**
 * 状态模式
 * 带有状态的业务，将定义一个状态接口A，接口实现功能方法比如当此状态成功后将会进入哪种状态，失败后又会进入哪种状态，
 * 如果有通用的方法可以建立一个抽象类B，实现A接口并，完成一些通用方法的实现，之后列出所可能的状态继承于B，
 * 新建一个状态机控制类C，C持有当前状态的一个实例，并向外提供设置当前状态的方法，和获得当前状态的方法，
 * 另外还应该提供一个对外的去触发当前状态的方法，此方法被外界触发后会使用当前持有状态实例调用状态的通用函数，
 * 状态类持有状态机实例这样可以在自己的想要改变成别的状态时通过持有状态机实例调用设置状态的方法将状态机持有状态改变成想要设置的状态。
 */
public class StatePattern {
    /**
     * 将外部关心的数据和状态定义在基类里，这样外部可以从当前状态拿到想到的东西。
     */
    public interface State {
        String getName();

        int getCoinCount();

        void addCoin();

        void removeCoin();
    }

    public class StateMachine {
        private State initState = new InitState(this);
        private State oneState = new OneCoinState(this);
        private State twoState = new TwoCoinState(this);

        private State fullState = new FullCoinState(this);

        private State currentState = initState;

        public void setState(State state) {
            this.currentState = state;
        }

        public State getState() {
            return currentState;
        }

        public StateMachine putCoin() {
            currentState.addCoin();
            return this;
        }

        public StateMachine outCoin() {
            currentState.removeCoin();
            return this;
        }
    }

    public class InitState implements State {
        private StateMachine stateMachine;

        public InitState(StateMachine stateMachine) {
            this.stateMachine = stateMachine;
        }

        @Override
        public String getName() {
            return "InitState";
        }

        @Override
        public int getCoinCount() {
            return 0;
        }

        @Override
        public void addCoin() {
            stateMachine.setState(stateMachine.oneState);
        }

        @Override
        public void removeCoin() {
            LogUtils.d("no coin to remove");
        }
    }

    public class OneCoinState implements State {
        private StateMachine stateMachine;

        public OneCoinState(StateMachine stateMachine) {
            this.stateMachine = stateMachine;
        }

        @Override
        public String getName() {
            return "OneCoinState";
        }

        @Override
        public int getCoinCount() {
            return 1;
        }

        @Override
        public void addCoin() {
            stateMachine.setState(stateMachine.twoState);
        }

        @Override
        public void removeCoin() {
            stateMachine.setState(stateMachine.initState);
        }
    }

    public class TwoCoinState implements State {
        private StateMachine stateMachine;

        public TwoCoinState(StateMachine stateMachine) {
            this.stateMachine = stateMachine;
        }

        @Override
        public String getName() {
            return "TwoCoinState";
        }

        @Override
        public int getCoinCount() {
            return 2;
        }

        @Override
        public void addCoin() {
            stateMachine.setState(stateMachine.fullState);
        }

        @Override
        public void removeCoin() {
            stateMachine.setState(stateMachine.oneState);
        }
    }

    public class FullCoinState implements State {
        private StateMachine stateMachine;

        public FullCoinState(StateMachine stateMachine) {
            this.stateMachine = stateMachine;
        }

        @Override
        public String getName() {
            return "FullCoinState";
        }

        @Override
        public int getCoinCount() {
            return 3;
        }

        @Override
        public void addCoin() {
            LogUtils.d("full can not be add");
        }

        @Override
        public void removeCoin() {
            stateMachine.setState(stateMachine.twoState);
        }
    }

    public static void main(String[] args) {
        new StatePattern().test();
    }

    public void test() {

        StateMachine stateMachine = new StateMachine();
        LogUtils.d("init:" + stateMachine.getState().getName() + "-" + stateMachine.getState().getCoinCount());
        stateMachine.putCoin().outCoin().putCoin().outCoin().outCoin().outCoin().putCoin();
        LogUtils.d("after:" + stateMachine.getState().getName() + "-" + stateMachine.getState().getCoinCount());
    }


}
