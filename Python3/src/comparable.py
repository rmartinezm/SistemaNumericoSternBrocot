import abc
from binary_tree import MethodNotImplement
'''
	Interface para objetos que necesiten poder ser comparados 
'''
class Comparable(object):

	__metaclass_= abc.ABCMeta

	'''
		Ćompara nuestro objeto con el objeto 'other' 
		@param other
			Un objeto con el que tenemos que comparar nuestro objeto 
		@return 
			Un entero mayor que 0 si nuestro objeto es mayor que other.
			0 si nuestro objeto es igual que el other.
			Un numero menor que 0 si nuestro objeto es menor que other.
		@raise MethodNotImplement
			Si el método no ha sido implementado
	'''
	@abc.abstractmethod
	def compare_to(self, other):
		raise MethodNotImplement()

