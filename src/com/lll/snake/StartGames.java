package com.lll.snake;

import javax.swing.*;

public class StartGames {

    public static void main(String[] args) {

        // 绘制静态窗口
        JFrame frame = new JFrame("Java--贪吃蛇小游戏");
        // 设置界面大小
        frame.setBounds(20,20,900,720);
        // 设置窗口大小不可改变
        frame.setResizable(false);
        // 设置关闭事件
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // 面板绘画
        frame.add(new GamePanel());

        // 让窗口能够展示出来
        frame.setVisible(true);

    }
}
