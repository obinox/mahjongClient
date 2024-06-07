package obinox.com;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

class MahjongFrame extends JFrame {
    private static final ImageIcon iic = new ImageIcon((URL)Objects.requireNonNull(MahjongFrame.class.getResource("/mahjong.png")));
    private static final Image mainImg;
    private Image screen;
    private Graphics graphics;
    private static final int WIDTH = 1080;
    private static final int HEIGHT = 720;

    public MahjongFrame() {
        this.setTitle("Java Mahjong Client");
        this.setSize(new Dimension(1080, 720));
        this.setResizable(false);
        this.setIconImage(iic.getImage());
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        int icoWidth = 400;
        int icoHeight = 400;
        this.screen = this.createImage(1080, 720);
        this.graphics = this.screen.getGraphics();
        this.graphics.drawImage(mainImg, 540 - icoWidth / 2, 360 - icoHeight / 2, icoWidth, icoHeight, (ImageObserver)null);
        this.graphics.setFont(new Font("Noto Sans", 1, 100));
        this.graphics.drawString("Java", 540 - icoWidth / 2, 360 - icoHeight / 2);
        this.graphics.drawString("Mahjong", 540 - icoWidth / 2, 360 + 3 * icoHeight / 4);
        g.drawImage(this.screen, 0, 0, (ImageObserver)null);
        this.repaint();
    }

    static {
        mainImg = iic.getImage();
    }
}
