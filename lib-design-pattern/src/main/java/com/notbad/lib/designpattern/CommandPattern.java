package com.notbad.lib.designpattern;

import java.util.HashMap;

/**
 * 命令模式，就是把操作封装成指令对象，然后发出去，执行器仅仅是执行这个指令统一的执行方法，具体由指令自己具体实现
 * 发送者和执行者并不关心指令的真正实现，他们仅负责把指令发下去即可。
 */
public class CommandPattern {
    public interface Command {
        void execute();
    }

    public class Light {
        public void open() {
            LogUtils.d("light open");
        }

        public void close() {
            LogUtils.d("light close");
        }
    }

    public class OpenLight implements Command {
        public Light light;

        public OpenLight(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.open();
        }
    }

    public class CloseLight implements Command {

        public Light light;

        public CloseLight(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.close();
        }
    }

    /**
     * 指令遥控
     */
    public class RemoteControl {
        private HashMap<String, Command> commandMap = new HashMap<>();

        public RemoteControl(Light light) {
            /**
             * 当支持新的遥控按键时，我们只需要往这个map中添加即可
             */
            commandMap.put("openLight", new OpenLight(light));
            commandMap.put("closeLight", new CloseLight(light));
        }

        public void control(String type) {
            Command command = commandMap.get(type);
            if (command != null) {
                command.execute();
            }
        }
    }

    public static void main(String[] args) {
        new CommandPattern().test();
    }

    public void test() {
        String cmd = "openLight";
        // 如果没有命令模式，就需要if else的判断，然后执行对应的方法
        Light light = new Light();
        if (cmd == "openLight") {
            light.open();
        }
        // 使用命令模式，将这些都解耦
        RemoteControl remoteControl = new RemoteControl(light);
        remoteControl.control("openLight");
    }

}
