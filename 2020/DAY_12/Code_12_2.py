file = open("PuzzleInput_12.txt", "r")

x = 10
y = 1
x_b = 0
y_b = 0
direction = 1
d = [0, 1, 2, 3]
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
        for _ in range(value//90):
            if direction == 0:
                direction = 3
            elif direction == 1:
                direction = 0
            elif direction == 2:
                direction = 1
            elif direction == 3:
                direction = 2
            x, y = y * -1, x
    elif com[:1] == "R":
        for _ in range(value // 90):
            if direction == 0:
                direction = 1
            elif direction == 1:
                direction = 2
            elif direction == 2:
                direction = 3
            elif direction == 3:
                direction = 0
            x, y = y, x * -1


print(abs(x_b) + abs(y_b))
