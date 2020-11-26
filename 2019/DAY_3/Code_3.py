file = open("PuzzleInput_3.txt", "r")
a_wire = file.readline()
b_wire = file.readline()
# a_wire = "R8,U5,L5,D3"
# b_wire = "U7,R6,D4,L4"
a_wire = a_wire.split(",")
b_wire = b_wire.split(",")
a_wire_list = {}
b_wire_list = {}


x = 0
y = 0


for command_nm in range(len(a_wire)):
    command = a_wire[command_nm]
    if command[0] == "U":
        for tel in range(int(command[1:])):
            y += 1
            coor = (x, y)
            a_wire_list[coor] = False
    elif command[0] == "D":
        for tel in range(int(command[1:])):
            y -= 1
            coor = (x, y)
            a_wire_list[coor] = False
    elif command[0] == "L":
        for tel in range(int(command[1:])):
            x -= 1
            coor = (x, y)
            a_wire_list[coor] = False
    elif command[0] == "R":
        for tel in range(int(command[1:])):
            x += 1
            coor = (x, y)
            a_wire_list[coor] = False

print(a_wire_list)

x = 0
y = 0
lijn_al = set()

for command_nm in range(len(b_wire)):
    command = b_wire[command_nm]
    if command[0] == "U":
        for tel in range(int(command[1:])):
            y += 1
            coor = (x, y)
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
            if coor not in lijn_al:
                lijn_al.add(coor)
                if coor in a_wire_list:
                    a_wire_list[coor] = True
                else:
                    a_wire_list[coor] = False

print(a_wire_list)

for y, x in a_wire_list.items():
    if x == True:
        b_wire_list[y] = "w"

print(f"{b_wire_list} \n{len(a_wire_list), len(b_wire_list)}")

kleinste = -1
jeetje = []

for x in b_wire_list.keys():
    yeey = abs(x[0]) + abs(x[1])
    if kleinste == -1:
        kleinste = yeey
        jeetje.append(x)
    if yeey < kleinste:
        jeetje.append(x)
        kleinste = yeey
print(jeetje)
print(kleinste)