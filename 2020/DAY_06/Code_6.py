import string

file = open("PuzzleInput_6.txt", "r")

input = file.readlines()

abc = list(string.ascii_lowercase)
lijst = []
count = 0
person = 0

for x in input:
    if x == "\n":
        for i in lijst:
            if lijst.count(i) == person and i in abc:
                count += 1
                abc.remove(i)
        lijst = []
        person = 0
        abc = list(string.ascii_lowercase)
    else:
        x = x.strip()
        person += 1
        for y in x:
            lijst.append(y)

print(count)