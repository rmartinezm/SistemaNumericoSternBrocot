from binary_tree import *

'''
	árbol Binario Ordenado:
	Un árbol instancia de esta clase siempre cumple que:
		- Cualquier elemento en el árbol es mayor o igual que todos sus descendientes por la izquierda.
 		- Cualquier elemento en el árbol es menor o igual que todos sus descendientes por la derecha.
'''
"""
	Clase para árboles binarios ordenados. Los árboles son acotados a Comparable
"""
class OrderedBinaryTree(BinaryTree):

	"""
		Método constructor que llama al contructor padre
	"""
	def __init__(self):
		super(OrderedBinaryTree, self).__init__()

	'''
		Método que agrega al árbol binario ordenado el elemento que nos pasan como parametro
		@param element 
			El elemento que agregaremos a nuestro árbol binario ordenado
	'''
	def add(self, element):
		node = self.new_node(element)
		if (self.root == None):
			self.root = node
		else:
			self.__add_aux(self.root, node)
		self.size += 1 

	"""
		Método privado recursivo que sirve para agregar el nodo en su posición dentro del árbol
		binario ordenado.
		@param node
			Nodo en el árbol binario con el que comparamos el nodo para agregar
		@param node_to_add
			Nodo que agregamos al árbol binario ordenado
	"""		
	def __add_aux(self, node, node_to_add):
		if (node_to_add.get_element().compare_to(node.get_element()) > 0):
			if (not node.have_right_child()):
				node.set_right_child(node_to_add)
			else:
				self.__add_aux(node.get_right_child(), node_to_add)
		else:
			if (not node.have_left_child()):
				node.set_left_child(node_to_add)
			else:
				self.__add_aux(node.get_left_child(), node_to_add)



######################################################################################################### no iimplementado todavía
	"""
		Método que elimina a un elemento del árbol binario ordenado
		@param element
			Elemento que eliminaremos del árbol
		@raise ElementNotFound
			Si el elemento no esta en el árbol
	"""
	def delete(self, element):
		node_to_delete = self.search(element)
		if node_to_delete == None:
			raise ElementNotFound()

		left_child = node_to_delete.get_left_child()
		right_child = node_to_delete.get_right_child()
		
		parent = node_to_delete.get_parent()
		maximum_in_left = self.get_maximum_in_left_subtree(node_to_delete)
		
		# Si el nodo es una hoja
		if left_child == None and right_child == None:
			self.delete_leaf(node_to_delete)
		# Si el nodo solo tiene un hijo 
		elif (left_child == None and right_child != None) or (left_child != None and right_child == None):
			self.delete_node_with_a_child(node_to_delete)
		# Si el nodo tiene ambos hijos
		else:  
			self.delete_node_with_both_children(node_to_delete)
		self.size -= 1 
##################################################################################################################################


	"""
		Regresa el nodo que contiene al elemento buscado si éste se encuentra
		en el árbol binario ordenado.
		@param element
			Elemento que buscamos en el árbol
		@return 
			El nodo que contiene al parametro element
			None Si el elemento no se encuentra en el árbol binario ordenado.
	"""
	def search(self, element):
		return __search_aux(self.root, element)


	"""
		Método privado recursivo que sirve para buscar el nodo que contiene al elemento que 
		se recibe como parametro
		@param node
			Nodo en el árbol binario con el que comparamos el elemento
		@param element
			Elemento que buscamos en el árbol binario ordenado
		@return
			El nodo dentro del árbol binario ordenado que contiene el elemento buscado
			None Si el elemento no se encuentra en el árbol binario ordenado.
	"""
	def __search_aux(self, node, element):
		if node == None:
			return None
		elif node.get_element().compare_to(element) == 0:
			return node
		elif node.get_element().compare_to(element) > 0:
			return __search_aux(node.get_left_child(), element)
		return __search_aux(node.get_right_child(), element)


	"""
		Regresa el nodo máximo en el subárbol izquierdo del nodo que se recibe.
		@param 
			node El nodo del que queremos encontrar el nodo máximo en su subárbol izquierdo.
		@return 
			El nodo máximo el subárbol izquierdo del nodo que se recibe.
			None si no existe un subárbol izquierdo
	"""
	def get_maximum_in_left_subtree(self, node):
		if not node.have_left_child():
			return None:
		aux_child = node.get_left_child()
		while (aux_child.have_right_child()):
			aux_child = aux_child.get_right_child()
		return aux_child		


	"""
		Regresa un iterador del árbol binario ordenado.
		El árbol se recorre in-order.
	"""
	def __iter__(self):
		self.iterator = []
		if self.root != None:
			node = self.root
			self.iterator.append(node)
			while (node.have_left_child()):
				node = node.get_left_child()
				self.iterator.append(node)				
		return self


	"""
		Regresa el siguiente elemento del iterador
	"""
	def __next__(self):
		if not self.iterator:
			raise StopIteration
		node = self.iterator.pop()
		if node.have_right_child():
			aux_node = node.get_right_child()
			self.iterator.append(aux_node)
			while (aux_node.have_left_child()):
				aux_node = aux_node.get_left_child()
				self.iterator.append(aux_node)
		return node


	"""
		Indica si el árbol es igual al objeto 'other'
		@param other
			Objeto con el que comparamos el árbol binario ordenado.
		@return
			True si other es igual al árbol binario ordenado.
			False en otro caso.
	"""
	def __eq__(self, other):
		if (isinstance(other, self.__class__)):
			sRoot = self.root
			oRoot = other.root
			return sRoot == oRoot
		return False


	"""
		Regresa un nuevo nodo
		@param
			element Elemento que metemos en el nodo
		@return 
			Un nuevo nodo con element como elemento
		@raise NotComparableElement
			Si el elemento no implementa el método compare_to de la clase Comparable
	"""
	def new_node(self, element):
		if not "compare_to" in dir(element.__class__):
			raise NotComparableElement()
		return super(OrderedBinaryTree, self).new_node(element)



"""
	Excepcion que ocurre cuando los elementos no implementan Comparable
"""
class NotComparableElement(Exception): pass

