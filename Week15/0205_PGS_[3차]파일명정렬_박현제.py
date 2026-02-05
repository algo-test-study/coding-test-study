"""
알고리즘
1. 분리
2. 파싱
3. 정렬

시간복잡도
O(files*file_n)
"""
def solution(files):
    result = []
    
    for file in files:
        head, number, tail = '', '', ''
        file_n = len(file)
        idx = 0
        
        while idx < file_n and not file[idx].isdigit():
            head += file[idx]
            idx += 1
            
        while idx < len(file) and file[idx].isdigit():
            number += file[idx]
            idx += 1
            
            if len(number) == 5:
                break
        
        tail = file[idx:]
    
        result.append((head, number, file))
    result.sort(key=lambda x : (x[0].lower(), int(x[1])))
    
    ans = [item[2] for item in result]
    return ans
