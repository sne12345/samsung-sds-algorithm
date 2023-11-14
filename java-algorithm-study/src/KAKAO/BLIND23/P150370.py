def solution(today, terms, privacies):
    answer = []
    today_y, today_m, today_d = int(today[0:4]), int(today[5:7]), int(today[8:])
    
    terms_dict = dict()
    for term in terms:
        terms_dict[term[0]] = int(term[2:])
        
    for idx, pri in enumerate(privacies):
        date, case = pri.split()
        date_y, date_m, date_d = int(pri[0:4]), int(pri[5:7]), int(pri[8:10])

        date_m += terms_dict[case]
        
        while date_m > 12:
            date_m -= 12
            date_y += 1
            
#         print(today_y, today_m, today_d)
#         print(date_y,date_m,date_d)
        
        if date_y > today_y :
            continue
        elif date_y == today_y :
            if date_m > today_m :
                continue
            elif date_m == today_m :
                if date_d > today_d :
                    continue
        answer.append(idx+1)

    return answer