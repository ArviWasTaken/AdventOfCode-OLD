file = open("PuzzleInput_17.txt", "r")

x = 0
y = 0
z = 0
w = 0

map_3D = {}

for line in file.readlines():
    line = line.strip()
    x = 0
    for char in line:
        map_3D[x, y, z, w] = char
        x += 1
    y += 1


def find_active_neigh(x, y, z, w):
    lst = []
    for x_offset in range(-1, 2, 1):
        x_check = x + x_offset
        for y_offset in range(-1, 2, 1):
            y_check = y + y_offset
            for z_offset in range(-1, 2, 1):
                z_check = z + z_offset
                for w_offset in range(-1, 2, 1):
                    w_check = w + w_offset
                    if x_offset == 0 and y_offset == 0 and z_offset == 0 and w_offset == 0:
                        continue
                    else:
                        if (x_check, y_check, z_check, w_check) in map_3D and map_3D[x_check, y_check, z_check, w_check] == "#":
                            lst.append([x_check, y_check, z_check, w_check])
    return lst


max_x = 0
min_x = 0
max_y = 0
min_y = 0
max_z = 0
min_z = 0
max_w = 0
min_w = 0

for _ in range(6):
    tmp_map_3d = map_3D.copy()
    for key in tmp_map_3d.keys():
        if max_x < key[0]:
            max_x = key[0]
        elif min_x > key[0]:
            min_x = key[0]
        if max_y < key[1]:
            max_y = key[1]
        elif min_y > key[1]:
            min_y = key[1]
        if max_z < key[2]:
            max_z = key[2]
        elif min_z > key[2]:
            min_z = key[2]
        if max_w < key[3]:
            max_w = key[3]
        elif min_w > key[3]:
            min_w = key[3]
    for cur_w in range(min_w - 1, max_w + 2,1):
        for cur_z in range(min_z - 1, max_z + 2, 1):
            for cur_y in range(min_y - 1, max_y + 2, 1):
                for cur_x in range(min_x - 1, max_x + 2, 1):
                    value = tmp_map_3d.setdefault((cur_x, cur_y, cur_z, cur_w), ".")
                    neigbours = find_active_neigh(cur_x, cur_y, cur_z, cur_w)
                    if value == "#" and (len(neigbours) != 2 and len(neigbours) != 3):
                        tmp_map_3d[cur_x, cur_y, cur_z, cur_w] = "."
                    elif value == "." and len(neigbours) == 3:
                        tmp_map_3d[cur_x, cur_y, cur_z, cur_w] = "#"

    map_3D = tmp_map_3d

counter = 0
for value in map_3D.values():
    if value == "#":
        counter += 1
print(f"Active cubes:{counter}")