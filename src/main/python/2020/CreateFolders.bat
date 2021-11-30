for /l %%x in (1, 1, 25) do (
    mkdir DAY_%%x
    cd  DAY_%%x
    type nul > PuzzleInput_%%x.txt
    type nul > Code_%%x.py
    cd ..
)