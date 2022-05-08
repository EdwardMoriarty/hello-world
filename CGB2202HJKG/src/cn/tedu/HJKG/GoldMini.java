package cn.tedu.HJKG;

import java.awt.*;

public class GoldMini extends Gold {

    public GoldMini() {
        this.width = 36;
        this.height = 36;
        this.m = 2;
        this.count = 50000;
        this.img = Toolkit.getDefaultToolkit().getImage("imgs/gold0.gif");
    }
}
