import java.io.*;

public class Suudoku{
  public static void main(String[] args){
    long start = System.currentTimeMillis();
    int[][] data = new int[9][9];
    if(args.length!=1){
      System.err.println("need 1 argument");
      System.exit(1);
    }

    BufferedReader br;
    try{
      br = new BufferedReader(new FileReader(args[0]));
      int n = 0;
      for(int i=0; i<9; i++){
        String tmp[] = br.readLine().split(" ");
         for(int j=0; j<9; j++){
          data[n][j]=Integer.parseInt(tmp[j]);
        }
        n++;
      }
    }catch(Exception e){
      System.err.println(""+e);
      System.exit(1);
    }
    Suudoku2.print(data);
    Suudoku2.saikiherasu(data);
    int[][] kuuhaku = new int [9][9];
    for (int i = 0; i < 9 ; i++ ) {
      for (int j = 0; j < 9 ; j++ ) {
        if (data[i][j] == 0) {
          kuuhaku[i][j]=1;
        }else{
          kuuhaku[i][j]=0;
        }
        }
      }
    Suudoku2.atari(data,kuuhaku);
    long stop = System.currentTimeMillis();
    System.out.println("実行にかかった時間は " + (stop - start) + " ミリ秒です。");
  }

public static class Suudoku2 extends Suudoku {
   private static int count;
    public static void saikiherasu(int[][]data) {
     for(int a = 0; a < 9; a++) {
        for(int b = 0; b < 9; b++) {
            if(data[a][b] == 0) {
                int[] m = {0,0,0,0,0,0,0,0,0};
                int count = 0;
                int suuji = 0;
                kouhotenn(a, b, m,data);
                for(int c = 0; c < 9 ; c++) {
                    if(m[c] == 1)
                        count++;
                    else
                        suuji = c + 1;
                }
                if(count == 8) {
                    data[a][b] = suuji;
                }
            }
        }
      }
    }

    public static void atari(int[][] data,int[][] data2) {
        for(int a = 0; a < 9; a++) {
            for(int b = 0; b < 9; b++) {
                if(data[a][b] == 0) {
                    int[] m = {0,0,0,0,0,0,0,0,0};
                    kouhotenn(a, b, m,data);
                    for(int c = 0; c < 9; c++) {
                        if(m[c] == 0) {
                            data[a][b] = c + 1;
                            atari(data,data2);
                            count++;
                        }
                    }
                    data[a][b] = 0;
                    return;
                }
            }
        }
        int saiki = count / 500;
        for (int i = 0; i < saiki ; i++ ) {
        System.out.print(".");
        }
        print2(data,data2);
        System.out.println("再帰回数：" + count);
    }


    public static void kouhotenn(int a, int b,int[] m,int[][] data) {
     //行
     for(int i = 0; i < 9; i++) {
        if(data[a][i] != 0) {
            m[data[a][i] -1] = 1;
        }
     }
     //列
     for(int i = 0; i < 9; i++) {
        if(data[i][b] != 0) {
            m[data[i][b] -1] = 1;
        }
     }
    //ブロック
     int x = a / 3 * 3;
     int y = b / 3 * 3;
     for(int l = x; l < x + 3; l++) {
        for(int k = y; k < y + 3; k++) {
            if(data[l][k] != 0)
                m[data[l][k] -1] = 1;
            }
        }
     }

     public static void print(int[][] data) {
       System.out.println("");
         for(int i = 0; i < 9; i++) {
             for(int j = 0; j < 9; j++) {
                 if(data[i][j] != 0) {
                     System.out.print(" " + data[i][j] + " ");
                 } else {
                     System.out.print("( )");
                 }
             }
             System.out.println();
         }
     }

     public static void print2(int[][] data,int[][] data2) {
       System.out.println("");
         for(int i = 0; i < 9; i++) {
             for(int j = 0; j < 9; j++) {
                 if(data2[i][j] != 0) {
                     System.out.print("(" + data[i][j] + ")");
                 } else {
                     System.out.print(" " + data[i][j] + " ");
                 }
             }
             System.out.println();
         }
     }
 }
}
