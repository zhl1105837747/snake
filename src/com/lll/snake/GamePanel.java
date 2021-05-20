package com.lll.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    // 身体的长度
    int length;
    // 蛇的坐标 x （根据头部定位）
    int[] snakeX = new int[600];
    // 蛇的坐标 y （根据头部定位）
    int[] snakeY = new int[500];
    // 定义一个食物 坐标
    int foodX;
    int foodY;
    Random random = new Random();
    // 上下左右  U D L R
    String fx = "R";
    // 积分系统
    int score;

    // 定时器
    Timer timer = new Timer(100, this);

    // 判断游戏是否开始
    boolean isStart = false;

    // 判断是否死亡
    boolean isEnd = false;

    // 构造器
    public GamePanel() {
        init();
        // 获取键盘监听事件
        this.setFocusable(true);
        this.addKeyListener(this);

        // 让时间动起来
        timer.start();
    }

    // 初始化
    public void init() {
        // 初始化小蛇
        // 初始化长度
        length = 3;
        // 头部的坐标
        snakeX[0] = 100;
        snakeY[0] = 100;
        // 第一节身体的坐标
        snakeX[1] = 75;
        snakeY[1] = 100;
        // 第二节身体的坐标
        snakeX[2] = 50;
        snakeY[2] = 100;

        // 初始化食物坐标
        foodX = 25 + 25 * random.nextInt(34);
        foodY = 75 + 25 * random.nextInt(24);

        // 初始化积分
        score = 0;
    }


    // 画板
    // Graphics ： 画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 设置背景颜色
        this.setBackground(Color.white);

        // 绘制头部广告栏
        Data.header.paintIcon(this, g, 25, 11);

        // 绘制游戏区域
        g.fillRect(25, 75, 850, 600);

        // 画一条静态的小蛇
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
            // 蛇的身体长度 通过length控制
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        // 画食物
        Data.food.paintIcon(this, g, foodX, foodY);

        // 画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("长度：" + length, 750, 35);
        g.drawString("分数：" + score, 750, 55);

        // 游戏提示 是否开始
        if (isStart == false) {
            //  设置字体参数
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));

            g.drawString("按下空格开始游戏！", 300, 300);
        }

        // 游戏提示 失败
        if (isEnd) {
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));

            g.drawString("游戏失败，按下空格重新开始！", 200, 300);
        }


    }


    /**
     * 键盘监听
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // 键盘按下 未释放

        // 获取键盘按下的是那个键
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {

            if (isEnd) {
                // 失败 再来一次
                isEnd = false;
                init();
            } else {
                isStart = !isStart;
            }

            // 刷新界面
            repaint();
        }

        // 根据方向键改变小蛇头朝向
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

    // 执行定时操作  监听时间  帧
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && isEnd == false) {
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }

            //  通过fx判断方向 让小蛇头部移动
            if (fx.equals("U")) {
                snakeY[0] = snakeY[0] - 25;
                // 边界判断  不能让小蛇飞出去
                if (snakeY[0] < 75) {
                    snakeY[0] = 650;
                }
            } else if (fx.equals("D")) {
                snakeY[0] = snakeY[0] + 25;
                // 边界判断  不能让小蛇飞出去
                if (snakeY[0] > 650) {
                    snakeY[0] = 75;
                }
            } else if (fx.equals("R")) {
                snakeX[0] = snakeX[0] + 25;
                // 边界判断  不能让小蛇飞出去
                if (snakeX[0] > 850) {
                    snakeX[0] = 25;
                }
            } else if (fx.equals("L")) {
                snakeX[0] = snakeX[0] - 25;
                // 边界判断  不能让小蛇飞出去
                if (snakeX[0] < 25) {
                    snakeX[0] = 850;
                }
            }

            // 如果小蛇的头的坐标和食物的坐标重合了 就长一节身体
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                // 长度加一
                length++;
                // 分数增加
                score += 10;

                // 重新生成食物
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(24);
            }
            // 如果小蛇的头的坐标和身体的坐标重合了 就失败
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
        // 键盘按下 弹起  敲击
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 键盘释放
    }


}
