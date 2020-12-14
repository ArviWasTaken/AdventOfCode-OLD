import itertools
file = open("PuzzleInput_14.txt", "r")

mask = ""
mem = {}
for x in file.readlines():
    x = x.strip()
    x = x.split(" = ")
    if x[0] == "mask":
        mask = x[1]
    elif x[0][:3] == "mem":
        value = x[1]
        mem_key_p2 = bin(int(x[0][4:-1]))[2:]
        mem_key_p1 = "0" * (36 - len(mem_key_p2))
        mem_key = mem_key_p1 + mem_key_p2
        index = -1
        X_index = []
        for x in mask:
            index += 1
            if x == "0":
                continue
            elif x == "1":
                p1 = mem_key[:index]
                p2 = mem_key[index+1:]
                mem_key = p1 + x + p2
            else:
                X_index.append(index)

        for i in itertools.product([0, 1], repeat=mask.count("X")):
            lijst_index = 0
            for x in range(mask.count("X")):
                p1 = mem_key[:X_index[lijst_index]]
                p2 = mem_key[X_index[lijst_index] + 1:]


                mem_key = p1 + str(i[lijst_index]) + p2

                mem[int(mem_key, 2)] = value
                lijst_index += 1


som = 0
for x in mem.values():
    som += int(x)
print(som)

