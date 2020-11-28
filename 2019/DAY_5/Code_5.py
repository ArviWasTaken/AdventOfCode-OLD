file = open("PuzzleInput_5.txt", "r")
line = file.readline()
intcode = line.split(",")

def AdvancePointer(amount):
    global instruction_pointer
    instruction_pointer += amount


def GetOpcode(adress):
    return int(str(adress)[-2:])


def Computer(intcode):
    memory = intcode

    global instruction_pointer
    instruction_pointer = 0
    while True:
        parameter_1 = memory[instruction_pointer + 1]
        parameter_2 = memory[instruction_pointer + 2]
        address = memory[instruction_pointer + 3]

        # END
        if int(str(memory[instruction_pointer])[-2:]) == 99:
            break

        # ADD instruction
        elif GetOpcode(memory[instruction_pointer]) == 1:
            memory[address] = memory[parameter_1] + memory[parameter_2]
            AdvancePointer(4)

        # MULTIPLY instruction
        elif GetOpcode(memory[instruction_pointer]) == 2:
            memory[address] = memory[parameter_1] * memory[parameter_2]
            AdvancePointer(4)

        # WRITE value
        elif GetOpcode(memory[instruction_pointer]) == 3:
            memory[parameter_1] = input("Geef input:")
            AdvancePointer(2)

        # READ value
        elif GetOpcode(memory[instruction_pointer]) == 4:
            print(memory[parameter_1])
            AdvancePointer(2)