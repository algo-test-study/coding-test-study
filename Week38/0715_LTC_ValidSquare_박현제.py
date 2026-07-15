class Solution(object):
    def validSquare(self, p1, p2, p3, p4):
        """
        :type p1: List[int]
        :type p2: List[int]
        :type p3: List[int]
        :type p4: List[int]
        :rtype: bool
        """

        lst = [p1, p2, p3, p4]

        def get_dist(a, b):
            dx = a[0] - b[0]
            dy = a[1] - b[1]
            return dx * dx + dy * dy
        
        dist = []

        for i in range(4):
            for j in range(i + 1, 4):
                curr = get_dist(lst[i], lst[j])
                dist.append(curr)

        dist.sort()

        if dist[0] > 0 and dist[0] == dist[1] == dist[2] == dist[3] and dist[4] == dist[5]:
            return True
        return False

            
