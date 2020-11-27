input = [234208, 765869]
tel = 0

for x in range(input[0], input[1]):
    x = str(x)
    dubbel = 0
    if x[0] >= x[1] >= x[2] >= x[3] >= x[4] >= x[5]:
        lijst_numbers = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
        al_een_dubbel = False
        for y in x:
            if y in lijst_numbers:
                lijst_numbers.remove(y)
                if x.count(y) == 2 and al_een_dubbel == False:
                    tel += 1
                    al_een_dubbel = True
print(tel)

