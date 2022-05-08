package cn.tedu.HJKG;

import java.awt.*;

public class Gold extends Object {

    public Gold() {
        this.x = (int) (Math.random() * 700);
        this.y = (int) (Math.random() * 550 + 300);
        this.width = 52;
        this.height = 52;
        this.flag = false;
        this.m = 10;
        this.count = 10;
        this.type = 1;
        this.img = Toolkit.getDefaultToolkit().getImage("imgs/gold1.gif");
    }
}

