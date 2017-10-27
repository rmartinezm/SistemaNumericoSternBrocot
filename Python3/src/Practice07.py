from ordered_binary_tree import *
from fraction import Fraction

if __name__ == '__main__':
	tree = OrderedBinaryTree()
	tree.add(Fraction(1,2))
	tree.add(Fraction(3,2))
	tree.add(Fraction(5,2))
	tree.add(Fraction(1,3))
	
	
	for node in tree:
		print(node)