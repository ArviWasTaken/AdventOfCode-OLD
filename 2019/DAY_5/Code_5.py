file = open("PuzzleInput_2.txt", "r")
line = file.readline()
line = "3,0,4,0,99"
intcode = line.split(",")


memory = []
instruction_pointer = 0

for x in range(len(intcode)):
    memory.append(int(intcode[x]))



while True:
    opcode = memory[instruction_pointer]

    # END
    if opcode == 99:
        break

    #-------------------OPERATIONS---------------------#


    # ADD instruction
    if opcode == 1:

        parameters_1 = memory[instruction_pointer + 1]
        parameters_2 = memory[instruction_pointer + 2]
        adrress = memory[instruction_pointer + 3]

        memory[adrress] = memory[parameters_1] + memory[parameters_2]
        instruction_pointer += 4

    # MULTIPLY instruction
    elif opcode == 2:
        parameters_1 = memory[instruction_pointer + 1]
        parameters_2 = memory[instruction_pointer + 2]
        adrress = memory[instruction_pointer + 3]

        memory[adrress] = memory[parameters_1] * memory[parameters_2]
        instruction_pointer += 4

    #INPUT instruction
    elif opcode == 3:
        parameters_1 = memory[instruction_pointer + 1]

        memory[parameters_1] = input("Geef input:")
        instruction_pointer += 2

    #OUTPUT instruction
    elif opcode == 4:
        parameters_1 = memory[instruction_pointer + 1]

        print(memory[parameters_1])
        instruction_pointer += 2

