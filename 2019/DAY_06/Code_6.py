file = open("PuzzleInput_6.txt", "r")


map = {}
for x in file.readlines():
    orbit = x.rstrip().split(")")
    map[orbit[1]] = orbit[0]
print(map)


you_transfers = []
x = map["YOU"]
while x != "COM":
    you_transfers.append(x)
    x = map[x]


san_transfers = []
x = map["SAN"]
while x != "COM":
    san_transfers.append(x)
    x = map[x]

common = ""
for x in you_transfers:
    if x in san_transfers:
        common = x
        break

print(common)

totaal_SAN = 0
x = map["SAN"]
while x != common:
    totaal_SAN += 1
    x = map[x]
totaal_YOU = 0
x = map["YOU"]
while x != common:
    totaal_YOU += 1
    x = map[x]
print(totaal_YOU + totaal_SAN)