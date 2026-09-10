class Solution(object):
    def solveNQueens(self, n):
        """
        :type n: int
        :rtype: List[List[str]]
        """

        answer = []

        col = set()
        set1 = set()
        set2 = set()

        grid = [["."] * n for _ in range(n)]

        def dfs(i):
            if i == n:
                answer.append(["".join(r) for r in grid])
                return

            for c in range(n):

                if c in col or i - c in set1 or i + c in set2:
                    continue

                grid[i][c] = "Q"
                
                col.add(c)
                set1.add(i - c)
                set2.add(i + c)

                dfs(i + 1)

                grid[i][c] = "."

                col.remove(c)

                set1.remove(i - c)
                set2.remove(i + c)

        dfs(0)

        return answer
