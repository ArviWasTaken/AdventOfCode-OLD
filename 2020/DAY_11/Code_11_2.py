file = open("PuzzleInput_11.txt", "r")

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

        for tmp_x in range(x-1, -1, -1): #Naar links
            if dict[tmp_x, y] == "L" or dict[tmp_x, y] == "#":
                neighbours.append([tmp_x, y])
                break
        for tmp_x in range(x+1, max_x+1, 1): #Naar rechts
            if dict[tmp_x, y] == "L" or dict[tmp_x, y] == "#":
                neighbours.append([tmp_x, y])
                break

        for tmp_y in range(y-1, -1, -1): #Naar boven
            if dict[x, tmp_y] == "L" or dict[x, tmp_y] == "#":
                neighbours.append([x, tmp_y])
                break
        for tmp_y in range(y+1, max_y+1, 1): #Naar onder
            if dict[x, tmp_y] == "L" or dict[x, tmp_y] == "#":
                neighbours.append([x, tmp_y])
                break

        if x < y:
            rng = x
        else:
            rng = y
        for tmp_offset in range(1, rng+1, 1): #Naar linksboven
            tmp_x = x - tmp_offset
            tmp_y = y - tmp_offset
            if x == tmp_x:
                continue
            if dict[tmp_x, tmp_y] == "L" or dict[tmp_x, tmp_y] == "#":
                neighbours.append([tmp_x, tmp_y])

                break
        if max_x - x < max_y - y:
            rng = max_x - x
        else:
            rng = max_y - y
        for tmp_offset in range(1, rng+1, 1): #Naar rechtsbeneden
            tmp_x = x + tmp_offset
            tmp_y = y + tmp_offset
            if x == tmp_x:
                continue
            if dict[tmp_x, tmp_y] == "L" or dict[tmp_x, tmp_y] == "#":
                neighbours.append([tmp_x, tmp_y])
                break

        if x < max_y - y:
            rng = x
        else:
            rng = max_y - y
        for tmp_offset in range(1, rng + 1, 1):  # Naar linksbeneden
            tmp_x = x - tmp_offset
            tmp_y = y + tmp_offset
            if x == tmp_x:
                continue
            if dict[tmp_x, tmp_y] == "L" or dict[tmp_x, tmp_y] == "#":
                neighbours.append([tmp_x, tmp_y])
                break

        if max_x - x < y:
            rng = max_x - x
        else:
            rng = y
        for tmp_offset in range(1, rng + 1, 1):  # Naar rechtsboven
            tmp_x = x + tmp_offset
            tmp_y = y - tmp_offset
            if x == tmp_x :
                continue
            if dict[tmp_x, tmp_y] == "L" or dict[tmp_x, tmp_y] == "#":
                neighbours.append([tmp_x, tmp_y])
                break


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
        elif dict[x, y] == "#" and seat_occupied >= 5:
            tmp_dict[x, y] = "L"
            changed = True
    dict = tmp_dict


hastag_counter = 0
for x in dict.values():
    if x == "#":
        hastag_counter += 1
print(hastag_counter)