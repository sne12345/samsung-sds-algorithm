def solution(people, limit):
    answer = 0
    
    people.sort(reverse=True)
    ll, rr = 0, len(people)-1
    
    while ll <= rr:
        if people[ll] + people[rr] <= limit:
            rr -= 1
            
        ll += 1
        answer += 1
    
    return answer