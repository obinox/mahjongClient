package obinox.com.ImgEnum;

import obinox.com.MahjongFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GBImg {

    RIICHI("/gameBtn/riichi.png", "rch", true),
    CHII("/gameBtn/chii.png", "chi", false),
    PONG("/gameBtn/pong.png", "pon", false),
    KANG("/gameBtn/kang.png", "kan", null),
    TSUMO("/gameBtn/tsumo.png", "tsu", true),
    RON("/gameBtn/ron.png", "ron", false),
    NUKI("/gameBtn/nuki.png", "nuk", true),
    RYUKYOKU("/gameBtn/ryukyoku.png", "ryu", true),
    SKIP("/gameBtn/skip.png", "skp", null),

    ;
    public final ImageIcon imgIc;
    public final Image img;
    public final String str;
    public final Boolean myTurn;

    GBImg(String path, String str, Boolean myTurn){
        this.imgIc = new ImageIcon((URL) Objects.requireNonNull(MahjongFrame.class.getResource(path)));
        this.img = imgIc.getImage();
        this.str = str;
        this.myTurn = myTurn;
    }

    public final String getStr(){
        return this.str;
    }
    private static final Map<String, GBImg> strFinder = Collections.unmodifiableMap(Stream.of(values()).collect(Collectors.toMap(GBImg::getStr, e->e)));
    public static GBImg of(String str){
        return strFinder.get(str);
    }
}
