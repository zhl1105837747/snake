package com.lll.snake;

import javax.swing.*;
import java.net.URL;

/**
 * 放置图片路径的类
 */
public class Data {

    // 顶部广告
    public static URL headerUrl = Data.class.getResource("/com/static/header.png");
    public static ImageIcon header = new ImageIcon(headerUrl);


    // 头部
    public static URL upUrl = Data.class.getResource("/com/static/up.png");
    public static URL downUrl = Data.class.getResource("/com/static/down.png");
    public static URL leftUrl = Data.class.getResource("/com/static/left.png");
    public static URL rightUrl = Data.class.getResource("/com/static/right.png");

    public static ImageIcon up = new ImageIcon(upUrl);
    public static ImageIcon down = new ImageIcon(downUrl);
    public static ImageIcon left = new ImageIcon(leftUrl);
    public static ImageIcon right = new ImageIcon(rightUrl);

    // 身体
    public static URL bodyUrl = Data.class.getResource("/com/static/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);

    // 食物
    public static URL foodUrl = Data.class.getResource("/com/static/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);

}
