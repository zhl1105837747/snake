package com.lll.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    // ����ĳ���
    int length;
    // �ߵ����� x ������ͷ����λ��
    int[] snakeX = new int[600];
    // �ߵ����� y ������ͷ����λ��
    int[] snakeY = new int[500];
    // ����һ��ʳ�� ����
    int foodX;
    int foodY;
    Random random = new Random();
    // ��������  U D L R
    String fx = "R";
    // ����ϵͳ
    int score;

    // ��ʱ��
    Timer timer = new Timer(100, this);

    // �ж���Ϸ�Ƿ�ʼ
    boolean isStart = false;

    // �ж��Ƿ�����
    boolean isEnd = false;

    // ������
    public GamePanel() {
        init();
        // ��ȡ���̼����¼�
        this.setFocusable(true);
        this.addKeyListener(this);

        // ��ʱ�䶯����
        timer.start();
    }

    // ��ʼ��
    public void init() {
        // ��ʼ��С��
        // ��ʼ������
        length = 3;
        // ͷ��������
        snakeX[0] = 100;
        snakeY[0] = 100;
        // ��һ�����������
        snakeX[1] = 75;
        snakeY[1] = 100;
        // �ڶ������������
        snakeX[2] = 50;
        snakeY[2] = 100;

        // ��ʼ��ʳ������
        foodX = 25 + 25 * random.nextInt(34);
        foodY = 75 + 25 * random.nextInt(24);

        // ��ʼ������
        score = 0;
    }


    // ����
    // Graphics �� ����
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // ���ñ�����ɫ
        this.setBackground(Color.white);

        // ����ͷ�������
        Data.header.paintIcon(this, g, 25, 11);

        // ������Ϸ����
        g.fillRect(25, 75, 850, 600);

        // ��һ����̬��С��
        switch (fx) {
            case "U":
                Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "D":
                Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "L":
                Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "R":
                Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }
        for (int i = 1; i < length; i++) {
            // �ߵ����峤�� ͨ��length����
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        // ��ʳ��
        Data.food.paintIcon(this, g, foodX, foodY);

        // ������
        g.setColor(Color.white);
        g.setFont(new Font("΢���ź�", Font.BOLD, 18));
        g.drawString("���ȣ�" + length, 750, 35);
        g.drawString("������" + score, 750, 55);

        // ��Ϸ��ʾ �Ƿ�ʼ
        if (isStart == false) {
            //  �����������
            g.setColor(Color.white);
            g.setFont(new Font("΢���ź�", Font.BOLD, 40));

            g.drawString("���¿ո�ʼ��Ϸ��", 300, 300);
        }

        // ��Ϸ��ʾ ʧ��
        if (isEnd) {
            g.setColor(Color.red);
            g.setFont(new Font("΢���ź�", Font.BOLD, 40));

            g.drawString("��Ϸʧ�ܣ����¿ո����¿�ʼ��", 200, 300);
        }


    }


    /**
     * ���̼���
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // ���̰��� δ�ͷ�

        // ��ȡ���̰��µ����Ǹ���
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {

            if (isEnd) {
                // ʧ�� ����һ��
                isEnd = false;
                init();
            } else {
                isStart = !isStart;
            }

            // ˢ�½���
            repaint();
        }

        // ���ݷ�����ı�С��ͷ����
        if (keyCode == KeyEvent.VK_UP) {
            fx = "U";
        } else if (keyCode == KeyEvent.VK_DOWN) {
            fx = "D";
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            fx = "R";
        } else if (keyCode == KeyEvent.VK_LEFT) {
            fx = "L";
        }

    }

    // ִ�ж�ʱ����  ����ʱ��  ֡
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && isEnd == false) {
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }

            //  ͨ��fx�жϷ��� ��С��ͷ���ƶ�
            if (fx.equals("U")) {
                snakeY[0] = snakeY[0] - 25;
                // �߽��ж�  ������С�߷ɳ�ȥ
                if (snakeY[0] < 75) {
                    snakeY[0] = 650;
                }
            } else if (fx.equals("D")) {
                snakeY[0] = snakeY[0] + 25;
                // �߽��ж�  ������С�߷ɳ�ȥ
                if (snakeY[0] > 650) {
                    snakeY[0] = 75;
                }
            } else if (fx.equals("R")) {
                snakeX[0] = snakeX[0] + 25;
                // �߽��ж�  ������С�߷ɳ�ȥ
                if (snakeX[0] > 850) {
                    snakeX[0] = 25;
                }
            } else if (fx.equals("L")) {
                snakeX[0] = snakeX[0] - 25;
                // �߽��ж�  ������С�߷ɳ�ȥ
                if (snakeX[0] < 25) {
                    snakeX[0] = 850;
                }
            }

            // ���С�ߵ�ͷ�������ʳ��������غ��� �ͳ�һ������
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                // ���ȼ�һ
                length++;
                // ��������
                score += 10;

                // ��������ʳ��
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(24);
            }
            // ���С�ߵ�ͷ�����������������غ��� ��ʧ��
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isEnd = true;
                }
            }

            repaint();
        }
        timer.start();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // ���̰��� ����  �û�
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // �����ͷ�
    }


}
