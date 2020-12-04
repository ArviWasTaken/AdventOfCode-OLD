file = open("PuzzleInput_4.txt", "r")


count = 0
byr_check = False
iyr_check = False
eyr_check = False
hgt_check = False
hcl_check = False
ecl_check = False
pid_check = False
complete = True

for x in file.readlines():
    line = x.split(" ")
    for i in line:

        if i == "\n":
            byr_check = False
            iyr_check = False
            eyr_check = False
            hgt_check = False
            hcl_check = False
            ecl_check = False
            pid_check = False
            complete = True
            continue
        i = i.strip()
        i = i.split(":")
        if "byr" in i[0]:
            if 1920 <= int(i[1]) <= 2002:
                byr_check = True

        if "iyr" in i[0]:
            if 2010 <= int(i[1]) <= 2020:
                iyr_check = True

        if "eyr" in i[0]:
            if 2020 <= int(i[1]) <= 2030:
                eyr_check = True

        if "hgt" in i[0]:
            if i[1][-2:] == "in":
                if 59 <= int(i[1][:-2]) <= 76:
                    hgt_check = True
            elif i[1][-2:] == "cm":
                if 150 <= int(i[1][:-2]) <= 193:
                    hgt_check = True

        if "hcl" in i[0]:
            if len(i[1]) == 7 and i[1][0] == "#":
                haircl_check = True
                for y in i[1][1:]:
                    if y not in "0123456789abcdef":
                        haircl_check = False
                if haircl_check:
                    hcl_check = True

        if "ecl" in i[0]:
            if i[1] == "amb" or i[1] == "blu" or i[1] == "brn" or i[1] == "gry" or i[1] == "grn" or i[1] == "hzl" or i[1] == "oth":
                ecl_check = True

        if "pid" in i[0]:
            if len(i[1]) == 9:
                pid_check = True

        if byr_check and iyr_check and eyr_check and hgt_check and hcl_check and ecl_check and pid_check and complete:
            count += 1
            complete = False
print(count)
