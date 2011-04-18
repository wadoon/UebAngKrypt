import sys

N=int(sys.argv[1])

print "   |",
for row in range(0,N):
	print "%2d" % row , 
print
	
for row in range(0,N):
	print "%2d |" % row , 
	for col in range(0,N):
		if ( (row*col)%N) == 1:
			print "\x1b[31;42m%2d\x1b[0m" % ( (row*col)%N) ,
		else:
			print "%2d" % ( (row*col)%N) ,
	print "" 
