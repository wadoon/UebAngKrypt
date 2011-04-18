#!/usr/bin/python

#!/usr/bin/python

MODUL = 29

CACHE = {}

def minverse(z):	
	for i in range(0,MODUL):
		if (z*i)%MODUL==1: return i
	return 0

#def minverse(z):
#	try:
#		return CACHE[(z,MODUL)]
#	except:
#		a = minverse_(z)
#		CACHE[(z,MODUL)] = a
#		return a

class Matrix(object):
	def __init__(self, m,n, f = None):
		self.A = []
		self.m = m
		self.n = n
		for i in range(m):
			self.A.append( [0,] *n)

		if f:
			for i,e in enumerate(f):
				self.A[i/n][i%n]=e


	
	def rdiv(self, row, div):
		inv = minverse(div)
		self.rmul(row,inv)
		return self

	def rmul(self, row, mul):
		for i in range(self.n):
			self.A[row][i] = (mul * self.A[row][i])%MODUL
		return self

	def rplus(self, row1, row2, mul=1 , div=1):
		inv = minverse(div) * mul

		for i in range(self.n):
			self.A[row1][i] =  \
			 (self.A[row1][i] + \
			 	(inv * self.A[row2][i]))%MODUL
		return self

	def rminus(self,row1,row2, mul = 1, div = 1):
		return self.rplus(row1,row2, -mul,div)	


	def __str__(self):
		s = "" 
		for i in self.A:
			s += str(i)+"\n"

		return s

	def __mul__(self,other):
		v = []
		for row in self.A:
			s = 0
			for ai,xi in zip(row,other):
				s += (ai*xi) % MODUL
			v.append(s%MODUL)
		return v

m = Matrix(4,4)
m.A = [[2, 4, 5, 3], [5, 6, 7, 8], [2, 2, 3, 5], [4, 4, 6, 10]]

print m
print m.rdiv(0, 2)


print m
print m.rminus(3,2, 2, 1)


print 

m = Matrix(2,2, (2, 5 , 6 ,3))
print m
print m * (5, 2)


