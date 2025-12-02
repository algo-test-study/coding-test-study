
"""
알고리즘
- user id : 이름 저장 -> dict : change / enter 경우
- 메시지 출력

시간복잡도
- O(2N) = O(N)
"""
def solution(record):
    ans = []
    users = dict()
    
    for v in record:
        info = v.split(" ")
        comd = info[0]
        uid = info[1]
        if comd != "Leave":
            name = info[2]
            users[uid] = name
        
    for v in record:
        info = v.split(" ")
        comd = info[0]
        uid = info[1]
        if comd == "Enter":
            ans.append(f"{users[uid]}님이 들어왔습니다.")
        elif comd == "Leave":
            ans.append(f"{users[uid]}님이 나갔습니다.")
    
    return ans
