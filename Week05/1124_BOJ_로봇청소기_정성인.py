N, M = map(int, input().split())
r, c, d = map(int, input().split())
room = [list(map(int, input().split())) for _ in range(N)]

# 0: 북, 1: 동, 2: 남, 3: 서
dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]

cleaned = 0

while True:
    # 1. 현재 칸을 청소
    if room[r][c] == 0:
        room[r][c] = 2  # 청소 표시
        cleaned += 1

    moved = False

    # 2. 네 방향 탐색
    for _ in range(4):
        d = (d + 3) % 4  # 반시계 회전 (왼쪽)
        nr, nc = r + dr[d], c + dc[d]

        if room[nr][nc] == 0:  # 청소되지 않은 빈 칸
            r, c = nr, nc
            moved = True
            break

    if moved:
        continue

    # 3. 네 방향 모두 청소 or 벽 → 후진
    back_dir = (d + 2) % 4
    br, bc = r + dr[back_dir], c + dc[back_dir]

    if room[br][bc] == 1:  # 벽이면 종료
        break

    # 벽이 아니면 후진
    r, c = br, bc

print(cleaned)
