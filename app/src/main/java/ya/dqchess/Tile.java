package ya.dqchess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

//盤の中の1つのタイル
public class Tile extends Board {

    int tileID;
    String tileName;
    int posX, posY;
    BitmapDrawable bmp;
    ImageView imgView;
    int onUnitID;
//    Object onUnitInstance = new Object();
    int onUnitInstanceID;
    int onUnitArrayListIdx;     //乗っているユニットのArrayListのインデックス

    public Tile(int tileNo, int positionX, int positionY, BitmapDrawable bmpfile, ImageView imageView) {     //コンストラクタ
        super();
        this.tileID = tileNo;
        this.posX = positionX;
        this.posY = positionY;
        this.bmp = bmpfile;
        this.imgView = imageView;
        this.onUnitID = 0;
        this.onUnitInstanceID = -1;
        this.onUnitArrayListIdx = -1;
        this.tileName = setTileName(this.tileID);
    }

    public Tile() {     //コンストラクタ
        super();
        this.tileID = 0;
        this.posX = 0;
        this.posY = 0;
    }

    public String setTileName (int tileNo) {
        String tilename;
        if (tileNo == 1) {
            tilename = "へいげん";
        } else if (tileNo == 2) {
            tilename = "しんりん";
        } else if (tileNo == 3) {
            tilename = "うみ";
        } else if (tileNo == 4) {
            tilename = "やま";
        } else if (tileNo == 5) {
            tilename = "いわやま";
        } else if (tileNo == 6) {
            tilename = "さばく";
        } else if (tileNo == 7) {
            tilename = "まち";
        } else if (tileNo == 8) {
            tilename = "しろ";
        } else {
            tilename = "ふめい";
        }
        return tilename;
    }

}
