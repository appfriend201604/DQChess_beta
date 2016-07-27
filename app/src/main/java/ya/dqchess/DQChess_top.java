package ya.dqchess;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import ya.dqchess.R;

public class DQChess_top extends Activity {

    private TextView textView;
    private MediaPlayer OpeningMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dqchess_top);

        /*
        // TextView の設定
        textView = (TextView) findViewById(R.id.textViewTitle);
        */

        // ImageView の設定
        ImageView imageViewTop = (ImageView)findViewById(R.id.image_top);
        imageViewTop.setImageResource(R.drawable.top);

        // ボタンを設定
        Button buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStart.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));

        Button buttonRule = (Button)findViewById(R.id.buttonRule);
        buttonRule.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));

        buttonRule.setOnClickListener(new MoveRuleOnClickListener());
        buttonStart.setOnClickListener(new MoveMainOnClickListener());

        //BGM
        // インタンスを生成
        OpeningMediaPlayer = new MediaPlayer();
        //音楽ファイル名, あるいはパス
        String filePath = "dq_openinig.wav";

        // assetsから 音源 ファイルを読み込み
        AssetFileDescriptor afdescripter = null;
        try {
            afdescripter = getAssets().openFd(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //MediaPlayerに読み込んだ音楽ファイルを指定
        try {
            OpeningMediaPlayer.setDataSource(afdescripter.getFileDescriptor(),
                    afdescripter.getStartOffset(),
                    afdescripter.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 音量調整を端末のボタンに任せる
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
//            OpeningMediaPlayer.prepare();

    }

    protected void onResume() {
        super.onResume();
        if (!OpeningMediaPlayer.isPlaying()) {
            OpeningMediaPlayer.setLooping(true);
            // 再生する
            try {
                OpeningMediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            OpeningMediaPlayer.start();
        }
    }

    //ルールボタンを押した場合の処理
    private class MoveRuleOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), DQChess_rule.class);
            startActivity(intent);
        }
    }

    //ゲーム開始ボタンを押した場合の処理
    private class MoveMainOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //BGMが再生中ならBGMを切る
            if (OpeningMediaPlayer.isPlaying()) {
                // 再生終了
                OpeningMediaPlayer.stop();
//                // リセット
//                OpeningMediaPlayer.reset();
//                // リソースの解放  戻ってきたときにまた音楽を鳴らすから解放したらダメ
//                OpeningMediaPlayer.release();
            }
            //OpeningMediaPlayer = null;

            Intent intent = new Intent(getApplication(), DQChess_game.class);
            startActivity(intent);
        }
    }
}
