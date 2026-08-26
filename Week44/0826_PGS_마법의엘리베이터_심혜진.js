function solution(storey) {
    let answer = 0;
    let n = storey;

    while (n > 0) {
        const digit = n % 10;
        const next = Math.floor(n / 10);

        if (digit > 5) {
            answer += 10 - digit;
            n = next + 1;
        } else if (digit < 5) {
            answer += digit;
            n = next;
        } else {
            const nextDigit = next % 10;
            if (nextDigit >= 5) {
                answer += 10 - digit;
                n = next + 1;
            } else {
                answer += digit;
                n = next;
            }
        }
    }

    return answer;
}
