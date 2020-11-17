import math

file =open("PuzzleInput_01", "r")
TotalFuel = 0
for line in file.readlines():
    FuelPerModule = int(line)//3 - 2
    TotalFuel += FuelPerModule
print(TotalFuel)
file.close()