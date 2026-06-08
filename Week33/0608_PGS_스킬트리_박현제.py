"""
구현, O(nk)
"""
def solution(skill, skill_trees):
    answer = 0
    n = len(skill_trees)
    
    s = set(skill)
    
    for skill_tree in skill_trees:
        
        strs = []
        for i in skill_tree:
            if i in s:
                strs.append(i)

        if skill.startswith("".join(strs)):
            answer += 1
    
    return answer
