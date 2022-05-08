package cn.tedu.HJKG;

import java.awt.*;

public class GoldPlus extends Gold {

    public GoldPlus() {
        this.x = (int) (Math.random() * 650);
        this.width = 105;
        this.height = 105;
        this.m = 20;
        this.count = 50;
        this.img = Toolkit.getDefaultToolkit().getImage("imgs/gold2.gif");
    }
}
