file = open("PuzzleInput_14.txt", "r")

mask = ""
mem = {}
for x in file.readlines():
    x = x.strip()
    x = x.split(" = ")
    if x[0] == "mask":
        mask = x[1]
    elif x[0][:3] == "mem":
        mem_key = x[0][4:-1]
        value_p2 = bin(int(x[1]))[2:]
        value_p1 = "0" * (36 - len(value_p2))
        value = value_p1 + value_p2
        index = -1
        for x in mask:
            index += 1
            if x == "X":
                continue
            else:
                p1 = value[:index]
                p2 = value[index+1:]
                value = p1 + x + p2

        mem[mem_key] = int(value, 2)

som = 0
for x in mem.values():
    som += x
print(som)
