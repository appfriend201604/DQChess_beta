package ya.dqchess;

public class Unit_Hime extends Unit {

    static int hime_count = 0;

   public Unit_Hime(int unitNo,int unitInstanceNo, int positionX, int positionY) {     //コンストラクタ
        super(unitNo, unitInstanceNo, positionX, positionY);
       hime_count++;
       super.instanseName = super.getInstanceName(hime_count);
    }
}
