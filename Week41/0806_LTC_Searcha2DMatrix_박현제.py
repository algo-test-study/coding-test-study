"""
O(log(m*n)) 안에 풀어야 함.

전체 행렬을 1차원 배열 처럼 펼쳐서 이분 탐색
-> 마지막까지 값을 찾지 못하면 false
-> 찾으면 true
"""
class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:

        m = len(matrix)
        n = len(matrix[0])

        s, e = 0, m*n - 1
        
        while s <= e:
            mid = (s + e) // 2

            i = mid // n
            j = mid % n

            if target < matrix[i][j]:
                e = mid - 1
            elif target > matrix[i][j]:
                s = mid + 1
            else:
                return True
        return False
                    
                    



        
        
