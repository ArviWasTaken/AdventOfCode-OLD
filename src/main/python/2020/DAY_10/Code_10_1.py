def read():

    file = open("PuzzleInput_10.txt", "r")

    lst = []
    for x in file.readlines():
        x = x.strip()
        x = int(x)
        lst.append(x)
    file.close()
    return lst


lst = read()
lst.append(0)
lst.sort()

lst.append(max(lst)+ 3)

diff_1 = 0
diff_3 = 0

for x in range(len(lst)-1):
    if lst[x+1] - lst[x] == 1:
        diff_1 += 1
    if lst[x+1] - lst[x] == 3:
        diff_3 += 1

print(diff_1 * diff_3)

