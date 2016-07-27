package ya.dqchess;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

//盤
public class Board {

    protected int boardID = 1;
    protected int board_sizeX, board_sizeY;
    protected int disp_sizeX, disp_sizeY;
    protected int disp_orientX, disp_orientY;
    protected int selecter_posX, selecter_posY, prevSelecter_posX, prevSelecter_posY;
    protected int tileInf[][];
    protected int unitPosInf[][];
//    protected int unitCount[][];
    protected int tern;
    protected int phase;
    protected int modeFlag;
    protected int friendMoveFinishFlag;
    protected int friendBattleFinishFlag;
    protected int friendBattleHitFlag;
    protected int enemryBattleHitFlag;
    protected int enemyMoveFinishFlag;
    protected int enemyBattleFinishFlag;
    protected ArrayList<Unit> allUnitOnBoard;
    protected ArrayList<Unit> enemyUnitHasBattlableFriend;
    protected Unit doBattleEnemyUnit;
    protected Unit battledFriendUnit;
    protected ArrayList<Unit> enemyUnitHasMoveable;
    protected Unit doMoveEnemyUnit;
    protected int gameOverFlag;

    public Board(int msizeX, int msizeY, int dsizeX, int dsizeY, int oposX, int oposY, int sposX, int sposY) {    //コンストラクタ
        this.board_sizeX = msizeX;
        this.board_sizeY = msizeY;
        this.disp_sizeX = dsizeX;
        this.disp_sizeY = dsizeY;

        this.disp_orientX = oposX;
        this.disp_orientY = oposY;
        this.selecter_posX = sposX;
        this.selecter_posY = sposY;
        this.prevSelecter_posX = this.selecter_posX;
        this.prevSelecter_posY = this.selecter_posY;

        tileInf = new int[board_sizeX][board_sizeY];
        unitPosInf = new int[board_sizeX][board_sizeY];

        this.tern = 1;
        this.phase = 1;
        this.modeFlag = 0;
        this.friendMoveFinishFlag = 0;
        this.friendBattleFinishFlag = 0;
        this.friendBattleHitFlag = 1;
        this.enemryBattleHitFlag = 1;

        allUnitOnBoard = new ArrayList<Unit>();
        enemyUnitHasBattlableFriend = new ArrayList<Unit>();
        enemyUnitHasMoveable = new ArrayList<Unit>();

        this.gameOverFlag = 0;

    }

    public Board() {    //コンストラクタ
    }

    //盤の初期化
    public void InitializeBoardInfo () {
        for (int y = 0; y<board_sizeY; y++) {
            for (int x = 0; x<board_sizeX; x++) {
                tileInf[x][y] = 1;
                unitPosInf[x][y] = 0;
            }
        }
    }

    //タイル情報ファイルの書き込み
    public void WriteToFileBoardTileInfo (FileOutputStream fos) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write("2,2,1,1,1,1,1,6,6");
            osw.write("\n");
            osw.write("2,2,1,1,1,1,1,6,6");
            osw.write("\n");
            osw.write("2,2,1,1,1,1,1,1,1");
            osw.write("\n");
            osw.write("2,2,1,1,1,1,1,1,1");
            osw.write("\n");
            osw.write("5,5,1,1,1,1,1,1,1");
            osw.write("\n");
            osw.write("1,1,1,1,1,1,1,1,1");
            osw.write("\n");
            osw.write("1,1,1,1,1,1,1,1,1");
            osw.write("\n");
            osw.write("1,1,1,1,1,1,1,3,3");
            osw.write("\n");
            osw.write("7,1,8,1,1,1,1,3,4");
            osw.flush();
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //タイル情報ファイルの読み出し
    public StringBuffer ReadFromFileBoardTileInfo (FileInputStream fis, String fileName) {
        StringBuffer sb = null;
        //ファイル読み込み
        try {
            String lineBuffer;
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            sb = new StringBuffer();
            int idxX;
            int idxY = 0;
            //String spBuf[] = new String[board_sizeY];
            while((lineBuffer = br.readLine()) != null ) {
                sb.append(lineBuffer);
                String spBuf[] = lineBuffer.split(",", -1);
                for (idxX = 0; idxX<this.board_sizeX; idxX++) {
                    tileInf[idxX][idxY] = Integer.parseInt(spBuf[idxX]);
                }
                idxY++;
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    //ユニット位置情報ファイルの書き込み
    public void WriteToFileBoardUnitPosInfo (FileOutputStream fos) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write("0,0,0,9010,9000,9020,0,0,0");
            osw.write("\n");
            osw.write("0,1080,1090,1100,1160,1120,1130,1140,0");
            osw.write("\n");
            osw.write("0,1010,1020,1030,1040,1150,1060,1070,0");
            osw.write("\n");
            osw.write("0,0,1000,1000,1000,1000,1000,0,0");
            osw.write("\n");
            osw.write("0,0,0,0,0,0,0,0,0");
            osw.write("\n");
            osw.write("0,0,0,0,0,0,0,0,0");
            osw.write("\n");
            osw.write("0,0,0,0,0,0,0,0,0");
            osw.write("\n");
            osw.write("0,0,0,30,10,0,0,0,0");
            osw.write("\n");
            osw.write("0,0,0,40,20,50,60,0,0");

            osw.flush();
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ユニット位置情報ファイルの読み出し
    public StringBuffer ReadFromFileBoardUnitPosInfo (FileInputStream fis, String fileName) {
        StringBuffer sb = null;
        //ファイル読み込み
        try {
            String lineBuffer;
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            sb = new StringBuffer();
            int idxX;
            int idxY = 0;
            //String spBuf[] = new String[board_sizeY];
            while((lineBuffer = br.readLine()) != null ) {
                sb.append(lineBuffer);
                String spBuf[] = lineBuffer.split(",", -1);
                for (idxX = 0; idxX<this.board_sizeX; idxX++) {
                    unitPosInf[idxX][idxY] = Integer.parseInt(spBuf[idxX]);
                }
                idxY++;
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    public int getTargetUnitCount(int targetUnitID) {
        int count = 0;
        for (int y=0; y<this.board_sizeY; y++) {
            for (int x = 0; x < this.board_sizeX; x++) {
                int unitNo = this.unitPosInf[x][y];    //ユニットIDを取得
                if (unitNo == targetUnitID) {
                    ++count;
                }
            }
        }
        return count;
    }

/*
    public void setArrayListUnitOnBoard(Unit unitInstance) {
        allUnitOnBoard.add(unitInstance);
    }
*/

/*
        HashMap<Integer, HashMap<Integer, String>> dim2 = new HashMap<>();

        // dim2 に値を設定する。
        HashMap<Integer, String> vec1 = new HashMap<>();
        vec1.put(0, "01-00");
        vec1.put(2, "01-01");
        dim2.put(1, vec1);

        HashMap<Integer, String> vec3 = new HashMap<>();
        vec3.put(0, "03-00");
        vec3.put(1, "03-01");
        dim2.put(3, vec3);

        // dim2 の内容を表示する。
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(get_val(dim2, i, j) + ",\t");
            }
            System.out.println();
        }
    }
    private static String get_val(HashMap<Integer, HashMap<Integer, String>> dim2, int i, int j) {
        if (dim2.get(i) == null) {
            return null;
        }
        return dim2.get(i).get(j);
    }

 */

}


