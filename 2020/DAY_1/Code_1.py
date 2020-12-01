file = open("PuzzleInput_1.txt", "r")
expenses = []
for x in file.readlines():
    x = x[:-1]
    x = int(x)
    expenses.append(x)
print(expenses)
for e in expenses:
    for y in expenses:
        if e + y == 2020:
            print(e * y)