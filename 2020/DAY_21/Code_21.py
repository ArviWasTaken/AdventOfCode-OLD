file = open("PuzzleInput_21.txt", "r")

dict = {}
count = {}
for line in file.readlines():
    line = line.strip()
    line = line.split(" (")
    allergen = line[-1]
    allergen = allergen[:-1]
    allergen = allergen.split(", ")
    line.pop(-1)
    line = str(line[0])
    line = line.split()
    for alergeen in allergen:
        if alergeen in dict.keys():
            already_al = dict[alergeen]
            for ingre in line:
                already_al.append(ingre)
            dict[alergeen] = already_al
            count[alergeen] = count[alergeen] + 1
        else:
            dict[alergeen] = line
            count[alergeen] = 1
print(dict)
print(count)

for aler, ingredienten in dict.items():
