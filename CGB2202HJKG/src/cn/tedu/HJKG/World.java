package cn.tedu.HJKG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class World extends JFrame {
    // 存储金块,石块
    public List<Object> objectList = new ArrayList<>();

    private Bg bg = new Bg();
    private Line line = new Line(this);

    // 0->未开始 1->运行中 2->商店 3->失败 4->胜利
    public static int state;


    public World(){
        boolean isPlace = true;  // 是否可以生成，重合了就不行

        for (int i = 0; i < 30; i++) {
            double random = Math.random();
            Gold gold;
            if (random < 0.6) {
                gold = new GoldMini();
            } else if (random < 0.8) {
                gold = new Gold();
            } else {
                gold = new GoldPlus();
            }

            // 生成三种类型的金块
            for (Object obj : objectList) {
                if (gold.getRec().intersects(obj.getRec())) {
                    isPlace = false;  // 不可放置,需要重新生成
                }
            }
            if (isPlace) {
                objectList.add(gold);
            } else {
                isPlace = true;
                i--;
            }
        }

        // 生成铁块ROCK
        for (int i = 0; i < 20; i++) {
            Rock rock = new Rock();
            for (Object obj : objectList) {
                if (rock.getRec().intersects(obj.getRec())) {
                    isPlace = false;
                }
            }
            if (isPlace) {
                objectList.add(rock);
            } else {
                isPlace = true;
                i--;
            }
        }
    }


    /** 下一关*/
    public void nextLevel() {
        if (bg.gameTime() && state == 1) {
            if (Bg.count >= bg.goal) {
                if (Bg.level == 2) {
                    state = 4;
                } else {
                    state = 2;
                    Bg.level++;
                }
            } else {
                state = 3;
            }
            dispose();
            World win = new World();
            win.action();
        }

    }


    /** 缓存画布*/
    Image offScreenImage;


    /** 画笔*/
    @Override
    public void paint(Graphics g) {
        offScreenImage = this.createImage(768, 1000);
        Graphics gImage = offScreenImage.getGraphics();

        bg.paintSelf(gImage);
        if (state == 1) {
            // 绘制物体
            for (Object obj : objectList) {
                obj.paintSelf(gImage);
            }
            line.paintSelf(gImage);
        }
        g.drawImage(offScreenImage, 0, 0, null);
    }


    /** 窗口设置*/
    public void action() {
        this.setVisible(true);                 // 窗口是否可见
        this.setSize(768, 1000);  // 窗口大小
        this.setLocationRelativeTo(null);      // 窗口居中
        this.setTitle("宗师强者，恐怖如斯！");    // 窗口标题
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);  // 窗口关闭

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (state) {
                    case 0:
                        if (e.getButton() == 3) {  // 点右键开玩！
                            state = 1;
                            bg.startTime = System.currentTimeMillis();
                        }
                        break;
                    case 1:
                        // 左右摇摆,点击左键
                        if (e.getButton() == 1 && line.state == 0) {
                            line.state = 1;
                        }
                        // 抓取返回,点击右键
                        if (e.getButton() == 3 && line.state == 3 && Bg.waterNum > 0) {
                            Bg.waterFlag = true;
                            Bg.waterNum--;
                        }
                        break;
                    case 2:
                        if (e.getButton() == 1) {
                            bg.shop = true;
                        }
                        if (e.getButton() == 3) {
                            state = 1;
                            bg.startTime = System.currentTimeMillis();
                        }
                        break;
                    case 3:
                    case 4:
                        if (e.getButton() == 1) {
                            state = 0;
                            bg.reGame();
                            line.reGame();
                        }
                        break;
                    default:
                }

            }
        });

        while (true) {  // 过关设置
            repaint();
            nextLevel();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /** main*/
    public static void main(String[] args) {
        World world = new World();
        world.action();
    }

























}
