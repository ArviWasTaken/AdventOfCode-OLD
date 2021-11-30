input = [0,3,1,6,7,5]


dict = {}
for x in input:
    dict[x] = input.index(x) + 1

turn = len(input)

prv_nm = input[-1]

while turn < 30000000:
    if prv_nm not in dict.keys():
        verschil = 0
    else:
        verschil = turn - dict[prv_nm]

    dict[prv_nm] = turn
    turn += 1
    prv_nm = verschil

print(verschil)