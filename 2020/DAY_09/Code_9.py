def read():

    file = open("PuzzleInput_9.txt", "r")

    dict = []
    line_count = 0
    for x in file.readlines():
        x = x.strip()
        x = int(x)
        dict.append(x)
        line_count += 1
    file.close()
    return dict


dict = read()

for x in range(25, len(dict)):
    check = False
    for y in dict[x-25: x]:
        if dict[x] - y in dict[x-25: x]:
            check = True
    if not check:
        invalid_number = dict[x]

index_number = 0
index_check = 0
answer = []
answer_fnd = True
while answer_fnd:
    check_sum = 0
    index_check = index_number
    while True:
        if check_sum == invalid_number:
            for x in dict[index_number:index_check]:
                answer.append(x)
            answer_fnd = False

            break
        if check_sum > invalid_number:
            break
        if index_check == len(dict):
            break
        check_sum += dict[index_check]
        index_check += 1
    index_number += 1

print(max(answer) + min(answer))

