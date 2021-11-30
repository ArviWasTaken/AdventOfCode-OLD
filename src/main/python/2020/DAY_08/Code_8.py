def read():

    file = open("PuzzleInput_8.txt", "r")

    dict = {}
    line_count = 0
    for x in file.readlines():
        x = x.strip()
        x = x.split(" ")
        x[1] = int(x[1])
        line = [x[0], x[1], False]
        dict[line_count] = line
        line_count += 1
    file.close()
    return dict

def run(dict):
    pointer = 0
    accumaltor = 0
    while True:
        if pointer not in dict.keys():
            return accumaltor, True

        op = dict[pointer]

        if op[2]:
            return accumaltor, False


        elif op[0] == "acc":
            accumaltor += op[1]
            pointer += 1

        elif op[0] == "jmp":

            pointer += op[1]

        elif op[0] == "nop":
            pointer += 1
        op[2] = True


dict = read()
for x in dict.keys():
    op = dict[x]

    if op[0] == "jmp":
        op[0] = "nop"

    elif op[0] == "nop":
        op[0] = "jmp"

    test = run(dict)
    if test[1]:
        print(test[0])

    dict = read()
