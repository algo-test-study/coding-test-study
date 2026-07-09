# class MedianFinder:

#     def __init__(self):
#         self.values = []
        

#     def addNum(self, num: int) -> None:
#         self.values.append(num)

#     def findMedian(self) -> float:
#         n = len(self.values)
#         arr = sorted(self.values)
#         if n%2 == 1:
#             return arr[n//2]
#         return (arr[n//2-1] + arr[n//2]) / 2

        


# # Your MedianFinder object will be instantiated and called as such:
# # num = 4
# # obj = MedianFinder()
# # obj.addNum(num)
# # param_2 = obj.findMedian()
import heapq
class MedianFinder:

    def __init__(self):
        self.right = []
        self.left = []
        

    def addNum(self, num: int) -> None:
        heapq.heappush(self.left, -num)
        
        if self.right and -self.left[0] > self.right[0]:
            v = -heapq.heappop(self.left)
            heapq.heappush(self.right, v)
        


        if len(self.left) < len(self.right):
            v = heapq.heappop(self.right)
            heapq.heappush(self.left, -v)
            
        if len(self.left) > len(self.right) + 1:
            v = -heapq.heappop(self.left)
            heapq.heappush(self.right, v)

    def findMedian(self) -> float:

        if len(self.left) > len(self.right):
            return -self.left[0]
        return (-self.left[0] + self.right[0]) / 2
