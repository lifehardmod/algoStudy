import java.util.*;
import java.io.*;

//Boj_16918_봄버맨
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

    static int R, C, N;
    static int[][] map;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                if (str.charAt(j) == 'O') {
                    map[i][j] = 2;
                } else {
                    map[i][j] = -1;
                }
            }
        }

        int time = 1;
        while (time < N) {
            time++;

            if (time % 2 == 0) {
                set();
            } else {
                count();
            }
        }

        print();
    }

    //map을 StringBuilder에 올려서 출력하는 메서드
    static void print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) {
                    sb.append('.');
                } else {
                    sb.append('O');
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    //폭탄 폭발 및 카운트 메서드
    static void count() {
        Queue<Point> bom = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1) {
                    bom.offer(new Point(i, j));
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    map[i][j]--;
                }
            }
        }

        for (Point p : bom) {
            map[p.i][p.j] = -1;
            for (int d = 0; d < 4; d++) {
                int ni = p.i + di[d];
                int nj = p.j + dj[d];

                if (ni < 0 || nj < 0 || ni >= R || nj >= C) continue;
                if (map[ni][nj] != 1) {
                    map[ni][nj] = -1;
                }
            }
        }
    }

    //전 맵 폭탄 설치
    static void set() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) {
                    map[i][j] = 3;
                } else {
                    map[i][j]--;
                }
            }
        }
    }
}
