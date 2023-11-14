
def solution(cap, n, deliveries, pickups):
    answer = 0
    cur_del, cur_pick = 0, 0
    for i in range(n-1,-1,-1):
        cur_del += deliveries[i]
        cur_pick += pickups[i]
        # print(cur_del, cur_pick)
        
        # 음수만큼 더 배달할수있음
        while cur_del > 0 or cur_pick > 0:
            cur_del -= cap
            cur_pick -= cap
            answer += (i+1) * 2
                
    return answer