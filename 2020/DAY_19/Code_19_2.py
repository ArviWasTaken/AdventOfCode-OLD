import re
import time
start_time = time.time()

file = open("PuzzleInput_19.txt", "r")

dict = {}
messages = ""

for line in file.readlines():
    line = line.strip()
    if ":" in line:
        rule_num = line[:line.index(":")]
        if rule_num == "8":
            c = [["42"], ["42", "2000"]]
            dict[rule_num] = c
        elif rule_num == "11":
            c = [["42", "31"], ["42", "1000", "31"]]
            dict[rule_num] = c
        else:
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
recursie_counter_8 = 0
recursie_counter_11 = 0


def functie(rule):
    values = dict[rule]
    str = ""
    length = len(values)
    str += "(?:"
    global recursie_counter_8, recursie_counter_11
    for x in values:

        for y in x:
            if y == "2000" and recursie_counter_8 <= 100:
                recursie_counter_8 += 1
                str += functie("8")
            elif y == "1000" and recursie_counter_11 <= 100:
                recursie_counter_11 += 1
                str += functie("11")
            elif y == "\"a\"" or y == "\"b\"":
                str += y[1]
            elif y == "2000" or y == "1000":
                continue
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
