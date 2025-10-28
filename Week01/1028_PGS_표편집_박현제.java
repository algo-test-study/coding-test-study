/*
시도 1. 구현 > 효율성 테스트 시간 초과

문제
U X: 행번호 -+ x
D X: 행번호 += x
C: 삭제
Z: 복구 - 행은 동일

알고리즘
- stk: 삭제 idx > 스택에 저장
- removed : 삭제되었는지 여부
*/
import java.util.*;
class Solution {
    static Stack<Integer> stk = new Stack<>();
    static boolean [] removed;
    static int n;
    static int nowIdx;
    public String solution(int n, int k, String[] cmd) {
        this.n = n;
        removed = new boolean [n];
        nowIdx = k;
        for(String now : cmd){
            String [] str = now.split(" ");

            String oper = str[0];
            if(oper.equals("C")){
                cmdC();
            }else if(oper.equals("Z")){
                cmdZ();
            }else{
                int x = Integer.parseInt(str[1]);
                if(oper.equals("U")){
                    cmdU(x);
                }else{
                    cmdD(x);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            if(removed[i]){
                sb.append("X");
            }else{
                sb.append("O");
            }
        }
        
        return sb.toString();
    }
    
    public static void cmdC(){
        removed[nowIdx] = true;
        stk.add(nowIdx);
        
        if(!hasNext()) cmdU(1);
        else cmdD(1);
    }
    
    public static boolean hasNext(){
        if(nowIdx == n-1) return false;
        for(int i=nowIdx+1; i<n; i++){
            if(!removed[i]) return true;
        }
        return false;
    }
    
    public static void cmdZ(){
        removed[stk.pop()] = false;
    }
    
    public static void cmdU(int x){
        int cnt = 0;
        for(int i=nowIdx-1; i>=0; i--){
            if(!removed[i]){
                cnt++;
                if(cnt == x){
                    nowIdx = i;
                    break;
                }
            }
        }
    }
    
    public static void cmdD(int x){
        int cnt = 0;
        for(int i=nowIdx+1; i<n; i++){
            if(!removed[i]){
                cnt++; 
                if(cnt == x){     
                    nowIdx=i;
                    break;
                }
            }           
        }
    }
}

/*
2. 연결 리스트
- 정답 참고함
*/

import java.util.*;

class Solution {
    static Stack<Integer> stk = new Stack<>();
    static boolean [] removed;
    static int [] prev;
    static int [] next;
    static int now;
    static int n;
        
    public String solution(int n, int k, String[] cmd) {
        prev = new int[n];
        next = new int[n];
        removed = new boolean[n];
        this.n = n;
        
        for(int i=0; i<n; i++){
            prev[i] = i-1;
            next[i] = i+1;
        }
        
        now = k;
        next[n-1] = -1;
        
        
        for(String now : cmd){
            String [] str = now.split(" ");

            String oper = str[0];
            if(oper.equals("C")){
                cmdC();
            }else if(oper.equals("Z")){
                cmdZ();
            }else{
                int x = Integer.parseInt(str[1]);
                if(oper.equals("U")){
                    cmdU(x);
                }else{
                    cmdD(x);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            if(removed[i]){
                sb.append("X");
            }else{
                sb.append("O");
            }
        }
        
        return sb.toString();
    }
    
    public static void cmdC(){
        stk.add(now);
        removed[now] = true;
        
        if(prev[now]!=-1){
            next[prev[now]] = next[now];
        }
        
        if(next[now]!=-1){
            prev[next[now]] = prev[now];
        }
        
        int temp = now;
        
        if(next[now] != -1){
            now=next[now];
        }else{
            now=prev[now];
        }
    }
    
    public static void cmdZ(){
        int reIdx = stk.pop();
        removed[reIdx] = false;
        
        if(prev[reIdx] != -1){
            next[prev[reIdx]] = reIdx;
        }
        
        if(next[reIdx] != -1){
            prev[next[reIdx]] = reIdx;
        }
            
    }
    
    public static void cmdU(int x){
        for(int i=0; i<x; i++){
            now = prev[now];
        }
    }
    
    public static void cmdD(int x){
        for(int i=0; i<x; i++){
            now = next[now];
        }
    }
    
}
