from collections import deque

dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]
    
def solution(maps):
    N = len(maps[0]) 
    M = len(maps) 
    visited = [[0 for _ in range(N)] for _ in range(M)]
    visited[0][0] = 1
    
    queue = deque()
    queue.append((0, 0))
    while len(queue) :
        r, c = queue.popleft()
        for i in range(4) :
            nr, nc = r + dr[i], c + dc[i]
            if nr < 0 or nr >= M or nc < 0 or nc >= N :
                continue
            if maps[nr][nc] == 1 and visited[nr][nc] == 0:
                visited[nr][nc] = visited[r][c] + 1
                queue.append((nr, nc))
        
    if visited[M-1][N-1] == 0 :
        return -1
    return visited[M-1][N-1]
