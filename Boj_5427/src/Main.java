import java.util.*;
import java.io.*;

//Boj_5427 불
public class Main {

    static class Point {
        int i, j;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        o:
        for (int tc = 0; tc < TC; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            char[][] map = new char[H][W];
            Queue<Point> qu = new ArrayDeque<>();
            Queue<Point> quu = new ArrayDeque<>();

            boolean[][] chk = new boolean[H][W];

            for (int i = 0; i < H; i++) {
                String str = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = str.charAt(j);

                    if (map[i][j] == '@') {
                        quu.add(new Point(i, j));
                        chk[i][j] = true;
                    }
                    if (map[i][j] == '*') {
                        qu.add(new Point(i, j));
                    }
                }
            }

            int Time = 0;
            while (!quu.isEmpty()) {

                Time++;

                int size = qu.size();
                for (int i = 0; i < size; i++) {
                    Point q = qu.poll();
                    for (int j = 0; j < 4; j++) {
                        int ii = q.i + di[j];
                        int jj = q.j + dj[j];

                        if (ii < H && jj < W && ii >= 0 && jj >= 0 && map[ii][jj] != '#' && map[ii][jj] != '*') {
                            map[ii][jj] = '*';
                            qu.add(new Point(ii, jj));
                        }
                    }
                }

                size = quu.size();
                for (int i = 0; i < size; i++) {
                    Point q = quu.poll();
                    for (int j = 0; j < 4; j++) {
                        int ii = q.i + di[j];
                        int jj = q.j + dj[j];

                        if (ii == H || jj == W || ii < 0 || jj < 0) {
                            sb.append(Time).append('\n');
                            continue o;
                        }

                        if (map[ii][jj] == '.' && !chk[ii][jj]) {
                            chk[ii][jj] = true;
                            quu.add(new Point(ii, jj));
                        }
                    }
                }

                if (quu.isEmpty()) {
                    sb.append("IMPOSSIBLE").append('\n');
                    continue o;
                }
            }
        }

        System.out.println(sb);
    }
}
