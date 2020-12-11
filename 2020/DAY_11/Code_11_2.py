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