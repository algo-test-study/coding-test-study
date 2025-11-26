def get_multiset(str1):
    multiset = dict()
    str1 = str1.lower()
    
    for i in range(len(str1)-1):
        word = ''.join(str1[i:i+2])
        if word.isalpha():
            multiset[word] = multiset.get(word, 0) + 1
    return multiset
    
def solution(str1, str2):
    answer = 0
    multiset1, multiset2 = get_multiset(str1), get_multiset(str2)
    
    if not multiset1 and not multiset2:
        return 65536
    
    union = set(multiset1.keys()) | set(multiset2.keys())
    intersect = set(multiset1.keys()) & set(multiset2.keys())

    intersect_num, union_num = 0, 0
    
    for i in intersect:
        intersect_num += min(multiset1[i], multiset2[i])
    for u in union:
        union_num += max(multiset1.get(u, 0), multiset2.get(u, 0))

    return intersect_num*65536 // union_num
