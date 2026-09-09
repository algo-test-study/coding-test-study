class TimeMap(object):

    def __init__(self):
        self.d = {}
        

    def set(self, key, value, timestamp):
        """
        :type key: str
        :type value: str
        :type timestamp: int
        :rtype: None
        """
        if key not in self.d:
            self.d[key] = []

        self.d[key].append((timestamp, value))

    def get(self, key, timestamp):
        """
        :type key: str
        :type timestamp: int
        :rtype: str
        """
        if key not in self.d:
            return ""

        values = self.d[key]

        start = 0
        end = len(values) - 1
        answer = ""

        while start <= end:
            mid = (start + end) // 2
            mid_time, mid_value = values[mid]

            if mid_time <= time:
                answer = mid_value
                start = mid + 1
            else:
                end = mid - 1

        return answer


# Your TimeMap object will be instantiated and called as such:
# obj = TimeMap()
# obj.set(key,value,timestamp)
# param_2 = obj.get(key,timestamp)
