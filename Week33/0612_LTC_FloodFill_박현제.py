class Solution(object):
    def floodFill(self, image, sr, sc, color):
        """
        :type image: List[List[int]]
        :type sr: int
        :type sc: int
        :type color: int
        :rtype: List[List[int]]
        """
        n = len(image)
        m = len(image[0])

        dx = [-1,1,0,0]
        dy = [0,0,-1,1]
        start = image[sr][sc]

        if start == color:
            return image

        def dfs(cx, cy):

            image[cx][cy] = color

            for i in range(4):
                nx, ny = cx + dx[i], cy + dy[i]

                if 0 <= nx < n and 0 <= ny < m and start == image[nx][ny]:
                    dfs(nx, ny)

        dfs(sr, sc)


        return image

