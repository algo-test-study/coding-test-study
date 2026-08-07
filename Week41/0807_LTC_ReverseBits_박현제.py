class Solution:
    def reverseBits(self, n: int) -> int:
        # return int(format(n, "032b")[::-1], 2)
        return int(f"{n:032b}"[::-1], 2)
