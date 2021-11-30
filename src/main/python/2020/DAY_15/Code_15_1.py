input = [0,3,1,6,7,5]

indx = len(input) - 1

turn = len(input)
last_nm = input[-1]

while turn != 2020:
    prv_nm = input[indx]
    turn += 1
    if input.count(prv_nm) == 1:
        cur_nm = 0

    else:
        rever = input.copy()
        rever.reverse()

        rever.remove(prv_nm)
        furthest_nm = rever.index(prv_nm) + 1

        cur_nm = furthest_nm

    input.append(cur_nm)
    indx += 1

print(input[-1])