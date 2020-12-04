def part_1():
    file = open("PuzzleInput_1.txt", "r")
    expenses = []
    for x in file.readlines():
        x = x[:-1]
        x = int(x)
        expenses.append(x)
    for e in expenses:
        for y in expenses:
            if e + y == 2020:
                print(e * y)



def part_2():
    file = open("PuzzleInput_1.txt", "r")
    expenses = []
    for x in file.readlines():
        x = x[:-1]
        x = int(x)
        expenses.append(x)
    for e in expenses:
        for y in expenses:
            for x in expenses:
                if e + y + x == 2020:
                    print(e * y * x)
part_1()
part_2()