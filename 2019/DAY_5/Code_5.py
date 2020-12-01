file = open("PuzzleInput_5.txt", "r")
line = file.readline()
intcode = line.split(",")


memory = []
instruction_pointer = 0

for x in range(len(intcode)):
    memory.append(int(intcode[x]))



while True:
    instruction = memory[instruction_pointer]
    opcode = instruction
    # END
    if opcode == 99:
        break

    if len(str(instruction)) == 5:
        opcode = int(str(instruction)[-2:])
        mode_1 = int(str(instruction)[2:3])
        mode_2 = int(str(instruction)[1:2])
        mode_3 = int(str(instruction)[0:1])
        if mode_1 == 1:
            parameters_1 = instruction_pointer + 1
        else:
            parameters_1 = memory[instruction_pointer + 1]
        if mode_2 == 1:
            parameters_2 = instruction_pointer + 2
        else:
            parameters_2 = memory[instruction_pointer + 2]
        if mode_3 == 1:
            adrress = instruction_pointer + 3
        else:
            adrress = memory[instruction_pointer + 3]

    elif len(str(instruction)) == 4:
        opcode = int(str(instruction)[-2:])
        mode_1 = int(str(instruction)[1:2])
        mode_2 = int(str(instruction)[0:1])
        if mode_1 == 1:
            parameters_1 = instruction_pointer + 1
        else:
            parameters_1 = memory[instruction_pointer + 1]
        if mode_2 == 1:
            parameters_2 = instruction_pointer + 2
        else:
            parameters_2 = memory[instruction_pointer + 2]
        adrress = memory[instruction_pointer + 3]

    elif len(str(instruction)) == 3:
        opcode = int(str(instruction)[-2:])
        parameters_1 = instruction_pointer + 1
        parameters_2 = memory[instruction_pointer + 2]
        adrress = memory[instruction_pointer + 3]

    elif len(str(instruction)) <= 2:
        opcode = instruction
        parameters_1 = memory[instruction_pointer + 1]
        parameters_2 = memory[instruction_pointer + 2]
        adrress = memory[instruction_pointer + 3]


    #-------------------OPERATIONS---------------------#


    # ADD instruction
    if opcode == 1:

        memory[adrress] = int(memory[parameters_1]) + int(memory[parameters_2])
        instruction_pointer += 4

    # MULTIPLY instruction
    elif opcode == 2:

        memory[adrress] = int(memory[parameters_1]) * int(memory[parameters_2])
        instruction_pointer += 4

    #INPUT instruction
    elif opcode == 3:

        memory[parameters_1] = input("Geef input:")
        instruction_pointer += 2

    #OUTPUT instruction
    elif opcode == 4:

        print(memory[parameters_1])
        instruction_pointer += 2

    #JUMP-IF-TRUE
    elif opcode == 5:
        instruction_pointer += 3
        if memory[parameters_1] != 0:
            instruction_pointer = memory[parameters_2]

    #JUMP-IF-FALSE
    elif opcode == 6:
        instruction_pointer += 3
        if memory[parameters_1] == 0:
            instruction_pointer = memory[parameters_2]


    #LESS THAN
    elif opcode == 7:
        if memory[parameters_1] < memory[parameters_2]:
            memory[adrress] = 1
        else:
            memory[adrress] = 0
        instruction_pointer += 4

    #EQUALS
    elif opcode == 8:
        if memory[parameters_1] == memory[parameters_2]:
            memory[adrress] = 1
        else:
            memory[adrress] = 0
        instruction_pointer += 4

