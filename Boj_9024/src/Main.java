import java.util.*;
import java.io.*;

// BOJ 9024 - 두 수의 합
public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < TC; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] map = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                map[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(map);  // O(N log N) 20,000,000

            int left = 0;
            int right = n - 1;

            int min = Integer.MAX_VALUE;  // 현재까지 발견된 최소 차이
            int num = 0;                  // 최소 차이를 갖는 쌍의 개수

            // 투포인터 탐색: O(N)
            while (left < right) {
                int sum = map[left] + map[right];
                int m = Math.abs(K - sum);

                if (m < min) {
                    min = m; // 차이 갱신
                    num = 1; // 개수 초기화
                } else if (m == min) {
                    num++;
                }

                if (sum < K) {
                    left++;
                } else {
                    right--;
                }
            }

            sb.append(num).append('\n');
        }

        System.out.println(sb);
    }
}
