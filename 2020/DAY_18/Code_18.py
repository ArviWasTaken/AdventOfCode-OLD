
file = open("PuzzleInput_18.txt", "r")

total_sum = 0

def simplify(line):
    level = 0
    inside = ""
    for char in line:
        if char == ")":
            level -= 1
            if level == 0:
                line = line.replace("(" + inside + ")", str(simplify(inside)))
                inside = ""
        if level >= 1:
            inside += char
        if char == "(":
            level += 1


    line = line.split()
    for _ in range((len(line) - 1) // 2):
        n1 = str(line[0])
        op = str(line[1])
        n2 = str(line[2])
        equat = n1 + op + n2
        value = eval(equat)
        line.pop(1)
        line.pop(1)
        line.insert(0, str(value))
        line.pop(1)

    if len(line) == 1:
        new_line = ""
        for x in line:
            new_line += str(x) + " "
        return new_line
    return 0


for line in file.readlines():
    total_sum += int(simplify(line))

print(total_sum)