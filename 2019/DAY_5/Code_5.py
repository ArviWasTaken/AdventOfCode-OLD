file = open("PuzzleInput_5.txt", "r")
line = file.readline()
intcode = line.split(",")


memory = []
instruction_pointer = 0

for x in range(len(intcode)):
    memory.append(int(intcode[x]))


while True:
    opcode = memory[instruction_pointer]


    #-------------------OPERATIONS---------------------#

    # END
    if opcode == 99:
        break
    parameters_1 = memory[instruction_pointer + 1]
    parameters_2 = memory[instruction_pointer + 2]
    adrress = memory[instruction_pointer + 3]

    # ADD instruction
    if opcode == 1:
        memory[adrress] = memory[parameters_1] + memory[parameters_2]
        instruction_pointer += 4

    # MULTIPLY instruction
    elif opcode == 2:
        memory[adrress] = memory[parameters_1] * memory[parameters_2]
        instruction_pointer += 4

print(memory[0])
