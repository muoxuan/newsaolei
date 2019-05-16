import javax.swing.*;
import java.util.Random;

public class Data {
    public int[][] kind = new int[25][25];
    private int leishu = 25;
    private int qishu = 0;
    private int kongbai = 625 - leishu;
    private int pailei = 0;
    private boolean wo = true;

    public void setKind(int[][] kind) {
        this.kind = kind;
    }

    public int[][] getKind() {
        return kind;
    }

    public void setLeishu(int leishu) {
        this.leishu = leishu;
    }

    public int getLeishu() {
        return leishu;
    }

    public void setQishu(int qishu) {
        this.qishu = qishu;
    }

    public int getQishu() {
        return qishu;
    }

    public void setKongbai(int kongbai) {
        System.out.println(kongbai);
        this.kongbai = kongbai;
    }

    public int getKongbai() {
        return kongbai;
    }

    public void setWo(boolean wo) {
        this.wo = wo;
    }

    public boolean isWo() {
        return wo;
    }

    public void setPailei(int pailei) {
        this.pailei = pailei;
    }

    public int getPailei() {
        return pailei;
    }

    public void initializeData(){
        for(int i = 0; i < 25; i++){
            for(int j = 0; j < 25; j++ ){
                kind[i][j] = 0;
            }
        }
        qishu = 0;
        int num = 0;
        while(true){
            int i = suiji();
            int j = suiji();
            if(kind[i][j] == 0){
                kind[i][j] = 1;
                num++;
            }
            if(num == leishu){
                break;
            }
        }
    }
    public void initializeLeishu(){
        leishu = 25;
    }
    public static int suiji(){
        int max = 25;
        int min = 0;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }
}
