package com.mydemo.utils;

import io.lettuce.core.dynamic.annotation.Param;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 使用Robot实现键盘按键模拟
 *
 * 启动会 延迟执行脚本，给与机会切屏，从而进行输入
 *      目前文本正常，移动失败(但是文本之间的移动正常)
 *
 *
 * 20251103
 * 针对DNF国服，键盘输入失败，均无效，其他应用正常
 * 采用虚拟键盘，鼠标指令
 * 问题： 移动后，模拟键盘不触发
 */

public class NewDemo {

    public static void main(String[] args) {
//        Point point = MouseInfo.getPointerInfo().getLocation();
//        System.out.println(point.x + " " + point.y);

        DNFKeySimulator dnfKeySimulator = new DNFKeySimulator();
        dnfKeySimulator.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dnfKeySimulator.finished();
    }


    public static class DNFKeySimulator {
        private Robot robot;
        private boolean running;

        public DNFKeySimulator() {
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            running = false;
        }

        public void start() {
            System.out.println("等待2s --- 用于切换窗口");
            delay(2000);
            System.out.println("开始输入");

            keyClick(KeyEvent.VK_SPACE);


//            keyPress();
//            mouseClick(0,0);

            delay(2000);
            System.out.println("结束输入");
        }

        public void finished() {
            running = false;
            System.out.println("结束");
        }

        /**
         *
         * @param key KeyEvent.VK_RIGHT
         */
        private void keyClick(int key) {
            delay();
            robot.keyPress(key);
            delay();
            robot.keyRelease(key);
            delay();
        }

        private void mouseClick(int x, int y) {
            delay();
            robot.mouseMove(x, y);
            delay();
            robot.mousePress(InputEvent.BUTTON1_MASK);
            delay();
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            delay();
        }

        private void delay(int... millis) {
            robot.delay(millis.length == 0 ? (int) (Math.random() * 2 * 100 + 100) : millis[0]);
        }

    }

}
