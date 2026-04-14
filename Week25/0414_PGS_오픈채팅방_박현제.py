
"""
해시, 구현, O(len(recode))
"""
from collections import defaultdict
def solution(record):
    
    ans = []
    names = dict()
    
    for s in record:
        l = s.split()
        oper, uid = l[0], l[1]
        
        if oper != "Leave":
            name = l[2]
            names[uid] = name
    
    for s in record:
        l = s.split()
        oper, uid = l[0], l[1]        
        if oper == "Change":
            continue

        if oper == "Enter":
            ans.append(f"{names[uid]}님이 들어왔습니다.")
        elif oper == "Leave":
            ans.append(f"{names[uid]}님이 나갔습니다.")

    
    return ans
