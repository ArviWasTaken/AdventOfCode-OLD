import re

file = open("PuzzleInput_19.txt", "r")

dict = {}
messages = ""

for line in file.readlines():
    line = line.strip()
    if ":" in line:
        rule_num = line[:line.index(":")]
        line = line.split(" ")
        if len(line) == 6:
            a = [line[1], line[2]]
            b = [line[4], line[5]]
            c = [a, b]
        elif len(line) == 4:
            if line[2] == "|":
                a = [line[1]]
                b = [line[3]]
                c = [a, b]
            else:
                c = [line[1], line[2], line[3]]
        elif len(line) == 3:
            a = [line[1], line[2]]
            c = [a]
        else:
            a = [line[1]]
            c = [a]

        dict[rule_num] = c

    else:
        break

file.close()


def functie(rule):
    values = dict[rule]
    print(values)
    str = ""
    length = len(values)
    str += "(?:"
    for x in values:
        for y in x:
            if y == "\"a\"" or y == "\"b\"":
                str += y[1]
            else:
                str += functie(y)
        if length == 2:
            str += "|"
            length -= 1
    str += ")"
    return str


regex = "^" + functie("0") + "$"

file = open("PuzzleInput_19.txt", "r")
count = 0
for line in file.readlines():
    line = line.strip()
    if re.compile(regex).match(line):
        count += 1
print(count)
