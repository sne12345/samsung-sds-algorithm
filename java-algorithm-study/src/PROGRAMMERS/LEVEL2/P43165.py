# think about the way to not use recursion.

def recur(cur_sum, idx, numbers, target):
    if idx == len(numbers):
        # print(cur_sum)
        if cur_sum == target:
            return 1
        else:
            return 0
        
    cnt_plus = recur(cur_sum+numbers[idx], idx+1, numbers, target)
    cnt_minus = recur(cur_sum-numbers[idx], idx+1, numbers, target)
    
    return cnt_plus + cnt_minus
    

def solution(numbers, target):
    answer = recur(0, 0, numbers, target)
    
    return answer