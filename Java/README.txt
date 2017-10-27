Facultad De Ciencias UNAM

Nombre: Roberto Martínez Medina
No. de Cuenta: 313087194

Forma de compilar el programa:
  Usar la terminal de linux y poner el comando 
    $ javac *.java
  Con esto se compilaran todos los archivos .java
  
Forma de Usar el programa:
  En la terminal utilizar 
    $ java Programa
  o bien
    $ java Programa mitexto.txt
    
  Con la primera forma ($ java Programa) se mostrara en terminal las intrucciones para
  introducir los numeros así como un ejemplo.
  Los datos ingresados en termial serán puestos en un archivo entrada.txt. 
  
  Con la segunda forma ($ java Programa mitexto.txt) el archivo mitexto.txt es cualquier archivo
  .txt que sera tomado como entrada al programa y realizará las operaciones necesarias para regresar
  las cadenas de L's y R's de las parejas ordenadas en el .txt
  IMPORTANTE: El archivo .txt que sea ingresado tiene que tener la pareja ordenada "1 1", si no 
  es así, el programa solo escribira en terminal "El archivo no tiene el par ordenado "1 1", verifique." 
  
  En ambos casos la salida será escrita en termial y también se generará un archivo
  salida.txt en el que estarán los datos ingresados junto con su respectiva salida.
  
  
Información sobre el programa:
  ROBUSTEZ:
  El programa es Robusto ante estos tipos de errores:
    - Si una entrada no tiene numeros enteros.
    - Si una entrada tiene mas de dos numeros.
    - Si una entrada tiene números negativos.
    - Si una entrada tiene como número entero al cero.
    - Si una entrada es una línea vacía.
    - Si una entrada tiene una pareja que no sean primos relativos.
  Si uno de estas entradas es ingresada al programa, este simplemente muestra el error como la 
  salida de la linea en que se encontró el error.
  
  COMPLEJIDAD:
  El tiempo de crear un árbol Stern-Brocot es O(1). Ya que siempre se añade solamente la pareja
  ordenada (1,1).
    
  El progama al utilizar un árbol binario ordenado para la representación del sistema númerico de 
  Stern-Brocot requiere O(log n) operaciones en el caso medio para encontrar la entrada deseada, 
  en un árbol construido a partir de n claves aleatoreas, y en el peor caso con n claves puede
  implicar revisar las n claves, por lo tanto, la complejidad en tiempo es O(n).
  
  IMPORTANTE: El árbol Stern-Brocot se va llenando con forme se va necesitando. ¿A que se refiere
  esto?, veamos un ejemplo para mostrar el funcionamiento: 
  
    El árbol al inicio tiene a la pareja ordenada (1,1), si queremos encontra a la pareja 
    ordenada (m,n) no se necesita que todo el árbol se llene hasta el nivel en que se encuentre
    esta pareja, simplemente se decide si la pareja (m,n) es mayor o menor con la entrada (1,1).
    En un caso particular, digamos (5,3), la pareja es mayor, por lo tanto se agrega al árbol a la
    pareja (2,1); el algoritmo se va recursivamente hasta que encuentre al elemento deseado.
    Ahora la pareja (5,3) es menor respecto a (2,1) por ende se va hacia la izquierda, añadiendo al
    arbol a la pareja (3,2). Nuevamente aplicando recursivamente el algoritmo tenemos que la pareja 
    (5,3) es mayor respecto a (3,2) por ende, se agrega la siguiente pareja a la derecha, en este
    caso la nueva pareja es (5,3), al aplicar el algoritmo sobre (5,3) tenemos que es la pareja
    deseada, ahora simplemente se va recursivamente desde este Vértice hasta la raíz del árbol para
    crear la cadena de L's y R's que representa a ésta pareja.
    
  Nótese que el árbol solo añadió a los Vértices que eran necesarios para encontrar a la pareja,
  en éste ejemplo, el árbol al terminar de buscar al elemento (5,3) contiene a las siguientes parejas:
  {(1,1), (2,1), (3,2), (5,3)}. Esto es excelente para reducir la complejidad en espacio del
  algoritmo.
  
    
  
