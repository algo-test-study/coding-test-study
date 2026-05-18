# def solution(routes):
#     routes.sort()
#     n = len(routes)
#     cx, cy = routes[0]
#     cnt = 0
#     for i in range(1, n):
#         nx, ny = routes[i]
#         if cy < nx:
#             cnt += 1
#             cx, cy = nx, ny
#         else:
#             cx = max(cx, nx)
#             cy = min(cy, ny)
        
#     return cnt + 1

# 그리디, O(nlogn)
def solution(routes):
    routes.sort(key=lambda x : x[1])
    n = len(routes)
    cnt = 0
    camera = -30001
    for nx, ny in routes:
        if camera < nx:
            camera = ny
            cnt += 1
        
    return cnt
