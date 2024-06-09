package obinox.com.ImgEnum;

import obinox.com.MahjongFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public enum UtilImg {

    DORAIND("/util/doraind.png", "dor"),
    FURITEN("/util/furiten.png", "fur"),
    HONBA("/util/honbapoint.png", "hon"),
    MACHI("/util/machi.png", "mch"),
    MACHIFURITEN("/util/machifuriten.png", "mfu"),
    RIICHI("/util/riichipoint.png", "rch"),
    TENPAIL("/util/tenpail.png", "tnl"),
    TENPAIM("/util/tenpaim.png", "tnm"),
    TENPAIN("/util/tenpain.png", "tnn"),
    TENPAIR("/util/tenpair.png", "tnr"),
    TENPAI0("/util/tenpai0.png", "tn0"),
    TENPAI1("/util/tenpai1.png", "tn1"),
    TENPAI2("/util/tenpai2.png", "tn2"),
    TENPAI3("/util/tenpai3.png", "tn3"),
    TENPAI4("/util/tenpai4.png", "tn4"),

    ;

    public final ImageIcon imgIc;
    public final Image img;
    public final String str;

    UtilImg(String path, String str){
        this.imgIc = new ImageIcon((URL) Objects.requireNonNull(MahjongFrame.class.getResource(path)));
        this.img = imgIc.getImage();
        this.str = str;
    }
}
