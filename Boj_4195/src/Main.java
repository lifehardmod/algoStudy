import java.util.*;
import java.io.*;

// BOJ 4195 - 친구 네트워크
public class Main {

    // parent: 각 사람의 대표 노드를 저장하는 맵
    static HashMap<String, String> parent;

    // rank: 각 네트워크의 크기를 저장하는 맵
    static HashMap<String, Integer> rank;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < TC; tc++) {

            int N = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            rank = new HashMap<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String A = st.nextToken();
                String B = st.nextToken();

                if (!parent.containsKey(A)) {
                    parent.put(A, A);
                    rank.put(A, 1);
                }

                if (!parent.containsKey(B)) {
                    parent.put(B, B);
                    rank.put(B, 1);
                }

                union(A, B);
                sb.append(rank.get(find(A))).append("\n");
            }
        }

        System.out.println(sb);
    }

    // find: 해당 노드의 루트(parent)를 찾는 연산 (경로 압축 포함)
    // 경로 압축을 통해 트리의 깊이를 줄여 이후 find 연산의 속도를 향상시킴
    static String find(String str) {
        if (!str.equals(parent.get(str))) {
            parent.put(str, find(parent.get(str)));
        }
        return parent.get(str);
    }

    // union: 두 집합을 병합하는 연산
    // 네트워크 크기(rank)를 비교하여 더 큰 쪽을 루트로 삼음으로써 트리 균형을 유지하고,
    // 병합 후 루트 노드의 rank를 갱신하여 전체 네트워크 크기를 유지함
    static void union(String A, String B) {
        String AP = find(A);
        String BP = find(B);

        if (!AP.equals(BP)) {
            if (rank.get(AP) >= rank.get(BP)) {
                parent.put(BP, AP);
                rank.put(AP, rank.get(AP) + rank.get(BP));
            } else {
                parent.put(AP, BP);
                rank.put(BP, rank.get(AP) + rank.get(BP));
            }
        }
    }
}
