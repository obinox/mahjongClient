package obinox.com;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

class MahjongFrame extends JFrame {
    private static final ImageIcon imageIcon = new ImageIcon((URL)Objects.requireNonNull(MahjongFrame.class.getResource("/mahjong.png")));
    private static final Image iconImg = imageIcon.getImage();
    private static final ImageIcon imageTable = new ImageIcon((URL)Objects.requireNonNull(MahjongFrame.class.getResource("/table.png")));
    private static final Image tableImg = imageTable.getImage();


    private Image screen;
    private Graphics graphics;
    private static final int screenWidth = 1280;
    private static final int screenHeight = 720;
    private static final int tileStart = 150;
    private static final int tileWidth = 60;
    private static final int tileHeight = 100;
    private static final int screenPad = 30;
    private static final int tilePad = 15;
    private static final int tileHoverPad = 45;
    private static final int tileSep = 3;
    private static final int tileTsumo = 15;

    private int mode = 0;
    private int cursorX, cursorY;

    private JButton startBtn = new JButton(imageIcon);
    private List<JButton> TileBtns = new ArrayList<>();
    private boolean[] TileBtnHover = new boolean[15];
    private JButton Tile0Btn = new JButton(TileImg.BACK.imgIc);
    private boolean Tile0BtnHover = false;
    private int handLen = 13;

    private String tileString = "4p8p9p1s2s2s4s5s6s6s8s1z3z";
    private String nakiString = "";
    private String tsumoString = "";
    private String kawaString = "";

    public MahjongFrame() {
//        this.setUndecorated(true);
//        this.setBackground(new Color(0, 0, 0, 0));
        this.setTitle("Java Mahjong Client");
        this.setSize(new Dimension(screenWidth, screenHeight + screenPad));
        this.setResizable(false);
        this.setIconImage(imageIcon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        TileBtnMng();


        startBtn.setBounds(0, screenPad, 720, 720);
        startBtn.setBorderPainted(false);
        startBtn.setContentAreaFilled(false);
        startBtn.setFocusPainted(false);
        startBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cursorX = e.getX();
                cursorY = e.getY();
                System.out.println("X:"+cursorX+", Y: "+cursorY);
                mode += 1;
                if (mode>2){mode=2;}
            }
        });
        this.add(startBtn);
    }

    public void TileBtnMng(){
        for (int i=0;i<handLen;i++){
            TileBtnHover[i] = false;
            TileBtns.add(new JButton(TileImg.BACK.imgIc));
            TileBtns.get(i).setBorderPainted(false);
            TileBtns.get(i).setContentAreaFilled(false);
            TileBtns.get(i).setFocusPainted(false);
            TileBtns.get(i).setBounds(tileStart+tileWidth*i+tileSep*i,screenHeight-tileHeight-tilePad, tileWidth, tileHeight+tileHoverPad);
            int finalI = i;
            TileBtns.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    TileBtnHover[finalI] = true;
                    TileBtns.get(finalI).setBounds(tileStart+tileWidth*finalI+tileSep*finalI,screenHeight-tileHeight-tileHoverPad, tileWidth, tileHeight+tileHoverPad);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    TileBtnHover[finalI] = false;
                    TileBtns.get(finalI).setBounds(tileStart+tileWidth*finalI+tileSep*finalI,screenHeight-tileHeight-tilePad, tileWidth, tileHeight+tileHoverPad);
                }
            });
            this.add(TileBtns.get(i));
        }
        Tile0BtnHover = false;
        Tile0Btn.setBorderPainted(false);
        Tile0Btn.setContentAreaFilled(false);
        Tile0Btn.setFocusPainted(false);
        Tile0Btn.setBounds(tileStart+tileWidth*handLen+tileSep*handLen,screenHeight-tileHeight-tilePad, tileWidth, tileHeight+tileHoverPad);
        int finalI = handLen;
        Tile0Btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Tile0BtnHover = true;
                Tile0Btn.setBounds(tileStart+tileWidth*handLen+tileSep*handLen,screenHeight-tileHeight-tileHoverPad, tileWidth, tileHeight+tileHoverPad*2);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                Tile0BtnHover = false;
                Tile0Btn.setBounds(tileStart+tileWidth*handLen+tileSep*handLen,screenHeight-tileHeight-tilePad, tileWidth, tileHeight+tileHoverPad);
            }
        });
        this.add(Tile0Btn);
    }

    public void paint(Graphics g) {
        this.screen = this.createImage(screenWidth, screenHeight);
        this.graphics = this.screen.getGraphics();
        switch (mode){
            case 0 -> this.drawMain(graphics);
            case 1 -> this.drawWait(graphics);
            case 2 -> this.drawTable(graphics,tileString, "7z");
        }
        g.drawImage(screen,0, screenPad,null);

        this.repaint();
    }

    public void drawMain(Graphics g) {
        g.drawImage(iconImg, 0,0, null);
        g.drawImage(iconImg, 40, 40+screenPad, 600, 100, null);
    }
    public void drawTable(Graphics g, String tileSeq, String tsumo) {
        g.setColor(new Color(0,0,0));
        g.fillRect(0, 0, screenWidth, screenHeight);
        g.drawImage(tableImg, screenWidth/2-screenHeight/2, 0, screenHeight, screenHeight,null);
        String[] tile = new String[15];
        for (int i=0;i<handLen;i++){
            tile[i] = (String) tileSeq.subSequence(2*i, 2*i+2);
        }
        for (int i=0;i<handLen;i++){
            if (this.TileBtnHover[i]){
                g.drawImage(TileImg.of(tile[i]).img, tileStart+tileWidth*i+tileSep*i,screenHeight-tileHeight-tileHoverPad, tileWidth, tileHeight,null);
            } else {
                g.drawImage(TileImg.of(tile[i]).img, tileStart+tileWidth*i+tileSep*i,screenHeight-tileHeight-tilePad, tileWidth, tileHeight,null);
            }
        }
        if (this.Tile0BtnHover){
            g.drawImage(TileImg.of(tsumo).img, tileStart+tileWidth*handLen+tileSep*handLen+ tileTsumo,screenHeight-tileHeight-tileHoverPad, tileWidth, tileHeight,null);
        } else {
            g.drawImage(TileImg.of(tsumo).img, tileStart+tileWidth*handLen+tileSep*handLen+ tileTsumo,screenHeight-tileHeight-tilePad, tileWidth, tileHeight,null);
        }
    }
    public void drawWait(Graphics g) {
        g.setColor(new Color(200,0,0));
        g.fillRect(0, 0, screenWidth, screenHeight);

    }
}
