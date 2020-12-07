file = open("PuzzleInput_7.txt", "r")

count = 0
rules = {}

for x in file.readlines():
    x = x.strip()
    x = x.split(" ")

    for y in x:
        if y == "bags":
            x.pop(x.index("bags"))
        elif y == "bags,":
            x.pop(x.index("bags,"))
        elif y == "bags.":
            x.pop(x.index("bags."))
        elif y == "bag,":
            x.pop(x.index("bag,"))
        elif y == "bag.":
            x.pop(x.index("bag."))
        elif y == ",":
            x.pop(x.index(","))


    x.pop(x.index("contain"))

    first_bag = x[0] + " " + x[1]

    x.pop(0)
    x.pop(0)

    bags = []

    for y in range(int(len(x)/3)):
        rule = int(x[0]), x[1] + " " + x[2]
        bags.append(rule)
        for z in range(3):
            x.pop(0)

    rules[first_bag] = bags



bags_check = ["shiny gold"]


def get_bags(bag):
    for key, value in rules.items():
            if bag in key:
                for x in value:
                    global count
                    count += int(x[0])
                    for y in range(x[0]):
                        bags_check.append(x[1])


while True:
    print(count)
    if not bags_check:
        break
    else:
        get_bags(bags_check[0])
        bags_check.pop(0)

print(count)