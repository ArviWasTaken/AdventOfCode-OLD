file = open("PuzzleInput_3.txt", "r")
a_wire = file.readline()
b_wire = file.readline()
a_wire = a_wire.split(",")
b_wire = b_wire.split(",")
a_wire_list = [[0, 0]]
b_wire_list = [[0, 0]]
cross_list = []

x = 0
y = 0

for command_nm in range(len(a_wire)):
    command = a_wire[command_nm]
    if command[0] == "U":
        y += int(command[1:])
        coor = [x, y]
        a_wire_list.append(coor)
    elif command[0] == "D":
        y -= int(command[1:])
        coor = [x, y]
        a_wire_list.append(coor)
    elif command[0] == "L":
        x -= int(command[1:])
        coor = [x, y]
        a_wire_list.append(coor)
    elif command[0] == "R":
        x += int(command[1:])
        coor = [x, y]
        a_wire_list.append(coor)

print(len(a_wire_list), a_wire_list)

x = 0
y = 0

for command_nm in range(len(b_wire)):
    command = b_wire[command_nm]
    if command[0] == "U":
        y += int(command[1:])
        coor = [x, y]
        b_wire_list.append(coor)
    elif command[0] == "D":
        y -= int(command[1:])
        coor = [x, y]
        b_wire_list.append(coor)
    elif command[0] == "L":
        x -= int(command[1:])
        coor = [x, y]
        b_wire_list.append(coor)
    elif command[0] == "R":
        x += int(command[1:])
        coor = [x, y]
        b_wire_list.append(coor)

print(len(b_wire_list), b_wire_list)
#x0y_a = False; False is verandering op x-as; True is verandering op de y-as; Voor wire A
#x0y_b = False; False is verandering op x-as; True is verandering op de y-as; Voor wire B

for a in range(len(a_wire_list)-1):
    seg_wire_a = [a_wire_list[a], a_wire_list[a+1]]

    if seg_wire_a[0][0] == seg_wire_a[1][0]: #loopt paralel aan y-as
        x0y_a = True
    else: #loopt paralel aan x-as
        x0y_a = False


    for b in range(len(b_wire_list)-1):
        seg_wire_b = [b_wire_list[b], b_wire_list[b+1]]
        if seg_wire_b[0][0] == seg_wire_b[1][0]: #loopt paralel aan y-as
            x0y_b = True
        else: #loopt paralel aan x-as
            x0y_b = False


        if x0y_a == x0y_b:
            continue
        # print(seg_wire_a, seg_wire_b)
        if x0y_a: #Lijn a loopt paralel aan y-as
            if min(seg_wire_a[0][1], seg_wire_a[1][1]) < seg_wire_b[0][1] < max(seg_wire_a[0][1], seg_wire_a[1][1]): #Als de y van het segment van b tussen de y's van segment a
                if min(seg_wire_b[0][0], seg_wire_b[1][0]) < seg_wire_a[0][0] < max(seg_wire_b[0][0], seg_wire_b[1][0]):
                    cross_list.append([seg_wire_a[0][0], seg_wire_b[0][1]])
                    # print(f"[{seg_wire_a[0][0], seg_wire_b[0][1]}]::: {seg_wire_a}::: {seg_wire_b}")
        else:
            if min(seg_wire_b[0][1], seg_wire_b[1][1]) < seg_wire_a[0][1] < max(seg_wire_b[0][1], seg_wire_b[1][1]):
                if min(seg_wire_a[0][0], seg_wire_a[1][0]) < seg_wire_b[0][0] < max(seg_wire_a[0][0], seg_wire_a[1][0]):
                    cross_list.append([seg_wire_b[0][0], seg_wire_a[0][1]])
                    # print(f"[{seg_wire_b[0][0], seg_wire_a[0][1]}]::: {seg_wire_a}::: {seg_wire_b}")

cross_list.pop(0)
print(cross_list)
kleinste = -1
for x in range(len(cross_list)):
    getal = abs(cross_list[x][0]) + abs(cross_list[x][1])
    if kleinste == -1:
        kleinste = getal
    if kleinste > getal:
        kleinste = getal
print(kleinste)
