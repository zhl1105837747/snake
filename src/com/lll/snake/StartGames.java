package com.lll.snake;

import javax.swing.*;

public class StartGames {

    public static void main(String[] args) {

        // ���ƾ�̬����
        JFrame frame = new JFrame("Java--̰����С��Ϸ");
        // ���ý����С
        frame.setBounds(20,20,900,720);
        // ���ô��ڴ�С���ɸı�
        frame.setResizable(false);
        // ���ùر��¼�
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // ���滭
        frame.add(new GamePanel());

        // �ô����ܹ�չʾ����
        frame.setVisible(true);

    }
}
