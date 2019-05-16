import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ui {
    public JFrame jf = new JFrame("扫雷");
    public JFrame set = new JFrame("设置雷数");
    public JFrame out = new JFrame("game over!");
    public JFrame win = new JFrame("NB!");


    public JButton[][] j = new JButton[25][25];
    public JButton se = new JButton("设置雷数");

    public JButton re2 = new JButton("重新开始");
    public JButton re1 = new JButton("重新开始");
    public JButton re = new JButton("重新开始");
    public JLabel shengyu = new JLabel();

    private JLabel set1 = new JLabel("默认雷数为30个，雷数为正整数且不超过625个");
    private JButton btn = new JButton("提交");
    public JLabel nb = new JLabel("   牛逼，真牛逼");
    public JLabel go = new JLabel("   垃圾，真垃圾");
    private Data Data = new Data();
    private int leishu = Data.getLeishu();
    private int kongbai = Data.getKongbai();
    private int [][] kind = new int [25][25];
    private int qishu = Data.getQishu();
    private int pailei = Data.getPailei();
    private int time=0;
    private JLabel timer = new JLabel();
    private boolean t = true;
    private jishi jishi = new jishi();


    Font fo = new Font("宋体",Font.BOLD,10);
    Font fo1 = new Font("宋体",Font.BOLD,40);
    Font fo2 = new Font("宋体",Font.BOLD,15);


    public void creatUi(){
        jf.setSize(515*2,588*2);
        jf.setLocationRelativeTo(null);             // 把窗口位置设置到屏幕中心
        jf.setLayout(null);
        for(int i = 0; i < 25; i++){
            for(int l = 0; l < 25; l++){
                //System.out.println(i+" "+l);
                j[i][l] = new JButton("");
                j[i][l].setFont(fo);
                j[i][l].setBounds(i*40+8, l*40+45, 40, 40);
                j[i][l].setBackground(Color.WHITE);
                jf.add(j[i][l]);
            }
        }

        set.setSize(400, 200);
        set1.setVerticalAlignment(JLabel.TOP);
        set.add(set1);
        set.setLocationRelativeTo(null);

        shengyu.setBounds(10, 0, 200, 50);
        jf.add(shengyu);

        timer.setBounds(850, 0, 200, 50);
        jf.add(timer);

        win.setLocationRelativeTo(null);
        jf.setLocationRelativeTo(null);

        se.setFont(fo2);
        se.setBounds(516, 1, 100, 43);
        jf.add(se);

        re.setFont(fo2);
        re.setBounds(415, 1, 100, 43);
        jf.add(re);

        out.setSize(400, 200);
        out.add(go);
        go.setFont(fo1);
        out.setLocationRelativeTo(null);
        re1.setFont(fo2);
        re1.setBounds(150, 125, 100, 25);
        go.add(re1);

        win.setSize(400, 200);
        win.add(nb);
        nb.setFont(fo1);
        win.setLocationRelativeTo(null);
        re2.setFont(fo2);
        re2.setBounds(150, 125, 100, 25);
        nb.add(re2);

        String lei="总雷数："+leishu+"  插旗数："+qishu;
        shengyu.setText(lei);

        String shijian = "时间: 0" +"s";
        timer.setText(shijian);

        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
        out.setVisible(false);
        win.setVisible(false);
        set.setVisible(false);

        monitor();
        retn(re, j);
        retn(re1, j);
        retn(re2, j);
        returnleishu();
        jishi.run();



    }
    public void returnleishu(){
        se.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                set.setVisible(true);

            }
        });
        btn.setBounds(150,112,100,40);
        set1.add(btn);
        JTextArea ls = new JTextArea(1, 3);
        ls.setBounds(150,55,100,50);
        set1.add(ls);
        btn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == e.BUTTON1) {
                    String s=ls.getText();
                    boolean k=true;
                    for(int i=0;i<s.length();i++){
                        char a=s.charAt(i);
                        int m=(int) a;
                        if(m<48||m>57)
                            k=false;
                    }
                    if(k) {
                        int m=Integer.parseInt(s);
                        if(m>0&&m<625) {
                            leishu = m;
                            Data.setLeishu(leishu);
                            kongbai = 625 - leishu;
                            Data.setKongbai(kongbai);
                            for (int i = 0; i < 25; i++) {
                                for (int l = 0; l < 25; l++) {
                                    kind[i][l] = 0;
                                }
                            }
                            int mun = 0;
                            while (true) {
                                int f = Data.suiji();
                                int l = Data.suiji();
                                if (kind[f][l] == 0) {
                                    kind[f][l] = 1;
                                    mun++;
                                }
                                if (mun == leishu) break;
                            }
                            Data.setKind(kind);
                            for (int i = 0; i < 25; i++) {
                                for (int l = 0; l < 25; l++) {
                                    int finalL1 = l;
                                    int finalI1 = i;
                                    j[finalI1][finalL1].setBackground(Color.white);
                                    j[finalI1][finalL1].setText("");
                                }
                            }
                            qishu=0;
                            Data.setQishu(qishu);
                            String le="总雷数："+leishu+"  插旗数："+qishu;
                            shengyu.setText(le);
                            set.setVisible(false);
                            time = 0;

                        }
                        else{
                            JFrame numerror=new JFrame("");
                            numerror.setSize(220,100);
                            JLabel nume=new JLabel("雷数必须大于0小于等于625");
                            numerror.add(nume);
                            numerror.setVisible(true);
                            numerror.setLocationRelativeTo(null);
                        }
                    }
                    else{
                        JFrame dataerror=new JFrame("");
                        dataerror.setSize(220,100);
                        JLabel dae=new JLabel("雷数必须为数字");
                        dataerror.add(dae);
                        dataerror.setVisible(true);
                        dataerror.setLocationRelativeTo(null);
                    }
                }
            }
        });

    }
    public void initializeUi(){
        for(int i = 0; i < 25; i++){
            for(int l = 0; l < 25; l++){
                j[i][l].setText("");
                j[i][l].setBackground(Color.WHITE);
            }
        }
        out.setVisible(false);
        win.setVisible(false);
        set.setVisible(false);
    }
    public void retn(JButton re, JButton [][] j){

        re.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (e.getButton() == e.BUTTON1) {
                    t = true;
                    time = 0;
                   // jishi.run();
                    pailei = 0;
                    Data.setPailei(0);
                    leishu=Data.getLeishu();
                    Data.setKongbai(625 - leishu);
                    kongbai = Data.getKongbai();
                    Data.setQishu(0);
                    qishu = Data.getQishu();
                    String lei="总雷数："+leishu+"  插旗数："+qishu;
                    shengyu.setText(lei);
                    kongbai=625-leishu;
                    for(int i=0;i<25;i++){
                        for(int l=0;l<25;l++){
                            kind[i][l]=0;
                        }
                    }
                    Data.setKind(kind);
                    int mun=0;
                    while(true){
                        int f=Data.suiji();
                        int l=Data.suiji();
                        if(kind[f][l]==0){
                            kind[f][l]=1;
                            mun++;
                        }
                        if(mun==leishu)break ;
                    }
                    Data.setKind(kind);
                    initializeUi();
                    //out.setVisible(false);
                    Data.setWo(true);
                }

            }
        });


    }
    public void monitor(){
        boolean wo;
        wo = Data.isWo();
        Data.initializeData();
        for(int i = 0; i < 25; i++){
            for(int l = 0; l < 25; l++){
                int finalI1 = i;
                int finalL1 = l;

                j[i][l].addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == e.BUTTON1) {

                            if (Data.kind[finalI1][finalL1] == 0) {
                                j[finalI1][finalL1].setText("");
                                demining(finalI1, finalL1,0,win,j,Data.kind);
                            }
                            if (Data.kind[finalI1][finalL1] == 1) {
                                j[finalI1][finalL1].setBackground(Color.red);
                                j[finalI1][finalL1].setText("b");
                                if(wo) {
                                    out.setVisible(true);
                                    t = false;
                                }
                                Data.setWo(false);
                                for (int i = 0; i < 25; i++) {
                                    for (int l = 0; l < 25; l++) {
                                        if (Data.kind[i][l] == 0) {
                                            j[i][l].setBackground(Color.cyan);

                                        }
                                        if(Data.kind[i][l]==1){
                                            j[i][l].setBackground(Color.red);
                                            j[i][l].setText("b");

                                        }

                                    }
                                }


                            }
                        }
                        if(e.getButton()==e.BUTTON3){
                            int qishu = Data.getQishu();
                            if(Data.kind[finalI1][finalL1] == 1){
                                pailei++;
                                Data.setPailei(pailei);
                                if(Data.getPailei() == Data.getLeishu()){
                                    t = false;
                                    if(Data.isWo()) {
                                        win.setVisible(true);
                                    }

                                    Data.setWo(false);
                                    for (int i = 0; i < 25; i++) {
                                        for (int l = 0; l < 25; l++) {
                                            if (kind[i][l] == 0) {
                                                j[i][l].setBackground(Color.cyan);
                                            }
                                            if  (kind[i][l] == 1) {
                                                j[i][l].setBackground(Color.red);
                                                j[i][l].setText("b");
                                            }
                                        }
                                    }
                                }
                            }
                            if(Data.kind[finalI1][finalL1]==0||Data.kind[finalI1][finalL1]==1) {
                                j[finalI1][finalL1].setBackground(Color.GREEN);
                                j[finalI1][finalL1].setText("Y");
                                Data.kind[finalI1][finalL1] = Data.kind[finalI1][finalL1]+100;

                                qishu++;
                                Data.setQishu(qishu);
                                String lei="总雷数："+Data.getLeishu()+"  插旗数："+qishu;
                                shengyu.setText(lei);

                            }
                            else if(Data.kind[finalI1][finalL1]==100||Data.kind[finalI1][finalL1]==101){
                                j[finalI1][finalL1].setBackground(Color.LIGHT_GRAY);
                                j[finalI1][finalL1].setText("?");
                                Data.kind[finalI1][finalL1] += 100;
                                qishu--;
                                Data.setQishu(qishu);
                                String lei="总雷数："+Data.getLeishu()+"  插旗数："+qishu;
                                shengyu.setText(lei);
                                if(kind[finalI1][finalL1] == 1){
                                    pailei--;
                                    Data.setPailei(pailei);
                                }
                            }
                            else if(Data.kind[finalI1][finalL1]==200||Data.kind[finalI1][finalL1]==201){
                                j[finalI1][finalL1].setBackground(Color.white );
                                j[finalI1][finalL1].setText("");
                                Data.kind[finalI1][finalL1] -=200;
                            }
                        }
                    }
                });

            }
        }
    }

    public void demining(int finalI1, int finalL1, int kind1, JFrame win, JButton [][] j, int [][] kind) {

        Data.setKongbai(kongbai);
        int num = 0;
        if(kind[finalI1][finalL1]!=0&&kind1==1) {
            return;
        }
        j[finalI1][finalL1].setBackground(Color.cyan);
        kongbai --;
        kind[finalI1][finalL1]=-1;
        if(kongbai==0 || Data.getPailei() == Data.getLeishu()){
            t = false;
            if(Data.isWo()) {
                win.setVisible(true);
            }

            Data.setWo(false);
            for (int i = 0; i < 25; i++) {
                for (int l = 0; l < 25; l++) {
                    if (kind[i][l] == 0) {
                        j[i][l].setBackground(Color.cyan);
                    }
                    if  (kind[i][l] == 1) {
                        j[i][l].setBackground(Color.red);
                        j[i][l].setText("b");
                    }
                }
            }
        }

        if (finalI1 - 1 >= 0 && finalL1 - 1 >= 0 && kind[finalI1 - 1][finalL1 - 1] == 1) {
            num++;
        }
        if (finalI1 >= 0 && finalL1 - 1 >= 0 && kind[finalI1][finalL1 - 1] == 1) {
            num++;
        }
        if (finalI1 - 1 >= 0 && finalL1 >= 0 && kind[finalI1 - 1][finalL1] == 1) {
            num++;
        }
        if (finalI1 + 1 < 25 && finalL1 - 1 >= 0 && kind[finalI1 + 1][finalL1 - 1] == 1) {
            num++;
        }
        if (finalI1 - 1 >= 0 && finalL1 + 1 < 25 && kind[finalI1 - 1][finalL1 + 1] == 1) {
            num++;
        }
        if (finalI1 >= 0 && finalL1 + 1 < 25 && kind[finalI1][finalL1 + 1] == 1) {
            num++;
        }
        if (finalI1 + 1 < 25 && finalL1 >= 0 && kind[finalI1 + 1][finalL1] == 1) {
            num++;
        }
        if (finalI1 + 1 < 25 && finalL1 + 1 < 25 && kind[finalI1 + 1][finalL1 + 1] == 1) {
            num++;
        }
        if (num != 0) {
            String s = "" + num;
            j[finalI1][finalL1].setText(s);
            return;
        }

        else {
            if (finalI1 - 1 >= 0 && finalL1 >= 0 && num == 0) {
                if(kind[finalI1-1][finalL1]!=-1)
                    demining(finalI1 - 1, finalL1,1,win,j,kind);
            }
            if (finalI1 >= 0 && finalL1 - 1 >= 0 && num == 0) {
                if(kind[finalI1][finalL1-1]!=-1)
                    demining(finalI1, finalL1 - 1,1,win,j,kind);
            }
            if  (finalI1 >= 0 && finalL1 + 1 < 25 && num == 0){
                if(kind[finalI1][finalL1+1]!=-1)
                    demining(finalI1,finalL1+1,1,win,j,kind);
            }
            if  (finalI1 + 1 < 25 && finalL1 >= 0 && num == 0){
                if(kind[finalI1+1][finalL1]!=-1)
                    demining(finalI1+1,finalL1,1,win,j,kind);
            }
        }
    }
    public class jishi implements Runnable {
        public void run(){
                try {
                    while(true) {

                        //time2 = time - time1;
                        Thread.sleep(1000);
                        if (t) {
                            String shijian = "时间: " + time + "s";
                            timer.setText(shijian);
                        }
                        time++;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


        }


    }



}
