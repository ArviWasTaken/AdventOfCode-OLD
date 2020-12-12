file = open("PuzzleInput_12.txt", "r")

x = 0
y = 0
direction = 1
d = [0, 1, 2, 3]
for com in file.readlines():
    com = com.strip()
    value = int(com[1:])
    print(value, type(value))
    if com[:1] == "N":
        y += value
    elif com[:1] == "S":
        y -= value
    elif com[:1] == "E":
        x += value
    elif com[:1] == "W":
        x -= value
    elif com[:1] == "L":
        for _ in range(value//90):
            if direction == 0:
                direction = 3
            else:
                direction = d[d.index(direction)-1]
    elif com[:1] == "R":
        for _ in range(value//90):
            if direction == 3:
                direction = 0
            else:
                direction = d[d.index(direction)+1]
    elif com[:1] == "F":
        if direction == 0:
            y += value
        elif direction == 2:
            y -= value
        elif direction == 1:
            x += value
        elif direction == 3:
            x -= value

print(abs(x) + abs(y))
