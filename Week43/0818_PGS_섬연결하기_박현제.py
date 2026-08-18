"""
n 노드, 최소 비용
노드를 연결하는데 드는 최소 비용 => mst
인접 리스트로 주어짐 -> 크루스칼

O(mlogm)
"""

def solution(n, costs):
    
    root = list(range(n))
    
    def union(a, b):
        a = find(a)
        b = find(b)
        if a != b:
            root[a] = b
            
    def find(a):
        if root[a] == a:
            return a
        
        root[a] = find(root[a])
        
        return root[a]
    
    costs.sort(key=lambda x : x[2])
    
    cnt = 0
    min_v = 0
    for a, b, w in costs:
        if find(a) != find(b):
            union(a, b)
            cnt += 1
            min_v += w
        if cnt == n - 1:
            break
            
    return min_v
