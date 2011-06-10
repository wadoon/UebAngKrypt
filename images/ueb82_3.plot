set title ""

set xrange [0:20]

set term png
set output "ueb82_3.png"


plot 15*x**3 + 6*x**2 +6*x**1 + 18 
