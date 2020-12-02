file = open("PuzzleInput_2.txt", "r")

passwords = {}
keys = 0
valid_pass = 0
for x in file.readlines():
    x = x.split(":")
    y = x[1]
    y = y.strip()
    x = x[0].split(" ")
    x[0] = x[0].split("-")
    passwords[keys] = [x, y]
    keys += 1


for x in passwords.values():
    letter = x[0][1]
    min = int(x[0][0][0])
    max = int(x[0][0][1])
    if (x[1][min - 1] == letter or x[1][max - 1] == letter) and x[1][min - 1] != x[1][max - 1]:
        valid_pass += 1
print(valid_pass)
