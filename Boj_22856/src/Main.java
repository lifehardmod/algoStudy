import java.io.*;
import java.util.*;

// BOJ 22856: 트리 순회
public class Main {

    // 트리의 각 노드를 저장하는 클래스 (왼쪽, 오른쪽 자식 노드 번호)
    static class Node {
        int left, right;
        Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static int rst; // 총 이동 횟수
    static HashMap<Integer, Node> ChildMap;    // 각 노드의 자식 정보
    static HashMap<Integer, Integer> ParentMap; // 각 노드의 부모 정보
    static int last; // 중위 순회에서 마지막으로 방문한 노드

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ChildMap = new HashMap<>();
        ParentMap = new HashMap<>();

        // 트리 정보 입력 받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            ChildMap.put(P, new Node(L, R));
            if (L != -1) ParentMap.put(L, P);
            if (R != -1) ParentMap.put(R, P);
        }

        last = -1;
        search(1); // 루트에서 순회 시작

        // 마지막 노드에서 루트로 돌아가는 경로는 제외
        if (last != -1) {
            while (last != 1) {
                last = ParentMap.get(last);
                rst--;
            }
        }

        // 최종 이동 횟수 출력
        System.out.println(rst);
    }

    // 중위 순회 + 이동 시뮬레이션
    static void search(int searchN) {
        Node Child = ChildMap.get(searchN);
        int LeftChild = Child.left;
        int RightChild = Child.right;

        if (LeftChild != -1) {
            rst++;               // 현재 → 왼쪽 자식
            search(LeftChild);
            rst++;               // 왼쪽 자식 → 현재
        }

        last = searchN;          // 중위 순회 방문 시점 기록

        if (RightChild != -1) {
            rst++;               // 현재 → 오른쪽 자식
            search(RightChild);
            rst++;               // 오른쪽 자식 → 현재
        }
    }
}
