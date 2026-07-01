"""
n개 섬
가장 작은 비용, 모든 점 연결 
시작점 x

MST
1. 프림 -> 특정 노드에서 시작해서 가장 작은 값을 택한다. (인접리스트로 변경 해야함)
2. 크루스칼 -> 작은 간선부터 고른다. Union-find

t: O(mlogm + m) = O(mlogm)
"""
def solution(n, costs):
    answer = 0
    
    root = [i for i in range(n)]
    
    def find(x):
        if root[x] == x:
            return x

        root[x] = find(root[x])
        return root[x]
    
    def union(x, y):
        x = find(x)
        y = find(y)
        
        root[x] = y
    
    costs.sort(key=lambda x: x[2])
    
    for a, b, w in costs:
        
        if find(a) != find(b):
            union(a, b)
            answer += w
    
    return answer
