file = open("PuzzleInput_5.txt", "r")
line = file.readline()
intcode = line.split(",")

def Computer(intcode):

    memory = intcode

    for x in range(len(memory)):
        memory[x] = int(memory[x])

    global instruction_pointer
    instruction_pointer = 0


    def AdvancePointer(amount):
        global instruction_pointer
        instruction_pointer += amount

    def GetMode(code):
        return int(str(code)[:-2])

    def GetOpcode(code):
        return int(str(code)[-2:])


    while True:
        opcode = memory[instruction_pointer]

        #-------------------OPERATIONS---------------------#

        # END
        if GetOpcode(opcode) == 99:
            break

        # ADD instruction
        elif GetOpcode(opcode) == 1:
            parameter_1 =
            parameter_2 =
            parameter_3 = memory[instruction_pointer + 3]
            memory[parameter_3] = memory[parameter_1] + memory[parameter_2]
            AdvancePointer(4)

        # MULTIPLY instruction
        elif GetOpcode(opcode) == 2:
            parameter_1 =
            parameter_2 =
            parameter_3 = memory[instruction_pointer + 3]
            memory[parameter_3] = memory[parameter_1] * memory[parameter_2]
            AdvancePointer(4)

        # WRITE value
        elif GetOpcode(opcode) == 3:
            memory[parameter_1] = int(input("Geef input:"))
            AdvancePointer(2)

        # READ value
        elif GetOpcode(opcode) == 4:
            print(memory[parameter_1])
            AdvancePointer(2)


Computer(intcode)