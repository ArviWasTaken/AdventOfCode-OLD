def read():

    file = open("PuzzleInput_10.txt", "r")

    lst = []
    for x in file.readlines():
        x = x.strip()
        x = int(x)
        lst.append(x)
    file.close()
    return lst

lst = read()
lst.append(0)
lst.append(max(lst)+ 3)
lst.sort()

alle = {}
for x in range(len((lst))):
    ding = []
    for y in range(1, min(4, len(lst[x:]))):
        if lst[y + x] - lst[x] == 1 or lst[y + x] - lst[x] == 2 or lst[y + x] - lst[x] == 3:
            ding.append(lst[y + x])
    alle[lst[x]] = ding

cache = {}


def functie(nummer, alle):
    if not alle[nummer]:
        return 1
    if nummer in cache.keys():
        return cache[nummer]
    opties = 0
    for x in alle[nummer]:
        iteratie = functie(x, alle)
        cache[x] = iteratie
        opties += iteratie
    return opties

print(functie(0, alle))
