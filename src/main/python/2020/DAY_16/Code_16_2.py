file = open("PuzzleInput_16.txt", "r")

counter = 1
rules = {}
valid_tickets = []
rule_count = {}
my_ticket = []
final_rules_index = {}

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

        rules[line[0]] = line[1]
        rule_count[line[0]] = []
        final_rules_index[line[0]] = -1
    elif counter == 23:
        line = line.split(",")
        for indexy in range(len(line)):
            my_ticket.append(int(line[indexy]))
    elif counter >= 26:
        line = line.split(",")
        line_safe = 0
        for ind in range(len(line)):
            line[ind] = int(line[ind])
            valid = False
            for keys, rnge in rules.items():
                range1 = rnge[0]
                range2 = rnge[1]
                if range1[0] <= line[ind] <= range1[1] or range2[0] <= line[ind] <= range2[1]:
                    valid = True
            if valid:
                line_safe += 1
        if line_safe == 20:
            valid_tickets.append(line)

    counter += 1

for index in range(len(line)):
    for key, rnge in rules.items():
        range1 = rnge[0]
        range2 = rnge[1]
        range_valid_counter = 0
        for ticket in valid_tickets:
            if range1[0] <= ticket[index] <= range1[1] or range2[0] <= ticket[index] <= range2[1]:
                range_valid_counter += 1
        if range_valid_counter == 190:
            lst = rule_count[key]
            lst.append(index)
            rule_count[key] = lst


for _ in range(len(rule_count)):
    for key, indexes in rule_count.items():
        if len(indexes) == 1:
            final_rules_index[key] = indexes
            for o in rule_count.keys():
                if key != o:
                    lst = rule_count[o]
                    if indexes[0] in lst:
                        lst.remove(indexes[0])


final_count = 1
for keys, index in final_rules_index.items():
    if "departure" in keys:
        final_count *= my_ticket[index[0]]
print(final_count)