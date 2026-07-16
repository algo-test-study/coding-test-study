class Solution(object):
    def fullJustify(self, words, maxWidth):
        """
        :type words: List[str]
        :type maxWidth: int
        :rtype: List[str]
        """
                
        result = []
        i = 0
        n = len(words)

        while i < n:
            j = i
            l = 0
            while j < n and l + len(words[j]) + (j - i) <= maxWidth:
                l += len(words[j])
                j += 1

            num_w = j - i
            num_s = maxWidth - l

            if j == n or num_w == 1:
                line = " ".join(words[i:j])
                line += " " * (maxWidth - len(line))
            else:
                gaps = num_w - 1
                base, extra = num_s // gaps, num_s % gaps
                parts = []
                for k in range(i, j):

                    parts.append(words[k])
                    if k < j - 1:
                        parts.append(" " * (base + (1 if (k - i) < extra else 0)))
                line = "".join(parts)

            result.append(line)
            i = j

        return result
