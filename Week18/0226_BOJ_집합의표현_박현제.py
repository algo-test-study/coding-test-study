"""
알고리즘
- 유니온 파인드
- root[0-n] -> 각각의 연결된 노드 루트 써넣기
- 합집합 - union
- 탐색 - find
시간복잡도
- O(n+m)
"""
import sys


# sys.stdin = open('input.txt', 'r')

input = sys.stdin.readline
n, m = map(int, input().split())

root = [i for i in range(n+1)]

def union(a, b):
    a = find(a)
    b = find(b)

    root[b] = a

def find(x):
    if root[x] != x:
        root[x] = find(root[x])

    return root[x]

for _ in range(m):
    oper, a, b = map(int, input().split())
    if oper == 0:
        union(a, b)
    else:
        if find(a) == find(b):
            print("YES")
        else:
            print("NO")
