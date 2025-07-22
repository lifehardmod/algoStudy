import java.util.*;
import java.io.*;

//Boj_17836_공주님을 구해라
public class Main {
    static class Point {
        int i, j;
        boolean Gram;
        int time;

        Point(int i, int j, boolean Gram, int time) {
            this.i = i;
            this.j = j;
            this.Gram = Gram;
            this.time = time;
        }
    }

    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    static int N, M, T;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Point> qu = new ArrayDeque<>();
        boolean[][] chk = new boolean[N][M];
        boolean[][] GramChk = new boolean[N][M];


        chk[0][0] = true;
        if (map[0][0] == 2) {
            GramChk[0][0] = true;
            qu.add(new Point(0, 0, true, 0));

        } else {
            qu.add(new Point(0, 0, false, 0));
        }

        while (!qu.isEmpty()) {

            Point q = qu.poll();

            for (int i = 0; i < 4; i++) {
                int ii = q.i + di[i];
                int jj = q.j + dj[i];

                if (ii < 0 || jj < 0 || ii == N || jj == M) {
                    continue;
                }
                if(!q.Gram && (chk[ii][jj] || map[ii][jj] == 1)){
                    continue;
                }
                if(q.Gram && GramChk[ii][jj]){
                    continue;
                }

                if (ii == N - 1 && jj == M - 1) {
                    if (q.time + 1 <= T) {
                        System.out.println(q.time+1);
                        return;
                    } else {
                        System.out.println("Fail");
                        return;
                    }
                }

                if (q.Gram) {
                    GramChk[ii][jj] = true;
                    qu.add(new Point(ii, jj, true, q.time + 1));
                } else {

                    if (map[ii][jj] == 2) {
                        GramChk[ii][jj] = true;
                        qu.add(new Point(ii, jj, true, q.time + 1));
                    } else {
                        chk[ii][jj] = true;
                        qu.add(new Point(ii, jj, false, q.time + 1));
                    }
                }
            }
        }
        System.out.println("Fail");
    }
}
