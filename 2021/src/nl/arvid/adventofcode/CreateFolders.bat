for /l %%x in (1, 1, 25) do (
    if %%x LSS 10 (mkdir day0%%x & cd day0%%x & type nul > PuzzleInput-0%%x.txt & type nul > Day0%%x.java) else (mkdir day%%x & cd day%%x & type nul > PuzzleInput-%%x.txt & type nul > Day%%x.java)
    cd ..
)