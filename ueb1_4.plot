set title ""

set xlabel "Runden"
set ylabel "Hammingdistanz"

set xtics 2
set ytics 1
set mytics 2

set yrange [0:64]
set xrange [0:16]

set term png
set output "ueb1_4.png"

plot "ueb1_4.data" notitle with lines

