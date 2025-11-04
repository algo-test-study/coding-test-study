/*
- k개 접시 연속 > 할인
- 1번 행사 참가 > 초밥 무료 1
최대 초밥 가짓수

알고리즘
- 완전탐색 X: O(n*k + d*n) 
- 투포인터 - 슬라이딩 윈도우: k가 범위로 고정. 시작, 끝 인덱스 늘림
- 이때 각 시행에서 초밥의 종류가 몇개인지도 셈: Map<초밥의 종류, 갯수>
- 초밥 갯수가 0이면 아예 map에서 삭제하기
- c의 유무 확인: 존재 안하면 맵 size++
- size 중 max가 답

시간복잡도
- O(n)
*/

import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));     
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int [] arr = new int [n];

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0; i<k; i++){
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        int max = map.size();
        if(!map.containsKey(c)) max++;

        int e = k-1;
        for(int s=0; s<n; s++){
            int start = arr[s];
            
            if(map.get(start) == 1){
                map.remove(start);
            }else{
                map.put(start, map.get(start) - 1);    
            }

            e = (e + 1)%n;
            int end = arr[e];
            map.put(end, map.getOrDefault(end, 0) + 1);

            int curr = map.size();

            if(!map.containsKey(c)) curr++;

            max = Math.max(max, curr);
        }

        System.out.println(max);
    }
}
