var longestPalindrome = function(s) {
    const count = new Map();

    for (const char of s) {
        count.set(char, (count.get(char) || 0) + 1);
    }

    let answer = 0;
    let hasOdd = false;

    for (const value of count.values()) {
        answer += Math.floor(value / 2) * 2;

        if (value % 2 === 1) {
            hasOdd = true;
        }
    }

    if (hasOdd) {
        answer += 1;
    }

    return answer;
};