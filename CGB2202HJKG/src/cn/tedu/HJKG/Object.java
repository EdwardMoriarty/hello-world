package cn.tedu.HJKG;

import java.awt.*;

public class Object {
    // 坐标 宽高
    public int x;
    public int y;
    public int width;
    public int height;
    // 图片
    public Image img;
    // 标记,是否能移动
    public boolean flag;
    // 质量
    public int m;
    // 积分
    public int count;
    // 标记 1为金块, 2为石块
    public int type;

    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    public int getWidth() {
        return width;
    }

    //获取矩形
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
