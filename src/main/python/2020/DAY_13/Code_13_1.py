file = open("PuzzleInput_13.txt", "r")

file.readline()

busses = file.readline()
busses = busses.strip()
busses = busses.split(",")


# cur_time = 100000000000000                    #Deze code werkt maar doet er veel te lang over
# while True:
#     if cur_time % int(busses[0]) == 0:
#         break
#     cur_time += 1
#
# found = True
# while found:
#     tmp_time = cur_time
#     index = 0
#     while True:
#         if busses[index] == "x" or tmp_time % int(busses[index]) == 0:
#             if index == len(busses) - 1:
#                 print(cur_time)
#                 found = False
#                 break
#             index += 1
#             tmp_time += 1
#         else:
#             break
#     cur_time += int(busses[0])            #Eind langzame code

factor = 1
index = 0
antwoord = 0

for x in busses:
    if x != "x":
        x = int(x)
        while (antwoord + index) % x != 0:         #WAAROM DE FUCK WERKT DIT
            antwoord += factor                     #Keihard over genomen van https://github.com/UnderKoen/AdventOfCode waarmee ik in een discord call zat. ik snap dit wel maar had het nooit zelf bedacht.
        factor *= x
    index += 1

print(antwoord)