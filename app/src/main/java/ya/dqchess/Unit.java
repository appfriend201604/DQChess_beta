package ya.dqchess;

import android.graphics.drawable.BitmapDrawable;

import java.util.ArrayList;

public class Unit{
    static int count = 0;
    int unitID;
    int unitInstanceID;
    String className;
    String instanseName;    //子クラスで設定
    int posX = -1;
    int posY = -1;
    BitmapDrawable bmp;
    int friendOrFoe;
    int level;
    int experience;
    int hp;
    int mp;
    int strength;
    int physical;
    int hit;
    int agility;
    int intelligence;
    int faith;
    int luck;
    int attackPower;
    int defensePower;
//    int movePower;
    int moveRange[][];
    int battleRange[][];
    protected ArrayList<Unit> battleCandidateUnit;
    //int moveablePos[][];
    protected ArrayList<String> moveablePos = new ArrayList<String>();

    public Unit(int unitNo, int unitInstanceNo,int positionX, int positionY) {     //コンストラクタ
        count++;

        this.unitID = unitNo;
        this.unitInstanceID = unitInstanceNo;
        this.className = getClassName(this.unitID);
        //this.instanseName = this.className;
        this.posX = positionX;
        this.posY = positionY;
//        this.moveFinishFlag = 0;
//        this.battleFinishFlag = 0;

        //this.friendOrFoe の値設定
        setFriendOrFoeNumber (unitNo);

        this.level = 1;
        this.experience = 0;
        this.hp = 1;
        this.mp = 1;
        this.strength = 1;
        this.physical = 1;
        this.hit = 1;
        this.agility = 1;
        this.intelligence = 1;
        this.faith = 1;
        this.luck = 1;
        this.attackPower = 1;
        this.defensePower = 1;
//        this.movePower = 1;

        battleCandidateUnit = new ArrayList<Unit>();
        moveablePos = new ArrayList<String>();

    }

    public String getClassName (int unitNo) {
        String classNameStr;
        if (unitNo == 0) {
            classNameStr = "";
        } else if (unitNo == 10) {
            classNameStr = "ゆうしゃ";
        } else if (unitNo == 20) {
            classNameStr = "ひめ";
        } else if (unitNo == 30) {
            classNameStr = "せんし";
        } else if (unitNo == 40) {
            classNameStr = "まほうつかい";
        } else if (unitNo == 50) {
            classNameStr = "そうりょ";
        } else if (unitNo == 60) {
            classNameStr = "トルネコ";

        } else if (unitNo == 1000) {
            classNameStr = "スライム";
        } else if (unitNo == 1010) {
            classNameStr = "メタルスライム";
        } else if (unitNo == 1020) {
            classNameStr = "ホイミスライム";
        } else if (unitNo == 1030) {
            classNameStr = "ドラキー";
        } else if (unitNo == 1040) {
            classNameStr = "バブルスライム";
        } else if (unitNo == 1050) {
            classNameStr = "はぐれメタル";
        } else if (unitNo == 1060) {
            classNameStr = "キメラ";
        } else if (unitNo == 1070) {
            classNameStr = "おおがらす";
        } else if (unitNo == 1080) {
            classNameStr = "ひとくいばこ";
        } else if (unitNo == 1090) {
            classNameStr = "くさったしたい";
        } else if (unitNo == 1100) {
            classNameStr = "さまようよろい";
        } else if (unitNo == 1110) {
            classNameStr = "あくまのきし";
        } else if (unitNo == 1120) {
            classNameStr = "ベビーサタン";
        } else if (unitNo == 1130) {
            classNameStr = "トロール";
        } else if (unitNo == 1140) {
            classNameStr = "ゴーレム";
        } else if (unitNo == 1150) {
            classNameStr = "しりょうのきし";
        } else if (unitNo == 1160) {
            classNameStr = "ドラゴン";
        } else if (unitNo == 9000) {
            classNameStr = "りゅうおう";
        } else if (unitNo == 9001) {
            classNameStr = "りゅうおう変身";
        } else if (unitNo == 9010) {
            classNameStr = "ゾーマ";
        } else if (unitNo == 9011) {
            classNameStr = "ゾーマ変身";
        } else if (unitNo == 9020) {
            classNameStr = "バラモス";
        } else if (unitNo == 9021) {
            classNameStr = "バラモスブロス";
        } else if (unitNo == 9022) {
            classNameStr = "バラモスエビル";
        } else if (unitNo == 9023) {
            classNameStr = "バラモスゾンビ";
        } else {
            classNameStr = "ふめい";
        }
        return classNameStr;
    }

    public String getInstanceName (int subCoutn) {
        String instanceNameStr;
        instanceNameStr = this.className + " " + String.valueOf(subCoutn);
        return instanceNameStr;
    }

    public void setFriendOrFoeNumber (int unitNo) {
        if (unitNo < 1000) {
            this.friendOrFoe = 1;   //味方
        } else {
            this.friendOrFoe = 2;   //敵
        }
    }

    public void setUnitMoveRange (int unitNo) {
        if (unitNo == 0) {
            //ありえない
        } else if (unitNo == 10) {  //ゆうしゃ
            moveRange = new int[8][2];
            this.moveRange[0][0] = 1;
            this.moveRange[0][1] = -2;
            this.moveRange[1][0] = 2;
            this.moveRange[1][1] = -1;
            this.moveRange[2][0] = 2;
            this.moveRange[2][1] = 1;
            this.moveRange[3][0] = 1;
            this.moveRange[3][1] = 2;
            this.moveRange[4][0] = -1;
            this.moveRange[4][1] = 2;
            this.moveRange[5][0] = -2;
            this.moveRange[5][1] = 1;
            this.moveRange[6][0] = -2;
            this.moveRange[6][1] = -1;
            this.moveRange[7][0] = -1;
            this.moveRange[7][1] = -2;
        } else if (unitNo == 20) {  //ひめ
            moveRange = new int[8][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 1;
            this.moveRange[1][1] = -1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 0;
            this.moveRange[3][0] = 1;
            this.moveRange[3][1] = 1;
            this.moveRange[4][0] = 0;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = -1;
            this.moveRange[5][1] = 1;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = 0;
            this.moveRange[7][0] = -1;
            this.moveRange[7][1] = -1;
        } else if (unitNo == 30) {  //せんし
            moveRange = new int[8][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 1;
            this.moveRange[1][1] = -1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 0;
            this.moveRange[3][0] = 1;
            this.moveRange[3][1] = 1;
            this.moveRange[4][0] = 0;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = -1;
            this.moveRange[5][1] = 1;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = 0;
            this.moveRange[7][0] = -1;
            this.moveRange[7][1] = -1;
        } else if (unitNo == 40) {  //まほうつかい
            moveRange = new int[8][2];
            this.moveRange[0][0] = 1;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 2;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 1;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = 2;
            this.moveRange[4][0] = -1;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = -2;
            this.moveRange[5][1] = 2;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = -1;
            this.moveRange[7][0] = -2;
            this.moveRange[7][1] = -2;
        } else if (unitNo == 50) {  //そうりょ
            moveRange = new int[8][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 0;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 0;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = 0;
            this.moveRange[4][0] = 0;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = 0;
            this.moveRange[5][1] = 2;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = 0;
            this.moveRange[7][0] = -2;
            this.moveRange[7][1] = 0;

        } else if (unitNo == 60) {  //トルネコ
            moveRange = new int[16][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 0;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = -1;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = -2;
            this.moveRange[4][0] = 1;
            this.moveRange[4][1] = 0;
            this.moveRange[5][0] = 2;
            this.moveRange[5][1] = 0;
            this.moveRange[6][0] = 1;
            this.moveRange[6][1] = 1;
            this.moveRange[7][0] = 2;
            this.moveRange[7][1] = 2;
            this.moveRange[8][0] = 0;
            this.moveRange[8][1] = 1;
            this.moveRange[9][0] = 0;
            this.moveRange[9][1] = 2;
            this.moveRange[10][0] = -1;
            this.moveRange[10][1] = 1;
            this.moveRange[11][0] = -2;
            this.moveRange[11][1] = 2;
            this.moveRange[12][0] = -1;
            this.moveRange[12][1] = 0;
            this.moveRange[13][0] = -2;
            this.moveRange[13][1] = 0;
            this.moveRange[14][0] = -1;
            this.moveRange[14][1] = -1;
            this.moveRange[15][0] = -2;
            this.moveRange[15][1] = -2;
        } else if (unitNo == 1000) {    //スライム
            moveRange = new int[3][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = 1;
            this.moveRange[1][0] = -1;
            this.moveRange[1][1] = 1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 1;
        } else if (unitNo == 1010) {    //メタルスライム
            moveRange = new int[3][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = 1;
            this.moveRange[1][0] = -1;
            this.moveRange[1][1] = 1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 1;
        } else if (unitNo == 1020) {    //ホイミスライム
            moveRange = new int[3][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = 1;
            this.moveRange[1][0] = -1;
            this.moveRange[1][1] = 1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 1;
        } else if (unitNo == 1030) {    //ドラキー
            moveRange = new int[5][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = 1;
            this.moveRange[1][0] = -1;
            this.moveRange[1][1] = 1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 1;
            this.moveRange[3][0] = -1;
            this.moveRange[3][1] = 0;
            this.moveRange[4][0] = 1;
            this.moveRange[4][1] = 0;
        } else if (unitNo == 1040) {    //バブルスライム
            moveRange = new int[3][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = 1;
            this.moveRange[1][0] = -1;
            this.moveRange[1][1] = 1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 1;
        } else if (unitNo == 1050) {    //はぐれメタル
            moveRange = new int[3][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = 1;
            this.moveRange[1][0] = -1;
            this.moveRange[1][1] = 1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 1;
        } else if (unitNo == 1060) {    //キメラ
            moveRange = new int[5][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = 1;
            this.moveRange[1][0] = -1;
            this.moveRange[1][1] = 1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 1;
            this.moveRange[3][0] = -1;
            this.moveRange[3][1] = 0;
            this.moveRange[4][0] = 1;
            this.moveRange[4][1] = 0;
        } else if (unitNo == 1070) {   //おおがらす
            moveRange = new int[5][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = 1;
            this.moveRange[1][0] = -1;
            this.moveRange[1][1] = 1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 1;
            this.moveRange[3][0] = -1;
            this.moveRange[3][1] = 0;
            this.moveRange[4][0] = 1;
            this.moveRange[4][1] = 0;
        } else if (unitNo == 1080) {    //ひとくいばこ
            moveRange = new int[8][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 1;
            this.moveRange[1][1] = -1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 0;
            this.moveRange[3][0] = 1;
            this.moveRange[3][1] = 1;
            this.moveRange[4][0] = 0;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = -1;
            this.moveRange[5][1] = 1;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = 0;
            this.moveRange[7][0] = -1;
            this.moveRange[7][1] = -1;
        } else if (unitNo == 1090) {    //くさったしたい
            moveRange = new int[8][2];
            this.moveRange[0][0] = 1;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 2;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 1;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = 2;
            this.moveRange[4][0] = -1;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = -2;
            this.moveRange[5][1] = 2;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = -1;
            this.moveRange[7][0] = -2;
            this.moveRange[7][1] = -2;
        } else if (unitNo == 1100) {    //さまようよろい
            moveRange = new int[8][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 0;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 0;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = 0;
            this.moveRange[4][0] = 0;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = 0;
            this.moveRange[5][1] = 2;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = 0;
            this.moveRange[7][0] = -2;
            this.moveRange[7][1] = 0;
        } else if (unitNo == 1110) {    //あくまのきし
            moveRange = new int[8][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 0;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 0;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = 0;
            this.moveRange[4][0] = 0;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = 0;
            this.moveRange[5][1] = 2;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = 0;
            this.moveRange[7][0] = -2;
            this.moveRange[7][1] = 0;
        } else if (unitNo == 1120) {    //ベビーサタン
            moveRange = new int[8][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 1;
            this.moveRange[1][1] = -1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 0;
            this.moveRange[3][0] = 1;
            this.moveRange[3][1] = 1;
            this.moveRange[4][0] = 0;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = -1;
            this.moveRange[5][1] = 1;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = 0;
            this.moveRange[7][0] = -1;
            this.moveRange[7][1] = -1;
        } else if (unitNo == 1130) {    //トロール
            moveRange = new int[8][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 1;
            this.moveRange[1][1] = -1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 0;
            this.moveRange[3][0] = 1;
            this.moveRange[3][1] = 1;
            this.moveRange[4][0] = 0;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = -1;
            this.moveRange[5][1] = 1;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = 0;
            this.moveRange[7][0] = -1;
            this.moveRange[7][1] = -1;
        } else if (unitNo == 1140) {    //ゴーレム
            moveRange = new int[8][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 1;
            this.moveRange[1][1] = -1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 0;
            this.moveRange[3][0] = 1;
            this.moveRange[3][1] = 1;
            this.moveRange[4][0] = 0;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = -1;
            this.moveRange[5][1] = 1;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = 0;
            this.moveRange[7][0] = -1;
            this.moveRange[7][1] = -1;
        } else if (unitNo == 1150) {    //しりょうのきし
            moveRange = new int[8][2];
            this.moveRange[0][0] = 1;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 2;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 1;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = 2;
            this.moveRange[4][0] = -1;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = -2;
            this.moveRange[5][1] = 2;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = -1;
            this.moveRange[7][0] = -2;
            this.moveRange[7][1] = -2;
        } else if (unitNo == 1160) {    //ドラゴン
            moveRange = new int[8][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 0;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 0;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = 0;
            this.moveRange[4][0] = 0;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = 0;
            this.moveRange[5][1] = 2;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = 0;
            this.moveRange[7][0] = -2;
            this.moveRange[7][1] = 0;
        } else if (unitNo == 9000) {    //りゅうおう
            moveRange = new int[8][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 1;
            this.moveRange[1][1] = -1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 0;
            this.moveRange[3][0] = 1;
            this.moveRange[3][1] = 1;
            this.moveRange[4][0] = 0;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = -1;
            this.moveRange[5][1] = 1;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = 0;
            this.moveRange[7][0] = -1;
            this.moveRange[7][1] = -1;
        } else if (unitNo == 9001) {    //りゅうおう変身
            moveRange = new int[8][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 1;
            this.moveRange[1][1] = -1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 0;
            this.moveRange[3][0] = 1;
            this.moveRange[3][1] = 1;
            this.moveRange[4][0] = 0;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = -1;
            this.moveRange[5][1] = 1;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = 0;
            this.moveRange[7][0] = -1;
            this.moveRange[7][1] = -1;
        } else if (unitNo == 9010) {    //ゾーマ
            moveRange = new int[16][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 0;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = -1;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = -2;
            this.moveRange[4][0] = 1;
            this.moveRange[4][1] = 0;
            this.moveRange[5][0] = 2;
            this.moveRange[5][1] = 0;
            this.moveRange[6][0] = 1;
            this.moveRange[6][1] = 1;
            this.moveRange[7][0] = 2;
            this.moveRange[7][1] = 2;
            this.moveRange[8][0] = 0;
            this.moveRange[8][1] = 1;
            this.moveRange[9][0] = 0;
            this.moveRange[9][1] = 2;
            this.moveRange[10][0] = -1;
            this.moveRange[10][1] = 1;
            this.moveRange[11][0] = -2;
            this.moveRange[11][1] = 2;
            this.moveRange[12][0] = -1;
            this.moveRange[12][1] = 0;
            this.moveRange[13][0] = -2;
            this.moveRange[13][1] = 0;
            this.moveRange[14][0] = -1;
            this.moveRange[14][1] = -1;
            this.moveRange[15][0] = -2;
            this.moveRange[15][1] = -2;
        } else if (unitNo == 9011) {    //ゾーマ変身
            moveRange = new int[16][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 0;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = -1;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = -2;
            this.moveRange[4][0] = 1;
            this.moveRange[4][1] = 0;
            this.moveRange[5][0] = 2;
            this.moveRange[5][1] = 0;
            this.moveRange[6][0] = 1;
            this.moveRange[6][1] = 1;
            this.moveRange[7][0] = 2;
            this.moveRange[7][1] = 2;
            this.moveRange[8][0] = 0;
            this.moveRange[8][1] = 1;
            this.moveRange[9][0] = 0;
            this.moveRange[9][1] = 2;
            this.moveRange[10][0] = -1;
            this.moveRange[10][1] = 1;
            this.moveRange[11][0] = -2;
            this.moveRange[11][1] = 2;
            this.moveRange[12][0] = -1;
            this.moveRange[12][1] = 0;
            this.moveRange[13][0] = -2;
            this.moveRange[13][1] = 0;
            this.moveRange[14][0] = -1;
            this.moveRange[14][1] = -1;
            this.moveRange[15][0] = -2;
            this.moveRange[15][1] = -2;
        } else if (unitNo == 9020) {    //バラモス
            moveRange = new int[16][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 0;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = -1;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = -2;
            this.moveRange[4][0] = 1;
            this.moveRange[4][1] = 0;
            this.moveRange[5][0] = 2;
            this.moveRange[5][1] = 0;
            this.moveRange[6][0] = 1;
            this.moveRange[6][1] = 1;
            this.moveRange[7][0] = 2;
            this.moveRange[7][1] = 2;
            this.moveRange[8][0] = 0;
            this.moveRange[8][1] = 1;
            this.moveRange[9][0] = 0;
            this.moveRange[9][1] = 2;
            this.moveRange[10][0] = -1;
            this.moveRange[10][1] = 1;
            this.moveRange[11][0] = -2;
            this.moveRange[11][1] = 2;
            this.moveRange[12][0] = -1;
            this.moveRange[12][1] = 0;
            this.moveRange[13][0] = -2;
            this.moveRange[13][1] = 0;
            this.moveRange[14][0] = -1;
            this.moveRange[14][1] = -1;
            this.moveRange[15][0] = -2;
            this.moveRange[15][1] = -2;
        } else if (unitNo == 9021) {    //バラモスブロス
            moveRange = new int[16][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 0;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = -1;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = -2;
            this.moveRange[4][0] = 1;
            this.moveRange[4][1] = 0;
            this.moveRange[5][0] = 2;
            this.moveRange[5][1] = 0;
            this.moveRange[6][0] = 1;
            this.moveRange[6][1] = 1;
            this.moveRange[7][0] = 2;
            this.moveRange[7][1] = 2;
            this.moveRange[8][0] = 0;
            this.moveRange[8][1] = 1;
            this.moveRange[9][0] = 0;
            this.moveRange[9][1] = 2;
            this.moveRange[10][0] = -1;
            this.moveRange[10][1] = 1;
            this.moveRange[11][0] = -2;
            this.moveRange[11][1] = 2;
            this.moveRange[12][0] = -1;
            this.moveRange[12][1] = 0;
            this.moveRange[13][0] = -2;
            this.moveRange[13][1] = 0;
            this.moveRange[14][0] = -1;
            this.moveRange[14][1] = -1;
            this.moveRange[15][0] = -2;
            this.moveRange[15][1] = -2;
        } else if (unitNo == 9022) {    //バラモスエビル
            moveRange = new int[16][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 0;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = -1;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = -2;
            this.moveRange[4][0] = 1;
            this.moveRange[4][1] = 0;
            this.moveRange[5][0] = 2;
            this.moveRange[5][1] = 0;
            this.moveRange[6][0] = 1;
            this.moveRange[6][1] = 1;
            this.moveRange[7][0] = 2;
            this.moveRange[7][1] = 2;
            this.moveRange[8][0] = 0;
            this.moveRange[8][1] = 1;
            this.moveRange[9][0] = 0;
            this.moveRange[9][1] = 2;
            this.moveRange[10][0] = -1;
            this.moveRange[10][1] = 1;
            this.moveRange[11][0] = -2;
            this.moveRange[11][1] = 2;
            this.moveRange[12][0] = -1;
            this.moveRange[12][1] = 0;
            this.moveRange[13][0] = -2;
            this.moveRange[13][1] = 0;
            this.moveRange[14][0] = -1;
            this.moveRange[14][1] = -1;
            this.moveRange[15][0] = -2;
            this.moveRange[15][1] = -2;
        } else if (unitNo == 9023) {    //バラモスゾンビ
            moveRange = new int[16][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 0;
            this.moveRange[1][1] = -2;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = -1;
            this.moveRange[3][0] = 2;
            this.moveRange[3][1] = -2;
            this.moveRange[4][0] = 1;
            this.moveRange[4][1] = 0;
            this.moveRange[5][0] = 2;
            this.moveRange[5][1] = 0;
            this.moveRange[6][0] = 1;
            this.moveRange[6][1] = 1;
            this.moveRange[7][0] = 2;
            this.moveRange[7][1] = 2;
            this.moveRange[8][0] = 0;
            this.moveRange[8][1] = 1;
            this.moveRange[9][0] = 0;
            this.moveRange[9][1] = 2;
            this.moveRange[10][0] = -1;
            this.moveRange[10][1] = 1;
            this.moveRange[11][0] = -2;
            this.moveRange[11][1] = 2;
            this.moveRange[12][0] = -1;
            this.moveRange[12][1] = 0;
            this.moveRange[13][0] = -2;
            this.moveRange[13][1] = 0;
            this.moveRange[14][0] = -1;
            this.moveRange[14][1] = -1;
            this.moveRange[15][0] = -2;
            this.moveRange[15][1] = -2;
        } else {    //ふめい
            moveRange = new int[8][2];
            this.moveRange[0][0] = 0;
            this.moveRange[0][1] = -1;
            this.moveRange[1][0] = 1;
            this.moveRange[1][1] = -1;
            this.moveRange[2][0] = 1;
            this.moveRange[2][1] = 0;
            this.moveRange[3][0] = 1;
            this.moveRange[3][1] = 1;
            this.moveRange[4][0] = 0;
            this.moveRange[4][1] = 1;
            this.moveRange[5][0] = -1;
            this.moveRange[5][1] = 1;
            this.moveRange[6][0] = -1;
            this.moveRange[6][1] = 0;
            this.moveRange[7][0] = -1;
            this.moveRange[7][1] = -1;
        }
    }

    public void setUnitBattleRange (int unitNo) {
        if (unitNo == 0) {
            //ありえない
        } else if (unitNo == 10) {  //ゆうしゃ
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 20) {  //ひめ
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 30) {  //せんし
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 40) {  //まほうつかい
            battleRange = new int[8][2];
            this.battleRange[0][0] = 1;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 2;
            this.battleRange[1][1] = -2;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 1;
            this.battleRange[3][0] = 2;
            this.battleRange[3][1] = 2;
            this.battleRange[4][0] = -1;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -2;
            this.battleRange[5][1] = 2;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = -1;
            this.battleRange[7][0] = -2;
            this.battleRange[7][1] = -2;
        } else if (unitNo == 50) {  //そうりょ
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 0;
            this.battleRange[1][1] = -2;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 2;
            this.battleRange[3][1] = 0;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = 0;
            this.battleRange[5][1] = 2;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -2;
            this.battleRange[7][1] = 0;

        } else if (unitNo == 60) {  //トルネコ
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1000) {    //スライム
            battleRange = new int[3][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = 1;
            this.battleRange[1][0] = -1;
            this.battleRange[1][1] = 1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 1;
        } else if (unitNo == 1010) {    //メタルスライム
            battleRange = new int[3][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = 1;
            this.battleRange[1][0] = -1;
            this.battleRange[1][1] = 1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 1;
        } else if (unitNo == 1020) {    //ホイミスライム
            battleRange = new int[3][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = 1;
            this.battleRange[1][0] = -1;
            this.battleRange[1][1] = 1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 1;
        } else if (unitNo == 1030) {    //ドラキー
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1040) {    //バブルスライム
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1050) {    //はぐれメタル
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1060) {    //キメラ
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1070) {   //おおがらす
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1080) {    //ひとくいばこ
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1090) {    //くさったしたい
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1100) {    //さまようよろい
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1110) {    //あくまのきし
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1120) {    //ベビーサタン
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1130) {    //トロール
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1140) {    //ゴーレム
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1150) {    //しりょうのきし
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 1160) {    //ドラゴン
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 9000) {    //りゅうおう
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 9001) {    //りゅうおう変身
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 9010) {    //ゾーマ
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 9011) {    //ゾーマ変身
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 9020) {    //バラモス
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 9021) {    //バラモスブロス
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 9022) {    //バラモスエビル
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else if (unitNo == 9023) {    //バラモスゾンビ
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        } else {    //ふめい
            battleRange = new int[8][2];
            this.battleRange[0][0] = 0;
            this.battleRange[0][1] = -1;
            this.battleRange[1][0] = 1;
            this.battleRange[1][1] = -1;
            this.battleRange[2][0] = 1;
            this.battleRange[2][1] = 0;
            this.battleRange[3][0] = 1;
            this.battleRange[3][1] = 1;
            this.battleRange[4][0] = 0;
            this.battleRange[4][1] = 1;
            this.battleRange[5][0] = -1;
            this.battleRange[5][1] = 1;
            this.battleRange[6][0] = -1;
            this.battleRange[6][1] = 0;
            this.battleRange[7][0] = -1;
            this.battleRange[7][1] = -1;
        }
    }
    public void setUnitParameter (int unitNo) {
        if (unitNo == 0) {
            //ありえない
        } else if (unitNo == 10) {  //ゆうしゃ
            this.level = 1;
            this.experience = 0;
            this.hp = 250;
            this.mp = 30;
            this.strength = 60;
            this.physical = 65;
            this.hit = 50;
            this.agility = 50;
            this.intelligence = 25;
            this.luck = 25;
            this.attackPower = 50;
            this.defensePower = 75;

        } else if (unitNo == 20) {  //ひめ
            this.level = 1;
            this.experience = 0;
            this.hp = 200;
            this.mp = 100;
            this.strength = 30;
            this.physical = 45;
            this.hit = 60;
            this.agility = 75;
            this.intelligence = 75;
            this.faith = 150;
            this.luck = 70;
            this.attackPower = 10;
            this.defensePower = 10;
        } else if (unitNo == 30) {  //せんし
            this.level = 1;
            this.experience = 0;
            this.hp = 300;
            this.mp = 10;
            this.strength = 65;
            this.physical = 65;
            this.hit = 40;
            this.agility = 30;
            this.intelligence = 10;
            this.faith = 100;
            this.luck = 30;
            this.attackPower = 75;
            this.defensePower = 75;
        } else if (unitNo == 40) {  //まほうつかい
            this.level = 1;
            this.experience = 0;
            this.hp = 200;
            this.mp = 200;
            this.strength = 45;
            this.physical = 45;
            this.hit = 60;
            this.agility = 70;
            this.intelligence = 100;
            this.faith = 200;
            this.luck = 50;
            this.attackPower = 25;
            this.defensePower = 25;
        } else if (unitNo == 50) {  //そうりょ
            this.level = 1;
            this.experience = 0;
            this.hp = 200;
            this.mp = 200;
            this.strength = 45;
            this.physical = 55;
            this.hit = 50;
            this.agility = 60;
            this.intelligence = 100;
            this.faith = 200;
            this.luck = 50;
            this.attackPower = 25;
            this.defensePower = 25;

        } else if (unitNo == 60) {  //トルネコ
            this.level = 1;
            this.experience = 0;
            this.hp = 200;
            this.mp = 100;
            this.strength = 35;
            this.physical = 50;
            this.hit = 50;
            this.agility = 75;
            this.intelligence = 50;
            this.faith = 200;
            this.luck = 75;
            this.attackPower = 25;
            this.defensePower = 25;

        } else if (unitNo == 1000) {    //スライム
            this.level = 1;
            this.experience = 0;
            this.hp = 30;
            this.mp = 30;
            this.strength = 35;
            this.physical = 5;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1010) {    //メタルスライム
            this.level = 1;
            this.experience = 0;
            this.hp = 30;
            this.mp = 30;
            this.strength = 35;
            this.physical = 10;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 60;
            this.attackPower = 15;
            this.defensePower = 5;
        } else if (unitNo == 1020) {    //ホイミスライム
            this.level = 1;
            this.experience = 0;
            this.hp = 30;
            this.mp = 30;
            this.strength = 35;
            this.physical = 10;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1030) {    //ドラキー
            this.level = 1;
            this.experience = 0;
            this.hp = 50;
            this.mp = 30;
            this.strength = 40;
            this.physical = 20;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1040) {    //バブルスライム
            this.level = 1;
            this.experience = 0;
            this.hp = 50;
            this.mp = 30;
            this.strength = 40;
            this.physical = 20;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1050) {    //はぐれメタル
            this.level = 1;
            this.experience = 0;
            this.hp = 50;
            this.mp = 30;
            this.strength = 40;
            this.physical = 20;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1060) {    //キメラ
            this.level = 1;
            this.experience = 0;
            this.hp = 50;
            this.mp = 30;
            this.strength = 40;
            this.physical = 20;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 20;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1070) {   //おおがらす
            this.level = 1;
            this.experience = 0;
            this.hp = 50;
            this.mp = 30;
            this.strength = 40;
            this.physical = 20;
            this.hit = 50;
            this.agility = 20;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1080) {    //ひとくいばこ
            this.level = 1;
            this.experience = 0;
            this.hp = 50;
            this.mp = 30;
            this.strength = 40;
            this.physical = 30;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1090) {    //くさったしたい
            this.level = 1;
            this.experience = 0;
            this.hp = 75;
            this.mp = 30;
            this.strength = 40;
            this.physical = 20;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1100) {    //さまようよろい
            this.level = 1;
            this.experience = 0;
            this.hp = 75;
            this.mp = 30;
            this.strength = 50;
            this.physical = 30;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1110) {    //あくまのきし
            this.level = 1;
            this.experience = 0;
            this.hp = 75;
            this.mp = 30;
            this.strength = 50;
            this.physical = 30;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1120) {    //ベビーサタン
            this.level = 1;
            this.experience = 0;
            this.hp = 75;
            this.mp = 30;
            this.strength = 40;
            this.physical = 20;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1130) {    //トロール
            this.level = 1;
            this.experience = 0;
            this.hp = 100;
            this.mp = 30;
            this.strength = 60;
            this.physical = 40;
            this.hit = 25;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1140) {    //ゴーレム
            this.level = 1;
            this.experience = 0;
            this.hp = 100;
            this.mp = 30;
            this.strength = 60;
            this.physical = 40;
            this.hit = 25;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1150) {    //しりょうのきし
            this.level = 1;
            this.experience = 0;
            this.hp = 75;
            this.mp = 30;
            this.strength = 50;
            this.physical = 30;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 1160) {    //ドラゴン
            this.level = 1;
            this.experience = 0;
            this.hp = 100;
            this.mp = 30;
            this.strength = 60;
            this.physical = 40;
            this.hit = 40;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 9000) {    //りゅうおう
            this.level = 1;
            this.experience = 0;
            this.hp = 200;
            this.mp = 30;
            this.strength = 70;
            this.physical = 50;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 9001) {    //りゅうおう変身
            this.level = 1;
            this.experience = 0;
            this.hp = 200;
            this.mp = 30;
            this.strength = 70;
            this.physical = 50;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 9010) {    //ゾーマ
            this.level = 1;
            this.experience = 0;
            this.hp = 200;
            this.mp = 30;
            this.strength = 70;
            this.physical = 50;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 9011) {    //ゾーマ変身
            this.level = 1;
            this.experience = 0;
            this.hp = 200;
            this.mp = 30;
            this.strength = 70;
            this.physical = 50;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 9020) {    //バラモス
            this.level = 1;
            this.experience = 0;
            this.hp = 200;
            this.mp = 30;
            this.strength = 70;
            this.physical = 50;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 9021) {    //バラモスブロス
            this.level = 1;
            this.experience = 0;
            this.hp = 200;
            this.mp = 30;
            this.strength = 70;
            this.physical = 50;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 9022) {    //バラモスエビル
            this.level = 1;
            this.experience = 0;
            this.hp = 200;
            this.mp = 30;
            this.strength = 70;
            this.physical = 50;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else if (unitNo == 9023) {    //バラモスゾンビ
            this.level = 1;
            this.experience = 0;
            this.hp = 200;
            this.mp = 30;
            this.strength = 70;
            this.physical = 50;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        } else {    //ふめい
            this.level = 1;
            this.experience = 0;
            this.hp = 30;
            this.mp = 30;
            this.strength = 15;
            this.physical = 5;
            this.hit = 50;
            this.agility = 5;
            this.intelligence = 5;
            this.faith = 5;
            this.luck = 5;
            this.attackPower = 5;
            this.defensePower = 5;
        }
    }

}

