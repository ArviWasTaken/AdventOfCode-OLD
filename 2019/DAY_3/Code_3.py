file = open("PuzzleInput_3.txt", "r")
a_wire = file.readline()
b_wire = file.readline()
a_wire = a_wire.split(",")
b_wire = b_wire.split(",")
a_wire_list = []
b_wire_list = []
cross_list = []
x = 0
y = 0

for command_nm in range(len(a_wire)):
    command = a_wire[command_nm]
    if command[0] == "U":
        for i in range(int(command[1:])):
            x += 1
            coor = [x, y]
            a_wire_list.append(coor)
    elif command[0] == "D":
        for i in range(int(command[1:])):
            x -= 1
            coor = [x, y]
            a_wire_list.append(coor)
    elif command[0] == "L":
        for i in range(int(command[1:])):
            y -= 1
            coor = [x, y]
            a_wire_list.append(coor)
    elif command[0] == "R":
        for i in range(int(command[1:])):
            y += 1
            coor = [x, y]
            a_wire_list.append(coor)

x = 0
y = 0

for command_nm in range(len(b_wire)):
    command = b_wire[command_nm]
    if command[0] == "U":
        for i in range(int(command[1:])):
            x += 1
            coor = [x, y]
            b_wire_list.append(coor)
    elif command[0] == "D":
        for i in range(int(command[1:])):
            x -= 1
            coor = [x, y]
            b_wire_list.append(coor)
    elif command[0] == "L":
        for i in range(int(command[1:])):
            y -= 1
            coor = [x, y]
            a_wire_list.append(coor)
    elif command[0] == "R":
        for i in range(int(command[1:])):
            y += 1
            coor = [x, y]
            b_wire_list.append(coor)


if a_wire_list > b_wire_list:
    for i in range(len(a_wire_list)):
        if a_wire_list[i] in b_wire_list:
            cross_list.append(a_wire_list[i])
else:
    for i in range(len(b_wire_list)):
        if b_wire_list[i] in a_wire_list:
            cross_list.append(b_wire_list[i])
print(len(cross_list))
print(sorted(cross_list))
smallest = [10000, 1000]

for i in range(len(cross_list)):
    if cross_list[i][0] < 0:
        cross_list[i][0] = abs(cross_list[i][0])
    if cross_list[i][1] < 0:
        cross_list[i][1] = abs(cross_list[i][1])
    if sum(cross_list[i]) < sum(smallest):
        smallest[0] = cross_list[i][0]
        smallest[1] = cross_list[i][1]
print(cross_list)
print(smallest)
