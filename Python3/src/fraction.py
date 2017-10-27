"""
	@author 
		Roberto Martínez Medina

"""

from comparable import Comparable

class Fraction(Comparable):

	def __init__(self, numerator, denominator):
		self.numerator = numerator
		self.denominator = denominator
		self.value = numerator / denominator

	def compare_to(self, other):
		if self.value == other.get_value():
			return 0
		elif self.value < other.get_value():
			return -1
		return 1

	def get_value(self):
		return self.value

	def __str__(self):
		return (str(self.numerator) + " / " + str(self.denominator))