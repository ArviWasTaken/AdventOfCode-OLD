file = open("PuzzleInput_3.txt", "r")
a_wire = file.readline()
b_wire = file.readline()
# a_wire = "R75,D30,R83,U83,L12,D49,R71,U7,L72"
# b_wire = "U62,R66,U55,R34,D71,R55,D58,R83"
a_wire = a_wire.split(",")
b_wire = b_wire.split(",")
a_wire_list = {}
b_wire_list = {}
a_wire_steps = {}
b_wire_steps = {}

x = 0
y = 0
steps = 0


for command_nm in range(len(a_wire)):
    command = a_wire[command_nm]
    if command[0] == "U":
        for tel in range(int(command[1:])):
            y += 1
            coor = (x, y)
            a_wire_list[coor] = False
            steps += 1
            if coor not in a_wire_steps:
                a_wire_steps[coor] = steps
    elif command[0] == "D":
        for tel in range(int(command[1:])):
            y -= 1
            coor = (x, y)
            a_wire_list[coor] = False
            steps += 1
            if coor not in a_wire_steps:
                a_wire_steps[coor] = steps
    elif command[0] == "L":
        for tel in range(int(command[1:])):
            x -= 1
            coor = (x, y)
            a_wire_list[coor] = False
            steps += 1
            if coor not in a_wire_steps:
                a_wire_steps[coor] = steps
    elif command[0] == "R":
        for tel in range(int(command[1:])):
            x += 1
            coor = (x, y)
            a_wire_list[coor] = False
            steps += 1
            if coor not in a_wire_steps:
                a_wire_steps[coor] = steps

print(len(a_wire_list), len(a_wire_steps))

x = 0
y = 0
steps = 0
lijn_al = set()

for command_nm in range(len(b_wire)):
    command = b_wire[command_nm]
    if command[0] == "U":
        for tel in range(int(command[1:])):
            y += 1
            coor = (x, y)
            steps += 1
            if coor not in b_wire_steps:
                b_wire_steps[coor] = steps
            if coor not in lijn_al:
                lijn_al.add(coor)
                if coor in a_wire_list:
                    a_wire_list[coor] = True
                else:
                    a_wire_list[coor] = False

    elif command[0] == "D":
        for tel in range(int(command[1:])):
            y -= 1
            coor = (x, y)
            steps += 1
            if coor not in b_wire_steps:
                b_wire_steps[coor] = steps
            if coor not in lijn_al:
                lijn_al.add(coor)
                if coor in a_wire_list:
                    a_wire_list[coor] = True
                else:
                    a_wire_list[coor] = False

    elif command[0] == "L":
        for tel in range(int(command[1:])):
            x -= 1
            coor = (x, y)
            steps += 1
            if coor not in b_wire_steps:
                b_wire_steps[coor] = steps
            if coor not in lijn_al:
                lijn_al.add(coor)
                if coor in a_wire_list:
                    a_wire_list[coor] = True
                else:
                    a_wire_list[coor] = False
    elif command[0] == "R":
        for tel in range(int(command[1:])):
            x += 1
            coor = (x, y)
            steps += 1
            if coor not in b_wire_steps:
                b_wire_steps[coor] = steps
            if coor not in lijn_al:
                lijn_al.add(coor)
                if coor in a_wire_list:
                    a_wire_list[coor] = True
                else:
                    a_wire_list[coor] = False


print(len(b_wire_steps))

for y, x in a_wire_list.items():
    if x == True:
        b_wire_list[y] = a_wire_steps[y] + b_wire_steps[y]

print(f"{b_wire_list} \n{len(a_wire_list), len(b_wire_list)}")

kleinste = -1
jeetje = []

for x in b_wire_list.values():
    # yeey = abs(x[0]) + abs(x[1])
    if kleinste == -1:
        kleinste = x
        jeetje.append(x)
    if x < kleinste:
        jeetje.append(x)
        kleinste = x
print(jeetje)
print(kleinste)