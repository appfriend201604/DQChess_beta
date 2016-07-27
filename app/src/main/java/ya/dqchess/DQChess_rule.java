package ya.dqchess;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DQChess_rule extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dqchess_rule);

        //勝利条件
        TextView syouriTextView = (TextView)findViewById(R.id.textView1);
        syouriTextView.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));
        //勝利条件の説明
        TextView syouriSetumeiTextView = (TextView)findViewById(R.id.textView3);
        syouriSetumeiTextView.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));
        //敗北条件
        TextView haibokuTextView = (TextView)findViewById(R.id.textView2);
        haibokuTextView.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));
        //敗北条件の説明
        TextView haibokuSetumeiTextView = (TextView)findViewById(R.id.textView4);
        haibokuSetumeiTextView.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));

        //タイトルへもどるボタン
        Button backButton = (Button)findViewById(R.id.backButton);
        backButton.setTypeface(Typeface.createFromAsset(getAssets(), "DQM.TTF"));
        //リスナの設定 トップ画面にもどる
        backButton.setOnClickListener(new MoveTopOnClickListener());
    }


    private class MoveTopOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //トップ画面に戻る
            finish();
        }
    }
}
