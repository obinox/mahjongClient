package obinox.com;

import obinox.com.ImgEnum.GBImg;
import obinox.com.ImgEnum.TileImg;
import obinox.com.ImgEnum.UtilImg;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

public class MahjongFrame extends JFrame {
    public static final ImageIcon imageIcon = new ImageIcon((URL)Objects.requireNonNull(MahjongFrame.class.getResource("/mahjong.png")));
    public static final Image iconImg = imageIcon.getImage();
    public static final ImageIcon imageTable = new ImageIcon((URL)Objects.requireNonNull(MahjongFrame.class.getResource("/table.png")));
    public static final Image tableImg = imageTable.getImage();


    public Image screen;
    public Graphics graphics;
    public static final int screenWidth = 1280;
    public static final int screenHeight = 720;
    public static final int tileStart = 155;
    public static final int tileWidth = 60;
    public static final int tileHeight = 100;
    public static final int screenPad = 30;
    public static final int tilePad = 15;
    public static final int tileHoverPad = 45;
    public static final int tileSep = 2;
    public static final int tileTsumo = 15;
    public static final int kawaTileWidth = 30;
    public static final int kawaTileHeight = 40;

    public static final int kawaStartWidth = 545;
    public static final int kawaStartHeight = 460;

    public static final int kamiKawaStartWidth = 500;
    public static final int kamiKawaStartHeight = 260;

    public static final int toiKawaStartWidth = 710;
    public static final int toiKawaStartHeight = 217;

    public static final int shimoKawaStartWidth = 746;
    public static final int shimoKawaStartHeight = 425;

    public static final int doraTileWidth = 34;
    public static final int doraTileHeight = 50;
    public static final int doraTileStartWidth = 25;
    public static final int doraTileStartHeight = 30;

    public static final int machiBtnStartWidth = 1200;
    public static final int machiBtnStartHeight = 550;
    public static final int machiBtnSize = 50;


    public static final int tenpaiWidth = 75;
    public static final int tenpaiHeight = 200;
    public static final int tenpaiStartHeight = 420;
    public static final int machiTileWidth = 54;
    public static final int machiTileHeight = 90;
    public static final int machiTilePad = 35;


    public int mode = 0;
    public int cursorX, cursorY;
    public JPanel contant = new JPanel();

    public JButton startBtn = new JButton(imageIcon);
    public List<JButton> TileBtns = new ArrayList<>();
    public boolean[] TileBtnHover = new boolean[15];
    public JButton Tile0Btn = new JButton(TileImg.BACK.imgIc);
    public JButton MachiBtn = new JButton(UtilImg.MACHI.imgIc);
    public JButton RiichiBtn = new JButton(GBImg.RIICHI.imgIc);
    public JButton PongBtn = new JButton(GBImg.PONG.imgIc);
    public JButton ChiiBtn = new JButton(GBImg.CHII.imgIc);
    public JButton KangBtn = new JButton(GBImg.KANG.imgIc);
    public JButton TsumoBtn = new JButton(GBImg.TSUMO.imgIc);
    public JButton RonBtn = new JButton(GBImg.TSUMO.imgIc);
    public JButton NukiBtn = new JButton(GBImg.NUKI.imgIc);
    public JButton RyukyokuBtn = new JButton(GBImg.RYUKYOKU.imgIc);
    public JButton SkipBtn = new JButton(GBImg.SKIP.imgIc);
    public boolean Tile0BtnHover = false;
    public boolean MachiBtnClicked = false;
    public int pressedTile = -1;
    public int handLen = 13;

    public int utilBtnMode = 0;

    public String tileString = "";
    public String tsumoString = "";

    public String myNakiString = "";
    public String myKawaString = "";
    public int myRiichi = -1;

    public String shimoNakiString = "0b0b0b";
    public String shimoKawaString = "";
    public int shimoRiichi = -1;

    public String toiNakiString = "0b0b0b";
    public String toiKawaString = "";
    public int toiRiichi = -1;

    public String kamiNakiString = "0b0b0b";
    public String kamiKawaString = "";
    public int kamiRiichi = -1;

    public String doraString = "";
    public String machiString = "";
    public String yakuString = "";
    public boolean furiten = false;
    public boolean machiFuriten = false;
    public int honbaP = 0;
    public int riichiP = 0;
    public int myPoint = 25000;
    public int shimoPoint = 25000;
    public int toiPoint = 25000;
    public int kamiPoint = 25000;
    public boolean tenpai = false;

    public PrintWriter out = null;
    public BufferedReader in = null;
    public boolean canInput = false;

    public MahjongFrame() {
//        this.setUndecorated(true);
//        this.setBackground(new Color(0, 0, 0, 0));
        this.setTitle("Java Mahjong Client");
        this.setSize(new Dimension(screenWidth, screenHeight + screenPad));
        this.setResizable(false);
        this.setIconImage(imageIcon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
//        contant.setLayout(null);
//        this.setContentPane(contant);
//        InetAddress IP = null;
//        try {
//            IP = InetAddress.getByName("localhost");
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//        int port = 8080;
//        Socket socket = new Socket();
//
        mode = 1;


        TileBtnMng();
        machiBtnMng();
        utilBtnSet();
        utilBtnMng();

        this.setVisible(true);

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

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (canInput){
                        if (handLen>finalI){
                            out.println("===Click:"+finalI);
                        }
                    }

                }
            });
            this.add(TileBtns.get(i));
        }
        Tile0BtnHover = false;
        Tile0Btn.setBorderPainted(false);
        Tile0Btn.setContentAreaFilled(false);
        Tile0Btn.setFocusPainted(false);
        Tile0Btn.setBounds(tileStart+tileWidth*handLen+tileSep*handLen,screenHeight-tileHeight-tilePad, tileWidth, tileHeight+tileHoverPad);
        Tile0Btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Tile0BtnHover = true;
                Tile0Btn.setBounds(tileStart+tileWidth*handLen+tileSep*handLen+tileTsumo,screenHeight-tileHeight-tileHoverPad, tileWidth, tileHeight+tileHoverPad*2);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                Tile0BtnHover = false;
                Tile0Btn.setBounds(tileStart+tileWidth*handLen+tileSep*handLen+tileTsumo,screenHeight-tileHeight-tilePad, tileWidth, tileHeight+tileHoverPad);
            }
        });
        this.add(Tile0Btn);
    }
    public void machiBtnMng(){
        MachiBtnClicked = false;
        MachiBtn.setBorderPainted(false);
        MachiBtn.setContentAreaFilled(false);
        MachiBtn.setFocusPainted(false);
        MachiBtn.setBounds(machiBtnStartWidth, machiBtnStartHeight, machiBtnSize, machiBtnSize);
        MachiBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                MachiBtnClicked = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MachiBtnClicked = false;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                utilBtnMng();
            }
        });
        this.add(MachiBtn);
    }
    public void utilBtnSet(){
        JButton[] buttons = new JButton[]{RyukyokuBtn, NukiBtn, KangBtn, RiichiBtn, TsumoBtn, ChiiBtn, PongBtn, RonBtn, };
        for (JButton j: buttons){
            j.setBorderPainted(false);
            j.setContentAreaFilled(false);
            j.setFocusPainted(false);
            j.setBounds(0,0,0,0);
            System.out.println(j.getIcon()+" "+utilBtnMode);
            j.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(j.getIcon()+" "+utilBtnMode);
                }
            });
            this.add(j);
        }
        SkipBtn.setBorderPainted(false);
        SkipBtn.setContentAreaFilled(false);
        SkipBtn.setFocusPainted(false);
        SkipBtn.setBounds(0,0,0,0);
        SkipBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                utilBtnMode--;
                utilBtnMng();
            }
        });
        this.add(SkipBtn);
    }
    public void utilBtnMng(){
        int[] x = new int[]{570, 370, 570, 370, 170};
        int[] y = new int[]{480, 480, 380, 380, 480};
        JButton[] buttons = new JButton[]{RyukyokuBtn, NukiBtn, KangBtn, RiichiBtn, TsumoBtn, ChiiBtn, PongBtn, RonBtn};
        JButton[] myTurn = new JButton[]{RyukyokuBtn, NukiBtn, KangBtn, RiichiBtn, TsumoBtn};
        JButton[] notTurn = new JButton[]{ChiiBtn, PongBtn, KangBtn, RonBtn, SkipBtn};
        List<JButton> stack = new ArrayList<>();
        String bmode = String.format("%5s",Integer.toBinaryString(utilBtnMode));
        int i=0;
        if (utilBtnMode>-1){
            for (String s: bmode.split("")){
                if (Objects.equals(s, "1")){
                    if (tsumoString.length()>0){
                        stack.add(0, myTurn[i]);
                    } else {
                        stack.add(0, notTurn[i]);
                    }
                }
                i++;
            }
            for (JButton j: buttons){
                if (!stack.contains(j)){
                    j.setBounds(0,0,0,0);
                }
            }
            i=0;
            for (JButton j: stack){
                j.setBounds(x[i], y[i], 200, 100);
                i++;
            }
            SkipBtn.setBounds(770,480, 200, 100);
        }

    }

    public void paint(Graphics g) {
        this.screen = this.createImage(screenWidth, screenHeight);
        this.graphics = this.screen.getGraphics();
        switch (mode){
            case 0 -> this.drawMain(graphics);
            case 1 -> this.drawWait(graphics);
            case 2 -> this.drawTable(graphics);
        }
        g.drawImage(screen,10, screenPad,null);

        this.repaint();
    }

    public void drawMain(Graphics g) {
        g.drawImage(iconImg, 0,0, null);
        g.drawImage(iconImg, 40, 40+screenPad, 600, 100, null);
    }
    public void drawTable(Graphics g) {
        g.setColor(new Color(0,0,0));
        g.fillRect(0, 0, screenWidth, screenHeight);
        g.drawImage(tableImg, screenWidth/2-screenHeight/2, 0, screenHeight, screenHeight,null);
        this.drawHand(g);
        this.drawKawa(g);
        this.drawDora(g);
        this.drawMachi(g);
        this.drawFuuro(g);
        this.drawPoint(g);
    }
    public void drawKawa(Graphics g) {
        this.drawMyKawa(g);
        this.drawKamiKawa(g);
        this.drawShimoKawa(g);
        this.drawToiKawa(g);
        this.drawTileButton(g);
    }

    public void drawHand(Graphics g){
        String[] tile = new String[15];
        for (int i=0;i<handLen;i++){
            try{
                tile[i] = (String) this.tileString.subSequence(2*i, 2*i+2);
            } catch (Exception e){

            }
        }

        for (int i=0;i<handLen;i++){
            TileImg t = TileImg.of(tile[i]);
            if (this.TileBtnHover[i]){
                g.drawImage(t.img, tileStart+tileWidth*i+tileSep*i,screenHeight-tileHeight-tileHoverPad, tileWidth, tileHeight,null);
            } else {
                g.drawImage(t.img, tileStart+tileWidth*i+tileSep*i,screenHeight-tileHeight-tilePad, tileWidth, tileHeight,null);
            }
        }
        if (tsumoString.length()>0){
            if (this.Tile0BtnHover){
                g.drawImage(TileImg.of(tsumoString).img, tileStart+tileWidth*handLen+tileSep*handLen+tileTsumo,screenHeight-tileHeight-tileHoverPad, tileWidth, tileHeight,null);
            } else {
                g.drawImage(TileImg.of(tsumoString).img, tileStart+tileWidth*handLen+tileSep*handLen+tileTsumo,screenHeight-tileHeight-tilePad, tileWidth, tileHeight,null);
            }
        }
        if (furiten){
            g.drawImage(UtilImg.FURITEN.img, tileStart-tileSep-tileWidth ,screenHeight-tileHeight-tilePad, tileWidth, tileHeight, null);
        }
    }

    public void drawMyKawa(Graphics g){
        String[] kawa = new String[25];
        for (int i = 0; i< myKawaString.length(); i+=2){
            kawa[i/2] = (String) this.myKawaString.subSequence(i, i+2);
        }
        int x;
        int y;
        for (int i = 0; i< myKawaString.length()/2; i++){
            if (i<18){
                x = kawaStartWidth+kawaTileWidth*(i%6)+tileSep*(i%6);
                y = kawaStartHeight+kawaTileHeight*(i/6)+tileSep*(i/6);
            } else {
                x = kawaStartWidth+kawaTileWidth*(6+i%6)+tileSep*(6+i%6);
                y = kawaStartHeight+kawaTileHeight*2+tileSep*2;
            }
            if (Math.min(i/6, 2) == myRiichi/6 && i>myRiichi){
                x += kawaTileHeight - kawaTileWidth;
            }
            if (i==myRiichi){
                y += (kawaTileHeight - kawaTileWidth)/2;
                g.drawImage(rotate(TileImg.of(kawa[i]).imgIc, 3*Math.PI/2).getImage(), x, y, kawaTileHeight, kawaTileWidth, null);
            } else {
                g.drawImage(TileImg.of(kawa[i]).img, x, y, kawaTileWidth, kawaTileHeight,null);
            }
        }
    }

    public void drawKamiKawa(Graphics g){
        String[] kawa = new String[25];
        for (int i = 0; i< kamiKawaString.length(); i+=2){
            kawa[i/2] = (String) this.kamiKawaString.subSequence(i, i+2);
        }
        int x;
        int y;
        for (int i = 0; i< kamiKawaString.length()/2; i++){
            if (i<18) {
                x = kamiKawaStartWidth - kawaTileHeight * (i / 6) - tileSep * (i / 6);
                y = kamiKawaStartHeight + kawaTileWidth * (i % 6) + tileSep * (i % 6);
            } else {
                x = kamiKawaStartWidth -kawaTileHeight*(2+i/6)-tileSep*(2+i/6);
                y = kamiKawaStartHeight +kawaTileWidth*(6+i%6)+tileSep*(6+i%6);
            }
            if (Math.min(i/6, 2) == shimoRiichi/6 && i>shimoRiichi){
                y += kawaTileHeight - kawaTileWidth;
            }
            if (i==shimoRiichi){
                x += (kawaTileHeight - kawaTileWidth)/2;
                g.drawImage(rotate(TileImg.of(kawa[i]).imgIc, 0).getImage(), x, y, kawaTileWidth, kawaTileHeight, null);
            } else {
                g.drawImage(rotate(TileImg.of(kawa[i]).imgIc, Math.PI/2).getImage(), x, y, kawaTileHeight, kawaTileWidth,null);
            }
        }
    }

    public void drawShimoKawa(Graphics g){
        String[] kawa = new String[25];
        for (int i = 0; i< shimoKawaString.length(); i+=2){
            kawa[i/2] = (String) this.shimoKawaString.subSequence(i, i+2);
        }
        int x;
        int y;
        for (int i = 0; i< shimoKawaString.length()/2; i++){
            if (i<18){
                x = shimoKawaStartWidth +kawaTileHeight*(i/6)+tileSep*(i/6);
                y = shimoKawaStartHeight -kawaTileWidth*(i%6)-tileSep*(i%6);
            } else {
                x = shimoKawaStartWidth +kawaTileHeight*(2+i/6)+tileSep*(2+i/6);
                y = shimoKawaStartHeight -kawaTileWidth*(6+i%6)-tileSep*(6+i%6);
            }
            if (Math.min(i/6, 2) == kamiRiichi/6 && i>=kamiRiichi){
                y -= kawaTileHeight - kawaTileWidth;
            }
            if (i==kamiRiichi){
                x += (kawaTileHeight - kawaTileWidth)/2;
                g.drawImage(rotate(TileImg.of(kawa[i]).imgIc, Math.PI).getImage(), x, y, kawaTileWidth, kawaTileHeight, null);
            } else {
                g.drawImage(rotate(TileImg.of(kawa[i]).imgIc, 3*Math.PI/2).getImage(), x, y, kawaTileHeight, kawaTileWidth,null);
            }
        }
    }

    public void drawToiKawa(Graphics g){
        String[] kawa = new String[25];
        for (int i = 0; i<toiKawaString.length(); i+=2){
            kawa[i/2] = (String) this.toiKawaString.subSequence(i, i+2);
        }
        int x;
        int y;
        for (int i=0; i<toiKawaString.length()/2; i++){
            if (i<18){
                x = toiKawaStartWidth-kawaTileWidth*(i%6)-tileSep*(i%6);
                y = toiKawaStartHeight-kawaTileHeight*(i/6)-tileSep*(i/6);
            } else {
                x = toiKawaStartWidth-kawaTileWidth*(6+i%6)-tileSep*(6+i%6);
                y = toiKawaStartHeight-kawaTileHeight*2-tileSep*2;
            }
            if (Math.min(i/6, 2) == toiRiichi/6 && i>=toiRiichi){
                x -= kawaTileHeight - kawaTileWidth;
            }
            if (i==toiRiichi){
                y += (kawaTileHeight - kawaTileWidth)/2;
                g.drawImage(rotate(TileImg.of(kawa[i]).imgIc, Math.PI/2).getImage(), x, y, kawaTileHeight, kawaTileWidth, null);
            } else {
                g.drawImage(rotate(TileImg.of(kawa[i]).imgIc, Math.PI).getImage(), x, y, kawaTileWidth, kawaTileHeight,null);
            }
        }
    }

    public void drawDora(Graphics g){
        g.drawImage(UtilImg.DORAIND.img, 15,20, 200, 100, null);
        g.drawImage(UtilImg.RIICHI.img, 30,80, 70, 35, null);
        g.drawImage(UtilImg.HONBA.img, 125,80, 70, 35, null);
        Font font = new Font("Noto Sans", Font.BOLD, 30);
        Color color = new Color(255,255,255);
        g.setColor(color);
        g.setFont(font);
        g.drawString(""+riichiP, 75, 108);
        g.drawString(""+honbaP, 75+95, 108);
        String[] doraInd = new String[5];
        if (!doraString.isEmpty()){
            for (int i = 0; i<10; i+=2){
                doraInd[i/2] = (String) this.doraString.subSequence(i, i+2);
            }

            for (int i=0; i<5; i++){
                g.drawImage(TileImg.of(doraInd[i]).img, doraTileStartWidth+doraTileWidth*i+tileSep*i, doraTileStartHeight, doraTileWidth, doraTileHeight,null);
            }
        }

    }

    public void drawMachi(Graphics g){
        if (tenpai){
            g.drawImage(UtilImg.MACHI.img, machiBtnStartWidth, machiBtnStartHeight, machiBtnSize, machiBtnSize, null);
            String[] machi = new String[15];
            for (int i = 0; i<machiString.length(); i+=2){
                machi[i/2] = (String) this.machiString.subSequence(i, i+2);
            }
            String[] yaku = new String[15];
            for (int i = 0; i<yakuString.length(); i+=2){
                yaku[i/2] = (String) this.yakuString.subSequence(i, i+2);

            }
            int machiLen = machiString.length()/2;
            int tenpaiStartWidth = screenWidth/2-tenpaiWidth/2*machiLen;
            int i = -1;
            if (MachiBtnClicked){
                g.drawImage(UtilImg.TENPAIL.img, tenpaiStartWidth+tenpaiWidth*i,tenpaiStartHeight, tenpaiWidth,tenpaiHeight,null);
                for (i=0;i<machiLen;i++){
                    switch (yaku[i]){
                        case "0t" -> g.drawImage(UtilImg.TENPAI0.img, tenpaiStartWidth+tenpaiWidth*i,tenpaiStartHeight, tenpaiWidth,tenpaiHeight,null);
                        case "1t" -> g.drawImage(UtilImg.TENPAI1.img, tenpaiStartWidth+tenpaiWidth*i,tenpaiStartHeight, tenpaiWidth,tenpaiHeight,null);
                        case "2t" -> g.drawImage(UtilImg.TENPAI2.img, tenpaiStartWidth+tenpaiWidth*i,tenpaiStartHeight, tenpaiWidth,tenpaiHeight,null);
                        case "3t" -> g.drawImage(UtilImg.TENPAI3.img, tenpaiStartWidth+tenpaiWidth*i,tenpaiStartHeight, tenpaiWidth,tenpaiHeight,null);
                        case "4t" -> g.drawImage(UtilImg.TENPAI4.img, tenpaiStartWidth+tenpaiWidth*i,tenpaiStartHeight, tenpaiWidth,tenpaiHeight,null);
                        case "0f" -> g.drawImage(UtilImg.TENPAIN.img, tenpaiStartWidth+tenpaiWidth*i,tenpaiStartHeight, tenpaiWidth,tenpaiHeight,null);
                    }
                    g.drawImage(TileImg.of(machi[i]).img, tenpaiStartWidth+tenpaiWidth*i+tenpaiWidth/2-machiTileWidth/2,tenpaiStartHeight+machiTilePad, machiTileWidth, machiTileHeight,null);
                    if (machiFuriten){
                        g.drawImage(UtilImg.MACHIFURITEN.img, tenpaiStartWidth+tenpaiWidth*i,tenpaiStartHeight, tenpaiWidth,tenpaiWidth/2,null);
                    }
                }
                g.drawImage(UtilImg.TENPAIR.img, tenpaiStartWidth+tenpaiWidth*i,tenpaiStartHeight, tenpaiWidth,tenpaiHeight,null);
            }
        }

    }

    public void drawFuuro(Graphics g){
        String[] myNaki = new String[25];
        for (int i = 0; i<myNakiString.length(); i+=2){
            myNaki[i/2] = (String) this.myNakiString.subSequence(i, i+2);
        }
        int nakiTileWidth = 48;
        int nakiTileHeight = 80;
        int ml = myNakiString.length();
        for (int i = 0; i<myNakiString.length();i++){
            TileImg t = TileImg.of(myNaki[i]);
            g.drawImage(t.img, 1250+nakiTileWidth*(i-ml)+tileSep*(i-ml),screenHeight-nakiTileHeight-10, nakiTileWidth, nakiTileHeight,null);
        }
    }

    public void drawPoint(Graphics g){
        Font font = new Font("Noto Sans", Font.BOLD, 30);
        Color color = new Color(255,255,0);
        g.drawImage(rotate(""+myPoint, 0, font , color).getImage(), 605, 420, null);
        g.drawImage(rotate(""+kamiPoint, Math.PI/2, font , color).getImage(), 520, 320, null);
        g.drawImage(rotate(""+toiPoint, Math.PI, font , color).getImage(), 530, 240, null);
        g.drawImage(rotate(""+shimoPoint, 3*Math.PI/2, font , color).getImage(), 705, 245, null);
    }

    public void drawTileButton(Graphics g){
        int[] x = new int[]{570, 370, 570, 370, 170};
        int[] y = new int[]{480, 480, 380, 380, 480};
        GBImg[] myTurn = new GBImg[]{GBImg.RYUKYOKU, GBImg.NUKI, GBImg.KANG, GBImg.RIICHI, GBImg.TSUMO};
        GBImg[] notTurn = new GBImg[]{GBImg.CHII, GBImg.PONG, GBImg.KANG, GBImg.RON, GBImg.SKIP};
        List<GBImg> stack = new ArrayList<>();
        if (utilBtnMode>0){
            String bmode = String.format("%5s",Integer.toBinaryString(utilBtnMode));

            int i=0;
            for (String s: bmode.split("")){
                if (Objects.equals(s, "1")){
                    if (tsumoString.length()>0){
                        stack.add(0, myTurn[i]);
                    } else {
                        stack.add(0, notTurn[i]);
                    }
                }
                i++;
            }
            if (mode > 0){
                i=0;
                g.drawImage(GBImg.SKIP.img, 770, 480, 200, 100, null);
                for (GBImg gb: stack){
                    g.drawImage(gb.img, x[i], y[i], 200, 100,null);
                    i++;
                }
            }
        }
    }

    public static ImageIcon rotate(ImageIcon src, double theta){
        BufferedImage buff = new BufferedImage(
                src.getIconWidth(),
                src.getIconHeight(),
                2
        );
        Graphics bufg = buff.createGraphics();
        src.paintIcon(null, bufg, 0, 0);

        int width = buff.getWidth();
        int height = buff.getHeight();
        int outWidth = (int)(Math.abs(width*Math.cos(theta)) + Math.abs(height*Math.sin(theta)));
        int outHeight = (int)(Math.abs(width*Math.sin(theta)) + Math.abs(height*Math.cos(theta)));

        BufferedImage out = new BufferedImage(outWidth, outHeight, 2);

        Graphics2D g = out.createGraphics();
        g.translate((outWidth - width) / 2, (outHeight - height) / 2);
        g.rotate(theta, (double) width / 2, (double) height / 2);
        g.drawImage(buff, 0, 0, width, height,null);

        return new ImageIcon(out);
    }
    public static ImageIcon rotate(String str, double theta, Font font, Color color){
        int width = str.length()*font.getSize();
        int height = font.getSize()*2;
        int outWidth = (int)(Math.abs(width*Math.cos(theta)) + Math.abs(height*Math.sin(theta)));
        int outHeight = (int)(Math.abs(width*Math.sin(theta)) + Math.abs(height*Math.cos(theta)));

        BufferedImage out = new BufferedImage(outWidth, outHeight, 2);

        Graphics2D g = out.createGraphics();
        g.translate((outWidth - width) / 2, (outHeight - height) / 2);
        g.rotate(theta, (double) width / 2, (double) height / 2);
//        System.out.println(width+" "+ height);
        g.setFont(font);
        g.setColor(color);
        g.drawString(str, 0, font.getSize());

        return new ImageIcon(out);
    }



    public void drawWait(Graphics g) {
        g.setColor(new Color(20,20,20));
        g.fillRect(0, 0, screenWidth, screenHeight);

    }
}
