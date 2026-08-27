"""
O(4^7 × 100 × 7)
"""
def solution(users, emoticons):
    answer = []
    
    n = len(users)
    m = len(emoticons)
    
    people, total_cost = 0, 0
    
    num = []
    def perm(depth):
        nonlocal people, total_cost
        
        if depth == m:    
            curr_people = 0
            curr_cost = 0

            for user_percent, user_cost in users:
                total = 0
                
                for i in range(m):
                    if num[i] >= user_percent:
                        total += emoticons[i] * (100 - num[i]) // 100
                
                if total >= user_cost:
                    curr_people += 1
                else:
                    curr_cost += total
            
            if curr_people > people:
                people = curr_people
                total_cost = curr_cost
            
            elif curr_people == people:
                total_cost = max(total_cost, curr_cost)
            
            return
        
        for i in range(10, 41, 10):
            num.append(i)
            perm(depth + 1)
            num.pop()
    perm(0)
    return [people, total_cost]
