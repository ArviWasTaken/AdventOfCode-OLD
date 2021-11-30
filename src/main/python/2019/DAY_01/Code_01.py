file =open("PuzzleInput_01", "r")
TotalFuel = 0


def fuel(mass):
    fuel = mass//3-2
    return fuel


for line in file.readlines():
    MassPerModule = int(line)
    FuelPerModule = fuel(MassPerModule)
    TotalFuel += FuelPerModule
    while FuelPerModule > 0:
        FuelPerModule = fuel(FuelPerModule)
        if FuelPerModule > 0:
            TotalFuel += FuelPerModule
print(TotalFuel)
file.close()
