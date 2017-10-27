"""
	@author
		Roberto Martinez Medina
	@github
		xxxxxxx
"""

import abc

'''
	Un nodo guarda un elemento y las referencias a su hijo izquierdo, hijo derecho y a su  nodo
	padre.
'''
class Node(object):

	'''
		Metodo constructor que recibe el elemento que guardara nuestro Nodo
		@param element
			El nodo que se guardara en el nodo
	'''
	def __init__(self, element):
		self.element = element
		self.parent = None
		self.left_child = None
		self.right_child = None

	'''
		Regresa el elemento del nodo
		@return 
			El elemento que contiene nuestro nodo
	'''
	def get_element(self):
		return self.element

	'''
		Intercambia el elemento que tiene el nodo por el elemento que se recibe como parametro
		@param element
			Elemento que almacenara el nodo
	'''
	def set_element(self, element):
		self.element = element

	'''
		Regresa el nodo padre del nodo
		@return 
			El padre del nodo
	'''
	def get_parent(self):
		return self.parent

	'''
		Asigna el padre del nodo
		@param node
			Nodo que asignaremos como padre
	'''
	def set_parent(self, node):
		self.parent = node

	'''
		Regresa el hijo izquierdo del nodo
		@return 
			El hijo izquierdo de nuestro nodo
	'''
	def get_left_child(self):
		return self.left_child

	'''
		Asigna el hijo izquierdo del nodo
		@param node
			Nodo que asignaremos como hijo izquierdo
	'''
	def set_left_child(self, node):
		self.left_child = node
		if (node != None):
			node.parent = self

	'''
		Regresa el hijo derecho del nodo
		@return
			El hijo derecho de nuestro nodo
	'''
	def get_right_child(self):
		return self.right_child

	'''
		Asigna el hijo derecho del nodo
		@param node
			Nodo que asignaremos como hijo derecho
	'''
	def set_right_child(self, node):
		self.right_child = node
		if (node != None):
			node.parent = self

	'''
		Indica si el nodo tiene hijo izquierdo
		@return 
			True si el hijo izquierdo es distinto de None
			False en otro caso
	'''
	def have_left_child(self):
		return self.left_child != None

	'''
		Nos indica si el nodo es hijo izquierdo
		@return
			True si el nodo es hijo izquierdo
			False si el nodo no es hijo izquierdo
		@raise Exception
			Si el nodo no tiene padre
	'''
	def im_left_child(self):
		if (self.parent == None):
			raise Exception("This node haven't parent")
		return self.parent.get_left_child() == self

	'''
		Indica si el nodo tiene hijo derecho
		@return 
			True si el hijo derecho es distinto de None
			False en otro caso
	'''
	def have_right_child(self):
		return self.right_child != None

	'''
		Nos indica si el nodo es hijo derecho
		@return
			True si el nodo es hijo derecho
			False si el nodo no es hijo derecho
		@raise Exception
			Si el nodo no tiene padre
	'''
	def im_right_child(self):
		if (self.parent == None):
			raise Exception("This node haven't parent")
		return self.parent.get_right_child() == self

	'''
		Regresa la representacion en cadena del nodo
		@return
			Representacion en forma de cadena de nuestro nodo
	'''
	def __str__(self):
		return "( " + str(self.element) + " )"

	'''
		Compara el nodo con el objeto que se pasa como parametro comparando también se comparan  
		los sub-árboles izquierdo y derecho
		@param other
			Objeto con el que compararemos el nodo
		@return 
			True si el objeto es igual que nuestro nodo 
			False en otro caso
	'''
	def _eq__(self, other):
		aList = []
		if (isinstance(other, self.__class__)):
			aList.append(other, self)
			while (len(aList) != 0) :
				node_one = aList.pop(0)
				node_two = aList.pop(0)
				if (node_one.get_element() != node_two.get_element()):
					return False
				else:
					if (node_one.have_left_child() and not node_two.have_left_child() or node_one.have_right_child and not node_two.have_right_child()):
						return False
					if (node_one.have_left_child()):
						aList.append(node_one.get_left_child())
						aList.append(node_two.get_left_child())
					if (node_one.have_right_child()):
						aList.append(node_one.get_right_child())
						aList.append(node_two.get_left_child())
			return True
		return False



"""
	Árbol binario:
	Es una estructura de datos en la cual cada nodo puede tener un hijo izquierdo y un hijo derecho.
	Cada nodo tiene a lo más dos hijos.
"""
class BinaryTree(object):

	__metaclass__ = abc.ABCMeta

	'''
		Metodo constructor que inicializa el árbol con la raiz None 
		y el tamanio en 0
	'''
	def __init__(self):
		self.root = None
		self.size = 0

	'''
		Metodo que agrega un elemento al árbol
		@param element
			El elemento que agregaremos al árbol

		@raise MethodNotImplement
			Si el método no ha sido implementado
	'''
	@abc.abstractmethod
	def add(self, element):
		'''
		Agrega un elemento al árbol
		'''
		raise MethodNotImplement()

	'''
		Elimina a un elemento del árbol
		@param element
			Elemento que eliminaremos del árbol
		@raise ElementNotFound
			Si el elemento no esta en el árbol

		@raise MethodNotImplement
			Si el método no ha sido implementado
	'''
	@abc.abstractmethod
	def delete(self, element):		
		'''
			Elimina a un elemento del árbol

		'''
		raise MethodNotImplement()

	'''
		Metodo que creara un tipo especifico de nodo dependiendo de lo que necesite
		el árbol
		@param element
			El elemento que contendrá nuestro nodo
		@return 
			El nodo con el elemento que nos dan como parametro
	'''
	def new_node(self, element):
		return Node(element)

	'''
		Nos indica el tamanio del árbol
		@return
			El tamanio del árbol

	'''
	def get_size(self):
		return self.size

	'''
		Nos indica si el árbol esta vacío
		@return
			True si el árbol está vacío
			False en otro caso
	'''
	def is_empty(self):
		return self.size == 0

	'''
		Metodo que elimina todos los elementos del árbol
	'''
	def clear(self):
		self.root = None
		self.size = 0

'''
	Excepcion para indicar que un elemento no pudo ser encontrado
'''
class ElementNotFound(Exception): pass

'''
	Excepcion para indicar que un metodo no ha sido implementado
'''
class MethodNotImplement(Exception): pass

