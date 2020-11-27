file = open("PuzzleInput_5.txt", "r")
line = file.readline()
intcode = line.split(",")

def AdvancePointer(amount):
    global pointer
    pointer =+ amount


def Computer(intcode):
    while True:
