file = open("PuzzleInput_12.txt", "r")

x = 10
y = 1
x_b = 0
y_b = 0
for com in file.readlines():
    com = com.strip()
    value = int(com[1:])
    if com[:1] == "N":
        y += value
    elif com[:1] == "S":
        y -= value
    elif com[:1] == "E":
        x += value
    elif com[:1] == "W":
        x -= value
    elif com[:1] == "F":
        for _ in range(value):
            x_b += x
            y_b += y
    elif com[:1] == "L":
        for _ in range(value // 90):
            x, y = y * -1, x
    elif com[:1] == "R":
        for _ in range(value // 90):
            x, y = y, x * -1


print(abs(x_b) + abs(y_b))
