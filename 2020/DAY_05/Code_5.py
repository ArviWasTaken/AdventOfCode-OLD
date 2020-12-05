import math

file = open("PuzzleInput_5.txt", "r")
input = file.readlines()
seat_id = []

for x in input:
    x = x.strip()
    lower_column = 0
    upper_column = 127
    lower_seat = 0
    upper_seat = 7
    column = -1
    column_set = False
    seat = -1
    seat_set = False

    for y in x:
        diffrence_col = upper_column - lower_column
        difference_seat = upper_seat - lower_seat

        if diffrence_col == 1 and column_set == False:
            if y == "F":
                column = lower_column
            elif y == "B":
                column = upper_column
            column_set = True
        elif difference_seat == 1 and seat_set == False:
            if y == "L":
                seat = lower_seat

            elif y == "R":
                seat = upper_seat
            seat_set = True

        elif y == "F":
            upper_column -= math.ceil(diffrence_col / 2.0)

        elif y == "B":
            lower_column += math.ceil(diffrence_col / 2.0)

        elif y == "L":
            upper_seat -= math.ceil(difference_seat / 2.0)

        elif y == "R":
            lower_seat += math.ceil(difference_seat / 2.0)

    seat_id.append(8 * column + seat)


for x in seat_id:
    if x+1 not in seat_id and x+2 in seat_id:
        print(x+1)


