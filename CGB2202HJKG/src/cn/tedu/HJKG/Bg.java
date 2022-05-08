package cn.tedu.HJKG;

import java.awt.*;

public class Bg {

    // 关卡数
    public static int level = 1;
    // 目标得分
    public int goal = level * 20;
    // 总分
    public static int count = 0;
    // 药水数量
    public static int waterNum = 3;
    // 药水状态, 默认F, T->正在使用, F->无法使用
    public static boolean waterFlag = false;
    // 开始时间
    public long startTime;
    // 结束时间
    public long endTime;
    // 药水价格
    public int price = (int) (Math.random() * 10);
    // 是否进入商店 f 不购买
    public boolean shop = false;

    // 载入图片
    private Image bg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    private Image bg1 = Toolkit.getDefaultToolkit().getImage("imgs/bg1.jpg");
    private Image peo = Toolkit.getDefaultToolkit().getImage("imgs/peo.png");
    private Image water = Toolkit.getDefaultToolkit().getImage("imgs/water.png");

    // 绘制
    public void paintSelf(Graphics g) {
        g.drawImage(bg1, 0, 0, null);
        g.drawImage(bg, 0, 200, null);
        switch (World.state) {
            case 0:
                drawWord(g, 80, Color.green, "是男人就抓100个！", 50, 400);
                break;
            case 1:
                g.drawImage(peo, 310, 50, null);
                drawWord(g, 30, Color.black, "积分:" + count, 30, 150);
                // 药水组件
                g.drawImage(water, 450, 40, null);
                drawWord(g, 30, Color.black, "*" + waterNum, 510, 70);
                // 关卡数
                drawWord(g, 20, Color.black, "第" + level + "关", 30, 60);
                // 目标积分
                drawWord(g, 30, Color.black, "目标" + goal, 30, 110);
                // 时间组件
                endTime = System.currentTimeMillis();
                long tim = 20 - (endTime - startTime) / 1000;
                drawWord(g, 30, Color.black, "时间:" + (tim > 0 ? tim : 0), 520, 150);
                break;
            case 2:
                g.drawImage(water, 300, 400, null);
                drawWord(g, 30, Color.black, "价格:" + price, 300, 500);
                drawWord(g, 30, Color.black, "是否购买?", 300, 550);
                if (shop) {
                    count = count - price;
                    waterNum += 10;
                    shop = false;
                    World.state = 1;
                    startTime = System.currentTimeMillis();
                }
                break;
            case 3:
                drawWord(g, 80, Color.ORANGE, "失败QAQ", 230, 350);
                drawWord(g, 80, Color.ORANGE, "积分:" + count, 100, 450);
                break;
            case 4:
                drawWord(g, 80, Color.red, "李是真滴流批~", 10, 350);
                drawWord(g, 80, Color.red, "积分:" + count, 100, 450);
                break;
            default:
        }


    }

    // t->倒计时完成 f->正在倒计时
    public boolean gameTime() {
        long time = (endTime - startTime) / 1000;
        if (time > 20) {
            return true;
        }
        return false;

    }

    // 重置元素
    public void reGame() {
        // 关卡数
        level = 1;
        // 目标得分
        goal = level * 15;
        // 总分
        count = 0;
        // 药水数量
        waterNum = 3;
        // 药水状态,默认F,T 正在使用 F 无法使用
        waterFlag = false;
    }

    // 打印字符串，写字专用方法
    public static void drawWord(Graphics g, int size, Color color, String str, int x, int y) {
        g.setColor(color);
        g.setFont(new Font("仿宋", Font.BOLD, size));
        g.drawString(str, x, y);
    }
}
