package obinox.com;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TileImg {
    //ManZu
    MAN0("/pai/m0.png", "0m"),
    MAN1("/pai/m1.png", "1m"),
    MAN2("/pai/m2.png", "2m"),
    MAN3("/pai/m3.png", "3m"),
    MAN4("/pai/m4.png", "4m"),
    MAN5("/pai/m5.png", "5m"),
    MAN6("/pai/m6.png", "6m"),
    MAN7("/pai/m7.png", "7m"),
    MAN8("/pai/m8.png", "8m"),
    MAN9("/pai/m9.png", "9m"),


    //PinZu
    PIN0("/pai/p0.png", "0p"),
    PIN1("/pai/p1.png", "1p"),
    PIN2("/pai/p2.png", "2p"),
    PIN3("/pai/p3.png", "3p"),
    PIN4("/pai/p4.png", "4p"),
    PIN5("/pai/p5.png", "5p"),
    PIN6("/pai/p6.png", "6p"),
    PIN7("/pai/p7.png", "7p"),
    PIN8("/pai/p8.png", "8p"),
    PIN9("/pai/p9.png", "9p"),


    //SouZu
    SOU0("/pai/s0.png", "0s"),
    SOU1("/pai/s1.png", "1s"),
    SOU2("/pai/s2.png", "2s"),
    SOU3("/pai/s3.png", "3s"),
    SOU4("/pai/s4.png", "4s"),
    SOU5("/pai/s5.png", "5s"),
    SOU6("/pai/s6.png", "6s"),
    SOU7("/pai/s7.png", "7s"),
    SOU8("/pai/s8.png", "8s"),
    SOU9("/pai/s9.png", "9s"),


    //KazeHai
    EAST("/pai/z1.png", "1z"),
    SOUTH("/pai/z2.png", "2z"),
    WEST("/pai/z3.png", "3z"),
    NORTH("/pai/z4.png", "4z"),


    //SangenPai
    WHITE("/pai/z5.png", "5z"),
    GREEN("/pai/z6.png", "6z"),
    RED("/pai/z7.png", "7z"),

    //Back
    BACK("/pai/b0.png", "0b"),

    ;

    public final ImageIcon imgIc;
    public final Image img;
    public final String str;

    TileImg(String path, String str){
        this.imgIc = new ImageIcon((URL) Objects.requireNonNull(MahjongFrame.class.getResource(path)));
        this.img = imgIc.getImage();
        this.str = str;
    }

    public final String getStr(){
        return this.str;
    }
    private static final Map<String, TileImg> strFinder = Collections.unmodifiableMap(Stream.of(values()).collect(Collectors.toMap(TileImg::getStr, e->e)));
    public static TileImg of(String str){
        return strFinder.get(str);
    }
}
