/*
가장 큰 점수 차이로 라이언이 이길 때의 과녁 조합
비기거나 지면 -1

알고리즘
- 모든 과녁 선택 중복 조합 구하기
- n개 과녁을 선택했을 시 점수 구하기. 어피치 < 라이언 -> 최대 점수일때 갱신
- 갱신을 한번도 하지 못했을 경우 -1 유지됨

시간복잡도
- O(11Hn)
*/
class Solution {
    static int max = -1;
    static int [] ans = new int [11];
    public int[] solution(int n, int[] info) {

        dfs(n, info, new int [11], 0, 0);
        
        if(max != -1){
            return ans;
        }
        
        return new int [] {-1};
    }
    
    public static void dfs(int n, int[] a, int [] r, int at, int depth){
        if(depth == n){
            int curr = getScore(a, r);
            
            if(curr != -1 && max <= curr){
                boolean isValid = true;
                if(max == curr){
                    for(int i=10; i>=0; i--){
                        if(r[i] > ans[i]){
                            break;
                        }
                        if(r[i] < ans[i]){
                            isValid = false;
                            break;
                        }
                    }
                }
                
                if(isValid) {
                    ans = r.clone();
                    max = curr;
                }
            }
            return;
        }
        
        for(int i=at; i<=10; i++){
            r[i]++;
            dfs(n, a, r, i, depth + 1);
            r[i]--;
        }
    }
    
    public static int getScore(int [] a, int [] r){
        int aScore = 0;
        int rScore = 0;
        for(int i=0; i<=10; i++){
            if(a[i] == 0 && r[i] == 0) continue;
            
            if(a[i] < r[i]){
                rScore += 10-i;
            }else{
                aScore += 10-i;
            }
        }
        
        if(aScore < rScore){
            return rScore - aScore;
        }
        return -1;
    }
}
