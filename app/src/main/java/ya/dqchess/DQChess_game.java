package ya.dqchess;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.RunnableFuture;

import static android.app.PendingIntent.getActivity;

public class DQChess_game extends Activity {

    RelativeLayout rl;
    Button menuButton;
    Button moveUpButton, moveDownButton, moveRightButton, moveLeftButton;
    ImageView graphicImageView;
    TextView infoTextView, messageTextView, selectedTextView, ternTextView, phaseTextView, modeTextView;
    TextView cmdMoveTextView, cmdBattleTextView, cmdShowStatusTextView, cmdFinishTextView;
    //Button cmdMoveButton, cmdBattleButton, cmdShowStatusButton;
    //TextView popupTextView;
    PopupWindow mPopupWindow;
//    Thread SubThread = new Thread();
    Handler subThreadHandler;
//    Handler battleHandler;
//    Handler battleMessageHandler;
//    Handler endHandler;
//    Handler enemyMoveHandler;
//    Handler enemyBattleHandler;
//    Handler initTurnHandler;

    Board mainBoard;    //盤クラス
    Tile[][] mainTile;  //タイルクラス
    //ユニットクラス
    Unit_Yusha[] yushaUnit;
    Unit_Hime[] himeUnit;
    Unit_Senshi[] senshiUnit;
    Unit_Mahotukai[] mahotukaiUnit;
    Unit_Souryo[] souryoUnit;
    Unit_Toruneko[] torunekoUnit;
    Unit_Suraimu[] suraimuUnit;
    Unit_Metarusuraimu[] metarusuraimuUnit;
    Unit_Hoimisuraimu[] hoimisuraimuUnit;
    Unit_Doraki[] dorakiUnit;
    Unit_Baburusuraimu[] baburusuraimuUnit;
    Unit_Haguremetaru[] haguremetaruUnit;
    Unit_Kimera[] kimeraUnit;
    Unit_Oogarasu[] oogarasuUnit;
    Unit_Hitokuibako[] hitokuibakoUnit;
    Unit_Kusattashitai[] kusattashitaiUnit;
    Unit_Samayouyoroi[] samayouyoroiUnit;
    Unit_Akumakishi[] akumakishiUnit;
    Unit_Bebisatann[] bebisatannUnit;
    Unit_Tororu[] tororuUnit;
    Unit_Goremu[] goremuUnit;
    Unit_Shiryouonokishi[] shiryouonokishiUnit;
    Unit_Doragon[] doragonUnit;
    Unit_Ryuuou[] ryuuouUnit;
    Unit_Ryuuouhennshinn[] ryuuouhennshinnUnit;
    Unit_Zoma[] zomaUnit;
    Unit_Zomahennshinn[] zomahennshinnUnit;
    Unit_Baramosu[] baramosuUnit;
    Unit_Baramosuburosu[] baramosuburosuUnit;
    Unit_Baramosuebiru[] baramosuebiruUnit;
    Unit_Baramosuzonnbi[] baramosuzonnbiUnit;

    MediaPlayer friendMediaPlayer;
    MediaPlayer enemyMediaPlayer;

    protected  void onDestroy() {
        super.onDestroy();
        //クラスの削除
        mainBoard = null;    //盤クラス
        mainTile  = null;  //タイルクラス
        //ユニットクラス
        Unit.count = 0;
        Unit_Yusha.yusha_count = 0;
        yushaUnit = null;
        Unit_Hime.hime_count = 0;
        himeUnit = null;
        Unit_Senshi.senshi_count = 0;
        senshiUnit = null;
        Unit_Mahotukai.mahotukai_count = 0;
        mahotukaiUnit = null;
        Unit_Souryo.souryo_count = 0;
        souryoUnit = null;
        Unit_Toruneko.toruneko_count = 0;
        torunekoUnit = null;
        Unit_Suraimu.suraimu_count = 0;
        suraimuUnit = null;

        Unit_Metarusuraimu.metarusuraimu_count = 0;
        metarusuraimuUnit = null;
        Unit_Hoimisuraimu.hoimisuraimu_count = 0;
        hoimisuraimuUnit = null;
        Unit_Doraki.doraki_count = 0;
        dorakiUnit = null;
        Unit_Baburusuraimu.baburusuraimu_count = 0;
        baburusuraimuUnit = null;
        Unit_Haguremetaru.haguremetaru_count = 0;
        haguremetaruUnit = null;
        Unit_Kimera.kimera_count = 0;
        kimeraUnit = null;
        Unit_Oogarasu.oogarasu_count = 0;
        oogarasuUnit = null;
        Unit_Hitokuibako.hitokuibako_count = 0;
        hitokuibakoUnit = null;
        Unit_Kusattashitai.kusattashitai_count = 0;
        kusattashitaiUnit = null;
        Unit_Samayouyoroi.samayouyoroi_count = 0;
        samayouyoroiUnit = null;
        Unit_Akumakishi.akumakishi_count = 0;
        akumakishiUnit = null;
        Unit_Bebisatann.bebisatann_count = 0;
        bebisatannUnit = null;
        Unit_Tororu.tororu_count = 0;
        tororuUnit = null;
        Unit_Goremu.goremu_count = 0;
        goremuUnit = null;
        Unit_Shiryouonokishi.shiryouonokishi_count = 0;
        shiryouonokishiUnit = null;
        Unit_Doragon.doragon_count = 0;
        doragonUnit = null;
        Unit_Ryuuou.ryuuou_count = 0;
        ryuuouUnit = null;
        Unit_Ryuuouhennshinn.ryuuouhennshinn_count = 0;
        ryuuouhennshinnUnit = null;
        Unit_Zoma.zoma_count = 0;
        zomaUnit = null;
        Unit_Zomahennshinn.zomahennshinn_count = 0;
        zomahennshinnUnit = null;
        Unit_Baramosu.baramosu_count = 0;
        baramosuUnit = null;
        Unit_Baramosuburosu.baramosuburosu_count = 0;
        baramosuburosuUnit = null;
        Unit_Baramosuebiru.baramosuebiru_count = 0;
        baramosuebiruUnit = null;
        Unit_Baramosuzonnbi.baramosuzonnbi_count = 0;
        baramosuzonnbiUnit = null;

//        mainBoard.allUnitOnBoard = null;
//        mainBoard.enemyUnitHasBattlableFriend = null;
    }

    protected void onResume() {
        super.onResume();
        friendMediaPlayer.setLooping(true);
        // 再生する
        try {
            friendMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        friendMediaPlayer.start();

        enemyMediaPlayer.setLooping(true);
        try {
            enemyMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //レイアウトの設定
        super.onCreate(savedInstanceState);

        rl = new RelativeLayout(this);
        rl.setBackgroundColor(Color.rgb(27, 27, 27));
        setContentView(rl);

        //サブスレッドの作成
        // Looperでメインスレッドを指定して生成
        subThreadHandler = new Handler(Looper.getMainLooper());
//        battleHandler = new Handler(Looper.getMainLooper());
//        battleMessageHandler = new Handler(Looper.getMainLooper());
//
//        endHandler = new Handler(Looper.getMainLooper());
//        enemyMoveHandler = new Handler(Looper.getMainLooper());
//        enemyBattleHandler = new Handler(Looper.getMainLooper());
//        initTurnHandler = new Handler(Looper.getMainLooper());

        //BGM
        // インタンスを生成
        friendMediaPlayer = new MediaPlayer();
        enemyMediaPlayer = new MediaPlayer();
        //音楽ファイル名, あるいはパス
        String friendfilePath = "dq_map01.wav";
        String enemyfilePath = "dq_battle01.wav";

        // assetsから 音源 ファイルを読み込み
        AssetFileDescriptor friendAfdescripter = null;
        AssetFileDescriptor enemyAfdescripter = null;
        try {
            friendAfdescripter = getAssets().openFd(friendfilePath);
            enemyAfdescripter = getAssets().openFd(enemyfilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //MediaPlayerに読み込んだ音楽ファイルを指定
        try {
            friendMediaPlayer.setDataSource(friendAfdescripter.getFileDescriptor(),
                    friendAfdescripter.getStartOffset(),
                    friendAfdescripter.getLength());
            enemyMediaPlayer.setDataSource(enemyAfdescripter.getFileDescriptor(),
                    enemyAfdescripter.getStartOffset(),
                    enemyAfdescripter.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 音量調整を端末のボタンに任せる
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        //盤クラスのインスタンス作成
        mainBoard = new Board(9, 9, 9, 9, 0, 0, 0, 0);
        mainBoard.InitializeBoardInfo();

        //タイルクラスの作成
        //後から設定する
//        mainTile = new Tile[mainBoard.board_sizeX][mainBoard.board_sizeY];

        //タイル情報ファイル書き込み ----------
        String BoardTileInfoFile = "Board_TileInfo.csv";
        FileOutputStream fos  = null;
        try {
            fos = openFileOutput(BoardTileInfoFile, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mainBoard.WriteToFileBoardTileInfo(fos);

        //タイル情報ファイル読み出し
        FileInputStream fis = null;
        try {
            fis = openFileInput(BoardTileInfoFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer sb = mainBoard.ReadFromFileBoardTileInfo(fis, BoardTileInfoFile);

        //ユニット情報ファイル書き込み -------------
        String BoardUnitPosInfoFile = "Board_UnitPosInfo.csv";
        fos  = null;
        try {
            fos = openFileOutput(BoardUnitPosInfoFile, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mainBoard.WriteToFileBoardUnitPosInfo(fos);

        //ユニット情報ファイル読み出し
        fis = null;
        try {
            fis = openFileInput(BoardUnitPosInfoFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sb = mainBoard.ReadFromFileBoardUnitPosInfo(fis, BoardUnitPosInfoFile);
        //messageTextView.setText(sb);

        //ユニットインスタンスの作成
        //盤クラスの全ユニットArrayList設定
        int count = 0;
        count = mainBoard.getTargetUnitCount(10);
        if (count != 0) {
            yushaUnit = new Unit_Yusha[count];
            createTargetUnitInstance(10);
        }
        count = mainBoard.getTargetUnitCount(20);
        if (count != 0) {
            himeUnit = new Unit_Hime[count];
            createTargetUnitInstance(20);
        }
        count = mainBoard.getTargetUnitCount(30);
        if (count != 0) {
            senshiUnit = new Unit_Senshi[count];
            createTargetUnitInstance(30);
        }
        count = mainBoard.getTargetUnitCount(40);
        if (count != 0) {
            mahotukaiUnit = new Unit_Mahotukai[count];
            createTargetUnitInstance(40);
        }
        count = mainBoard.getTargetUnitCount(50);
        if (count != 0) {
            souryoUnit = new Unit_Souryo[count];
            createTargetUnitInstance(50);
        }
        count = mainBoard.getTargetUnitCount(60);
        if (count != 0) {
            torunekoUnit = new Unit_Toruneko[count];
            createTargetUnitInstance(60);
        }
        count = mainBoard.getTargetUnitCount(1000);
        if (count != 0) {
            suraimuUnit = new Unit_Suraimu[count];
            createTargetUnitInstance(1000);
        }
        count = mainBoard.getTargetUnitCount(1010);
        if (count != 0) {
            metarusuraimuUnit = new Unit_Metarusuraimu[count];
            createTargetUnitInstance(1010);
        }
        count = mainBoard.getTargetUnitCount(1020);
        if (count != 0) {
            hoimisuraimuUnit = new Unit_Hoimisuraimu[count];
            createTargetUnitInstance(1020);
        }
        count = mainBoard.getTargetUnitCount(1030);
        if (count != 0) {
            dorakiUnit = new Unit_Doraki[count];
            createTargetUnitInstance(1030);
        }
        count = mainBoard.getTargetUnitCount(1040);
        if (count != 0) {
            baburusuraimuUnit = new Unit_Baburusuraimu[count];
            createTargetUnitInstance(1040);
        }
        count = mainBoard.getTargetUnitCount(1050);
        if (count != 0) {
            haguremetaruUnit = new Unit_Haguremetaru[count];
            createTargetUnitInstance(1050);
        }
        count = mainBoard.getTargetUnitCount(1060);
        if (count != 0) {
            kimeraUnit = new Unit_Kimera[count];
            createTargetUnitInstance(1060);
        }
        count = mainBoard.getTargetUnitCount(1070);
        if (count != 0) {
            oogarasuUnit = new Unit_Oogarasu[count];
            createTargetUnitInstance(1070);
        }
        count = mainBoard.getTargetUnitCount(1080);
        if (count != 0) {
            hitokuibakoUnit = new Unit_Hitokuibako[count];
            createTargetUnitInstance(1080);
        }
        count = mainBoard.getTargetUnitCount(1090);
        if (count != 0) {
            kusattashitaiUnit = new Unit_Kusattashitai[count];
            createTargetUnitInstance(1090);
        }
        count = mainBoard.getTargetUnitCount(1100);
        if (count != 0) {
            samayouyoroiUnit = new Unit_Samayouyoroi[count];
            createTargetUnitInstance(1100);
        }
        count = mainBoard.getTargetUnitCount(1110);
        if (count != 0) {
            akumakishiUnit = new Unit_Akumakishi[count];
            createTargetUnitInstance(1110);
        }
        count = mainBoard.getTargetUnitCount(1120);
        if (count != 0) {
            bebisatannUnit = new Unit_Bebisatann[count];
            createTargetUnitInstance(1120);
        }
        count = mainBoard.getTargetUnitCount(1130);
        if (count != 0) {
            tororuUnit = new Unit_Tororu[count];
            createTargetUnitInstance(1130);
        }
        count = mainBoard.getTargetUnitCount(1140);
        if (count != 0) {
            goremuUnit = new Unit_Goremu[count];
            createTargetUnitInstance(1140);
        }
        count = mainBoard.getTargetUnitCount(1150);
        if (count != 0) {
            shiryouonokishiUnit = new Unit_Shiryouonokishi[count];
            createTargetUnitInstance(1150);
        }
        count = mainBoard.getTargetUnitCount(1160);
        if (count != 0) {
            doragonUnit = new Unit_Doragon[count];
            createTargetUnitInstance(1160);
        }

        count = mainBoard.getTargetUnitCount(9000);
        if (count != 0) {
            ryuuouUnit = new Unit_Ryuuou[count];
            createTargetUnitInstance(9000);
        }
        count = mainBoard.getTargetUnitCount(9001);
        if (count != 0) {
            ryuuouhennshinnUnit = new Unit_Ryuuouhennshinn[count];
            createTargetUnitInstance(9001);
        }
        count = mainBoard.getTargetUnitCount(9010);
        if (count != 0) {
            zomaUnit = new Unit_Zoma[count];
            createTargetUnitInstance(9010);
        }
        count = mainBoard.getTargetUnitCount(9011);
        if (count != 0) {
            zomahennshinnUnit = new Unit_Zomahennshinn[count];
            createTargetUnitInstance(9011);
        }
        count = mainBoard.getTargetUnitCount(9020);
        if (count != 0) {
            baramosuUnit = new Unit_Baramosu[count];
            createTargetUnitInstance(9020);
        }
        count = mainBoard.getTargetUnitCount(9021);
        if (count != 0) {
            baramosuburosuUnit = new Unit_Baramosuburosu[count];
            createTargetUnitInstance(9021);
        }
        count = mainBoard.getTargetUnitCount(9022);
        if (count != 0) {
            baramosuebiruUnit = new Unit_Baramosuebiru[count];
            createTargetUnitInstance(9022);
        }
        count = mainBoard.getTargetUnitCount(9023);
        if (count != 0) {
            baramosuzonnbiUnit = new Unit_Baramosuzonnbi[count];
            createTargetUnitInstance(9023);
        }

        //盤の設定
        TableLayout mainTableLayout = new TableLayout(this);
        mainTile = new Tile[mainBoard.board_sizeX][mainBoard.board_sizeY];
        for (int y=0; y<mainBoard.board_sizeY; y++) {
            TableRow tableRowy = new TableRow(this);
            for (int x=0; x<mainBoard.board_sizeX; x++) {
                ImageView tileiv =  new ImageView(this);
                //タイルクラスの作成と設定
                int tileNo = mainBoard.tileInf[x][y];
                int unitNo = mainBoard.unitPosInf[x][y];    //ユニットIDを取得
                BitmapDrawable bmpDrTile = getTileBmpDrFileName(tileNo);  //タイル画像取得
                mainTile[x][y] = new Tile(tileNo, x, y, bmpDrTile, tileiv);    //タイルクラス作成
                mainTile[x][y].onUnitID = unitNo;   //タイルクラスに乗っているユニットID値設定

                //ユニット
                BitmapDrawable bmpDrUnit = getUnitBmpDrFileName(unitNo);  //ユニット画像取得
                //タイルクラスに乗っているユニットインスタンスNoを設定
                //タイルクラスに乗っているユニットArrayListのインデックスNoを設定
                setTargetUnitInstanceNoOnTile(x, y);
                //イメージビューにタイルとユニットの画像を重ねる
                Drawable[] drawables = {
                        bmpDrTile,
                        bmpDrUnit
                };
                LayerDrawable ld = new LayerDrawable(drawables);
                tileiv.setImageDrawable(ld);

                //テーブルRowに追加
                TableRow.LayoutParams tilePrm = new TableRow.LayoutParams(135, 135);
                tileiv.setLayoutParams(tilePrm);
                tableRowy.addView(tileiv);

                //リスナ設定
                mainTile[x][y].imgView = tileiv;
                mainTile[x][y].imgView.setOnClickListener(new tileBmpOnClickListener(x, y));

            }
            //テーブルレイアウトに追加
            mainTableLayout.addView(tableRowy);
        }

        //------------
        //ボタンの作成 タイトル
        menuButton = new Button(this);
        menuButton.setText("メニュー");
        menuButton.setTextColor(Color.rgb(255, 255, 255));
        menuButton.setBackgroundColor(Color.rgb(27, 27, 27));
        menuButton.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));
        //リスナの設定 トップ画面にもどる
        menuButton.setOnClickListener(new MoveTopOnClickListener());

        //ターンテキストビュー作成
        ternTextView = new TextView(this);
        ternTextView.setText("ターン：1");
        ternTextView.setTextColor(Color.rgb(255, 255, 255));
        ternTextView.setBackgroundColor(Color.rgb(27, 27, 27));

        //フェーズビュー作成
        phaseTextView = new TextView(this);
        phaseTextView.setText("フェーズ：みかた");
        phaseTextView.setTextColor(Color.rgb(255, 255, 255));
        phaseTextView.setBackgroundColor(Color.rgb(27, 27, 27));

        //モードビュー作成
        modeTextView = new TextView(this);
        modeTextView.setText("");
        modeTextView.setTextColor(Color.rgb(255, 255, 255));
        modeTextView.setBackgroundColor(Color.rgb(27, 27, 27));

        //ボタンの作成 上
        moveUpButton = new Button(this);
        moveUpButton.setText("＾");
//        moveUpButton.setTextColor(Color.rgb(255,255,255));
//        moveUpButton.setBackgroundColor(Color.rgb(27,27,27));
//        moveUpButton.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));

        //ボタンの作成 下
        moveDownButton = new Button(this);
        moveDownButton.setText("v");

        //ボタンの作成 右
        moveRightButton = new Button(this);
        moveRightButton.setText(">");

        //ボタンの作成 左
        moveLeftButton = new Button(this);
        moveLeftButton.setText("<");

        //グラフィックイメージビューの作成
        graphicImageView = new ImageView(this);

        //情報テキストビューの作成
        infoTextView = new TextView(this);
        infoTextView.setText("");
        infoTextView.setTextColor(Color.rgb(255, 255, 255));
//        infoTextView.setBackgroundColor(Color.rgb(128, 128, 128));  //グレー
        infoTextView.setBackgroundColor(Color.rgb(27, 27, 27));
        //infoTextView.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));

        //コマンドテキストビューの作成
        cmdMoveTextView = new TextView(this);
        cmdMoveTextView.setText("いどう");
        cmdMoveTextView.setTextColor(Color.rgb(64, 64, 64));
        cmdMoveTextView.setBackgroundColor(Color.rgb(27, 27, 27));
        cmdMoveTextView.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));
        cmdMoveTextView.setClickable(true);
        //リスナの設定
        cmdMoveTextView.setOnClickListener(new cmdMoveOnClickListener());

        cmdBattleTextView = new TextView(this);
        cmdBattleTextView.setText("たたかう");
        cmdBattleTextView.setTextColor(Color.rgb(64, 64, 64));
        cmdBattleTextView.setBackgroundColor(Color.rgb(27, 27, 27));
        cmdBattleTextView.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));
        cmdBattleTextView.setClickable(true);
        //リスナの設定
        cmdBattleTextView.setOnClickListener(new cmdBattleOnClickListener());

        cmdShowStatusTextView = new TextView(this);
        cmdShowStatusTextView.setText("ステータス");
        cmdShowStatusTextView.setTextColor(Color.rgb(64, 64, 64));
        cmdShowStatusTextView.setBackgroundColor(Color.rgb(27, 27, 27));
        cmdShowStatusTextView.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));
        cmdShowStatusTextView.setClickable(true);
        //リスナの設定
        cmdShowStatusTextView.setOnClickListener(new cmdStatusOnClickListener());

        cmdFinishTextView = new TextView(this);
        cmdFinishTextView.setText("しゅうりょう");
        cmdFinishTextView.setTextColor(Color.rgb(255, 255, 255));
        cmdFinishTextView.setBackgroundColor(Color.rgb(27, 27, 27));
        cmdFinishTextView.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));
        cmdFinishTextView.setClickable(true);
        //リスナの設定
        cmdFinishTextView.setOnClickListener(new cmdFinishOnClickListener());

        //メッセージテキストビューの作成
        messageTextView = new TextView(this);
        messageTextView.setText("");
        messageTextView.setTextSize(18);
        messageTextView.setTextColor(Color.rgb(255, 255, 255));
        messageTextView.setBackgroundColor(Color.rgb(27, 27, 27));
//        messageTextView.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));

        //選択座標テキストビューの作成
        selectedTextView = new TextView(this);
        selectedTextView.setText("ざひょう（ 0 , 0 ）");
        selectedTextView.setTextSize(18);
        selectedTextView.setTextColor(Color.rgb(255, 255, 255));
        selectedTextView.setBackgroundColor(Color.rgb(27, 27, 27));
//        selectedTextView.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));

        //ポップアップテキストビューの作成
        //popupTextView = new TextView(this);


        //レイアウト位置の設定    ------------
        //menuButton.setId(1);
        menuButton.setId(R.id.id1);
        //RelativeLayout.LayoutParams pm1 = new RelativeLayout.LayoutParams(140, 80);
        RelativeLayout.LayoutParams pm1 = new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        pm1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        pm1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        mainTableLayout.setId(R.id.id2);
        RelativeLayout.LayoutParams pm2 = new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        pm2.addRule(RelativeLayout.BELOW, R.id.id3);
        pm2.addRule(RelativeLayout.CENTER_HORIZONTAL);

        moveUpButton.setId(R.id.id3);
        RelativeLayout.LayoutParams pm3 = new RelativeLayout.LayoutParams(createParam(150, 130));
//        RelativeLayout.LayoutParams pm3 = new RelativeLayout.LayoutParams
//                (ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        pm3.addRule(RelativeLayout.BELOW, R.id.id1);
        pm3.addRule(RelativeLayout.CENTER_HORIZONTAL, R.id.id2);

        moveDownButton.setId(R.id.id4);
        RelativeLayout.LayoutParams pm4 = new RelativeLayout.LayoutParams(createParam(150, 130));
//        RelativeLayout.LayoutParams pm4 = new RelativeLayout.LayoutParams
//                (ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        pm4.addRule(RelativeLayout.BELOW, R.id.id2);
        pm4.addRule(RelativeLayout.CENTER_HORIZONTAL, R.id.id2);

        moveRightButton.setId(R.id.id5);
        RelativeLayout.LayoutParams pm5 = new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        pm5.addRule(RelativeLayout.RIGHT_OF, R.id.id2);
        pm5.addRule(RelativeLayout.CENTER_VERTICAL, R.id.id2);

        moveLeftButton.setId(R.id.id6);
        RelativeLayout.LayoutParams pm6 = new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        pm6.addRule(RelativeLayout.LEFT_OF, R.id.id2);
        pm6.addRule(RelativeLayout.CENTER_VERTICAL, R.id.id2);

        graphicImageView.setId(R.id.id7);
//        RelativeLayout.LayoutParams pm7 = new RelativeLayout.LayoutParams
//                (ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams pm7 = new RelativeLayout.LayoutParams(createParam(300, 300));
        pm7.addRule(RelativeLayout.BELOW, R.id.id4);
        pm7.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        infoTextView.setId(R.id.id8);
        RelativeLayout.LayoutParams pm8 = new RelativeLayout.LayoutParams(createParam(400, 300));
        pm8.addRule(RelativeLayout.BELOW, R.id.id4);
//        pm8.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        pm8.addRule(RelativeLayout.RIGHT_OF, R.id.id7);

        messageTextView.setId(R.id.id9);
        RelativeLayout.LayoutParams pm9 = new RelativeLayout.LayoutParams(createParam(1400, 300));
        pm9.addRule(RelativeLayout.BELOW, R.id.id8);
        pm9.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        selectedTextView.setId(R.id.id10);
        RelativeLayout.LayoutParams pm10 = new RelativeLayout.LayoutParams(createParam(600, 100));
        pm10.addRule(RelativeLayout.ABOVE, R.id.id7);
        pm10.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        ternTextView.setId(R.id.id11);
        RelativeLayout.LayoutParams pm11 = new RelativeLayout.LayoutParams(createParam(500, 100));
        pm11.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        pm11.addRule(RelativeLayout.RIGHT_OF, R.id.id1);

        phaseTextView.setId(R.id.id12);
        RelativeLayout.LayoutParams pm12 = new RelativeLayout.LayoutParams(createParam(500, 100));
        pm12.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        pm12.addRule(RelativeLayout.RIGHT_OF, R.id.id11);

        modeTextView.setId(R.id.id13);
        RelativeLayout.LayoutParams pm13 = new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        pm13.addRule(RelativeLayout.RIGHT_OF, R.id.id1);
        pm13.addRule(RelativeLayout.BELOW, R.id.id11);

        cmdMoveTextView.setId(R.id.id14);
        RelativeLayout.LayoutParams pm14 = new RelativeLayout.LayoutParams(createParam(300, 100));
        pm14.addRule(RelativeLayout.BELOW, R.id.id4);
        pm14.addRule(RelativeLayout.RIGHT_OF, R.id.id8);

        cmdBattleTextView.setId(R.id.id15);
        RelativeLayout.LayoutParams pm15 = new RelativeLayout.LayoutParams(createParam(300, 100));
        pm15.addRule(RelativeLayout.BELOW, R.id.id14);
//        pm15.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        pm15.addRule(RelativeLayout.RIGHT_OF, R.id.id8);

        cmdShowStatusTextView.setId(R.id.id16);
        RelativeLayout.LayoutParams pm16 = new RelativeLayout.LayoutParams(createParam(300, 100));
        pm16.addRule(RelativeLayout.BELOW, R.id.id15);
//        pm16.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        pm16.addRule(RelativeLayout.RIGHT_OF, R.id.id8);

        cmdFinishTextView.setId(R.id.id17);
        RelativeLayout.LayoutParams pm17 = new RelativeLayout.LayoutParams(createParam(300, 100));
        pm17.addRule(RelativeLayout.BELOW, R.id.id4);
        pm17.addRule(RelativeLayout.RIGHT_OF, R.id.id14);

        //レイアウトに配置
        rl.addView(menuButton, pm1);
        rl.addView(mainTableLayout, pm2);
        rl.addView(moveUpButton, pm3);
        rl.addView(moveDownButton, pm4);
        rl.addView(moveRightButton, pm5);
        rl.addView(moveLeftButton, pm6);
        rl.addView(graphicImageView, pm7);
        rl.addView(infoTextView, pm8);
        rl.addView(messageTextView, pm9);
        rl.addView(selectedTextView, pm10);
        rl.addView(ternTextView, pm11);
        rl.addView(phaseTextView, pm12);
        rl.addView(modeTextView, pm13);
        rl.addView(cmdMoveTextView, pm14);
        rl.addView(cmdBattleTextView, pm15);
        rl.addView(cmdShowStatusTextView, pm16);
        rl.addView(cmdFinishTextView, pm17);

        //カーソルを初期位置(勇者の場所)に設定する
        mainBoard.selecter_posX = yushaUnit[0].posX;
        mainBoard.selecter_posY = yushaUnit[0].posY;
        onClickTileCommonProcess();
        mainBoard.prevSelecter_posX = mainBoard.selecter_posX;
        mainBoard.prevSelecter_posY = mainBoard.selecter_posY ;
        //モード設定
        mainBoard.modeFlag = 11;
        setCommandTextViewStatus(mainBoard.modeFlag);

    }

    //--------------------------------------------------------
    //トップ画面に戻る
    private class MoveTopOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //BGMが再生中ならBGMを切る
            if (friendMediaPlayer.isPlaying()) {
                // 再生終了
                friendMediaPlayer.stop();
            }
            if (enemyMediaPlayer.isPlaying()) {
                // 再生終了
                enemyMediaPlayer.stop();
            }
            //トップ画面に戻る
            finish();
        }
    }

    //タイルをクリックした時の処理 --------------------
    private class tileBmpOnClickListener implements View.OnClickListener {

        int clickPosX, clickPosY;

        public tileBmpOnClickListener(int posX, int posY) {   //コンストラクタ
            this.clickPosX = posX;
            this.clickPosY = posY;
        }

        @Override
        public void onClick(View v) {
            //選択座標更新
            mainBoard.selecter_posX = this.clickPosX;
            mainBoard.selecter_posY = this.clickPosY;

            //サブスレッド実行フラグの設定
            int subThreadFlag = 0x0;

            //モードチェックしモードに応じた処理をする
            // (この時点では、前回クリックした時のモード)
            //選択カーソルが表示されているので、カーソルがないイメージに戻す
            //前回グラフィックイメージビューの更新
            clearPrevCursorBmp(mainBoard.prevSelecter_posX, mainBoard.prevSelecter_posY);
//                clearPrevCursorBmp();
            //クリア処理
            if (mainBoard.modeFlag == 0) {  //ユニット未選択
            } else if (mainBoard.modeFlag == 11 || mainBoard.modeFlag == 12 || mainBoard.modeFlag == 13) {   //ユニット選択中
            } else if (mainBoard.modeFlag == 21) {   //移動前（移動先表示）
                clearPrevMoveRangeBmp();
//            } else if (mainBoard.modeFlag == 22) {   //移動後（移動確定）
            } else if (mainBoard.modeFlag == 31) {   //戦闘前（戦闘対象表示）
                clearPrevBattleRangeBmp();
//            } else if (mainBoard.modeFlag == 32) {   //戦闘
            } else if (mainBoard.modeFlag == 4) {   //ステータス表示
            } else if (mainBoard.modeFlag == 5) {   //終了
                //ありえない？
            }

            //-------------------------------------
            //今回クリックした時の処理
            //モードチェックしモードに応じた処理をする
            // (この時点では、前回クリックした時のモード)
            // (ここで今回のモードに遷移させる)

            //今回クリック位置のユニット(敵)のArrayListのインデックスNoの特定
            int tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
            Unit enemyUnitforBattle = null;
//            if (tileOnUnitArrayIdx != -1) { //ユニットあり
//                enemyUnitforBattle = mainBoard.allUnitOnBoard.get(tileOnUnitArrayIdx);
//            }
            //前回クリック位置のユニット(味方)のArrayListのインデックスNoの特定
            int tilePrevOnUnitArrayIdx = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitArrayListIdx;
            Unit friendUnitforBattle = null;
//            if (tilePrevOnUnitArrayIdx != -1) {     //ユニットあり
//                friendUnitforBattle = mainBoard.allUnitOnBoard.get(tilePrevOnUnitArrayIdx);
//            }

            if (mainBoard.modeFlag == 0
                    || mainBoard.modeFlag == 11 || mainBoard.modeFlag == 12 || mainBoard.modeFlag == 13) {  //ユニット未選択、ユニット選択中
                //モード遷移 要判定処理
                //今回クリック位置のユニットのArrayListのインデックスNoの特定
                tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
                if (tileOnUnitArrayIdx == -1) {
                    //盤クラスのArrayListからインスタンスを取得
                    mainBoard.modeFlag = 0;
                } else {
                    if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 0) {
                        mainBoard.modeFlag = 11;
                    } else if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 1) {
                        mainBoard.modeFlag = 12;
                    } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 0) {
                        mainBoard.modeFlag = 13;
                    } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 1) {
                        mainBoard.modeFlag = 5;
                    }
                }
                /*
                //前回クリック位置のタイルクラスIDの特定
                int tileOnUnitNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitID;
                //前回クリック位置のユニットインスタンスIDの特定
                int tileOnUnitInstanceNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitInstanceID;
                Unit unitPrevObject = getTargetUnitInstance(tileOnUnitNo, tileOnUnitInstanceNo);
                if(unitPrevObject == null) {    //今回クリック位置にユニットがない場合
                    mainBoard.modeFlag = 0;
                } else {    //今回クリック位置にユニットがある場合
                    if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 0) {
                        mainBoard.modeFlag = 11;
                    } else if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 1) {
                        mainBoard.modeFlag = 12;
                    } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 0) {
                        mainBoard.modeFlag = 13;
                    } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 1) {
                        mainBoard.modeFlag = 5;
                    }
                }
                */
                setCommandTextViewStatus(mainBoard.modeFlag);
            } else if (mainBoard.modeFlag == 21) {   //移動前（移動先表示）
                //クリックした場所は移動可能範囲かチェック
                //前回クリック位置のユニットのArrayListのインデックスNoの特定
                tilePrevOnUnitArrayIdx = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitArrayListIdx;
//                //前回クリック位置のタイルクラスIDの特定
//                int tilePrevOnUnitNo = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitID;
//                //前回クリック位置のユニットインスタンスIDの特定
//                int tilePrevOnUnitInstanceNo = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitInstanceID;
//                Unit unitPrevObject = getTargetUnitInstance(tilePrevOnUnitNo, tilePrevOnUnitInstanceNo);
                //クリックした場所は移動可能範囲かチェック
                if (tilePrevOnUnitArrayIdx != -1) {
                    Unit unitPrevObject = mainBoard.allUnitOnBoard.get(tilePrevOnUnitArrayIdx);
//                if (unitPrevObject != null) {
                    int hitFlag = 0;
                    for (int idx = 0; idx <unitPrevObject.moveRange.length; idx++) {
                        //移動可能範囲を算出し、イメージビューに画像をのせる。
                        int dPosX = unitPrevObject.moveRange[idx][0];
                        int dPosY = unitPrevObject.moveRange[idx][1];
                        int nowPosX = unitPrevObject.posX + dPosX;
                        if (nowPosX < 0 || nowPosX > mainBoard.board_sizeX - 1) {   //移動範囲Xチェック
                            continue;
                        }
                        int nowPosY = unitPrevObject.posY + dPosY;
                        if (nowPosY < 0 || nowPosY > mainBoard.board_sizeY - 1) {  //移動範囲Yチェック
                            continue;
                        }
                        //クリックした場所は移動可能範囲かチェック
                        if (nowPosX == mainBoard.selecter_posX && nowPosY == mainBoard.selecter_posY) {
                            hitFlag = 1;
                            break;
                        }
                    }

                    //クリックした場所にユニットがあるかチェック
//                    //クリック位置のユニットのArrayListのインデックスNoの特定
                    tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
//                    //クリック位置のタイルクラスIDの特定
//                    int tileOnUnitNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitID;
//                    //クリック位置のユニットインスタンスIDの特定
//                    int tileOnUnitInstanceNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitInstanceID;
//                    Unit unitObject = getTargetUnitInstance(tileOnUnitNo, tileOnUnitInstanceNo);
                    Unit unitObject;
                    if (tileOnUnitArrayIdx != -1) {
                        unitObject = mainBoard.allUnitOnBoard.get(tileOnUnitArrayIdx);
                    } else {
                        unitObject = null;
                    }

                    if(hitFlag == 1) {  //クリックした場所は移動可能範囲である
                        if (unitObject == null) {
                            //クリックした場所にユニットがない場合は移動する
                            //盤クラス更新
                            mainBoard.friendMoveFinishFlag = 1;
                            //ユニットクラス更新
                            unitPrevObject.posX = mainBoard.selecter_posX;
                            unitPrevObject.posY = mainBoard.selecter_posY;
                            //unitPrevObject.moveFinishFlag = 1;
                            //タイルクラス更新
                            int tmpOnUnitID = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitID;
                            int tmpOnUnitInstanceID = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitInstanceID;
                            int tmpOnUnitArrayListIdx = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitArrayListIdx;
                            mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitID = tmpOnUnitID;
                            mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitInstanceID = tmpOnUnitInstanceID;
                            mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx = tmpOnUnitArrayListIdx;
                            mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitID = 0;
                            mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitInstanceID = -1;
                            mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitArrayListIdx = -1;
                            //選択カーソルが表示されているので、カーソルがないイメージに戻す
                            //前回グラフィックイメージビューの更新
                            clearPrevCursorBmp(mainBoard.prevSelecter_posX, mainBoard.prevSelecter_posY);
                            //モード遷移 要判定処理
//                            mainBoard.modeFlag = 22;
                            if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 0) {
                                mainBoard.modeFlag = 11;
                            } else if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 1) {
                                mainBoard.modeFlag = 12;
                            } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 0){
                                mainBoard.modeFlag = 13;
                            } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 1){
                                mainBoard.modeFlag = 5;
                            }

                        } else {
                            //クリックした場所にユニットがある場合は移動キャンセルする
                            String msg = ("ユニットがいるところへ移動できません。\n移動をキャンセルします。");
                            setMessageToPopupWindow(msg);
                            //モード遷移 要判定処理 setMessageToPopupWindow でやるから不要
//                            if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 0) {
//                                mainBoard.modeFlag = 11;
//                            } else if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 1) {
//                                mainBoard.modeFlag = 12;
//                            } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 0){
//                                mainBoard.modeFlag = 13;
//                            } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 1){
//                                mainBoard.modeFlag = 5;
//                            }
                        }
                    } else {    //クリックした場所は移動可能範囲でない
                        //クリックした場所は移動可能範囲でない場合は移動キャンセル
                        String msg = ("移動範囲外です。\n移動をキャンセルします。");
                        setMessageToPopupWindow(msg);
                        //モード遷移 要判定処理 setMessageToPopupWindow でやるから不要
//                        //モード遷移
//                        if (unitObject == null) {   //クリックした場所にユニットがない
//                            mainBoard.modeFlag = 0;
//                        } else {    //クリックした場所にユニットがある
//                            //モード遷移 要判定処理
//                            if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 0) {
//                                mainBoard.modeFlag = 11;
//                            } else if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 1) {
//                                mainBoard.modeFlag = 12;
//                            } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 0){
//                                mainBoard.modeFlag = 13;
//                            } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 1){
//                                mainBoard.modeFlag = 5;
//                            }
//                        }
                    }
                    setCommandTextViewStatus(mainBoard.modeFlag);

                } else {    //前回クリックした場所にユニットがない状態
                    //ありえない
                }

//            } else if (mainBoard.modeFlag == 21) {   //移動後（移動確定）
//                //何か処理がいる？

            } else if (mainBoard.modeFlag == 31) {   //戦闘前（戦闘対象表示）
                //クリックした場所は戦闘可能範囲かチェック
                //前回クリック位置のユニットのArrayListのインデックスNoの特定
                tilePrevOnUnitArrayIdx = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitArrayListIdx;
                /*
                //前回クリック位置のタイルクラスIDの特定
                int tilePrevOnUnitNo = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitID;
                //前回クリック位置のユニットインスタンスIDの特定
                int tilePrevOnUnitInstanceNo = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitInstanceID;
                Unit unitPrevObject = getTargetUnitInstance(tilePrevOnUnitNo, tilePrevOnUnitInstanceNo);
                */
                if (tilePrevOnUnitArrayIdx != -1) {
                    Unit unitPrevObject = mainBoard.allUnitOnBoard.get(tilePrevOnUnitArrayIdx);
//                if (unitPrevObject != null) {
                    int hitFlag = 0;
                    for (int idx = 0; idx <unitPrevObject.battleRange.length; idx++) {
                        //戦闘可能範囲を算出し、イメージビューに画像をのせる。
                        int dPosX = unitPrevObject.battleRange[idx][0];
                        int dPosY = unitPrevObject.battleRange[idx][1];
                        int nowPosX = unitPrevObject.posX + dPosX;
                        if (nowPosX < 0 || nowPosX > mainBoard.board_sizeX - 1) {   //戦闘範囲Xチェック
                            continue;
                        }
                        int nowPosY = unitPrevObject.posY + dPosY;
                        if (nowPosY < 0 || nowPosY > mainBoard.board_sizeY - 1) {  //戦闘範囲Yチェック
                            continue;
                        }
                        //クリックした場所は戦闘可能範囲かチェック
                        if (nowPosX == mainBoard.selecter_posX && nowPosY == mainBoard.selecter_posY) {
                            hitFlag = 1;
                            break;
                        }
                    }

                    //クリックした場所にユニットがあるかチェック
//                    //クリック位置のユニットのArrayListのインデックスNoの特定
                    tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
                    /*
                    //クリック位置のタイルクラスIDの特定
                    int tileOnUnitNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitID;
                    //クリック位置のユニットインスタンスIDの特定
                    int tileOnUnitInstanceNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitInstanceID;
                    Unit unitObject = getTargetUnitInstance(tileOnUnitNo, tileOnUnitInstanceNo);
                    */
                    Unit unitObject;
                    if (tileOnUnitArrayIdx != -1) {
                        unitObject = mainBoard.allUnitOnBoard.get(tileOnUnitArrayIdx);
                    } else {
                        unitObject = null;
                    }
                    if(hitFlag == 1) {  //クリックした場所は戦闘可能範囲である
                        if (unitObject == null) {
                            //クリックした場所にユニットがない場合は戦闘キャンセル
                            String msg = ("相手がいません。\n戦闘をキャンセルします。");
                            setMessageToPopupWindow(msg);
                            //モード遷移 要判定処理
                            mainBoard.modeFlag = 0;

                        } else {    //クリックした場所にユニットがある場合は戦闘する
                            //クリックした場所に敵ユニットがある場合は戦闘する
                            if (unitObject.friendOrFoe == 2) {

                                mainBoard.friendBattleFinishFlag = 1;
                                //今回クリック位置のユニット(敵)のArrayListのインデックスNoの特定
                                tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
                                enemyUnitforBattle = mainBoard.allUnitOnBoard.get(tileOnUnitArrayIdx);
                                //前回クリック位置のユニット(味方)のArrayListのインデックスNoの特定
                                tilePrevOnUnitArrayIdx = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitArrayListIdx;
                                friendUnitforBattle = mainBoard.allUnitOnBoard.get(tilePrevOnUnitArrayIdx);
                                //サブスレッド処理フラグのセット　味方戦闘処理の実行
                                subThreadFlag = subThreadFlag | 0x1;
//                                subThreadProcess(mainBoard.modeFlag);
//                                subThreadProcess(enemyUnit, friendUnit, subThreadFlag);

                                //味方戦闘処理の実行
//                                friendBattleProcess(enemyUnit, friendUnit);

                                //モード遷移 要判定処理
                                //今回クリック位置のユニットのArrayListのインデックスNoの特定
                                tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
                                if (tileOnUnitArrayIdx == -1) {
                                    mainBoard.modeFlag = 0;
                                } else {
                                    if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 0) {
                                        mainBoard.modeFlag = 11;
                                    } else if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 1) {
                                        mainBoard.modeFlag = 12;
                                    } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 0) {
                                        mainBoard.modeFlag = 13;
                                    } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 1) {
                                        mainBoard.modeFlag = 5;
                                    }
                                }

                                //ユニットクラス更新
//                            unitPrevObject.posX = mainBoard.selecter_posX;
//                            unitPrevObject.posY = mainBoard.selecter_posY;
                               // unitPrevObject.battleFinishFlag = 1;
                                //タイルクラス更新
//                            int tmpOnUnitID = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitID;
//                            int tmpOnUnitInstanceID = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitInstanceID;
//                            mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitID = tmpOnUnitID;
//                            mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitInstanceID = tmpOnUnitInstanceID;
//                            mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitID = 0;
//                            mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitInstanceID = 0;
                                //前回グラフィックイメージビューの更新
                                //前回クリック位置のユニットの画像をクリアする
                                //clearPrevCursorBmp();
                            } else {
                                //クリックした場所に味方ユニットがある場合は戦闘キャンセルする
                                String msg = ("味方に戦闘できません。\n戦闘をキャンセルします。");
                                setMessageToPopupWindow(msg);
                            }
                            //モード遷移 要判定処理 setMessageToPopupWindow でやるから不要
//                            //モード遷移 要判定処理
//                            if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 0) {
//                                mainBoard.modeFlag = 11;
//                            } else if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 1) {
//                                mainBoard.modeFlag = 12;
//                            } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 0){
//                                mainBoard.modeFlag = 13;
//                            } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 1){
//                                mainBoard.modeFlag = 5;
//                            }

                        }
                    } else {    //クリックした場所は戦闘可能範囲でない
                        //クリックした場所は戦闘可能範囲でない場合は移動キャンセル
                        String msg = ("戦闘範囲外です。\n戦闘をキャンセルします。");
                        setMessageToPopupWindow(msg);
                        //モード遷移 要判定処理 setMessageToPopupWindow でやるから不要
//                        //モード遷移
//                        if (unitObject == null) {   //クリックした場所にユニットがない
//                            mainBoard.modeFlag = 0;
//                        } else {    //クリックした場所にユニットがある
//                            //モード遷移 要判定処理
//                            if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 0) {
//                                mainBoard.modeFlag = 11;
//                            } else if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 1) {
//                                mainBoard.modeFlag = 12;
//                            } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 0){
//                                mainBoard.modeFlag = 13;
//                            } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 1){
//                                mainBoard.modeFlag = 5;
//                            }
//                        }
                    }
                    setCommandTextViewStatus(mainBoard.modeFlag);

                } else {    //前回クリックした場所にユニットがない状態
                    //ありえない
                }
//            } else if (mainBoard.modeFlag == 32) {   //戦闘
//                //何か処理がいる？
            } else if (mainBoard.modeFlag == 4) {   //ステータス表示
                //ありえない
            } else if (mainBoard.modeFlag == 5) {   //終了
                //ありえない
            }

            //モードにかかわらず共通の処理------------
            onClickTileCommonProcess();

            //モード遷移後の処理
            if (mainBoard.modeFlag == 0) {  //ユニット未選択
            } else if (mainBoard.modeFlag == 11 || mainBoard.modeFlag == 12 || mainBoard.modeFlag == 13) {   //ユニット選択中
            } else if (mainBoard.modeFlag == 21) {   //移動前（移動先表示）
//            } else if (mainBoard.modeFlag == 22) {   //移動後（移動確定）
            } else if (mainBoard.modeFlag == 31) {   //戦闘前（戦闘対象表示）
//            } else if (mainBoard.modeFlag == 32) {   //戦闘後
            } else if (mainBoard.modeFlag == 4) {   //ステータス表示
                //ありえない
            } else if (mainBoard.modeFlag == 5) {   //味方ユニット終了
                //サブスレッド処理フラグのセット　終了の実行
                subThreadFlag = subThreadFlag | 0x10;
//                //ターン終了処理
//                subThreadProcess(mainBoard.modeFlag);
//                //ターン終了処理
//                turnEndProcess();

                //BGMの切り替え
                EnemyBattleBGMProcess(2);   //味方→敵BGM
            } else {
                //モード遷移フラグが想定外の値の場合
            }

            //サブスレッド処理の実行
            subThreadProcess(subThreadFlag, enemyUnitforBattle, friendUnitforBattle);

            //BGMの切り替え
//            EnemyBattleBGMProcess(1);   //敵→味方BGM

            //前回選択座標情報の更新   ここではやらない。
            mainBoard.prevSelecter_posX = mainBoard.selecter_posX;
            mainBoard.prevSelecter_posY = mainBoard.selecter_posY;

        }
    }

    private void onClickTileCommonProcess() {
        //選択座標テキストビューの表示更新
        selectedTextView.setText("ざひょう（ " + mainBoard.selecter_posX + " , " + mainBoard.selecter_posY + " ）");

        //グラフィックイメージビューの更新
        //クリック位置のタイルクラスIDの特定
        //ユニットクラスからBitmapDrawableをとってきて表示した方がいい？
        int onUnitNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitID;
        Bitmap bmp;
        if (onUnitNo == 0) {    //ユニットなし
            int TileNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].tileID;
            bmp = getTileBmpFileName(TileNo);
        } else {    //ユニットあり
            bmp = getUnitBmpFileName(onUnitNo);
        }
        graphicImageView.setImageBitmap(bmp);

        //タイルイメージの更新
        //カーソル
        BitmapDrawable bmpDrCursor = (BitmapDrawable) getDrawable(R.drawable.selected);  //ユニット画像取得
        //タイル
        BitmapDrawable bmpDrTile = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].bmp;    //タイル画像取得
        //ユニット
        BitmapDrawable bmpDrUnit;
        //クリック位置のユニットのArrayListのインデックスNoの特定
        Unit unitObject = null;
        int tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
        if (tileOnUnitArrayIdx != -1) {
            unitObject = mainBoard.allUnitOnBoard.get(tileOnUnitArrayIdx);
            bmpDrUnit = unitObject.bmp;  //ユニット画像取得
        } else {
            bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);  //ユニット画像取得
        }
        /*
        //クリック位置のタイルクラスIDの特定
        int tileOnUnitNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitID;
        //クリック位置のユニットインスタンスIDの特定
        int tileOnUnitInstanceNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitInstanceID;
        Unit unitObject = null;
        if (tileOnUnitNo != 0){ //ユニットを選択している状態
            unitObject = getTargetUnitInstance(tileOnUnitNo, tileOnUnitInstanceNo);
        } else {    //ユニットを選択していない状態
        }

        if (unitObject != null) {
            bmpDrUnit = unitObject.bmp;  //ユニット画像取得
        } else {
            bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);  //ユニット画像取得
        }
        */
        //イメージビューにタイルとユニットの画像を重ねる
        Drawable[] drawablesNew = {
                bmpDrTile,
                bmpDrUnit,
                bmpDrCursor
        };
        LayerDrawable ldNew = new LayerDrawable(drawablesNew);
        mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].imgView.setImageDrawable(ldNew);

        //情報テキストビューの更新
        if (unitObject != null) {   //ユニットを選択している状態
            String unitCNameStr = String.valueOf(unitObject.instanseName);
            String unitLevelStr = String.valueOf(unitObject.level);
            String unitHpStr = String.valueOf(unitObject.hp);
            String unitMpStr = String.valueOf(unitObject.mp);
            //情報テキストビューの更新
            infoTextView.setText(unitCNameStr + "\nＬＶ：" + unitLevelStr + "\nＨＰ：" + unitHpStr + "\nＭＰ：" + unitMpStr);
        } else {    //ユニットを選択していな状態
            //情報テキストビューの更新
            String infStr = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].tileName;
            //情報テキストビューの更新
            infoTextView.setText(infStr);
        }

        //メッセージテキストビューの更新
    }

    //コマンドテキストビューの移動をクリックした時
    private class cmdMoveOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //モードに応じてコマンドテキストビューの状態を設定する。
            if (mainBoard.modeFlag == 0) {  //ユニット未選択
                //ありえない
                //この状態の時は、移動コマンドテキストビューが有効ではないため
            } else if(mainBoard.modeFlag == 11 || mainBoard.modeFlag == 12 || mainBoard.modeFlag == 13) {       //ユニット選択中
                //モード遷移 移動先表示
                mainBoard.modeFlag = 21;
                //クリック位置のユニットのArrayListのインデックスNoの特定
                Unit unitObject = null;
                int tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
                if (tileOnUnitArrayIdx != -1) {
                    unitObject = mainBoard.allUnitOnBoard.get(tileOnUnitArrayIdx);
                /*
                //クリック位置のタイルクラスIDの特定
                int tileOnUnitNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitID;
                //クリック位置のユニットインスタンスIDの特定
                int tileOnUnitInstanceNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitInstanceID;
                //ポップアップウィンドウに表示する内容を設定
                Unit unitObject = null;
                if (tileOnUnitNo != 0){
                    unitObject = getTargetUnitInstance(tileOnUnitNo, tileOnUnitInstanceNo);
                */
                    //移動可能範囲を算出し、イメージビューに画像をのせる。
                    for (int idx = 0; idx <unitObject.moveRange.length; idx++) {
                        int dPosX = unitObject.moveRange[idx][0];
                        int dPosY = unitObject.moveRange[idx][1];
                        int nowPosX = unitObject.posX + dPosX;
                        if (nowPosX < 0 || nowPosX > mainBoard.board_sizeX -1) {   //移動範囲Xチェック
                            continue;
                        }
                        int nowPosY = unitObject.posY + dPosY;
                        if (nowPosY < 0 || nowPosY > mainBoard.board_sizeY -1) {  //移動範囲Yチェック
                            continue;
                        }
                        //移動範囲の画像
                        BitmapDrawable bmpMoveMap = (BitmapDrawable) getDrawable(R.drawable.map_moveable);
                        //タイル
                        BitmapDrawable bmpDrTile = mainTile[nowPosX][nowPosY].bmp;    //タイル画像取得
                        //ユニット
                        int tileMoveRangeOnUnitNo = mainTile[nowPosX][nowPosY].onUnitID;
                        //クリック位置のユニットインスタンスIDの特定
                        int tileMoveRangeOnUnitInstanceNo = mainTile[nowPosX][nowPosY].onUnitInstanceID;

                        Unit unitMoveRangeObject = null;
                        BitmapDrawable bmpDrUnit;
                        if (tileMoveRangeOnUnitNo != 0){ //ユニットあり状態
                            unitMoveRangeObject = getTargetUnitInstance(tileMoveRangeOnUnitNo, tileMoveRangeOnUnitInstanceNo);
                            bmpDrUnit = unitMoveRangeObject.bmp;
                        } else {
                            bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);
                        }

                        //イメージビューにタイルとユニットの画像を重ねる
                        Drawable[] drawables = {
                               bmpDrTile,
                                bmpDrUnit,
                                bmpMoveMap
                        };
                        LayerDrawable ld = new LayerDrawable(drawables);
                        mainTile[nowPosX][nowPosY].imgView.setImageDrawable(ld);

                    }
                } else {
                    //ありえない？
                }

            }
            //コマンドテキストビューの状態設定
            setCommandTextViewStatus(mainBoard.modeFlag);

        }
    }

    //コマンドテキストビューのたたかうをクリックした時
    private class cmdBattleOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //モードに応じてコマンドテキストビューの状態を設定する。
            if (mainBoard.modeFlag == 0) {  //ユニット未選択
                //ありえない
                //この状態の時は、移動コマンドテキストビューが有効ではないため
            } else if(mainBoard.modeFlag == 11 || mainBoard.modeFlag == 12 || mainBoard.modeFlag == 13) {       //ユニット選択中
                //モード遷移 戦闘先表示
                mainBoard.modeFlag = 31;
                //クリック位置のユニットのArrayListのインデックスNoの特定
                Unit unitObject = null;
                int tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
                if (tileOnUnitArrayIdx != -1) {
                    unitObject = mainBoard.allUnitOnBoard.get(tileOnUnitArrayIdx);
                /*
                //クリック位置のタイルクラスIDの特定
                int tileOnUnitNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitID;
                //クリック位置のユニットインスタンスIDの特定
                int tileOnUnitInstanceNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitInstanceID;
                //ポップアップウィンドウに表示する内容を設定
                Unit unitObject = null;
                if (tileOnUnitNo != 0){
                    unitObject = getTargetUnitInstance(tileOnUnitNo, tileOnUnitInstanceNo);
                    */
                    //戦闘可能範囲を算出し、イメージビューに画像をのせる。
                    for (int idx = 0; idx <unitObject.battleRange.length; idx++) {
                        int dPosX = unitObject.battleRange[idx][0];
                        int dPosY = unitObject.battleRange[idx][1];
                        int nowPosX = unitObject.posX + dPosX;
                        if (nowPosX < 0 || nowPosX > mainBoard.board_sizeX -1) {   //戦闘範囲Xチェック
                            continue;
                        }
                        int nowPosY = unitObject.posY + dPosY;
                        if (nowPosY < 0 || nowPosY > mainBoard.board_sizeY -1) {  //戦闘範囲Yチェック
                            continue;
                        }
                        //移動範囲の画像
                        BitmapDrawable bmpMoveMap = (BitmapDrawable) getDrawable(R.drawable.map_battlable);
                        //タイル
                        BitmapDrawable bmpDrTile = mainTile[nowPosX][nowPosY].bmp;    //タイル画像取得
                        //ユニット
                        int tileMoveRangeOnUnitNo = mainTile[nowPosX][nowPosY].onUnitID;
                        //クリック位置のユニットインスタンスIDの特定
                        int tileMoveRangeOnUnitInstanceNo = mainTile[nowPosX][nowPosY].onUnitInstanceID;

                        Unit unitMoveRangeObject = null;
                        BitmapDrawable bmpDrUnit;
                        if (tileMoveRangeOnUnitNo != 0){ //ユニットあり状態
                            unitMoveRangeObject = getTargetUnitInstance(tileMoveRangeOnUnitNo, tileMoveRangeOnUnitInstanceNo);
                            bmpDrUnit = unitMoveRangeObject.bmp;
                        } else {
                            bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);
                        }

                        //イメージビューにタイルとユニットの画像を重ねる
                        Drawable[] drawables = {
                                bmpDrTile,
                                bmpDrUnit,
                                bmpMoveMap
                        };
                        LayerDrawable ld = new LayerDrawable(drawables);
                        mainTile[nowPosX][nowPosY].imgView.setImageDrawable(ld);

                    }
                } else {
                    //ありえない？
                }

            }
            //コマンドテキストビューの状態設定
            setCommandTextViewStatus(mainBoard.modeFlag);

        }
    }

    //コマンドテキストビューのステータスをクリックした時
    private class cmdStatusOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //モードに応じてコマンドテキストビューの状態を設定する。
            if (mainBoard.modeFlag == 0) {  //ユニット未選択
                //ありえない
            } else if (mainBoard.modeFlag == 11 || mainBoard.modeFlag == 12 || mainBoard.modeFlag == 13) {       //ユニット選択中
                //モード遷移 ステータス表示
                mainBoard.modeFlag = 4;
                //コマンドテキストビューの状態設定
                setCommandTextViewStatus(mainBoard.modeFlag);

                //ポップアップウィンドウに渡すメッセージの作成
                //クリック位置のユニットのArrayListのインデックスNoの特定
                Unit unitObject = null;
                int tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
                String popupWndMsg = "";
                if (tileOnUnitArrayIdx != -1) {
                    unitObject = mainBoard.allUnitOnBoard.get(tileOnUnitArrayIdx);
                /*
                //クリック位置のタイルクラスIDの特定
                int tileOnUnitNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitID;
                //クリック位置のユニットインスタンスIDの特定
                int tileOnUnitInstanceNo = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitInstanceID;
                */
                //ポップアップウィンドウに表示する内容を設定
//                if (tileOnUnitNo != 0) {
//                    unitObject = getTargetUnitInstance(tileOnUnitNo, tileOnUnitInstanceNo);
                    popupWndMsg = (unitObject.instanseName + "\n"
                            + "    LV:" + String.valueOf(unitObject.level) + "\n"
                            + "    HP:" + String.valueOf(unitObject.hp) + "\n"
                            + "    MP:" + String.valueOf(unitObject.mp) + "\n"
                            + "    ちから:" + String.valueOf(unitObject.strength) + "\n"
                            + "    たいりょく:" + String.valueOf(unitObject.physical) + "\n"
                            + "    めいちゅう:" + String.valueOf(unitObject.hit) + "\n"
                            + "    すばやさ:" + String.valueOf(unitObject.agility) + "\n"
                            + "    かしこさ:" + String.valueOf(unitObject.intelligence) + "\n"
                            + "    しんこう:" + String.valueOf(unitObject.faith) + "\n"
                            + "    うん:" + String.valueOf(unitObject.luck) + "\n");
//                            + "    こうげきりょく:" + String.valueOf(unitObject.attackPower) + "\n"
//                            + "    ぼうぎょりょく:" + String.valueOf(unitObject.defensePower) + "\n");
                } else {
                    popupWndMsg = "";
                }

                //ポップアップウィンドウの表示
                setMessageToPopupWindow(popupWndMsg);

            }
        }
    }

    private class cmdFinishOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //コマンドテキストビューの状態設定
            mainBoard.modeFlag = 5; //終了
            setCommandTextViewStatus(mainBoard.modeFlag);

            //サブスレッド処理フラグのセット　終了の実行
            int subThreadFlag = 0x0;
            subThreadFlag = subThreadFlag | 0x10;

            //今回クリック位置のユニット(敵)のArrayListのインデックスNoの特定
//            int tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
//            Unit enemyUnitDummy = mainBoard.allUnitOnBoard.get(tileOnUnitArrayIdx);
//            //前回クリック位置のユニット(味方)のArrayListのインデックスNoの特定
//            int tilePrevOnUnitArrayIdx = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitArrayListIdx;
//            Unit friendUnitDummy = mainBoard.allUnitOnBoard.get(tilePrevOnUnitArrayIdx);
            Unit enemyUnitDummy = null;
            Unit friendUnitDummy = null;
//
            //BGMの切り替え
            EnemyBattleBGMProcess(2);   //味方→敵BGM

            subThreadProcess(subThreadFlag, enemyUnitDummy, friendUnitDummy);

//            //BGMの切り替え
//            EnemyBattleBGMProcess(1);   //敵→味方BGM

//            //ターン終了処理
//            turnEndProcess();
            //デバッグコード-----
            //スレッドだとUIを変更したときに落ちる
//            backThread.start();     //バックグラウンドでスレッドスタート
//            Runnable runnable = new MyRunnable();
//            new Thread(runnable).start();
            //デバッグコード-----
        }
    }

    void ExcuteSleepProcess(int sleeptime) {
        //スリープ
        try{
            Thread.sleep(sleeptime);
        }catch(InterruptedException e){}
    }

    void EnemyBattleBGMProcess(int bgm) {
        //BGMの切り替え
        if(bgm == 1){   //敵→味方BGM
            if (enemyMediaPlayer.isPlaying()) {
                // 再生終了
                enemyMediaPlayer.stop();
                try {
                    enemyMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            friendMediaPlayer.start();
        }
        if(bgm == 2){   //味方→敵BGM
            if (friendMediaPlayer.isPlaying()) {
                // 再生終了
                friendMediaPlayer.stop();
                try {
                    friendMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            enemyMediaPlayer.start();
        }
    }

    private class EnemyBattleBGMRunnable implements Runnable {
        int bgm;
        public EnemyBattleBGMRunnable(int bgm) {
            this.bgm = bgm;
        }

        @Override
        public void run() {
            //BGMの切り替え
            if(bgm == 1){   //敵→味方BGM
                if (enemyMediaPlayer.isPlaying()) {
                    // 再生終了
                    enemyMediaPlayer.stop();
                    try {
                        enemyMediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                friendMediaPlayer.start();
            }
            if(bgm == 2){   //味方→敵BGM
                if (friendMediaPlayer.isPlaying()) {
                    // 再生終了
                    friendMediaPlayer.stop();
                    try {
                        friendMediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                enemyMediaPlayer.start();
            }
        }
    }

    private class EnemyBattleSearchRunnable implements Runnable {
        @Override
        public void run() {
            if (mainBoard.gameOverFlag == 0) {  //ゲームオーバフラグがゲーム続行のとき実行
                //敵の攻撃がまだ済んでない場合この処理を実行する。
                if (mainBoard.enemyBattleFinishFlag == 0) {
                    //前回クリック位置のカーソルを消す
                    clearPrevCursorBmp(mainBoard.selecter_posX, mainBoard.selecter_posY);
//                clearPrevCursorBmp(mainBoard.prevSelecter_posX, mainBoard.prevSelecter_posY);

                    String msg;
                    //最初に戦闘可能範囲内に味方ユニットがいるかチェック
                    //盤に存在する敵ユニットをすべて検索
                    //盤と各クラスの戦闘候補ArrayListに値を設定する
                    mainBoard.enemyUnitHasBattlableFriend.clear();  //ArrayListのクリア
                    for (int unitIdx = 0; unitIdx < mainBoard.allUnitOnBoard.size(); unitIdx++) {
                        //各敵ユニットの戦闘可能範囲を調べる
                        Unit checkingEnemyUnit = mainBoard.allUnitOnBoard.get(unitIdx);
                        if (checkingEnemyUnit.friendOrFoe == 2) {  //敵ユニットの場合
                            checkingEnemyUnit.battleCandidateUnit.clear();  //ArrayListのクリア
                            for (int idx = 0; idx < checkingEnemyUnit.battleRange.length; idx++) {
                                //戦闘可能範囲を算出し、
                                int dPosX = checkingEnemyUnit.battleRange[idx][0];
                                int dPosY = checkingEnemyUnit.battleRange[idx][1];
                                int nowPosX = checkingEnemyUnit.posX + dPosX;
                                if (nowPosX < 0 || nowPosX > mainBoard.board_sizeX - 1) {   //戦闘範囲Xチェック
                                    continue;
                                }
                                int nowPosY = checkingEnemyUnit.posY + dPosY;
                                if (nowPosY < 0 || nowPosY > mainBoard.board_sizeY - 1) {  //戦闘範囲Yチェック
                                    continue;
                                }
                                int candidateFriendIdx = mainTile[nowPosX][nowPosY].onUnitArrayListIdx;
                                if (candidateFriendIdx != -1) {  //攻撃範囲にユニットあり
                                    //戦闘可能範囲に味方ユニットがいる場合
                                    Unit candidateFriendUnit = mainBoard.allUnitOnBoard.get(candidateFriendIdx);
                                    if (candidateFriendUnit.friendOrFoe == 1) { //味方ユニットの場合
                                        //ユニットクラスの戦闘候補配列に追加（複数存在する場合がある）
                                        checkingEnemyUnit.battleCandidateUnit.add(candidateFriendUnit);
                                        if (mainBoard.enemyUnitHasBattlableFriend.size() != 0) {    //ArrayListが空でない場合
                                            int num = mainBoard.enemyUnitHasBattlableFriend.size();
                                            Unit unit = mainBoard.enemyUnitHasBattlableFriend.get(num - 1);
                                            if (unit != checkingEnemyUnit) {   //現在チェック中の戦闘候補が存在する敵ユニットが盤クラスの戦闘候補ArrayListに追加されていない場合
                                                //盤クラスに戦闘候補が存在する敵ユニットを戦闘候補配列に追加（複数存在する場合がある）
                                                mainBoard.enemyUnitHasBattlableFriend.add(checkingEnemyUnit);
                                            }
                                        } else {    //ArrayListが~空の場合
                                            //盤クラスに戦闘候補が存在する敵ユニットを戦闘候補配列に追加（複数存在する場合がある）
                                            mainBoard.enemyUnitHasBattlableFriend.add(checkingEnemyUnit);
                                        }
                                    }
                                } else {
                                    //戦闘可能範囲に味方ユニットがいない場合
                                }
                            }
                        }
                    }

                    //戦闘候補配列が空でない（いずれかの敵ユニットは、戦闘可能な味方ユニットがいる）の場合
                    if (mainBoard.enemyUnitHasBattlableFriend.size() != 0) {
//                    mainBoard.enemyBattleFinishFlag = 1;  //ここではダメ 敵戦闘ダメージ処理で立てる
                        //どの敵ユニットが戦闘を行うか決定し戦闘する。（ランダム?優先順位?）
                        mainBoard.doBattleEnemyUnit = null;
                        mainBoard.battledFriendUnit = null;
                        Random doBattleEnemyRandom = new Random();
                        int doBattleEnemyNum = mainBoard.enemyUnitHasBattlableFriend.size();
                        int doBattleEnemyIdx = doBattleEnemyRandom.nextInt(doBattleEnemyNum);
                        mainBoard.doBattleEnemyUnit = mainBoard.enemyUnitHasBattlableFriend.get(doBattleEnemyIdx);

                        //戦闘を行うユニットが決定したら、どの味方ユニットに戦闘を行うか決定する。（ランダム?優先順位?）
                        Random battledFriendRandom = new Random();
                        int battledFriendNum = mainBoard.doBattleEnemyUnit.battleCandidateUnit.size();
                        int battledFriendIdx = battledFriendRandom.nextInt(battledFriendNum);
                        mainBoard.battledFriendUnit = mainBoard.doBattleEnemyUnit.battleCandidateUnit.get(battledFriendIdx);

                        //UI更新
                        //盤イメージの更新
                        int posX = mainBoard.doBattleEnemyUnit.posX;
                        int posY = mainBoard.doBattleEnemyUnit.posY;
                        BitmapDrawable bmpDrCursor = (BitmapDrawable) getDrawable(R.drawable.selected);  //ユニット画像取得
                        BitmapDrawable bmpDrTile = mainTile[posX][posY].bmp;    //タイル画像取得
                        BitmapDrawable bmpDrUnit = mainBoard.doBattleEnemyUnit.bmp;  //ユニット画像取得
                        Drawable[] drawables = {
                                bmpDrTile,
                                bmpDrUnit,
                                bmpDrCursor
                        };
                        LayerDrawable ld = new LayerDrawable(drawables);
                        mainTile[posX][posY].imgView.setImageDrawable(ld);

                        //選択座標テキストビューの表示更新
                        selectedTextView.setText("ざひょう（ " + posX + " , " + posY + " ）");

                        //グラフィックイメージビューの更新
                        //クリック位置のタイルクラスIDの特定
                        //ユニットクラスからBitmapDrawableをとってきて表示した方がいい？
                        int onUnitNo = mainTile[posX][posY].onUnitID;
                        Bitmap bmp;
                        if (onUnitNo == 0) {    //ユニットなし
                            int TileNo = mainTile[posX][posY].tileID;
                            bmp = getTileBmpFileName(TileNo);
                        } else {    //ユニットあり
                            bmp = getUnitBmpFileName(onUnitNo);
                        }
                        graphicImageView.setImageBitmap(bmp);

                        //情報テキストビューの更新
                        if (mainBoard.doBattleEnemyUnit != null) {   //ユニットを選択している状態
                            String unitCNameStr = String.valueOf(mainBoard.doBattleEnemyUnit.instanseName);
                            String unitLevelStr = String.valueOf(mainBoard.doBattleEnemyUnit.level);
                            String unitHpStr = String.valueOf(mainBoard.doBattleEnemyUnit.hp);
                            String unitMpStr = String.valueOf(mainBoard.doBattleEnemyUnit.mp);
                            //情報テキストビューの更新
                            infoTextView.setText(unitCNameStr + "\nＬＶ：" + unitLevelStr + "\nＨＰ：" + unitHpStr + "\nＭＰ：" + unitMpStr);
                        } else {    //ユニットを選択していな状態
                            //情報テキストビューの更新
                            String infStr = mainTile[posX][posY].tileName;
                            //情報テキストビューの更新
                            infoTextView.setText(infStr);
                        }
                        //盤クラスの更新
                        mainBoard.prevSelecter_posX = posX;
                        mainBoard.prevSelecter_posY = posY;

                    }

                    phaseTextView.setText("フェーズ：てき");
                    //メッセージ表示
                    messageTextView.setText("みかたフェーズ　しゅうりょう。");
                }
            }
        }
    }

    private class EnemyBattleHitRunnable implements Runnable {
//        Unit doBattleEnemyUnit, battledFriendUnit;
//
//        public EnemyBattleHitRunnable(Unit doBattleEnemyUnit, Unit battledFriendUnit) {
//            this.doBattleEnemyUnit = doBattleEnemyUnit;
//            this.battledFriendUnit = battledFriendUnit;
//        }

        @Override
        public void run() {
            if (mainBoard.gameOverFlag == 0) {  //ゲームオーバフラグがゲーム続行のとき実行
                //敵の攻撃がまだ済んでない場合この処理を実行する。
                if (mainBoard.enemyBattleFinishFlag == 0) {
                    if (mainBoard.enemyUnitHasBattlableFriend.size() != 0) {    //Runnableの中で判定すること
                        //盤クラスの戦闘候補をもつ敵ユニットArrayListが空でないとき処理を行う
                        //敵命中判定処理
                        enemyBattleHitProcess(mainBoard.doBattleEnemyUnit, mainBoard.battledFriendUnit);
                    }
                }
            }
        }
    }

    private class EnemyBattleDamageRunnable implements Runnable {
//        Unit doBattleEnemyUnit, battledFriendUnit;
//
//        public EnemyBattleDamageRunnable(Unit doBattleEnemyUnit, Unit battledFriendUnit) {
//            this.doBattleEnemyUnit = doBattleEnemyUnit;
//            this.battledFriendUnit = battledFriendUnit;
//        }

        @Override
        public void run() {
            if (mainBoard.gameOverFlag == 0) {  //ゲームオーバフラグがゲーム続行のとき実行
                //敵の攻撃がまだ済んでない場合この処理を実行する。
                if (mainBoard.enemyBattleFinishFlag == 0) {
                    //戦闘候補配列が空でない（いずれかの敵ユニットは、戦闘可能な味方ユニットがいる）の場合
                    if (mainBoard.enemyUnitHasBattlableFriend.size() != 0) {    //Runnableの中で判定すること
                        //盤クラスの戦闘候補をもつ敵ユニットArrayListが空でないとき処理を行う
                        //敵の攻撃が命中時のみこの処理を行う
                        if (mainBoard.enemryBattleHitFlag == 1) { //Runnableの中で判定すること
                            //敵戦闘処理
                            enemyBattleDamageProcess(mainBoard.doBattleEnemyUnit, mainBoard.battledFriendUnit);

                        }
                    }
                }
            }
        }
    }


    private class EnemyBattleMoveRunnable implements Runnable {
        @Override
        public void run() {
            if (mainBoard.gameOverFlag == 0) {  //ゲームオーバフラグがゲーム続行のとき実行
                //敵移動処理
                enemyBattleMoveProcess(mainBoard.doBattleEnemyUnit, mainBoard.battledFriendUnit);
            }
        }
    }


    private class TurnEndInitRnnable implements Runnable {
        @Override
        public void run() {
            //初期化
            mainBoard.friendMoveFinishFlag = 0;
            mainBoard.friendBattleFinishFlag = 0;
            mainBoard.enemyMoveFinishFlag = 0;
            mainBoard.enemyBattleFinishFlag = 0;
            mainBoard.doBattleEnemyUnit = null;
            mainBoard.battledFriendUnit = null;

            //ゲームの終了条件判定
            //竜王ユニットHP:0    味方勝利
            //姫ユニットHP:0    敵勝利
            if (mainBoard.gameOverFlag == 1) {  //味方勝利
                //ポップアップウィンドウに表示する文字を作成
                String popupWndMsg = ("りゅうおうをたおした！！！\nあなたの勝利です。");
                //ポップアップウィンドウ表示
                gameWinPopupWindow(popupWndMsg);
            } else if (mainBoard.gameOverFlag == 2) {   //敵勝利
                //ポップアップウィンドウに表示する文字を作成
                String popupWndMsg = ("ひめはしんでしまった！！！\nゲームオーバー");
                //ポップアップウィンドウ表示
                gameLossPopupWindow(popupWndMsg);
            } else {
                //敵フェーズが完了しターンが終了たら、モード遷移する
                //前回クリック位置ユニットのArrayListのインデックスNoの特定   //Runnableの中で判定すること
                int tileOnUnitArrayIdx = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitArrayListIdx;
                if (tileOnUnitArrayIdx == -1) { //クリック位置にユニットが存在しなし
                    //盤クラスのArrayListからインスタンスを取得
                    mainBoard.modeFlag = 0;     //ユニット未選択状態
                } else {    //クリック位置にユニットが存在する。
                    mainBoard.modeFlag = 11;    //ユニット選択状態
                }

                //UIの更新
                mainBoard.tern++;
                ternTextView.setText("ターン：" + String.valueOf(mainBoard.tern));
                phaseTextView.setText("フェーズ：みかた");

                //ポップアップウィンドウに表示する文字を作成
                String popupWndMsg = ("ターン：" + String.valueOf(mainBoard.tern) + "\n" + "スタート！");
                //ポップアップウィンドウ表示
                setMessageToPopupWindow(popupWndMsg);
            }
        }
    }

    private class GameEndTunnable implements Runnable {
        @Override
        public void run() {
//            if (mainBoard.gameOverFlag == 1) {  //味方勝利
//                //ポップアップウィンドウに表示する文字を作成
//                String popupWndMsg = ("りゅうおうをたおした！！！\nあなたの勝利です。");
//                //ポップアップウィンドウ表示
//                setMessageToPopupWindow(popupWndMsg);
//            } else if (mainBoard.gameOverFlag == 2) {   //敵勝利
//                //ポップアップウィンドウに表示する文字を作成
//                String popupWndMsg = ("ひめはしんでしまった！！！\nゲームオーバー");
//                //ポップアップウィンドウ表示
//                setMessageToPopupWindow(popupWndMsg);
//            } else {
//            }

            }
    }



    private class FriendBattleRunnable implements Runnable {
        Unit enemyUnit, friendUnit;

        public FriendBattleRunnable(Unit enemyUnit, Unit friendUnit) {
            this.enemyUnit = enemyUnit;
            this.friendUnit = friendUnit;
        }

        @Override
        public void run() {
//            //今回クリック位置のユニット(敵)のArrayListのインデックスNoの特定
//            int tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
//            Unit enemyUnit = mainBoard.allUnitOnBoard.get(tileOnUnitArrayIdx);
//            //前回クリック位置のユニット(味方)のArrayListのインデックスNoの特定
//            int tilePrevOnUnitArrayIdx = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitArrayListIdx;
//            Unit friendUnit = mainBoard.allUnitOnBoard.get(tilePrevOnUnitArrayIdx);
            messageTextView.setText(friendUnit.instanseName + " の攻撃");
        }
    }

    private class FriendBattleHitRunnable implements Runnable {
        Unit enemyUnit, friendUnit;
        public FriendBattleHitRunnable(Unit enemyUnit, Unit friendUnit) {
            this.enemyUnit = enemyUnit;
            this.friendUnit = friendUnit;
        }

        @Override
        public void run() {
            if (mainBoard.gameOverFlag == 0) {  //ゲームオーバフラグがゲーム続行のとき実行
                //命中判定
                String msg;
                //基準値の設定
                int baseFixVal = 60;
                Random hitRandom = new Random();
                int baseVariableVal = hitRandom.nextInt(30);
                //命中率の計算
                int intHitRandom = hitRandom.nextInt(100);
                int hitRate = ((baseFixVal + baseVariableVal) + friendUnit.hit - enemyUnit.agility);
                if (intHitRandom <= hitRate) {
                    mainBoard.friendBattleHitFlag = 1;
                    msg = (friendUnit.instanseName + " のこうげきは "+ enemyUnit.instanseName + " にめいちゅうした。\n");
                } else {
                    mainBoard.friendBattleHitFlag = 0;
                    msg = (enemyUnit.instanseName + " は "+ friendUnit.instanseName + " のこうげきをひらりとかわした。\n");
                    //運による補正
                    int intLuckRandom = hitRandom.nextInt(100);
                    if (intLuckRandom <= friendUnit.luck) {
                        //ここカバレッジ大丈夫か？
                        mainBoard.friendBattleHitFlag = 1;
                        msg = msg + ("しかし、こううんのめがみが "+ friendUnit.instanseName +" にほほえみかけた。");
                    }
                }
                messageTextView.setText(msg);
            }
        }
    }

    private class FriendBattleDamageRunnable implements Runnable {
        Unit enemyUnit, friendUnit;
        public FriendBattleDamageRunnable(Unit enemyUnit, Unit friendUnit) {
            this.enemyUnit = enemyUnit;
            this.friendUnit = friendUnit;
        }

        @Override
        public void run() {
            if (mainBoard.gameOverFlag == 0) {  //ゲームオーバフラグがゲーム続行のとき実行
                //命中であたり判定の時のみこの処理を実行する
                String msg;
                if (mainBoard.friendBattleHitFlag == 1) {   //命中があたった場合 //Runnableの中で判定すること
                    //ダメージの算出
//                    //自分の力と相手の体力が、あまりにも差がありすぎる時の補正
//                    //自分の力と相手の体力が、あまりにも差がありすぎるかチェック
//                    //自分の力*1.2 – 相手の体力*0.7
//                    double check = ((double)friendUnit.strength * 1.0) - ((double)enemyUnit.physical * 1.0);
//                    int friendStrength;
//                    if (check <= 0) {
//                        //自分の力をプラス補正する
//                        friendStrength = (int)(((double)enemyUnit.physical * 0.7) / 1.0);
//                    } else {
//                        friendStrength = friendUnit.strength;
//                    }
                    //基準値の設定
                    Random damageRandom = new Random();
                    int baseFriendFixRate = 60;
                    int baseFriendVariableRate = damageRandom.nextInt(70);
                    double baseFriendRate = ((double)(baseFriendFixRate) + (double)(baseFriendVariableRate)) /100;
                    int baseEnemyFixRate = 60;
                    int baseEnemyVariableRate = damageRandom.nextInt(70);
                    double baseEnemyRate = ((double)(baseEnemyFixRate) + (double)(baseEnemyVariableRate)) /100;

                    //ダメージの算出
                    int damage = ((int)(friendUnit.strength * baseEnemyRate)) - (int)(friendUnit.strength * (enemyUnit.physical * baseFriendRate /100));
//                    int damage = ((int)(friendStrength * baseFriendRate)) - ((int)(enemyUnit.physical * baseEnemyRate));
//                    int damage = (int)((friendStrength - enemyUnit.physical) * baseRate);
                    //運による補正
                    int intLuckRandom = damageRandom.nextInt(100);
                    if (intLuckRandom <= friendUnit.luck) {
                        //クリティカルダメージ
                        //ここカバレッジ大丈夫か？
                        damage = (int)(damage * 1.5);
                        msg = ("かいしんのいちげき！\n" );
                    } else {
                        msg = "";
                    }

                    if(damage <= 0) {
                        msg = msg + ("しかし、" + friendUnit.instanseName + " は " + enemyUnit.instanseName + " にダメージをあたえられない。\n");
                    } else {
                        msg = msg + (friendUnit.instanseName + " は " + enemyUnit.instanseName + " に " + damage + "のダメージをあたえた。\n");
                        enemyUnit.hp = enemyUnit.hp - damage;
                    }

                    //戦闘後処理
                    if (enemyUnit.hp <= 0){
                        //敵ユニットを倒した場合
                        //ユニット消滅処理
                        int dPosX = enemyUnit.posX;
                        int dPosY = enemyUnit.posY;

                        //盤クラスの更新
                        mainBoard.allUnitOnBoard.remove(mainBoard.allUnitOnBoard.indexOf(enemyUnit));
                        //タイルクラスの更新
                        mainTile[dPosX][dPosY].onUnitID = 0;
                        mainTile[dPosX][dPosY].onUnitInstanceID = -1;
                        mainTile[dPosX][dPosY].onUnitArrayListIdx = -1;

                        //すべてのタイルクラスのonUnitArrayListIdxを更新する
                        //タイルクラスの乗っているユニットのArrayListのインデックスNoに値をセットする
                        int cnt;
                        for (cnt = 0; cnt <mainBoard.allUnitOnBoard.size(); cnt++) {
                            Unit UnitBuf = mainBoard.allUnitOnBoard.get(cnt);
                            mainTile[UnitBuf.posX][UnitBuf.posY].onUnitArrayListIdx = cnt;
                        }

                        //ユニットクラスの更新
                        enemyUnit.hp = 0;
                        enemyUnit.posX = -1;
                        enemyUnit.posY = -1;
                        //盤イメージの更新
                        BitmapDrawable bmpDrCursor = (BitmapDrawable) getDrawable(R.drawable.selected);  //ユニット画像取得
                        BitmapDrawable bmpDrTile = mainTile[dPosX][dPosY].bmp;    //タイル画像取得
                        BitmapDrawable bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);  //ユニット画像取得
                        Drawable[] drawables = {
                                bmpDrTile,
                                bmpDrUnit,
                                bmpDrCursor
                        };
                        LayerDrawable ld = new LayerDrawable(drawables);
                        mainTile[dPosX][dPosY].imgView.setImageDrawable(ld);

                        //メッセージ作成
                        msg = msg + (friendUnit.instanseName + " は " + enemyUnit.instanseName + " をたおした。\n");

                        //ゲームの終了条件判定
                        //竜王ユニットHP:0    味方勝利
                        //姫ユニットHP:0    敵勝利
                        if (enemyUnit.unitID == 9000) {  //倒したユニットがりゅうおうなら味方勝利
                            mainBoard.gameOverFlag = 1;
                        }

                    } else {
                        //敵ユニットはまだ倒していない場合
                    }
                    //メッセージ表示
                    messageTextView.setText(msg);

                    mainBoard.friendBattleHitFlag = 0;  //フラグを落とす

                }
            }
        }
    }

    private class FriendBattleFinishRunnable implements Runnable {
        Unit enemyUnit, friendUnit;
        public FriendBattleFinishRunnable(Unit enemyUnit, Unit friendUnit) {
            this.enemyUnit = enemyUnit;
            this.friendUnit = friendUnit;
        }

        @Override
        public void run() {
            if (mainBoard.gameOverFlag == 0) {  //ゲームオーバフラグがゲーム続行のとき実行
                messageTextView.setText("みかたのせんとうはしゅうりょうした。");
            }
        }
    }

    //-----------------
    //RelativeLayoutでサイズを指定する
    private RelativeLayout.LayoutParams createParam(int w, int h){
        return new RelativeLayout.LayoutParams(w, h);
    }

    //タイルの画像を取得する
    BitmapDrawable getTileBmpDrFileName (int TileNo) {
        BitmapDrawable bmp;
        //タイル BMPの設定
        if (TileNo == 1) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.map_heigen);
        } else if (TileNo == 2) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.map_shinrin);
        } else if (TileNo == 3) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.map_umi);
        } else if (TileNo == 4) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.map_yama);
        } else if (TileNo == 5) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.map_iwayama);
        } else if (TileNo == 6) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.map_sabaku);
        } else if (TileNo == 7) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.map_machi);
        } else if (TileNo == 8) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.map_shiro);
        } else {
            bmp = (BitmapDrawable) getDrawable(R.drawable.map_heigen);
        }
        return bmp;
    }

    //タイルの画像を取得する
    Bitmap getTileBmpFileName (int TileNo) {
        Bitmap bmp;
        //タイル BMPの設定
        if (TileNo == 1) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map_heigen);
        } else if (TileNo == 2) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map_shinrin);
        } else if (TileNo == 3) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map_umi);
        } else if (TileNo == 4) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map_yama);
        } else if (TileNo == 5) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map_iwayama);
        } else if (TileNo == 6) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map_sabaku);
        } else if (TileNo == 7) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map_machi);
        } else if (TileNo == 8) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map_shiro);
        } else {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map_heigen);
        }
        return bmp;
    }


    //ユニットの画像を取得する
    BitmapDrawable getUnitBmpDrFileName (int unitNo) {
        BitmapDrawable bmp;
        //ユニット BMPの設定
        if (unitNo == 0) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);
        } else if (unitNo == 10) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u1_yusha);
        } else if (unitNo == 20) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u1_hime);
        } else if (unitNo == 30) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u1_senshi);
        } else if (unitNo == 40) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u1_maho);
        } else if (unitNo == 50) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u1_souryo);
        } else if (unitNo == 60) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u1_toruneko);
        } else if (unitNo == 1000) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_suraimu);
        } else if (unitNo == 1010) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_metarusuraimu);
        } else if (unitNo == 1020) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_hoimisuraimu);
        } else if (unitNo == 1030) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_doraki);
        } else if (unitNo == 1040) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_baburusuraimu);
        } else if (unitNo == 1050) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_haguremetaru);
        } else if (unitNo == 1060) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_kimera);
        } else if (unitNo == 1070) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_oogarasu);
        } else if (unitNo == 1080) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_hitokuibako);
        } else if (unitNo == 1090) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_kusattashitai);
        } else if (unitNo == 1100) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_samayouyoroi);
        } else if (unitNo == 1110) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_akumakishi);
        } else if (unitNo == 1120) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_bebisatann);
        } else if (unitNo == 1130) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_tororu);
        } else if (unitNo == 1140) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_goremu);
        } else if (unitNo == 1150) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_shiryouonokishi);
        } else if (unitNo == 1160) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_doragon);
        } else if (unitNo == 9000) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_ryuuou);
        } else if (unitNo == 9001) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_ryuuouhennshinn);
        } else if (unitNo == 9010) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_zoma);
        } else if (unitNo == 9011) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_zomahennshinn);
        } else if (unitNo == 9020) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_baramosu);
        } else if (unitNo == 9021) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_baramosuburosu);
        } else if (unitNo == 9022) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_baramosuebiru);
        } else if (unitNo == 9023) {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_baramosuzonnbi);
        } else {
            bmp = (BitmapDrawable) getDrawable(R.drawable.u2_suraimu);
        }
        return bmp;
    }

    //ユニットの画像を取得する
    Bitmap getUnitBmpFileName (int unitNo) {
        Bitmap bmp;
        //ユニット BMPの設定
        if (unitNo == 0) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u_unit_non);
        } else if (unitNo == 10) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u1_yusha);
        } else if (unitNo == 20) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u1_hime);
        } else if (unitNo == 30) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u1_senshi);
        } else if (unitNo == 40) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u1_maho);
        } else if (unitNo == 50) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u1_souryo);
        } else if (unitNo == 60) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u1_toruneko);
        } else if (unitNo == 1000) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_suraimu);
        } else if (unitNo == 1010) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_metarusuraimu);
        } else if (unitNo == 1020) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_hoimisuraimu);
        } else if (unitNo == 1030) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_doraki);
        } else if (unitNo == 1040) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_baburusuraimu);
        } else if (unitNo == 1050) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_haguremetaru);
        } else if (unitNo == 1060) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_kimera);
        } else if (unitNo == 1070) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_oogarasu);
        } else if (unitNo == 1080) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_hitokuibako);
        } else if (unitNo == 1090) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_kusattashitai);
        } else if (unitNo == 1100) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_samayouyoroi);
        } else if (unitNo == 1110) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_akumakishi);
        } else if (unitNo == 1120) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_bebisatann);
        } else if (unitNo == 1130) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_tororu);
        } else if (unitNo == 1140) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_goremu);
        } else if (unitNo == 1150) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_shiryouonokishi);
        } else if (unitNo == 1160) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_doragon);
        } else if (unitNo == 9000) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_ryuuou);
        } else if (unitNo == 9001) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_ryuuouhennshinn);
        } else if (unitNo == 9010) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_zoma);
        } else if (unitNo == 9011) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_zomahennshinn);
        } else if (unitNo == 9020) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_baramosu);
        } else if (unitNo == 9021) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_baramosuburosu);
        } else if (unitNo == 9022) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_baramosuebiru);
        } else if (unitNo == 9023) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_baramosuzonnbi);
        } else {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.u2_suraimu);
        }
        return bmp;
    }

    /*
    //情報テキストビューの更新
    String getInfoTextString(int onUnitNo, int xpos, int ypos) {

        String infStr;
        if (onUnitNo == 0) {    //ユニットなし タイル情報表示
            //情報テキストビューの更新
            infStr = mainTile[xpos][ypos].tileName;
            //infoTextView.setText(infStr);

        } else {    //ユニットあり ユニット情報表示
//            10:ゆうしゃ
//            20:ひめ
//            30:せんし
//            40:まほうつかい
//            50:そうりょ
//            60:トルネコ
//            1000	スライム
//            1010	メタルスライム
//            1020	ホイミスライム
//            1030	ドラキー
//            1040	バブルスライム
//            1050	はぐれメタル
//            1060	キメラ
//            1070	おおがらす
//            1080	ひとくいばこ
//            1090	くさったしたい
//            1100	さまようよろい
//            1110	あくまのきし
//            1120	ベビーサタン
//            1130	トロール
//            1140	ゴーレム
//            1150	しりょうのきし
//            1160	ドラゴン
//            9000	りゅうおう
//            9001	りゅうおう変身
//            9010	ゾーマ
//            9011	ゾーマ変身
//            9020	バラモス
//            9021	バラモスブロス
//            9022	バラモスエビル
//            9023	バラモスゾンビ

            String unitCNameStr = "";
            String unitLevelStr = "";
            String unitHpStr = "";
            String unitMpStr = "";
            int idx;
            if (onUnitNo == 10) {   //ゆうしゃ
                for (idx = 0; idx<yushaUnit.length; idx++) {
                    if (yushaUnit[idx].posX == xpos && yushaUnit[idx].posY == ypos) {
                        unitCNameStr = yushaUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(yushaUnit[idx].level);
                        unitHpStr = String.valueOf(yushaUnit[idx].hp);
                        unitMpStr = String.valueOf(yushaUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 20) {
                for (idx = 0; idx<himeUnit.length; idx++) {
                    if (himeUnit[idx].posX == xpos && himeUnit[idx].posY == ypos) {
                        unitCNameStr = himeUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(himeUnit[idx].level);
                        unitHpStr = String.valueOf(himeUnit[idx].hp);
                        unitMpStr = String.valueOf(himeUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 30) {
                for (idx = 0; idx<senshiUnit.length; idx++) {
                    if (senshiUnit[idx].posX == xpos && senshiUnit[idx].posY == ypos) {
                        unitCNameStr = senshiUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(senshiUnit[idx].level);
                        unitHpStr = String.valueOf(senshiUnit[idx].hp);
                        unitMpStr = String.valueOf(senshiUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 40) {
                for (idx = 0; idx<mahotukaiUnit.length; idx++) {
                    if (mahotukaiUnit[idx].posX == xpos && mahotukaiUnit[idx].posY == ypos) {
                        unitCNameStr = mahotukaiUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(mahotukaiUnit[idx].level);
                        unitHpStr = String.valueOf(mahotukaiUnit[idx].hp);
                        unitMpStr = String.valueOf(mahotukaiUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 50) {
                for (idx = 0; idx<souryoUnit.length; idx++) {
                    if (souryoUnit[idx].posX == xpos && souryoUnit[idx].posY == ypos) {
                        unitCNameStr = souryoUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(souryoUnit[idx].level);
                        unitHpStr = String.valueOf(souryoUnit[idx].hp);
                        unitMpStr = String.valueOf(souryoUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 60) {
                for (idx = 0; idx<torunekoUnit.length; idx++) {
                    if (torunekoUnit[idx].posX == xpos && torunekoUnit[idx].posY == ypos) {
                        unitCNameStr = torunekoUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(torunekoUnit[idx].level);
                        unitHpStr = String.valueOf(torunekoUnit[idx].hp);
                        unitMpStr = String.valueOf(torunekoUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1000) {
                for (idx = 0; idx<suraimuUnit.length; idx++) {
                    if (suraimuUnit[idx].posX == xpos && suraimuUnit[idx].posY == ypos) {
                        unitCNameStr = suraimuUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(suraimuUnit[idx].level);
                        unitHpStr = String.valueOf(suraimuUnit[idx].hp);
                        unitMpStr = String.valueOf(suraimuUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1010) {
                for (idx = 0; idx<metarusuraimuUnit.length; idx++) {
                    if (metarusuraimuUnit[idx].posX == xpos && metarusuraimuUnit[idx].posY == ypos) {
                        unitCNameStr = metarusuraimuUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(metarusuraimuUnit[idx].level);
                        unitHpStr = String.valueOf(metarusuraimuUnit[idx].hp);
                        unitMpStr = String.valueOf(metarusuraimuUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1020) {
                for (idx = 0; idx<hoimisuraimuUnit.length; idx++) {
                    if (hoimisuraimuUnit[idx].posX == xpos && hoimisuraimuUnit[idx].posY == ypos) {
                        unitCNameStr = hoimisuraimuUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(hoimisuraimuUnit[idx].level);
                        unitHpStr = String.valueOf(hoimisuraimuUnit[idx].hp);
                        unitMpStr = String.valueOf(hoimisuraimuUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1030) {
                for (idx = 0; idx<dorakiUnit.length; idx++) {
                    if (dorakiUnit[idx].posX == xpos && dorakiUnit[idx].posY == ypos) {
                        unitCNameStr = dorakiUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(dorakiUnit[idx].level);
                        unitHpStr = String.valueOf(dorakiUnit[idx].hp);
                        unitMpStr = String.valueOf(dorakiUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1040) {
                for (idx = 0; idx<baburusuraimuUnit.length; idx++) {
                    if (baburusuraimuUnit[idx].posX == xpos && baburusuraimuUnit[idx].posY == ypos) {
                        unitCNameStr = baburusuraimuUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(baburusuraimuUnit[idx].level);
                        unitHpStr = String.valueOf(baburusuraimuUnit[idx].hp);
                        unitMpStr = String.valueOf(baburusuraimuUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1050) {
                for (idx = 0; idx<haguremetaruUnit.length; idx++) {
                    if (haguremetaruUnit[idx].posX == xpos && haguremetaruUnit[idx].posY == ypos) {
                        unitCNameStr = haguremetaruUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(haguremetaruUnit[idx].level);
                        unitHpStr = String.valueOf(haguremetaruUnit[idx].hp);
                        unitMpStr = String.valueOf(haguremetaruUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1060) {
                for (idx = 0; idx<kimeraUnit.length; idx++) {
                    if (kimeraUnit[idx].posX == xpos && kimeraUnit[idx].posY == ypos) {
                        unitCNameStr = kimeraUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(kimeraUnit[idx].level);
                        unitHpStr = String.valueOf(kimeraUnit[idx].hp);
                        unitMpStr = String.valueOf(kimeraUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1070) {
                for (idx = 0; idx<oogarasuUnit.length; idx++) {
                    if (oogarasuUnit[idx].posX == xpos && oogarasuUnit[idx].posY == ypos) {
                        unitCNameStr = oogarasuUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(oogarasuUnit[idx].level);
                        unitHpStr = String.valueOf(oogarasuUnit[idx].hp);
                        unitMpStr = String.valueOf(oogarasuUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1080) {
                for (idx = 0; idx<hitokuibakoUnit.length; idx++) {
                    if (hitokuibakoUnit[idx].posX == xpos && hitokuibakoUnit[idx].posY == ypos) {
                        unitCNameStr = hitokuibakoUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(hitokuibakoUnit[idx].level);
                        unitHpStr = String.valueOf(hitokuibakoUnit[idx].hp);
                        unitMpStr = String.valueOf(hitokuibakoUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1090) {
                for (idx = 0; idx<kusattashitaiUnit.length; idx++) {
                    if (kusattashitaiUnit[idx].posX == xpos && kusattashitaiUnit[idx].posY == ypos) {
                        unitCNameStr = kusattashitaiUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(kusattashitaiUnit[idx].level);
                        unitHpStr = String.valueOf(kusattashitaiUnit[idx].hp);
                        unitMpStr = String.valueOf(kusattashitaiUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1100) {
                for (idx = 0; idx<samayouyoroiUnit.length; idx++) {
                    if (samayouyoroiUnit[idx].posX == xpos && samayouyoroiUnit[idx].posY == ypos) {
                        unitCNameStr = samayouyoroiUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(samayouyoroiUnit[idx].level);
                        unitHpStr = String.valueOf(samayouyoroiUnit[idx].hp);
                        unitMpStr = String.valueOf(samayouyoroiUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1110) {
                for (idx = 0; idx<akumakishiUnit.length; idx++) {
                    if (akumakishiUnit[idx].posX == xpos && akumakishiUnit[idx].posY == ypos) {
                        unitCNameStr = akumakishiUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(akumakishiUnit[idx].level);
                        unitHpStr = String.valueOf(akumakishiUnit[idx].hp);
                        unitMpStr = String.valueOf(akumakishiUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1120) {
                for (idx = 0; idx<bebisatannUnit.length; idx++) {
                    if (bebisatannUnit[idx].posX == xpos && bebisatannUnit[idx].posY == ypos) {
                        unitCNameStr = bebisatannUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(bebisatannUnit[idx].level);
                        unitHpStr = String.valueOf(bebisatannUnit[idx].hp);
                        unitMpStr = String.valueOf(bebisatannUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1130) {
                for (idx = 0; idx<tororuUnit.length; idx++) {
                    if (tororuUnit[idx].posX == xpos && tororuUnit[idx].posY == ypos) {
                        unitCNameStr = tororuUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(tororuUnit[idx].level);
                        unitHpStr = String.valueOf(tororuUnit[idx].hp);
                        unitMpStr = String.valueOf(tororuUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1140) {
                for (idx = 0; idx<goremuUnit.length; idx++) {
                    if (goremuUnit[idx].posX == xpos && goremuUnit[idx].posY == ypos) {
                        unitCNameStr = goremuUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(goremuUnit[idx].level);
                        unitHpStr = String.valueOf(goremuUnit[idx].hp);
                        unitMpStr = String.valueOf(goremuUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1150) {
                for (idx = 0; idx<shiryouonokishiUnit.length; idx++) {
                    if (shiryouonokishiUnit[idx].posX == xpos && shiryouonokishiUnit[idx].posY == ypos) {
                        unitCNameStr = shiryouonokishiUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(shiryouonokishiUnit[idx].level);
                        unitHpStr = String.valueOf(shiryouonokishiUnit[idx].hp);
                        unitMpStr = String.valueOf(shiryouonokishiUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 1160) {
                for (idx = 0; idx<doragonUnit.length; idx++) {
                    if (doragonUnit[idx].posX == xpos && doragonUnit[idx].posY == ypos) {
                        unitCNameStr = doragonUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(doragonUnit[idx].level);
                        unitHpStr = String.valueOf(doragonUnit[idx].hp);
                        unitMpStr = String.valueOf(doragonUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 9000) {
                for (idx = 0; idx<ryuuouUnit.length; idx++) {
                    if (ryuuouUnit[idx].posX == xpos && ryuuouUnit[idx].posY == ypos) {
                        unitCNameStr = ryuuouUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(ryuuouUnit[idx].level);
                        unitHpStr = String.valueOf(ryuuouUnit[idx].hp);
                        unitMpStr = String.valueOf(ryuuouUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 9001) {
                for (idx = 0; idx<ryuuouhennshinnUnit.length; idx++) {
                    if (ryuuouhennshinnUnit[idx].posX == xpos && ryuuouhennshinnUnit[idx].posY == ypos) {
                        unitCNameStr = ryuuouhennshinnUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(ryuuouhennshinnUnit[idx].level);
                        unitHpStr = String.valueOf(ryuuouhennshinnUnit[idx].hp);
                        unitMpStr = String.valueOf(ryuuouhennshinnUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 9010) {
                for (idx = 0; idx<zomaUnit.length; idx++) {
                    if (zomaUnit[idx].posX == xpos && zomaUnit[idx].posY == ypos) {
                        unitCNameStr = zomaUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(zomaUnit[idx].level);
                        unitHpStr = String.valueOf(zomaUnit[idx].hp);
                        unitMpStr = String.valueOf(zomaUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 9011) {
                for (idx = 0; idx<zomahennshinnUnit.length; idx++) {
                    if (zomahennshinnUnit[idx].posX == xpos && zomahennshinnUnit[idx].posY == ypos) {
                        unitCNameStr = zomahennshinnUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(zomahennshinnUnit[idx].level);
                        unitHpStr = String.valueOf(zomahennshinnUnit[idx].hp);
                        unitMpStr = String.valueOf(zomahennshinnUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 9020) {
                for (idx = 0; idx<baramosuUnit.length; idx++) {
                    if (baramosuUnit[idx].posX == xpos && baramosuUnit[idx].posY == ypos) {
                        unitCNameStr = baramosuUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(baramosuUnit[idx].level);
                        unitHpStr = String.valueOf(baramosuUnit[idx].hp);
                        unitMpStr = String.valueOf(baramosuUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 9021) {
                for (idx = 0; idx<baramosuburosuUnit.length; idx++) {
                    if (baramosuburosuUnit[idx].posX == xpos && baramosuburosuUnit[idx].posY == ypos) {
                        unitCNameStr = baramosuburosuUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(baramosuburosuUnit[idx].level);
                        unitHpStr = String.valueOf(baramosuburosuUnit[idx].hp);
                        unitMpStr = String.valueOf(baramosuburosuUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 9022) {
                for (idx = 0; idx<baramosuebiruUnit.length; idx++) {
                    if (baramosuebiruUnit[idx].posX == xpos && baramosuebiruUnit[idx].posY == ypos) {
                        unitCNameStr = baramosuebiruUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(baramosuebiruUnit[idx].level);
                        unitHpStr = String.valueOf(baramosuebiruUnit[idx].hp);
                        unitMpStr = String.valueOf(baramosuebiruUnit[idx].mp);
                    }
                }
            } else if (onUnitNo == 9023) {
                for (idx = 0; idx<baramosuzonnbiUnit.length; idx++) {
                    if (baramosuzonnbiUnit[idx].posX == xpos && baramosuzonnbiUnit[idx].posY == ypos) {
                        unitCNameStr = baramosuzonnbiUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(baramosuzonnbiUnit[idx].level);
                        unitHpStr = String.valueOf(baramosuzonnbiUnit[idx].hp);
                        unitMpStr = String.valueOf(baramosuzonnbiUnit[idx].mp);
                    }
                }
            } else {
                for (idx = 0; idx<suraimuUnit.length; idx++) {
                    if (suraimuUnit[idx].posX == xpos && suraimuUnit[idx].posY == ypos) {
                        unitCNameStr = suraimuUnit[idx].instanseName;
                        unitLevelStr = String.valueOf(suraimuUnit[idx].level);
                        unitHpStr = String.valueOf(suraimuUnit[idx].hp);
                        unitMpStr = String.valueOf(suraimuUnit[idx].mp);
                    }
                }
            }

            //情報テキストビューの更新
            infStr = (unitCNameStr + "\nＬＶ：" + unitLevelStr + "\nＨＰ：" + unitHpStr + "\nＭＰ：" + unitMpStr);
            //infoTextView.setText(unitCNameStr + "\nＬＶ：" + unitLevelStr + "\nＨＰ：" + unitHpStr + "\nＭＰ：" + unitMpStr);
        }
        return infStr;
    }
*/

    void createTargetUnitInstance(int targetUnitNo) {
        int idx = 0;
        for (int y=0; y<mainBoard.board_sizeY; y++) {
            for (int x = 0; x < mainBoard.board_sizeX; x++) {
                int unitNo = mainBoard.unitPosInf[x][y];    //ユニットIDを取得
//                mainTile[x][y].onUnitID = unitNo;   //タイルクラスに値設定  ここは何度もコールされるので別の箇所で設定する
                BitmapDrawable bmpDrUnit = getUnitBmpDrFileName(unitNo);  //ユニット画像取得
                if (unitNo == targetUnitNo) {
                    Unit targetUnit = null;
                    if (targetUnitNo == 0) {
                        //ユニットがないので何も作成しない
                    } else if (targetUnitNo == 10) {
                        yushaUnit[idx] = new Unit_Yusha(unitNo,idx, x, y);
                        yushaUnit[idx].bmp = bmpDrUnit;
                        yushaUnit[idx].setUnitMoveRange(targetUnitNo);
                        yushaUnit[idx].setUnitBattleRange(targetUnitNo);
                        yushaUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = yushaUnit[idx];
                    } else if (targetUnitNo == 20) {
                        himeUnit[idx] = new Unit_Hime(unitNo,idx, x, y);
                        himeUnit[idx].bmp = bmpDrUnit;
                        himeUnit[idx].setUnitMoveRange(targetUnitNo);
                        himeUnit[idx].setUnitBattleRange(targetUnitNo);
                        himeUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = himeUnit[idx];
                    } else if (targetUnitNo == 30) {
                        senshiUnit[idx] = new Unit_Senshi(unitNo,idx, x, y);
                        senshiUnit[idx].bmp = bmpDrUnit;
                        senshiUnit[idx].setUnitMoveRange(targetUnitNo);
                        senshiUnit[idx].setUnitBattleRange(targetUnitNo);
                        senshiUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = senshiUnit[idx];
                    } else if (targetUnitNo == 40) {
                        mahotukaiUnit[idx] = new Unit_Mahotukai(unitNo,idx, x, y);
                        mahotukaiUnit[idx].bmp = bmpDrUnit;
                        mahotukaiUnit[idx].setUnitMoveRange(targetUnitNo);
                        mahotukaiUnit[idx].setUnitBattleRange(targetUnitNo);
                        mahotukaiUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = mahotukaiUnit[idx];
                    } else if (targetUnitNo == 50) {
                        souryoUnit[idx] = new Unit_Souryo(unitNo,idx, x, y);
                        souryoUnit[idx].bmp = bmpDrUnit;
                        souryoUnit[idx].setUnitMoveRange(targetUnitNo);
                        souryoUnit[idx].setUnitBattleRange(targetUnitNo);
                        souryoUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = souryoUnit[idx];
                    } else if (targetUnitNo == 60) {
                        torunekoUnit[idx] = new Unit_Toruneko(unitNo,idx, x, y);
                        torunekoUnit[idx].bmp = bmpDrUnit;
                        torunekoUnit[idx].setUnitMoveRange(targetUnitNo);
                        torunekoUnit[idx].setUnitBattleRange(targetUnitNo);
                        torunekoUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = torunekoUnit[idx];
                    } else if (targetUnitNo == 1000) {
                        suraimuUnit[idx] = new Unit_Suraimu(unitNo,idx, x, y);
                        suraimuUnit[idx].bmp = bmpDrUnit;
                        suraimuUnit[idx].setUnitMoveRange(targetUnitNo);
                        suraimuUnit[idx].setUnitBattleRange(targetUnitNo);
                        suraimuUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = suraimuUnit[idx];

                    } else if (targetUnitNo == 1010) {
                        metarusuraimuUnit[idx] = new Unit_Metarusuraimu(unitNo,idx, x, y);
                        metarusuraimuUnit[idx].bmp = bmpDrUnit;
                        metarusuraimuUnit[idx].setUnitMoveRange(targetUnitNo);
                        metarusuraimuUnit[idx].setUnitBattleRange(targetUnitNo);
                        metarusuraimuUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = metarusuraimuUnit[idx];
                    } else if (targetUnitNo == 1020) {
                        hoimisuraimuUnit[idx] = new Unit_Hoimisuraimu(unitNo,idx, x, y);
                        hoimisuraimuUnit[idx].bmp = bmpDrUnit;
                        hoimisuraimuUnit[idx].setUnitMoveRange(targetUnitNo);
                        hoimisuraimuUnit[idx].setUnitBattleRange(targetUnitNo);
                        hoimisuraimuUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = hoimisuraimuUnit[idx];
                    } else if (targetUnitNo == 1030) {
                        dorakiUnit[idx] = new Unit_Doraki(unitNo,idx, x, y);
                        dorakiUnit[idx].bmp = bmpDrUnit;
                        dorakiUnit[idx].setUnitMoveRange(targetUnitNo);
                        dorakiUnit[idx].setUnitBattleRange(targetUnitNo);
                        dorakiUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = dorakiUnit[idx];
                    } else if (targetUnitNo == 1040) {
                        baburusuraimuUnit[idx] = new Unit_Baburusuraimu(unitNo,idx, x, y);
                        baburusuraimuUnit[idx].bmp = bmpDrUnit;
                        baburusuraimuUnit[idx].setUnitMoveRange(targetUnitNo);
                        baburusuraimuUnit[idx].setUnitBattleRange(targetUnitNo);
                        baburusuraimuUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = baburusuraimuUnit[idx];
                    } else if (targetUnitNo == 1050) {
                        haguremetaruUnit[idx] = new Unit_Haguremetaru(unitNo,idx, x, y);
                        haguremetaruUnit[idx].bmp = bmpDrUnit;
                        haguremetaruUnit[idx].setUnitMoveRange(targetUnitNo);
                        haguremetaruUnit[idx].setUnitBattleRange(targetUnitNo);
                        haguremetaruUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = haguremetaruUnit[idx];
                    } else if (targetUnitNo == 1060) {
                        kimeraUnit[idx] = new Unit_Kimera(unitNo,idx, x, y);
                        kimeraUnit[idx].bmp = bmpDrUnit;
                        kimeraUnit[idx].setUnitMoveRange(targetUnitNo);
                        kimeraUnit[idx].setUnitBattleRange(targetUnitNo);
                        kimeraUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = kimeraUnit[idx];
                    } else if (targetUnitNo == 1070) {
                        oogarasuUnit[idx] = new Unit_Oogarasu(unitNo,idx, x, y);
                        oogarasuUnit[idx].bmp = bmpDrUnit;
                        oogarasuUnit[idx].setUnitMoveRange(targetUnitNo);
                        oogarasuUnit[idx].setUnitBattleRange(targetUnitNo);
                        oogarasuUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = oogarasuUnit[idx];
                    } else if (targetUnitNo == 1080) {
                        hitokuibakoUnit[idx] = new Unit_Hitokuibako(unitNo,idx, x, y);
                        hitokuibakoUnit[idx].bmp = bmpDrUnit;
                        hitokuibakoUnit[idx].setUnitMoveRange(targetUnitNo);
                        hitokuibakoUnit[idx].setUnitBattleRange(targetUnitNo);
                        hitokuibakoUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = hitokuibakoUnit[idx];
                    } else if (targetUnitNo == 1090) {
                        kusattashitaiUnit[idx] = new Unit_Kusattashitai(unitNo,idx, x, y);
                        kusattashitaiUnit[idx].bmp = bmpDrUnit;
                        kusattashitaiUnit[idx].setUnitMoveRange(targetUnitNo);
                        kusattashitaiUnit[idx].setUnitBattleRange(targetUnitNo);
                        kusattashitaiUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = kusattashitaiUnit[idx];
                    } else if (targetUnitNo == 1100) {
                        samayouyoroiUnit[idx] = new Unit_Samayouyoroi(unitNo,idx, x, y);
                        samayouyoroiUnit[idx].bmp = bmpDrUnit;
                        samayouyoroiUnit[idx].setUnitMoveRange(targetUnitNo);
                        samayouyoroiUnit[idx].setUnitBattleRange(targetUnitNo);
                        samayouyoroiUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = samayouyoroiUnit[idx];
                    } else if (targetUnitNo == 1110) {
                        akumakishiUnit[idx] = new Unit_Akumakishi(unitNo,idx, x, y);
                        akumakishiUnit[idx].bmp = bmpDrUnit;
                        akumakishiUnit[idx].setUnitMoveRange(targetUnitNo);
                        akumakishiUnit[idx].setUnitBattleRange(targetUnitNo);
                        akumakishiUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = akumakishiUnit[idx];
                    } else if (targetUnitNo == 1120) {
                        bebisatannUnit[idx] = new Unit_Bebisatann(unitNo,idx, x, y);
                        bebisatannUnit[idx].bmp = bmpDrUnit;
                        bebisatannUnit[idx].setUnitMoveRange(targetUnitNo);
                        bebisatannUnit[idx].setUnitBattleRange(targetUnitNo);
                        bebisatannUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = bebisatannUnit[idx];
                    } else if (targetUnitNo == 1130) {
                        tororuUnit[idx] = new Unit_Tororu(unitNo,idx, x, y);
                        tororuUnit[idx].bmp = bmpDrUnit;
                        tororuUnit[idx].setUnitMoveRange(targetUnitNo);
                        tororuUnit[idx].setUnitBattleRange(targetUnitNo);
                        tororuUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = tororuUnit[idx];
                    } else if (targetUnitNo == 1140) {
                        goremuUnit[idx] = new Unit_Goremu(unitNo,idx, x, y);
                        goremuUnit[idx].bmp = bmpDrUnit;
                        goremuUnit[idx].setUnitMoveRange(targetUnitNo);
                        goremuUnit[idx].setUnitBattleRange(targetUnitNo);
                        goremuUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = goremuUnit[idx];
                    } else if (targetUnitNo == 1150) {
                        shiryouonokishiUnit[idx] = new Unit_Shiryouonokishi(unitNo,idx, x, y);
                        shiryouonokishiUnit[idx].bmp = bmpDrUnit;
                        shiryouonokishiUnit[idx].setUnitMoveRange(targetUnitNo);
                        shiryouonokishiUnit[idx].setUnitBattleRange(targetUnitNo);
                        shiryouonokishiUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = shiryouonokishiUnit[idx];
                    } else if (targetUnitNo == 1160) {
                        doragonUnit[idx] = new Unit_Doragon(unitNo,idx, x, y);
                        doragonUnit[idx].bmp = bmpDrUnit;
                        doragonUnit[idx].setUnitMoveRange(targetUnitNo);
                        doragonUnit[idx].setUnitBattleRange(targetUnitNo);
                        doragonUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = doragonUnit[idx];

                    } else if (targetUnitNo == 9000) {
                        ryuuouUnit[idx] = new Unit_Ryuuou(unitNo,idx, x, y);
                        ryuuouUnit[idx].bmp = bmpDrUnit;
                        ryuuouUnit[idx].setUnitMoveRange(targetUnitNo);
                        ryuuouUnit[idx].setUnitBattleRange(targetUnitNo);
                        ryuuouUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = ryuuouUnit[idx];
                    } else if (targetUnitNo == 9001) {
                        ryuuouhennshinnUnit[idx] = new Unit_Ryuuouhennshinn(unitNo,idx, x, y);
                        ryuuouhennshinnUnit[idx].bmp = bmpDrUnit;
                        ryuuouhennshinnUnit[idx].setUnitMoveRange(targetUnitNo);
                        ryuuouhennshinnUnit[idx].setUnitBattleRange(targetUnitNo);
                        ryuuouhennshinnUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = ryuuouhennshinnUnit[idx];
                    } else if (targetUnitNo == 9010) {
                        zomaUnit[idx] = new Unit_Zoma(unitNo,idx, x, y);
                        zomaUnit[idx].bmp = bmpDrUnit;
                        zomaUnit[idx].setUnitMoveRange(targetUnitNo);
                        zomaUnit[idx].setUnitBattleRange(targetUnitNo);
                        zomaUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = zomaUnit[idx];
                    } else if (targetUnitNo == 9011) {
                        zomahennshinnUnit[idx] = new Unit_Zomahennshinn(unitNo,idx, x, y);
                        zomahennshinnUnit[idx].bmp = bmpDrUnit;
                        zomahennshinnUnit[idx].setUnitMoveRange(targetUnitNo);
                        zomahennshinnUnit[idx].setUnitBattleRange(targetUnitNo);
                        zomahennshinnUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = zomahennshinnUnit[idx];
                    } else if (targetUnitNo == 9020) {
                        baramosuUnit[idx] = new Unit_Baramosu(unitNo,idx, x, y);
                        baramosuUnit[idx].bmp = bmpDrUnit;
                        baramosuUnit[idx].setUnitMoveRange(targetUnitNo);
                        baramosuUnit[idx].setUnitBattleRange(targetUnitNo);
                        baramosuUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = baramosuUnit[idx];
                    } else if (targetUnitNo == 9021) {
                        baramosuburosuUnit[idx] = new Unit_Baramosuburosu(unitNo,idx, x, y);
                        baramosuburosuUnit[idx].bmp = bmpDrUnit;
                        baramosuburosuUnit[idx].setUnitMoveRange(targetUnitNo);
                        baramosuburosuUnit[idx].setUnitBattleRange(targetUnitNo);
                        baramosuburosuUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = baramosuburosuUnit[idx];
                    } else if (targetUnitNo == 9022) {
                        baramosuebiruUnit[idx] = new Unit_Baramosuebiru(unitNo,idx, x, y);
                        baramosuebiruUnit[idx].bmp = bmpDrUnit;
                        baramosuebiruUnit[idx].setUnitMoveRange(targetUnitNo);
                        baramosuebiruUnit[idx].setUnitBattleRange(targetUnitNo);
                        baramosuebiruUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = baramosuebiruUnit[idx];
                    } else if (targetUnitNo == 9023) {
                        baramosuzonnbiUnit[idx] = new Unit_Baramosuzonnbi(unitNo,idx, x, y);
                        baramosuzonnbiUnit[idx].bmp = bmpDrUnit;
                        baramosuzonnbiUnit[idx].setUnitMoveRange(targetUnitNo);
                        baramosuzonnbiUnit[idx].setUnitBattleRange(targetUnitNo);
                        baramosuzonnbiUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = baramosuzonnbiUnit[idx];
                    } else {
                        suraimuUnit[idx] = new Unit_Suraimu(unitNo,idx, x, y);
                        suraimuUnit[idx].bmp = bmpDrUnit;
                        suraimuUnit[idx].setUnitMoveRange(targetUnitNo);
                        suraimuUnit[idx].setUnitBattleRange(targetUnitNo);
                        suraimuUnit[idx].setUnitParameter(targetUnitNo);
                        targetUnit = suraimuUnit[idx];
                    }
                    ++idx;
                    //盤クラスのArrayListにインスタンスを追加
                    if (targetUnitNo != 0) {
                        mainBoard.allUnitOnBoard.add(targetUnit);
                    }
                }
            }
        }
    }

    void setTargetUnitInstanceNoOnTile(int posX, int posY) {

        int onUnitID =  mainTile[posX][posY].onUnitID;
//        int onUnitInstanceID = mainTile[posX][posY].onUnitInstanceID;
        int idx = 0;
        int intBuf = 0;
        Unit targetUnit = null;

        if (onUnitID == 0) {
            intBuf = 0;
        } else if (onUnitID == 10) {
            for (idx = 0; idx < yushaUnit.length; idx++) {
                if ((yushaUnit[idx].posX == posX) && (yushaUnit[idx].posY == posY)) {
                    intBuf = yushaUnit[idx].unitInstanceID;
                    targetUnit = yushaUnit[idx];
                }
            }
        } else if (onUnitID == 20) {
            for (idx = 0; idx < himeUnit.length; idx++) {
                if ((himeUnit[idx].posX == posX) && (himeUnit[idx].posY == posY)) {
                    intBuf = himeUnit[idx].unitInstanceID;
                    targetUnit = himeUnit[idx];
                }
            }
        } else if (onUnitID == 30) {
            for (idx = 0; idx < senshiUnit.length; idx++) {
                if ((senshiUnit[idx].posX == posX) && (senshiUnit[idx].posY == posY)) {
                    intBuf = senshiUnit[idx].unitInstanceID;
                    targetUnit = senshiUnit[idx];
                }
            }
        } else if (onUnitID == 40) {
            for (idx = 0; idx < mahotukaiUnit.length; idx++) {
                if ((mahotukaiUnit[idx].posX == posX) && (mahotukaiUnit[idx].posY == posY)) {
                    intBuf = mahotukaiUnit[idx].unitInstanceID;
                    targetUnit = mahotukaiUnit[idx];
                }
            }
        } else if (onUnitID == 50) {
            for (idx = 0; idx < souryoUnit.length; idx++) {
                if ((souryoUnit[idx].posX == posX) && (souryoUnit[idx].posY == posY)) {
                    intBuf = souryoUnit[idx].unitInstanceID;
                    targetUnit = souryoUnit[idx];
                }
            }
        } else if (onUnitID == 60) {
            for (idx = 0; idx < torunekoUnit.length; idx++) {
                if ((torunekoUnit[idx].posX == posX) && (torunekoUnit[idx].posY == posY)) {
                    intBuf = torunekoUnit[idx].unitInstanceID;
                    targetUnit = torunekoUnit[idx];
                }
            }
        } else if (onUnitID == 1000) {
            for (idx = 0; idx < suraimuUnit.length; idx++) {
                if ((suraimuUnit[idx].posX == posX) && (suraimuUnit[idx].posY == posY)) {
                    intBuf = suraimuUnit[idx].unitInstanceID;
                    targetUnit = suraimuUnit[idx];
                }
            }

        } else if (onUnitID == 1010) {
            for (idx = 0; idx < metarusuraimuUnit.length; idx++) {
                if ((metarusuraimuUnit[idx].posX == posX) && (metarusuraimuUnit[idx].posY == posY)) {
                    intBuf = metarusuraimuUnit[idx].unitInstanceID;
                    targetUnit = metarusuraimuUnit[idx];
                }
            }
        } else if (onUnitID == 1020) {
            for (idx = 0; idx < hoimisuraimuUnit.length; idx++) {
                if ((hoimisuraimuUnit[idx].posX == posX) && (hoimisuraimuUnit[idx].posY == posY)) {
                    intBuf = hoimisuraimuUnit[idx].unitInstanceID;
                    targetUnit = hoimisuraimuUnit[idx];
                }
            }
        } else if (onUnitID == 1030) {
            for (idx = 0; idx < dorakiUnit.length; idx++) {
                if ((dorakiUnit[idx].posX == posX) && (dorakiUnit[idx].posY == posY)) {
                    intBuf = dorakiUnit[idx].unitInstanceID;
                    targetUnit = dorakiUnit[idx];
                }
            }
        } else if (onUnitID == 1040) {
            for (idx = 0; idx < baburusuraimuUnit.length; idx++) {
                if ((baburusuraimuUnit[idx].posX == posX) && (baburusuraimuUnit[idx].posY == posY)) {
                    intBuf = baburusuraimuUnit[idx].unitInstanceID;
                    targetUnit = baburusuraimuUnit[idx];
                }
            }
        } else if (onUnitID == 1050) {
            for (idx = 0; idx < haguremetaruUnit.length; idx++) {
                if ((haguremetaruUnit[idx].posX == posX) && (haguremetaruUnit[idx].posY == posY)) {
                    intBuf = haguremetaruUnit[idx].unitInstanceID;
                    targetUnit = haguremetaruUnit[idx];
                }
            }
        } else if (onUnitID == 1060) {
            for (idx = 0; idx < kimeraUnit.length; idx++) {
                if ((kimeraUnit[idx].posX == posX) && (kimeraUnit[idx].posY == posY)) {
                    intBuf = kimeraUnit[idx].unitInstanceID;
                    targetUnit = kimeraUnit[idx];
                }
            }
        } else if (onUnitID == 1070) {
            for (idx = 0; idx < oogarasuUnit.length; idx++) {
                if ((oogarasuUnit[idx].posX == posX) && (oogarasuUnit[idx].posY == posY)) {
                    intBuf = oogarasuUnit[idx].unitInstanceID;
                    targetUnit = oogarasuUnit[idx];
                }
            }
        } else if (onUnitID == 1080) {
            for (idx = 0; idx < hitokuibakoUnit.length; idx++) {
                if ((hitokuibakoUnit[idx].posX == posX) && (hitokuibakoUnit[idx].posY == posY)) {
                    intBuf = hitokuibakoUnit[idx].unitInstanceID;
                    targetUnit = hitokuibakoUnit[idx];
                }
            }
        } else if (onUnitID == 1090) {
            for (idx = 0; idx < kusattashitaiUnit.length; idx++) {
                if ((kusattashitaiUnit[idx].posX == posX) && (kusattashitaiUnit[idx].posY == posY)) {
                    intBuf = kusattashitaiUnit[idx].unitInstanceID;
                    targetUnit = kusattashitaiUnit[idx];
                }
            }
        } else if (onUnitID == 1100) {
            for (idx = 0; idx < samayouyoroiUnit.length; idx++) {
                if ((samayouyoroiUnit[idx].posX == posX) && (samayouyoroiUnit[idx].posY == posY)) {
                    intBuf = samayouyoroiUnit[idx].unitInstanceID;
                    targetUnit = samayouyoroiUnit[idx];
                }
            }
        } else if (onUnitID == 1110) {
            for (idx = 0; idx < akumakishiUnit.length; idx++) {
                if ((akumakishiUnit[idx].posX == posX) && (akumakishiUnit[idx].posY == posY)) {
                    intBuf = akumakishiUnit[idx].unitInstanceID;
                    targetUnit = akumakishiUnit[idx];
                }
            }
        } else if (onUnitID == 1120) {
            for (idx = 0; idx < bebisatannUnit.length; idx++) {
                if ((bebisatannUnit[idx].posX == posX) && (bebisatannUnit[idx].posY == posY)) {
                    intBuf = bebisatannUnit[idx].unitInstanceID;
                    targetUnit = bebisatannUnit[idx];
                }
            }
        } else if (onUnitID == 1130) {
            for (idx = 0; idx < tororuUnit.length; idx++) {
                if ((tororuUnit[idx].posX == posX) && (tororuUnit[idx].posY == posY)) {
                    intBuf = tororuUnit[idx].unitInstanceID;
                    targetUnit = tororuUnit[idx];
                }
            }
        } else if (onUnitID == 1140) {
            for (idx = 0; idx < goremuUnit.length; idx++) {
                if ((goremuUnit[idx].posX == posX) && (goremuUnit[idx].posY == posY)) {
                    intBuf = goremuUnit[idx].unitInstanceID;
                    targetUnit = goremuUnit[idx];
                }
            }
        } else if (onUnitID == 1150) {
            for (idx = 0; idx < shiryouonokishiUnit.length; idx++) {
                if ((shiryouonokishiUnit[idx].posX == posX) && (shiryouonokishiUnit[idx].posY == posY)) {
                    intBuf = shiryouonokishiUnit[idx].unitInstanceID;
                    targetUnit = shiryouonokishiUnit[idx];
                }
            }
        } else if (onUnitID == 1160) {
            for (idx = 0; idx < doragonUnit.length; idx++) {
                if ((doragonUnit[idx].posX == posX) && (doragonUnit[idx].posY == posY)) {
                    intBuf = doragonUnit[idx].unitInstanceID;
                    targetUnit = doragonUnit[idx];
                }
            }

        } else if (onUnitID == 9000) {
            for (idx = 0; idx < ryuuouUnit.length; idx++) {
                if ((ryuuouUnit[idx].posX == posX) && (ryuuouUnit[idx].posY == posY)) {
                    intBuf = ryuuouUnit[idx].unitInstanceID;
                    targetUnit = ryuuouUnit[idx];
                }
            }
        } else if (onUnitID == 9001) {
            for (idx = 0; idx < ryuuouhennshinnUnit.length; idx++) {
                if ((ryuuouhennshinnUnit[idx].posX == posX) && (ryuuouhennshinnUnit[idx].posY == posY)) {
                    intBuf = ryuuouhennshinnUnit[idx].unitInstanceID;
                    targetUnit = ryuuouhennshinnUnit[idx];
                }
            }
        } else if (onUnitID == 9010) {
            for (idx = 0; idx < zomaUnit.length; idx++) {
                if ((zomaUnit[idx].posX == posX) && (zomaUnit[idx].posY == posY)) {
                    intBuf = zomaUnit[idx].unitInstanceID;
                    targetUnit = zomaUnit[idx];
                }
            }
        } else if (onUnitID == 9011) {
            for (idx = 0; idx < zomahennshinnUnit.length; idx++) {
                if ((zomahennshinnUnit[idx].posX == posX) && (zomahennshinnUnit[idx].posY == posY)) {
                    intBuf = zomahennshinnUnit[idx].unitInstanceID;
                    targetUnit = zomahennshinnUnit[idx];
                }
            }
        } else if (onUnitID == 9020) {
            for (idx = 0; idx < baramosuUnit.length; idx++) {
                if ((baramosuUnit[idx].posX == posX) && (baramosuUnit[idx].posY == posY)) {
                    intBuf = baramosuUnit[idx].unitInstanceID;
                    targetUnit = baramosuUnit[idx];
                }
            }
        } else if (onUnitID == 9021) {
            for (idx = 0; idx < baramosuburosuUnit.length; idx++) {
                if ((baramosuburosuUnit[idx].posX == posX) && (baramosuburosuUnit[idx].posY == posY)) {
                    intBuf = baramosuburosuUnit[idx].unitInstanceID;
                    targetUnit = baramosuburosuUnit[idx];
                }
            }
        } else if (onUnitID == 9022) {
            for (idx = 0; idx < baramosuebiruUnit.length; idx++) {
                if ((baramosuebiruUnit[idx].posX == posX) && (baramosuebiruUnit[idx].posY == posY)) {
                    intBuf = baramosuebiruUnit[idx].unitInstanceID;
                    targetUnit = baramosuebiruUnit[idx];
                }
            }
        } else if (onUnitID == 9023) {
            for (idx = 0; idx < baramosuzonnbiUnit.length; idx++) {
                if ((baramosuzonnbiUnit[idx].posX == posX) && (baramosuzonnbiUnit[idx].posY == posY)) {
                    intBuf = baramosuzonnbiUnit[idx].unitInstanceID;
                    targetUnit = baramosuzonnbiUnit[idx];
                }
            }
        } else {
//            for (idx = 0; idx < suraimuUnit.length; idx++) {
//                if ((suraimuUnit[idx].posX == posX) && (suraimuUnit[idx].posY == posY)) {
//                    intBuf = suraimuUnit[idx].unitInstanceID;
//                    targetUnit = suraimuUnit[idx];
//                }
//            }
        }
        //タイルクラスのインスタンスIDに値をセットする
        mainTile[posX][posY].onUnitInstanceID = intBuf;

        //タイルクラスの乗っているユニットのArrayListのインデックスNoに値をセットする
        if (onUnitID != 0) {
            int cnt;
            for (cnt = 0; cnt <mainBoard.allUnitOnBoard.size(); cnt++) {
                Unit UnitBuf = mainBoard.allUnitOnBoard.get(cnt);
                if (UnitBuf == targetUnit) {
                    mainTile[posX][posY].onUnitArrayListIdx = cnt;
                    break;
                }
            }
        }

    }

    void setCommandTextViewStatus(int modeNo) {
        //モードに応じてコマンドテキストビューの状態を設定する。
        if (mainBoard.modeFlag == 0) {  //ユニット未選択
            modeTextView.setText("未選択");
            messageTextView.setText("ユニットをせんたくしてください。");
            cmdMoveTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdMoveTextView.setEnabled(false);
            cmdBattleTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdBattleTextView.setEnabled(false);
            cmdShowStatusTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdShowStatusTextView.setEnabled(false);
            cmdFinishTextView.setTextColor(Color.rgb(255, 255, 255));
            cmdFinishTextView.setEnabled(true);

        } else if (mainBoard.modeFlag == 11) {   //ユニット選択中 移動前かつ戦闘前
            modeTextView.setText("移動：未 戦闘：未");
            messageTextView.setText("ユニットをせんたくちゅうです。");
            cmdMoveTextView.setTextColor(Color.rgb(255, 255, 255));
            cmdMoveTextView.setEnabled(true);
            cmdBattleTextView.setTextColor(Color.rgb(255, 255, 255));
            cmdBattleTextView.setEnabled(true);
            cmdShowStatusTextView.setTextColor(Color.rgb(255, 255, 255));
            cmdShowStatusTextView.setEnabled(true);
            cmdFinishTextView.setTextColor(Color.rgb(255, 255, 255));
            cmdFinishTextView.setEnabled(true);

        } else if (mainBoard.modeFlag == 12) {   //ユニット選択中 移動前かつ戦闘後
            modeTextView.setText("移動：未 戦闘：済");
            messageTextView.setText("ユニットをせんたくちゅうです。");
            cmdMoveTextView.setTextColor(Color.rgb(255, 255, 255));
            cmdMoveTextView.setEnabled(true);
            cmdBattleTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdBattleTextView.setEnabled(false);
            cmdShowStatusTextView.setTextColor(Color.rgb(255, 255, 255));
            cmdShowStatusTextView.setEnabled(true);
            cmdFinishTextView.setTextColor(Color.rgb(255, 255, 255));
            cmdFinishTextView.setEnabled(true);

        } else if (mainBoard.modeFlag == 13) {   //ユニット選択中 移動後かつ戦闘前
            modeTextView.setText("移動：済 戦闘：未");
            messageTextView.setText("ユニットをせんたくちゅうです。");
            cmdMoveTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdMoveTextView.setEnabled(false);
            cmdBattleTextView.setTextColor(Color.rgb(255, 255, 255));
            cmdBattleTextView.setEnabled(true);
            cmdShowStatusTextView.setTextColor(Color.rgb(255, 255, 255));
            cmdShowStatusTextView.setEnabled(true);
            cmdFinishTextView.setTextColor(Color.rgb(255, 255, 255));
            cmdFinishTextView.setEnabled(true);

        } else if (mainBoard.modeFlag == 21) {   //移動前（移動先表示）
            modeTextView.setText("移動中");
            messageTextView.setText("いどうさきをけっていしてください。");
            cmdMoveTextView.setTextColor(Color.rgb(255, 255, 255));
            cmdMoveTextView.setEnabled(true);
            cmdBattleTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdBattleTextView.setEnabled(false);
            cmdShowStatusTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdShowStatusTextView.setEnabled(false);
            cmdFinishTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdFinishTextView.setEnabled(false);

//        } else if (mainBoard.modeFlag == 22) {   //移動後（移動確定）不要か？
//            modeTextView.setText("モード：移動完了");
//            messageTextView.setText("たたかうあいてをけっていしてください。");
//            cmdMoveTextView.setTextColor(Color.rgb(64, 64, 64));
//            cmdMoveTextView.setEnabled(false);
//            cmdBattleTextView.setTextColor(Color.rgb(255, 255, 255));
//            cmdBattleTextView.setEnabled(true);
//            cmdShowStatusTextView.setTextColor(Color.rgb(255, 255, 255));
//            cmdShowStatusTextView.setEnabled(true);
        } else if (mainBoard.modeFlag == 31) {   //戦闘前（戦闘対象表示）
            messageTextView.setText("せんとうさきをけっていしてください。");
            cmdMoveTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdMoveTextView.setEnabled(false);
            cmdBattleTextView.setTextColor(Color.rgb(255, 255, 255));
            cmdBattleTextView.setEnabled(true);
            cmdShowStatusTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdShowStatusTextView.setEnabled(false);
            cmdFinishTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdFinishTextView.setEnabled(false);

//        } else if (mainBoard.modeFlag == 32) {   //戦闘 不要か？
//            modeTextView.setText("モード：戦闘");

        } else if (mainBoard.modeFlag == 4) {   //ステータス表示
            modeTextView.setText("ステータス表示");
            messageTextView.setText("ステータスをひょうじします。");

            cmdFinishTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdFinishTextView.setEnabled(false);
        } else if (mainBoard.modeFlag == 5) {   //終了
            modeTextView.setText("終了");
            messageTextView.setText("みかたフェーズはしゅうりょうしました。");
            cmdMoveTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdMoveTextView.setEnabled(false);
            cmdBattleTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdBattleTextView.setEnabled(false);
            cmdShowStatusTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdShowStatusTextView.setEnabled(false);
            cmdFinishTextView.setTextColor(Color.rgb(64, 64, 64));
            cmdFinishTextView.setEnabled(false);
        }

    }

    Unit getTargetUnitInstance(int unitNo, int instanceNo) {
        Unit unitObject;
        if (unitNo == 10) {   //ゆうしゃ
            unitObject = yushaUnit[instanceNo];
        } else if (unitNo == 20) {
            unitObject = himeUnit[instanceNo];
        } else if (unitNo == 30) {
            unitObject = senshiUnit[instanceNo];
        } else if (unitNo == 40) {
            unitObject = mahotukaiUnit[instanceNo];
        } else if (unitNo == 50) {
            unitObject = souryoUnit[instanceNo];
        } else if (unitNo == 60) {
            unitObject = torunekoUnit[instanceNo];
        } else if (unitNo == 1000) {
            unitObject = suraimuUnit[instanceNo];
        } else if (unitNo == 1010) {
            unitObject = metarusuraimuUnit[instanceNo];
        } else if (unitNo == 1020) {
            unitObject = hoimisuraimuUnit[instanceNo];
        } else if (unitNo == 1030) {
            unitObject = dorakiUnit[instanceNo];
        } else if (unitNo == 1040) {
            unitObject = baburusuraimuUnit[instanceNo];
        } else if (unitNo == 1050) {
            unitObject = haguremetaruUnit[instanceNo];
        } else if (unitNo == 1060) {
            unitObject = kimeraUnit[instanceNo];
        } else if (unitNo == 1070) {
            unitObject = oogarasuUnit[instanceNo];
        } else if (unitNo == 1080) {
            unitObject = hitokuibakoUnit[instanceNo];
        } else if (unitNo == 1090) {
            unitObject = kusattashitaiUnit[instanceNo];
        } else if (unitNo == 1100) {
            unitObject = samayouyoroiUnit[instanceNo];
        } else if (unitNo == 1110) {
            unitObject = akumakishiUnit[instanceNo];
        } else if (unitNo == 1120) {
            unitObject = bebisatannUnit[instanceNo];
        } else if (unitNo == 1130) {
            unitObject = tororuUnit[instanceNo];
        } else if (unitNo == 1140) {
            unitObject = goremuUnit[instanceNo];
        } else if (unitNo == 1150) {
            unitObject = shiryouonokishiUnit[instanceNo];
        } else if (unitNo == 1160) {
            unitObject = doragonUnit[instanceNo];
        } else if (unitNo == 9000) {
            unitObject = ryuuouUnit[instanceNo];
        } else if (unitNo == 9001) {
            unitObject = ryuuouhennshinnUnit[instanceNo];
        } else if (unitNo == 9010) {
            unitObject = zomaUnit[instanceNo];
        } else if (unitNo == 9011) {
            unitObject = zomahennshinnUnit[instanceNo];
        } else if (unitNo == 9020) {
            unitObject = baramosuUnit[instanceNo];
        } else if (unitNo == 9021) {
            unitObject = baramosuburosuUnit[instanceNo];
        } else if (unitNo == 9022) {
            unitObject = baramosuebiruUnit[instanceNo];
        } else if (unitNo == 9023) {
            unitObject = baramosuzonnbiUnit[instanceNo];
        } else {
            unitObject = null;
        }

        return unitObject;
    }

    void clearPrevCursorBmp(int boardSelectedPosX, int boardSelectedPosY) {
//    void clearPrevCursorBmp() {
        //前回グラフィックイメージビューの更新
        //選択カーソルが表示されているので、
        // カーソルがないイメージに戻す
        //タイル
        BitmapDrawable bmpDrTile = mainTile[boardSelectedPosX][boardSelectedPosY].bmp;    //タイル画像取得
        //ユニット
        //クリック位置の乗っているユニットのArrayListのインデックスNoの特定
        int tilePrevOnUnitArrayIdx = mainTile[boardSelectedPosX][boardSelectedPosY].onUnitArrayListIdx;
        Unit targetPrevUnit = null;
        BitmapDrawable bmpDrUnit;
        if (tilePrevOnUnitArrayIdx != -1) {
            //盤クラスのArrayListからインスタンスを取得
            targetPrevUnit = mainBoard.allUnitOnBoard.get(tilePrevOnUnitArrayIdx);
            bmpDrUnit = targetPrevUnit.bmp;  //ユニット画像取得
        } else {
            bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);  //ユニット画像取得
        }

        /* old
        //クリック位置のタイルクラスIDの特定
        int tilePrevOnUnitNo = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitID;
        //クリック位置のユニットインスタンスIDの特定
        int tilePrevOnUnitInstanceNo = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitInstanceID;
        Unit unitPrevObject = null;
        BitmapDrawable bmpDrUnit;
        if (tilePrevOnUnitNo != 0){ //ユニットを選択している状態
            unitPrevObject = getTargetUnitInstance(tilePrevOnUnitNo, tilePrevOnUnitInstanceNo);
            bmpDrUnit = unitPrevObject.bmp;  //ユニット画像取得
//            int unitNo = mainBoard.unitPosInf[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY];    //ユニットIDを取得
//            BitmapDrawable bmpDrUnit = getUnitBmpDrFileName(unitNo);  //ユニット画像取得
        } else {
            bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);  //ユニット画像取得
        }
        */

        //イメージビューにタイルとユニットの画像を重ねる
        Drawable[] drawables = {
                bmpDrTile,
                bmpDrUnit
        };
        LayerDrawable ld = new LayerDrawable(drawables);
        mainTile[boardSelectedPosX][boardSelectedPosY].imgView.setImageDrawable(ld);
    }

    void clearPrevMoveRangeBmp() {
        //前回選択したユニットの移動可能範囲の画像をクリア
        //クリック位置の乗っているユニットのArrayListのインデックスNoの特定
        int tilePrevOnUnitArrayIdx = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitArrayListIdx;
        Unit targetPrevUnit = null;
        if (tilePrevOnUnitArrayIdx != -1) { //ユニットが乗っている場合
            //盤クラスのArrayListからインスタンスを取得
            targetPrevUnit = mainBoard.allUnitOnBoard.get(tilePrevOnUnitArrayIdx);
            for (int idx = 0; idx <targetPrevUnit.moveRange.length; idx++) {
                //移動可能範囲を算出し、イメージビューに画像をのせる。
                int dPosX = targetPrevUnit.moveRange[idx][0];
                int dPosY = targetPrevUnit.moveRange[idx][1];
                int nowPosX = targetPrevUnit.posX + dPosX;
                if (nowPosX < 0 || nowPosX > mainBoard.board_sizeX -1) {   //移動範囲Xチェック
                    continue;
                }
                int nowPosY = targetPrevUnit.posY + dPosY;
                if (nowPosY < 0 || nowPosY > mainBoard.board_sizeY -1) {  //移動範囲Yチェック
                    continue;
                }

                //タイル
                BitmapDrawable bmpDrTile = mainTile[nowPosX][nowPosY].bmp;    //タイル画像取得
                //ユニット
                int tileOnUnitArrayIdx = mainTile[nowPosX][nowPosY].onUnitArrayListIdx;
                Unit targetUnit = null;
                BitmapDrawable bmpDrUnit;
                if (tileOnUnitArrayIdx != -1){ //ユニットを選択している状態
                    targetUnit = mainBoard.allUnitOnBoard.get(tileOnUnitArrayIdx);
                    bmpDrUnit = targetUnit.bmp;
                } else {
                    bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);
                }
                //イメージビューにタイルとユニットの画像を重ねる
                Drawable[] clearDrawables = {
                        bmpDrTile,
                        bmpDrUnit,
                };
                LayerDrawable ld = new LayerDrawable(clearDrawables);
                mainTile[nowPosX][nowPosY].imgView.setImageDrawable(ld);
            }
        } else {
            //ユニットが乗っていない状態
        }

        /*
        //クリック位置のタイルクラスIDの特定
        int tilePrevOnUnitNo = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitID;
        //クリック位置のユニットインスタンスIDの特定
        int tilePrevOnUnitInstanceNo = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitInstanceID;
        Unit unitPrevObject = null;
        BitmapDrawable bmpDrUnit;
        if (tilePrevOnUnitNo != 0){ //ユニットを選択している状態
            unitPrevObject = getTargetUnitInstance(tilePrevOnUnitNo, tilePrevOnUnitInstanceNo);
        }
        if (unitPrevObject != null) {
            for (int idx = 0; idx <unitPrevObject.moveRange.length; idx++) {
                //移動可能範囲を算出し、イメージビューに画像をのせる。
                int dPosX = unitPrevObject.moveRange[idx][0];
                int dPosY = unitPrevObject.moveRange[idx][1];
                int nowPosX = unitPrevObject.posX + dPosX;
                if (nowPosX < 0 || nowPosX > mainBoard.board_sizeX -1) {   //移動範囲Xチェック
                    continue;
                }
                int nowPosY = unitPrevObject.posY + dPosY;
                if (nowPosY < 0 || nowPosY > mainBoard.board_sizeY -1) {  //移動範囲Yチェック
                    continue;
                }

                //タイル
                BitmapDrawable bmpDrTile = mainTile[nowPosX][nowPosY].bmp;    //タイル画像取得
                //ユニット
               int tileOnUnitNo = mainTile[nowPosX][nowPosY].onUnitID;
                //クリック位置のユニットインスタンスIDの特定
               int tileOnUnitInstanceNo = mainTile[nowPosX][nowPosY].onUnitInstanceID;
                Unit unitObject = null;
                if (tileOnUnitNo != 0){ //ユニットを選択している状態
                    unitObject = getTargetUnitInstance(tileOnUnitNo, tileOnUnitInstanceNo);
                    bmpDrUnit = unitObject.bmp;
                } else {
                    bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);
                }
                //イメージビューにタイルとユニットの画像を重ねる
                Drawable[] clearDrawables = {
                        bmpDrTile,
                        bmpDrUnit,
                };
                LayerDrawable ld = new LayerDrawable(clearDrawables);
                mainTile[nowPosX][nowPosY].imgView.setImageDrawable(ld);
            }
        } else {

        }
        */

    }

    void clearPrevBattleRangeBmp() {
        //前回選択したユニットの移動可能範囲の画像をクリア
        int tilePrevOnUnitArrayIdx = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitArrayListIdx;
        Unit targetPrevUnit = null;
        if (tilePrevOnUnitArrayIdx != -1) { //ユニットが乗っている場合
            //盤クラスのArrayListからインスタンスを取得
            targetPrevUnit = mainBoard.allUnitOnBoard.get(tilePrevOnUnitArrayIdx);

            for (int idx = 0; idx <targetPrevUnit.battleRange.length; idx++) {
                //移動可能範囲を算出し、イメージビューに画像をのせる。
                int dPosX = targetPrevUnit.battleRange[idx][0];
                int dPosY = targetPrevUnit.battleRange[idx][1];
                int nowPosX = targetPrevUnit.posX + dPosX;
                if (nowPosX < 0 || nowPosX > mainBoard.board_sizeX -1) {   //移動範囲Xチェック
                    continue;
                }
                int nowPosY = targetPrevUnit.posY + dPosY;
                if (nowPosY < 0 || nowPosY > mainBoard.board_sizeY -1) {  //移動範囲Yチェック
                    continue;
                }

                //タイル
                BitmapDrawable bmpDrTile = mainTile[nowPosX][nowPosY].bmp;    //タイル画像取得
                //ユニット
                int tileOnUnitArrayIdx = mainTile[nowPosX][nowPosY].onUnitArrayListIdx;
                Unit targetUnit = null;
                BitmapDrawable bmpDrUnit;
                if (tileOnUnitArrayIdx != -1) { //ユニットを選択している状態
                    targetUnit = mainBoard.allUnitOnBoard.get(tileOnUnitArrayIdx);
                    bmpDrUnit = targetUnit.bmp;
                } else {
                    bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);
                }
                //イメージビューにタイルとユニットの画像を重ねる
                Drawable[] clearDrawables = {
                        bmpDrTile,
                        bmpDrUnit,
                };
                LayerDrawable ld = new LayerDrawable(clearDrawables);
                mainTile[nowPosX][nowPosY].imgView.setImageDrawable(ld);
            }
        }

        /*
        //クリック位置のタイルクラスIDの特定
        int tilePrevOnUnitNo = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitID;
        //クリック位置のユニットインスタンスIDの特定
        int tilePrevOnUnitInstanceNo = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitInstanceID;
        Unit unitPrevObject = null;
        BitmapDrawable bmpDrUnit;
        if (tilePrevOnUnitNo != 0){ //ユニットを選択している状態
            unitPrevObject = getTargetUnitInstance(tilePrevOnUnitNo, tilePrevOnUnitInstanceNo);
        }
        if (unitPrevObject != null) {
            for (int idx = 0; idx <unitPrevObject.battleRange.length; idx++) {
                //移動可能範囲を算出し、イメージビューに画像をのせる。
                int dPosX = unitPrevObject.battleRange[idx][0];
                int dPosY = unitPrevObject.battleRange[idx][1];
                int nowPosX = unitPrevObject.posX + dPosX;
                if (nowPosX < 0 || nowPosX > mainBoard.board_sizeX -1) {   //移動範囲Xチェック
                    continue;
                }
                int nowPosY = unitPrevObject.posY + dPosY;
                if (nowPosY < 0 || nowPosY > mainBoard.board_sizeY -1) {  //移動範囲Yチェック
                    continue;
                }

                //タイル
                BitmapDrawable bmpDrTile = mainTile[nowPosX][nowPosY].bmp;    //タイル画像取得
                //ユニット
                int tileOnUnitNo = mainTile[nowPosX][nowPosY].onUnitID;
                //クリック位置のユニットインスタンスIDの特定
                int tileOnUnitInstanceNo = mainTile[nowPosX][nowPosY].onUnitInstanceID;
                Unit unitObject = null;
                if (tileOnUnitNo != 0){ //ユニットを選択している状態
                    unitObject = getTargetUnitInstance(tileOnUnitNo, tileOnUnitInstanceNo);
                    bmpDrUnit = unitObject.bmp;
                } else {
                    bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);
                }
                //イメージビューにタイルとユニットの画像を重ねる
                Drawable[] clearDrawables = {
                        bmpDrTile,
                        bmpDrUnit,
                };
                LayerDrawable ld = new LayerDrawable(clearDrawables);
                mainTile[nowPosX][nowPosY].imgView.setImageDrawable(ld);
            }
        } else {

        }
        */
    }

    void gameWinPopupWindow(String msg) {

        //ポップアップウィンドウの表示
        mPopupWindow = new PopupWindow(DQChess_game.this);
        // レイアウト設定
        View popupView = getLayoutInflater().inflate(R.layout.popupwnd_win, null);
        popupView.findViewById(R.id.popup_win_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    //BGMが再生中ならBGMを切る
                    if (friendMediaPlayer.isPlaying()) {
                        // 再生終了
                        friendMediaPlayer.stop();
                    }
                    if (enemyMediaPlayer.isPlaying()) {
                        // 再生終了
                        enemyMediaPlayer.stop();
                    }
                    //トップ画面に戻る
                    finish();

                    mPopupWindow.dismiss();
                }
            }
        });

        //ポップアップウィンドウのテキスト設定
        TextView tv = (TextView)popupView.findViewById(R.id.popup_win_text);
        tv.setText(msg);

        //ポップアップウィンドウのコンテキスト設定
        mPopupWindow.setContentView(popupView);

        // タップ時に他のViewでキャッチされないための設定
        //ここがうまく動いていない？
        //ポップアップダイアログの外を表示すると、ポップアップは消えるが、モード遷移状態がおかしくなる。
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);

        // 表示サイズの設定 今回は幅300dp
        float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
        mPopupWindow.setWindowLayoutMode((int) width, WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setWidth((int) width);
        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 画面中央に表示
//                mPopupWindow.showAtLocation(findViewById(R.id.show_button), Gravity.CENTER, 0, 0);
        mPopupWindow.showAtLocation(rl, Gravity.CENTER, 0, -400);

    }

    void gameLossPopupWindow(String msg) {

        //ポップアップウィンドウの表示
        mPopupWindow = new PopupWindow(DQChess_game.this);
        // レイアウト設定
        View popupView = getLayoutInflater().inflate(R.layout.popupwind_loss, null);
        popupView.findViewById(R.id.popup_loss_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    //BGMが再生中ならBGMを切る
                    if (friendMediaPlayer.isPlaying()) {
                        // 再生終了
                        friendMediaPlayer.stop();
                    }
                    if (enemyMediaPlayer.isPlaying()) {
                        // 再生終了
                        enemyMediaPlayer.stop();
                    }
                    //トップ画面に戻る
                    finish();

                    mPopupWindow.dismiss();
                }
            }
        });

        //ポップアップウィンドウのテキスト設定
        TextView tv = (TextView)popupView.findViewById(R.id.popup_loss_text);
        tv.setText(msg);

        //ポップアップウィンドウのコンテキスト設定
        mPopupWindow.setContentView(popupView);

        // タップ時に他のViewでキャッチされないための設定
        //ここがうまく動いていない？
        //ポップアップダイアログの外を表示すると、ポップアップは消えるが、モード遷移状態がおかしくなる。
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);

        // 表示サイズの設定 今回は幅300dp
        float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
        mPopupWindow.setWindowLayoutMode((int) width, WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setWidth((int) width);
        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 画面中央に表示
//                mPopupWindow.showAtLocation(findViewById(R.id.show_button), Gravity.CENTER, 0, 0);
        mPopupWindow.showAtLocation(rl, Gravity.CENTER, 0, -400);

    }

    void setMessageToPopupWindow(String msg) {

        //ポップアップウィンドウの表示
        mPopupWindow = new PopupWindow(DQChess_game.this);
        // レイアウト設定
        View popupView = getLayoutInflater().inflate(R.layout.popupwnd_layout, null);
        popupView.findViewById(R.id.popup_close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    //モード遷移 要判定処理
                    int tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
                    if (tileOnUnitArrayIdx == -1) { //クリックした位置にユニットがない場合
                        mainBoard.modeFlag = 0;
                    } else {
                        if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 0) {
                            mainBoard.modeFlag = 11;
                        } else if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 1) {
                            mainBoard.modeFlag = 12;
                        } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 0){
                            mainBoard.modeFlag = 13;
                        }
                    }
                    //コマンドテキストビューの状態設定
                    setCommandTextViewStatus(mainBoard.modeFlag);
                    mPopupWindow.dismiss();
                }
            }
        });

        //ポップアップウィンドウのテキスト設定
        TextView tv = (TextView)popupView.findViewById(R.id.popup_text_view);
        tv.setText(msg);

        //ポップアップウィンドウのコンテキスト設定
        mPopupWindow.setContentView(popupView);

        // タップ時に他のViewでキャッチされないための設定
        //ここがうまく動いていない？
        //ポップアップダイアログの外を表示すると、ポップアップは消えるが、モード遷移状態がおかしくなる。
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);

        // 表示サイズの設定 今回は幅300dp
        float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
        mPopupWindow.setWindowLayoutMode((int) width, WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setWidth((int) width);
        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 画面中央に表示
//                mPopupWindow.showAtLocation(findViewById(R.id.show_button), Gravity.CENTER, 0, 0);
        mPopupWindow.showAtLocation(rl, Gravity.CENTER, 0, -400);

                /*
                //ダイアログ表示
                new AlertDialog.Builder(DQChess_game.this)
                        .setTitle("ステータス")
                        .setMessage("パラメータを表示する。")
                        .setPositiveButton("OK", null)  //リスナを設定する
                        .show();
                */
        //コマンドテキストビューの状態設定
        setCommandTextViewStatus(mainBoard.modeFlag);

    }

    void subThreadProcess(final int subThreadFlag, Unit enemyUnit, Unit friendUnit) {
//    void subThreadProcess(final int subThreadFlag) {
//    void subThreadProcess(Unit enemyUnit, Unit friendUnit, int subThreadFlag) {
        //サブスレッドフラグが0以外の時実行
        if (subThreadFlag != 0x0) {
            //味方ユニット戦闘処理------------
            final Runnable friendBattleRunnable = new FriendBattleRunnable(enemyUnit, friendUnit);
            final Runnable friendBattleHitRunnable = new FriendBattleHitRunnable(enemyUnit, friendUnit);
            final Runnable friendBattleDamageRunnable = new FriendBattleDamageRunnable(enemyUnit, friendUnit);
            final Runnable friendBattleFinishRunnable = new FriendBattleFinishRunnable(enemyUnit, friendUnit);

            //サブスレッドを作成し、終了処理を行う
            new Thread(new Runnable() {
                public void run() {
                    if ((subThreadFlag & 0x1) == 0x1) { //味方ユニット戦闘処理------------
                        ExcuteSleepProcess(1500);   // スリープ処理をmHandler.postの外で実行
                        subThreadHandler.post(friendBattleRunnable);
                        ExcuteSleepProcess(1500);
                        //命中判定
                        subThreadHandler.post(friendBattleHitRunnable);
                        ExcuteSleepProcess(3000);

                        //命中があたった場合 //Runnableの中で判定すること
                        //ダメージ算出
                        subThreadHandler.post(friendBattleDamageRunnable);
                        ExcuteSleepProcess(3000);

                        //後処理
                        subThreadHandler.post(friendBattleFinishRunnable);
                        ExcuteSleepProcess(1500);
                    }

                    if ((subThreadFlag & 0x10) == 0x10) {   //ターン終了処理--------

                        final Runnable enemyBattleSearchRunnable = new EnemyBattleSearchRunnable();
                        //戦闘する敵ユニットと戦闘される味方ユニットの決定
                        subThreadHandler.post(enemyBattleSearchRunnable);

                        //最初の敵戦闘処理
                        //敵攻撃　命中判定
                        //盤クラスの戦闘候補配列をチェック
                        final Runnable enemyBattleHitRunnable = new EnemyBattleHitRunnable();
                        //最初の敵戦闘処理
                        //敵攻撃　命中判定
                        subThreadHandler.post(enemyBattleHitRunnable);
                        ExcuteSleepProcess(3000);

                        final Runnable enemyBattleDamageRunnable = new EnemyBattleDamageRunnable();
                        //最初の敵戦闘処理
                        //敵攻撃　ダメージ判定
                        subThreadHandler.post(enemyBattleDamageRunnable);
                        ExcuteSleepProcess(3000);

                        //敵移動処理
                        final Runnable enemyBattleMoveRunnable = new EnemyBattleMoveRunnable();
                        subThreadHandler.post(enemyBattleMoveRunnable);
                        ExcuteSleepProcess(500);

                        //2回目の敵戦闘処理---
                        //最初の戦闘処理で未戦闘の時に実行する
                        //最初の敵戦闘処理
                        //敵攻撃　命中判定
                        subThreadHandler.post(enemyBattleHitRunnable);
                        ExcuteSleepProcess(3000);

                        //最初の敵戦闘処理
                        //敵攻撃　ダメージ判定
                        subThreadHandler.post(enemyBattleDamageRunnable);
                        ExcuteSleepProcess(3000);

                        //ターン終了処理
                        final Runnable turnEndInitRnnable = new TurnEndInitRnnable();
                        subThreadHandler.post(turnEndInitRnnable);
                        ExcuteSleepProcess(1500);

                        //ゲーム終了処理
                        final  Runnable gameEndRunnable = new GameEndTunnable();
                        subThreadHandler.post(gameEndRunnable);

                        //BGMの切り替え
                        EnemyBattleBGMProcess(1);   //敵→味方BGM
                    }

                }
            }).start();
        }

    }

    void enemyBattleHitProcess(Unit enemyUnit, Unit friendUnit) {
        //命中判定
        String msg;
        //基準値の設定
        int baseFixVal = 60;
        Random hitRandom = new Random();
        int baseVariableVal = hitRandom.nextInt(30);
        //命中率の計算
        int intHitRandom = hitRandom.nextInt(100);
        int hitRate = ((baseFixVal + baseVariableVal) + enemyUnit.hit - friendUnit.agility);
        if (intHitRandom <= hitRate) {  //命中した場合
            mainBoard.enemryBattleHitFlag = 1;
            msg = (enemyUnit.instanseName + " のこうげきは "+ friendUnit.instanseName + " にめいちゅうした。\n");
        } else {    //はずれた場合
            mainBoard.enemryBattleHitFlag = 0;
            msg = (friendUnit.instanseName + " は "+ enemyUnit.instanseName + " のこうげきをひらりとかわした。\n");
            //運による補正
            int intLuckRandom = hitRandom.nextInt(100);
            if (intLuckRandom <= friendUnit.luck) { //運補正により命中した場合
                //ここカバレッジ大丈夫か？
                mainBoard.enemryBattleHitFlag = 1;
                msg = msg + ("しかし、こううんのめがみが "+ enemyUnit.instanseName +" にほほえみかけた。");
            }
        }
        messageTextView.setText(msg);
    }

    void enemyBattleDamageProcess(Unit enemyUnit, Unit friendUnit) {
        //命中であたり判定の時のみこの処理を実行する
        String msg;
        //ダメージの算出
//        //相手の力と自分の体力が、あまりにも差がありすぎる時の補正
//        //相手の力と自分の体力が、あまりにも差がありすぎるかチェック
//        //相手の力*1.2 – 自分の体力*0.7
//        double def = ((double)enemyUnit.strength * 1.0) - ((double)friendUnit.physical * 1.0);
//        int enemyStrength;
//        if (def <= 0) {
//            //自分の力をプラス補正する
//            enemyStrength = (int)(((double)friendUnit.physical * 0.7) / 1.0);
//        } else {
//            enemyStrength = enemyUnit.strength;
//        }
//        //基準値の設定
        Random damageRandom = new Random();
        int baseFriendFixRate = 60;
        int baseFriendVariableRate = damageRandom.nextInt(70);
        double baseFriendRate = ((double)(baseFriendFixRate) + (double)(baseFriendVariableRate)) /100;
        int baseEnemyFixRate = 60;
        int baseEnemyVariableRate = damageRandom.nextInt(70);
        double baseEnemyRate = ((double)(baseEnemyFixRate) + (double)(baseEnemyVariableRate)) /100;

        //ダメージの算出
        int damage = ((int)(enemyUnit.strength * baseEnemyRate)) - (int)(enemyUnit.strength * (friendUnit.physical * baseFriendRate /100));
//        int damage = ((int)(enemyStrength * baseEnemyRate)) - ((int)(friendUnit.physical * baseFriendRate));
//                    int damage = (int)((friendStrength - enemyUnit.physical) * baseRate);

//        //基準値の設定
//        int baseFixRate = 70;
//        Random damageRandom = new Random();
//        int baseVariableRate = damageRandom.nextInt(50);
//        double baseRate = ((double)(baseFixRate) + (double)(baseVariableRate)) /100;
//
//        //ダメージの算出
//        int damage = (int)((enemyUnit.strength - friendUnit.physical) * baseRate);
        //運による補正
        int intLuckRandom = damageRandom.nextInt(100);
        if (intLuckRandom <= enemyUnit.luck) {
            //クリティカルダメージ
            //ここカバレッジ大丈夫か？
            damage = (int)(damage * 1.5);
            msg = ("かいしんのいちげき！\n" );
        } else {
            msg = "";
        }
        if(damage <= 0) {
            msg = msg + ("しかし、" + enemyUnit.instanseName + " は " + friendUnit.instanseName + " にダメージをあたえられない。\n");
        } else {
            msg = msg + (enemyUnit.instanseName + " は " + friendUnit.instanseName + " に " + damage + "のダメージをあたえた。\n");
            friendUnit.hp = friendUnit.hp - damage;
        }

        //戦闘後処理
        int friendUnitDeleteFlag = 0;
        int destPosX = friendUnit.posX;
        int destPosY = friendUnit.posY;
        if (friendUnit.hp <= 0){
            //味方ユニットを倒した場合
            friendUnitDeleteFlag = 1;   //フラグを立てる

            //敵戦闘完了フラグを立てる
            mainBoard.enemyBattleFinishFlag = 1;

            //メッセージ作成
            msg = msg + (enemyUnit.instanseName + " は " + friendUnit.instanseName + " をたおした。\n");

            //ユニット消滅処理
            //タイルクラスの更新
            mainTile[destPosX][destPosY].onUnitID = 0;
            mainTile[destPosX][destPosY].onUnitInstanceID = -1;
            mainTile[destPosX][destPosY].onUnitArrayListIdx = -1;

            //すべてのタイルクラスのonUnitArrayListIdxを更新する
            //タイルクラスの乗っているユニットのArrayListのインデックスNoに値をセットする
            int cnt;
            for (cnt = 0; cnt <mainBoard.allUnitOnBoard.size(); cnt++) {
                Unit UnitBuf = mainBoard.allUnitOnBoard.get(cnt);
                mainTile[UnitBuf.posX][UnitBuf.posY].onUnitArrayListIdx = cnt;
            }

            //ユニットクラスの更新
            friendUnit.hp = 0;
            friendUnit.posX = -1;
            friendUnit.posY = -1;

            //ゲームの終了条件判定
            //竜王ユニットHP:0    味方勝利
            //姫ユニットHP:0    敵勝利
            if (friendUnit.unitID == 20) {  //倒されたユニットが姫なら敵勝利
                mainBoard.gameOverFlag = 2;
            }

        } else {
            //味方ユニットはまだ倒していない場合
        }

//        //UI更新
//        //盤イメージの更新
//        BitmapDrawable bmpDrCursor = (BitmapDrawable) getDrawable(R.drawable.selected);  //ユニット画像取得
//        BitmapDrawable bmpDrTile = mainTile[destPosX][destPosY].bmp;    //タイル画像取得
//        BitmapDrawable bmpDrUnit;
//        if(friendUnit.hp <= 0) {
//            bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);  //ユニット画像取得
//        } else {
//            bmpDrUnit = friendUnit.bmp;  //ユニット画像取得
//        }
//        Drawable[] drawables = {
//                bmpDrTile,
//                bmpDrUnit,
//                bmpDrCursor
//        };
//        LayerDrawable ld = new LayerDrawable(drawables);
//        mainTile[destPosX][destPosY].imgView.setImageDrawable(ld);
//
//        //グラフィックイメージビューの更新
//        //クリック位置のタイルクラスIDの特定
//        //ユニットクラスからBitmapDrawableをとってきて表示した方がいい？
//        int onUnitNo = mainTile[destPosX][destPosY].onUnitID;
//        Bitmap bmp;
//        if (onUnitNo == 0) {    //ユニットなし
//            int TileNo = mainTile[destPosX][destPosY].tileID;
//            bmp = getTileBmpFileName(TileNo);
//        } else {    //ユニットあり
//            bmp = getUnitBmpFileName(onUnitNo);
//        }
//        graphicImageView.setImageBitmap(bmp);

        //敵戦闘ArrayListのクリア
        int unitIdx;
        for (unitIdx = 0; unitIdx < mainBoard.enemyUnitHasBattlableFriend.size(); unitIdx++) {
            Unit unit = mainBoard.allUnitOnBoard.get(unitIdx);
            unit.battleCandidateUnit.clear();
        }
        mainBoard.enemyUnitHasBattlableFriend.clear();
        if (friendUnitDeleteFlag == 1) {
            //盤クラスの更新
            mainBoard.allUnitOnBoard.remove(mainBoard.allUnitOnBoard.indexOf(friendUnit));
            friendUnitDeleteFlag = 0;
        }
        //メッセージ表示
        messageTextView.setText(msg);

    }

    void enemyBattleMoveProcess(Unit enemyUnit, Unit friendUnit) {
        String msg;
        //最初に移動可能な味方ユニットがいるかチェック
        //盤に存在する敵ユニットをすべて検索
        //盤と各クラスの戦闘候補ArrayListに値を設定する
        mainBoard.enemyUnitHasMoveable.clear();  //ArrayListのクリア
        for (int unitIdx = 0; unitIdx < mainBoard.allUnitOnBoard.size(); unitIdx++) {
            //各敵ユニットの移動可能範囲を調べる
            Unit checkingEnemyUnit = mainBoard.allUnitOnBoard.get(unitIdx);
            if (checkingEnemyUnit.friendOrFoe == 2) {  //敵ユニットの場合
                checkingEnemyUnit.moveablePos.clear();
                for (int idx = 0; idx <checkingEnemyUnit.moveRange.length; idx++) {
                    //移動可能範囲を算出し、
                    int dPosX = checkingEnemyUnit.moveRange[idx][0];
                    int dPosY = checkingEnemyUnit.moveRange[idx][1];
                    int nowPosX = checkingEnemyUnit.posX + dPosX;
                    if (nowPosX < 0 || nowPosX > mainBoard.board_sizeX - 1) {   //移動範囲Xチェック
                        continue;
                    }
                    int nowPosY = checkingEnemyUnit.posY + dPosY;
                    if (nowPosY < 0 || nowPosY > mainBoard.board_sizeY - 1) {  //移動範囲Yチェック
                        continue;
                    }
                    int candidateFriendIdx = mainTile[nowPosX][nowPosY].onUnitArrayListIdx;
                    if (candidateFriendIdx != -1) {  //移動範囲にユニットあり
                        //移動できない ユニットクラスの移動候補配列に追加しない
                    } else {
                        //ユニットクラスの移動候補配列に追加（複数存在する場合がある）
                        checkingEnemyUnit.moveablePos.add(nowPosX + "," + nowPosY);
                        if (mainBoard.enemyUnitHasMoveable.size() != 0) {    //ArrayListが空でない場合
                            int num = mainBoard.enemyUnitHasMoveable.size();
                            Unit unit = mainBoard.enemyUnitHasMoveable.get(num -1);
                            if(unit != checkingEnemyUnit){   //現在チェック中の移動候補が存在する敵ユニットが盤クラスの移動候補ArrayListに追加されていない場合
                                //盤クラスに移動候補が存在する敵ユニットを移動候補配列に追加（複数存在する場合がある）
                                mainBoard.enemyUnitHasMoveable.add(checkingEnemyUnit);
                            }
                        } else {    //ArrayListが~空の場合
                            //盤クラスに移動候補が存在する敵ユニットを移動候補配列に追加（複数存在する場合がある）
                            mainBoard.enemyUnitHasMoveable.add(checkingEnemyUnit);
                        }
                    }
                }
            }
        }

        //移動候補配列が空でない（いずれかの敵ユニットは、移動可能）の場合
        //どの敵ユニットが移動を行うか決定し戦闘する。（ランダム?優先順位?）
        if (mainBoard.enemyUnitHasMoveable.size() != 0) {
//          mainBoard.enemyMoveFinishFlag = 1;  //ここではダメ
            mainBoard.doMoveEnemyUnit = null;
//            mainBoard.battledFriendUnit = null;
            Random doMoveEnemyRandom = new Random();
            int doMoveEnemyNum = mainBoard.enemyUnitHasMoveable.size();
            int doMoveEnemyIdx = doMoveEnemyRandom.nextInt(doMoveEnemyNum);
            mainBoard.doMoveEnemyUnit = mainBoard.enemyUnitHasMoveable.get(doMoveEnemyIdx);

            //移動を行うユニットが決定したら、どの位置に移動を行うか決定する。（ランダム?優先順位?）
            Random moveablePosRandom = new Random();
            int moveablePosRandomNum = mainBoard.doMoveEnemyUnit.moveablePos.size();
            int moveablePosRandomNumIdx = moveablePosRandom.nextInt(moveablePosRandomNum);
            String movePos = mainBoard.doMoveEnemyUnit.moveablePos.get(moveablePosRandomNumIdx);
            String[] movePosArray = movePos.split(",", 0);  //[0]･･･移動先x、[1]･･･移動先y
            int movePosX = Integer.parseInt(movePosArray[0]);   //移動先x
            int movePosY = Integer.parseInt(movePosArray[1]);   //移動先y
            int movePrevPosX = mainBoard.doMoveEnemyUnit.posX;  //移動前x
            int movePrevPosY = mainBoard.doMoveEnemyUnit.posY;  //移動前y

            //移動処理
            //前回クリック位置のカーソルを消す
            clearPrevCursorBmp(mainBoard.prevSelecter_posX, mainBoard.prevSelecter_posY);
//            clearPrevCursorBmp(movePrevPosX, movePrevPosY);

            //盤イメージの更新 移動前
            BitmapDrawable bmpDrTile = mainTile[movePrevPosX][movePrevPosY].bmp;    //タイル画像取得
            BitmapDrawable bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);  //ユニット画像取得
            Drawable[] drawablesPrev = {
                    bmpDrTile,
                    bmpDrUnit,
            };
            LayerDrawable ld = new LayerDrawable(drawablesPrev);
            mainTile[movePrevPosX][movePrevPosY].imgView.setImageDrawable(ld);
            //盤イメージの更新 移動後
            BitmapDrawable bmpDrCursor = (BitmapDrawable) getDrawable(R.drawable.selected);  //ユニット画像取得
            bmpDrTile = mainTile[movePosX][movePosY].bmp;    //タイル画像取得
            bmpDrUnit = mainBoard.doMoveEnemyUnit.bmp;  //ユニット画像取得
            Drawable[] drawables = {
                    bmpDrTile,
                    bmpDrUnit,
                    bmpDrCursor
            };
            ld = new LayerDrawable(drawables);
            mainTile[movePosX][movePosY].imgView.setImageDrawable(ld);

            //盤クラスの更新
            mainBoard.prevSelecter_posX = movePosX;
            mainBoard.prevSelecter_posY = movePosY;

            //タイルクラスの更新
            mainTile[movePosX][movePosY].onUnitID = mainBoard.doMoveEnemyUnit.unitID;
            mainTile[movePosX][movePosY].onUnitInstanceID = mainBoard.doMoveEnemyUnit.unitInstanceID;
            mainTile[movePosX][movePosY].onUnitArrayListIdx = mainTile[movePrevPosX][movePrevPosY].onUnitArrayListIdx;
            mainTile[movePrevPosX][movePrevPosY].onUnitID = 0;
            mainTile[movePrevPosX][movePrevPosY].onUnitInstanceID = -1;
            mainTile[movePrevPosX][movePrevPosY].onUnitArrayListIdx = -1;

            //ユニットクラスの更新
            mainBoard.doMoveEnemyUnit.posX = movePosX;
            mainBoard.doMoveEnemyUnit.posY = movePosY;

            //選択座標テキストビューの表示更新
            selectedTextView.setText("ざひょう（ " + movePosX + " , " + movePosY + " ）");

            //グラフィックイメージビューの更新
            //クリック位置のタイルクラスIDの特定
            //ユニットクラスからBitmapDrawableをとってきて表示した方がいい？
            int onUnitNo = mainTile[movePosX][movePosY].onUnitID;
            Bitmap bmp;
            if (onUnitNo == 0) {    //ユニットなし
                int TileNo = mainTile[movePosX][movePosY].tileID;
                bmp = getTileBmpFileName(TileNo);
            } else {    //ユニットあり
                bmp = getUnitBmpFileName(onUnitNo);
            }
            graphicImageView.setImageBitmap(bmp);

            //情報テキストビューの更新
            if (mainBoard.doMoveEnemyUnit != null) {   //ユニットを選択している状態
                String unitCNameStr = String.valueOf(mainBoard.doMoveEnemyUnit.instanseName);
                String unitLevelStr = String.valueOf(mainBoard.doMoveEnemyUnit.level);
                String unitHpStr = String.valueOf(mainBoard.doMoveEnemyUnit.hp);
                String unitMpStr = String.valueOf(mainBoard.doMoveEnemyUnit.mp);
                //情報テキストビューの更新
                infoTextView.setText(unitCNameStr + "\nＬＶ：" + unitLevelStr + "\nＨＰ：" + unitHpStr + "\nＭＰ：" + unitMpStr);
            } else {    //ユニットを選択していな状態
                //情報テキストビューの更新
                String infStr = mainTile[movePosX][movePosY].tileName;
                //情報テキストビューの更新
                infoTextView.setText(infStr);
            }

            //敵移動ArrayListのクリア
            int unitIdx;
            for (unitIdx = 0; unitIdx < mainBoard.enemyUnitHasMoveable.size(); unitIdx++) {
                Unit unit = mainBoard.allUnitOnBoard.get(unitIdx);
                unit.moveablePos.clear();
            }
            mainBoard.enemyUnitHasMoveable.clear();
        }

        msg = ("てきのいどうちゅうです。\n");
        messageTextView.setText(msg);

    }

    /*
    void turnEndProcess() {
        //ターン終了処理
        final Runnable endRunnable = new EndRunnable();
        final Runnable enemyMoverunnable = new EnemyMoveRunnable();
        final Runnable enemyBattlerunnable = new EnemyBattleRunnable();
        final Runnable initTurnrunnable = new InitTurnRunnable();
        //サブスレッドを作成し、終了処理を行う
        new Thread(new Runnable() {
            public void run() {
                // スリープ処理をmHandler.postの外で実行
                ExcuteSleepProcess(1500);
                endHandler.post(endRunnable);
                ExcuteSleepProcess(1500);
                enemyMoveHandler.post(enemyMoverunnable);
                ExcuteSleepProcess(1500);
                enemyBattleHandler.post(enemyBattlerunnable);
                ExcuteSleepProcess(1500);
                initTurnHandler.post(initTurnrunnable);
            }
        }).start();

        //初期化
        mainBoard.friendMoveFinishFlag = 0;
        mainBoard.friendBattleFinishFlag = 0;
        //敵フェーズが完了したら、モード遷移する
        //今回クリック位置のユニットのArrayListのインデックスNoの特定
        int tileOnUnitArrayIdx = mainTile[mainBoard.selecter_posX][mainBoard.selecter_posY].onUnitArrayListIdx;
        if (tileOnUnitArrayIdx == -1) {
            //盤クラスのArrayListからインスタンスを取得
            mainBoard.modeFlag = 0;
        } else {
            if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 0) {
                mainBoard.modeFlag = 11;
            } else if (mainBoard.friendMoveFinishFlag == 0 && mainBoard.friendBattleFinishFlag == 1) {
                mainBoard.modeFlag = 12;
            } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 0) {
                mainBoard.modeFlag = 13;
            } else if (mainBoard.friendMoveFinishFlag == 1 && mainBoard.friendBattleFinishFlag == 1) {
                mainBoard.modeFlag = 5;
            }
        }
    }
    */

    /*
    private void friendBattleProcess(Unit enemyUnit, Unit friendUnit) {

        final Runnable friendBattleRunnable = new FriendBattleRunnable(enemyUnit, friendUnit);
        //サブスレッドを作成し、終了処理を行う
        new Thread(new Runnable() {
            public void run() {
                // スリープ処理をmHandler.postの外で実行
                ExcuteSleepProcess(1500);
                endHandler.post(friendBattleRunnable);
                ExcuteSleepProcess(1500);

            }
        }).start();
        //命中判定

        //ダメージ算出

        //後処理

    }
*/
    /*
    void clearPrevUnitBmp() {
        // debuging -------------------------------------
        //前回グラフィックイメージビューの更新
        //前回選択ユニットが表示されているので、クリアする
        //タイル
        BitmapDrawable bmpDrTile = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].bmp;    //タイル画像取得
        //ユニット
        ：：：
        //クリック位置のタイルクラスIDの特定
        int tilePrevOnUnitNo = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitID;
        //クリック位置のユニットインスタンスIDの特定
        int tilePrevOnUnitInstanceNo = mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].onUnitInstanceID;
        Unit unitPrevObject = null;
        BitmapDrawable bmpDrUnit;
        if (tilePrevOnUnitNo != 0){ //ユニットを選択している状態
            unitPrevObject = getTargetUnitInstance(tilePrevOnUnitNo, tilePrevOnUnitInstanceNo);
            bmpDrUnit = unitPrevObject.bmp;  //ユニット画像取得
        } else {
            bmpDrUnit = (BitmapDrawable) getDrawable(R.drawable.u_unit_non);  //ユニット画像取得
        }
        //イメージビューにタイルとユニットの画像を重ねる
        Drawable[] drawables = {
                bmpDrTile,
                bmpDrUnit
        };
        LayerDrawable ld = new LayerDrawable(drawables);
        mainTile[mainBoard.prevSelecter_posX][mainBoard.prevSelecter_posY].imgView.setImageDrawable(ld);
    }
       */

}

