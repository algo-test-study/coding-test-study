"""
(a, b, c) 나눠 입력받아 정렬
이후에 다시 붙여서 answer에 넣는다.

정렬, O(nlogn)

"""
def solution(files):
    answer = []
    
    strs = []
    
    def get_idx(s):
        idx = 0
        k = len(s)
        a, b = 0, k
        while idx < k:
            if s[idx].isdigit():
                a = idx
                break
            idx += 1
            
        while idx < k:
            if not s[idx].isdigit():
                b = idx
                break
            idx += 1
        
        
        return a, b
                
    
    for f in files:
        a, b = get_idx(f)
        head = f[0:a]
        number = f[a:b]
        tail = f[b:]
        
        strs.append((head, number, tail))
        
    strs.sort(key=lambda x: (x[0].lower(), int(x[1])))
    
    for s in strs:
        answer.append("".join(s))
        
    return answer
