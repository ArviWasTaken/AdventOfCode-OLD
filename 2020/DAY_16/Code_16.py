file = open("PuzzleInput_16.txt", "r")

counter = 1
ranges = {}

error_rate = 0

for line in file.readlines():
    line = line.strip()
    if counter <= 20:
        line = line.split(": ")
        line[1] = line[1].split(" or ")
        line[1][0] = line[1][0].split("-")
        line[1][1] = line[1][1].split("-")
        for range_str in line[1]:
            for indexz in range(len(range_str)):
                range_str[indexz] = int(range_str[indexz])

        ranges[line[0]] = line[1]
        print(line)
    elif counter == 23:
        print("Your ticket", line)
    elif counter >= 26:
        line = line.split(",")
        for ind in range(len(line)):
            line[ind] = int(line[ind])
            valid = False
            for keys, rnge in ranges.items():
                range1 = rnge[0]
                range2 = rnge[1]
                if range1[0] <= line[ind] <= range1[1] or range2[0] <= line[ind] <= range2[1]:
                    valid = True
            if not valid:
                error_rate += line[ind]

    counter += 1
print("error rate:", error_rate)