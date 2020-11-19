for verb in range(99):
    for noun in range(99):
        file = open("PuzzleInput_2.txt", "r")
        lines = file.readline()
        memory = lines.split(",")

        for x in range(len(memory)):
            memory[x] = int(memory[x])

        memory[1] = noun
        memory[2] = verb

        instruction_pointer = 0
        while True:
            parameter_1 = memory[instruction_pointer + 1]
            parameter_2 = memory[instruction_pointer + 2]
            address = memory[instruction_pointer + 3]
            if memory[instruction_pointer] == 99:
                break
            elif memory[instruction_pointer] == 1:
                memory[address] = memory[parameter_1] + memory[parameter_2]
            elif memory[instruction_pointer] == 2:
                memory[address] = memory[parameter_1] * memory[parameter_2]
            instruction_pointer += 4

        if memory[0] == 19690720:
            print(100*memory[1]+memory[2])
            exit()
