file = open("PuzzleInput_11_1.txt", "r")

x = 0
y = 0
max_x = 0
max_y = 0
dict = {}
coordinates = []

for row in file.readlines():
    row = row.strip()
    x = 0
    for value in row:
        coor = (x, y)
        dict[coor] = value
        coordinates.append(coor)
        max_x = x
        x += 1
    max_y = y
    y += 1


changed = True
while changed:
    tmp_dict = dict.copy()
    changed = False
    for coor in coordinates:
        neighbours = []
        x = coor[0]
        y = coor[1]
        if dict[coor] == ".":
            continue
        if x == 0 and y == 0:
            for neigh_x in range(x, x+2):
                for neigh_y in range(y, y + 2):
                    if neigh_x == x and neigh_y == y:
                        continue
                    else:
                        neigh_coor = [neigh_x, neigh_y]
                        neighbours.append(neigh_coor)


        elif x == max_x and y == 0:
            for neigh_x in range(x - 1, x + 1):
                for neigh_y in range(y, y + 2):
                    if neigh_x == x and neigh_y == y:
                        continue
                    else:
                        neigh_coor = [neigh_x, neigh_y]
                        neighbours.append(neigh_coor)

        elif x == max_x and y == max_y:
            for neigh_x in range(x - 1, x + 1):
                for neigh_y in range(y-1, y+1):
                    if neigh_x == x and neigh_y == y:
                        continue
                    else:
                        neigh_coor = [neigh_x, neigh_y]
                        neighbours.append(neigh_coor)

        elif x == 0 and y == max_y:
            for neigh_x in range(x, x+2):
                for neigh_y in range(y-1, y+1):
                    if neigh_x == x and neigh_y == y:
                        continue
                    else:
                        neigh_coor = [neigh_x, neigh_y]
                        neighbours.append(neigh_coor)

        elif x == 0:
            for neigh_x in range(x, x+2):
                for neigh_y in range(y-1, y+2):
                    if neigh_x == x and neigh_y == y:
                        continue
                    else:
                        neigh_coor = [neigh_x, neigh_y]
                        neighbours.append(neigh_coor)

        elif x == max_x:
            for neigh_x in range(x - 1, x + 1):
                for neigh_y in range(y - 1, y + 2):
                    if neigh_x == x and neigh_y == y:
                        continue
                    else:
                        neigh_coor = [neigh_x, neigh_y]
                        neighbours.append(neigh_coor)

        elif y == 0:
            for neigh_x in range(x - 1, x + 2):
                for neigh_y in range(y, y + 2):
                    if neigh_x == x and neigh_y == y:
                        continue
                    else:
                        neigh_coor = [neigh_x, neigh_y]
                        neighbours.append(neigh_coor)

        elif y == max_y:
            for neigh_x in range(x-1, x+2):
                for neigh_y in range(y-1, y+1):
                    if neigh_x == x and neigh_y == y:
                        continue
                    else:
                        neigh_coor = [neigh_x, neigh_y]
                        neighbours.append(neigh_coor)

        else:
            for neigh_x in range(x-1, x+2):
                for neigh_y in range(y-1, y+2):
                    if neigh_x == x and neigh_y == y:
                        continue
                    else:
                        neigh_coor = [neigh_x, neigh_y]
                        neighbours.append(neigh_coor)

        seat_occupied = 0
        for neigh_coor in neighbours:
            tmp_y = neigh_coor[1]
            tmp_x = neigh_coor[0]
            if dict[tmp_x, tmp_y] == ".":
                continue
            elif dict[tmp_x, tmp_y] == "L":
                continue
            elif dict[tmp_x, tmp_y] == "#":
                seat_occupied += 1
        if dict[x, y] == "L" and seat_occupied == 0:
            tmp_dict[x, y] = "#"
            changed = True
        elif dict[x, y] == "#" and seat_occupied >= 4:
            tmp_dict[x, y] = "L"
            changed = True
    dict = tmp_dict

hastag_counter = 0
for x in dict.values():
    if x == "#":
        hastag_counter += 1
print(hastag_counter)