file = open("PuzzleInput_3.txt", "r")
input = file.readlines()

def multiple(slope_r, slope_down):
    x = slope_r
    y = slope_down
    tree_count = 0
    while y < len(input):
        i = input[y]
        i = i.strip()

        if x > len(i) - 1:
            x = x - len(i)

        if i[x] == "#":
            tree_count += 1
        x += slope_r
        y += slope_down
    return tree_count

lijst = [[1,1], [3,1], [5,1], [7,1], [1,2]]

total = 1
for x in lijst:
    total *= multiple(x[0], x[1])
    print(multiple(x[0], x[1]), x)
print(total)