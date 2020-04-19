package model.data_structures;
import java.util.Iterator;

//ACLARACIÓN IMPORTANTE:
//La implementación de esta estructura de datos fue tomada del libro Algorithms 4th.
//Se da crédito total a los autores quienes lo desarrollaron.


public class RedBlackBST<K extends Comparable<K>, V extends Comparable<V>> implements IRedBlackBST<K,V>
{

	/**
	 * Atributos
	 */
	/**
	 * Constante que maneja el color rojo.
	 */
	private static final boolean RED = true;
	/**
	 * Constante que maneja el color negro.
	 */
	private static final boolean BLACK = false;

	/**
	 * Nodo que será la raíz del árbol.
	 */
	private Node raiz;

	/**
	 * Método constructor que construye el árbol. 
	 */
	public RedBlackBST()
	{

	}
	


	/**
	 * Método que determina si un nodo es rojo o no.
	 * @param x Nodo x que será ingresado por parámetro.
	 * @return
	 */
	private boolean isRed(Node x)
	{
		if (x == null)
		{
			return false;
		}
		return x.color == RED;
	}

	/**
	 * Método que rota la izquierda un nodo que ingresa por parámetro.
	 * @param h Nodo que será rotado.
	 * @return Nodo rotado a la izquierda.
	 */
	public Node rotateLeft(Node h)
	{
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left)
		+ size(h.right);
		return x;
	}

	/**
	 * Método que rota la derecha un nodo que ingresa por parámetro.
	 * @param h
	 * @return Nodo rotado a la derecha.
	 */
	public Node rotateRight(Node h)
	{
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left)
		+ size(h.right);
		return x;
	}

	/**
	 * Intercambia los colores de los nodos hijos del nodo ingresado por parámetro.
	 * @param h Nodo al cuál sus hijos se les cambiará el color.
	 */
	public void flipColors(Node h)
	{
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}

	/**
	 * Método que retorna el número de parejas [Llave, Valor] del árbol.
	 * @return Números de parejas.
	 */
	public int size() 
	{
		return size(raiz);
	}

	/**
	 * Método auxiliar que retorna las parejas de un subárbol de un nodo.
	 * @return Número de parejas.
	 */
	private int size(Node x)
	{
		if (x == null)
		{
			return 0;
		}
		else
		{
			return x.N;
		}
	}

	/**
	 * Informa si el árbol es vacio.
	 * @return True en caso de que sea vacio. False en caso contrario.
	 */
	public boolean isEmpty() 
	{
		return raiz==null;
	}

	/**
	 * Retorna el nodo que contiene la llave
	 * @param key llave
	 * @return nodo
	 */
	public Node darNodo(K key)
	{
		if (key == null)
		{
			throw new IllegalArgumentException("La llave es nula");
		}
		return darNodo(raiz, key);
	}
	
	/**
	 * Método auxiliar para retornar el nodo
	 * @param x Nodo con la llave
	 * @param key Llave que está en el nodo
	 * @return Nodo
	 */
	private Node darNodo(Node x, K key)
	{
		while (x != null) 
		{
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
			{
				x = x.left;
			}
			else if(cmp > 0) 
			{
				x = x.right;
			}
			else
			{
				return x;
			}
		}
		return null;
	}
	/**
	 * Retorna el valor V asociado a la llave key dada. Si la llave no se encuentra se retorna el valor null.
	 * @param key Llave de la cual se obtendrá el valor.
	 * @return Valor de la llave ingresada por parámetro.
	 * @throws IllegalArgumentException Si la llave o el valor es nulo.
	 */
	public V get(K key) 
	{
		if (key == null)
		{
			throw new IllegalArgumentException("La llave es nula");
		}
		return get(raiz, key);
	}

	/**
	 * Método auxiliar que retorna el valor asignado a una llave y un nodo.
	 * @param x Nodo que contiene.
	 * @param key Llave que contiene el valor.
	 * @return Retorna el valor de la llave en el nodo.
	 */
	private V get(Node x, K key) 
	{
		while (x != null) 
		{
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
			{
				x = x.left;
			}
			else if(cmp > 0) 
			{
				x = x.right;
			}
			else
			{
				return x.val;
			}
		}
		return null;
	}

	/**
	 * Método que indica si la llave ingresada por parámetro se encuentra en el árbol.
	 * @param key  Llave que será buscada.
	 * @return
	 */
	public boolean contains(K key) 
	{
		return get(key)!=null;
	}

	/**
	 * Método que inserta una pareja [key, valor] en el árbol respetando el balanceo RedBlack. 
	 * Si la llave ya existe se reemplaza el valor. 
	 * Si la llave key o el valor es null se debe lanzar una Exception.
	 * @param key
	 * @param val
	 * @throws IllegalArgumentException Si la llave o el valor es nulo.
	 */
	public void put(K key, V val) 
	{
		if(key==null || val==null)
		{
			throw new IllegalArgumentException("La llave o el valor es nulo.");
		}
		raiz = put(raiz, key, val);
		raiz.color = BLACK;
	}

	/**
	 * Método auxiliar que pone una llave y el valor en un nodo.
	 * @param h Nodo en el que será ingresado la llave y el valor.
	 * @param key Llave que será añadida.
	 * @param val Valor que será añadido
	 * @return Nodo donde fue colocada la llave y el valor.
	 */
	private Node put(Node h, K key, V val)
	{
		if (h == null)
		{
			return new Node(key, val, 1, RED);
		}
		int cmp = key.compareTo(h.key);
		if (cmp < 0)
		{
			h.left = put(h.left, key, val);
		}
		else if (cmp > 0)
		{
			h.right = put(h.right, key, val);
		}
		else
		{
			h.val = val;
		}
		if (isRed(h.right) && !isRed(h.left))
		{
			h = rotateLeft(h);
		}
		if (isRed(h.left) && isRed(h.left.left))
		{
			h = rotateRight(h);
		}
		if (isRed(h.left) && isRed(h.right))
		{
			flipColors(h);
		}
		h.N = size(h.left)+size(h.right)+1;
		return h;
	} 

	/**
	 * Método que retorna la altura del árbol (longitud de la rama más alta).
	 * @return Altura del árbol.
	 */
	public int height() 
	{
		if(raiz==null)
			return 0;
		
		return height(raiz);
	}

	/**
	 * Método auxiliar que retorna la altura de un nodo. Si no existe, retorna -1.
	 * @return Retorna la altura del nodo.
	 */
	private int height(Node x) 
	{
		if (x == null)
		{
			return -1;
		}
		return 1 + Math.max(height(x.left), height(x.right));
	}

	/**
	 * Método que retorna la altura del camino desde la raíz para llegar a la llave key (si existe).
	 * @param Llave de cual será buscada el camino.
	 * @return Altura del camino. En caso de que no exista la llave retorna -1.
	 */
	public int getHeight(K key)
	{
		if(key==null)
		{
			throw new IllegalArgumentException("La llave es nula.");
		}
		if(contains(key))
		{
			return getHeight(key, raiz);
		}
		return -1;
		
	}

	private int getHeight(K key, Node x)
	{
		if(x.key==key)
		{
			return 0;
		}
		else
		{
			int altura=1;
			if(get(x.left, key)!=null)
			{
				altura+=getHeight(key,x.left);
			}
			else if(get(x.right, key)!=null)
			{
				altura+=getHeight(key, x.right);
			}
			return altura;
		}
	}


	/**
	 * Método que retorna la llave más pequeña del árbol.
	 * @return Llave más pequeña del árbol. Retorna null si el árbol está vacío.
	 */
	public K min() 
	{
		if (isEmpty())
		{
			return null;
		}
		return min(raiz).key;
	}

	/**
	 * Método auxiliar que retorna el nodo con la mínima llave a partir del nodo ingresado por parámetro.
	 * @param x Nodo ingresado por parámetro.
	 * @return Nodo con la llave mínima.
	 */
	private Node min(Node x) 
	{ 
		if (x.left == null)
		{
			return x; 
		}
		else
		{
			return min(x.left); 
		}
	} 

	/**
	 * Método que retorna la llave más grande del árbol.
	 * @return Llave más grande del árbol. Retorna null si el árbol está vacío.
	 */
	public K max() 
	{
		if (isEmpty())
		{
			return null;
		}
		return max(raiz).key;
	}

	/**
	 * Método auxiliar que retorna el nodo con la máxima llave a partir del nodo ingresado por parámetro.
	 * @param x Nodo ingresado por parámetro.
	 * @return Nodo con la llave máxima.
	 */
	private Node max(Node x) 
	{ 
		if (x.right == null)
		{
			return x; 
		}
		else
		{
			return max(x.right); 
		}
	} 

	/**
	 * Valida si el árbol es Binario Ordenado y está balanceado Rojo-Negro a la izquierda. Hay que validar que: 
	 * (a) la llave de cada nodo sea mayor que cualquiera de su sub-árbol izquierdo, 
	 * (b) la llave de cada nodo sea menor que cualquiera de su sub-árbol derecho, 
	 * (c) un nodo NO puede tener enlace rojo a su hijo derecho, 
	 * (d) No puede haber dos enlaces rojos consecutivos a la izquierda. Es decir, un nodo NO puede tener un enlace rojo a su hijo izquierdo y su hijo izquierdo NO puede tener enlace rojo a su hijo izquierdo, 
	 * (e) todas las ramas tienen el mismo número de enlaces negros.
	 */
	public boolean check()
	{
		if(!isBST()) return false;
		if(!is23()) return false;
		if(!isBalanced()) return false;
		
		return true;
	}
	
	/**
     * Método que comprueba si el árbol es BST.
     * @param Nodo que ingresa por parámetro.
     * @return True si está balanceado. False en caso contrario.
     */
	private boolean isBST() {
        return isBST(raiz, null, null);
    }

	/**
     * Método auxiliar que comprueba si el árbol es BST.
     * @param Nodo que ingresa por parámetro.
     * @param Llave mínima.
     * @param Llave maxima.
     * @return True si está balanceado. False en caso contrario.
     */
    private boolean isBST(Node x, K min, K max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    } 


    /**
     * Método que comprueba si el árbol es 2-3.
     * @return True si está balanceado. False en caso contrario.
     */
    private boolean is23() { return is23(raiz); }
    
    /**
     * Método auxiliar que comprueba si el árbol es 2-3.
     * @param Nodo que ingresa por parámetro.
     * @return True si está balanceado. False en caso contrario.
     */
    private boolean is23(Node x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (x != raiz && isRed(x) && isRed(x.left))
            return false;
        return is23(x.left) && is23(x.right);
    } 

    /**
     * Método que comprueba si el árbol es balanceado.
     * @return True si está balanceado. False en caso contrario.
     */
    private boolean isBalanced() { 
        int black = 0;     
        Node x = raiz;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(raiz, black);
    }

    /**
     * Método auxiliar que comprueba si el árbol es balanceado.
     * @param x Nodo que ingresa por parámetro.
     * @param black Número de nodos negros que ingresan por parámetro.
     * @return True si está balanceado. False en caso contrario.
     */
    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    } 


	/**
	 * Método que retorna todas las llaves del árbol como un iterador.
	 * @return Llaves del árbol.
	 */
	public Iterator<K> keys() 
	{
		Queue<K> queue=new Queue<K>();
		if (isEmpty())
		{
			return queue.darElementos().iterator();
		}
		return keysInRange(min(),max());
	}

	/**
	 * Método que retorna los valores V en el árbol que estén asociados al rango de llaves dado. 
	 * Debe intentarse NO recorrer el árbol.
	 * @param init Llave inicial donde empieza el rango.
	 * @param end Llave final del rango.
	 * @return Retorna valores V en el rango establecido.
	 */
	public Iterator<V> valuesInRange(K init, K end) 
	{
		if (init == null) throw new IllegalArgumentException("Primer parámetro nulo");
		if (end == null) throw new IllegalArgumentException("Segundo parámetro nulo");
		Queue<V> queue = new Queue<V>();
		values(raiz, queue, init, end);
		return queue.darElementos().iterator();
	}

	/**
	 * Método que retorna las llaves K en el árbol que estén asociados al rango de llaves dado. 
	 * Debe intentarse NO recorrer el árbol.
	 * @param init Llave inicial donde empieza el rango.
	 * @param end Llave final del rango.
	 * @return Retorna las llaves K en el rango establecido.
	 */
	public Iterator<K> keysInRange(K init, K end) 
	{
		if (init == null) throw new IllegalArgumentException("Primer parámetro nulo");
		if (end == null) throw new IllegalArgumentException("Segundo parámetro nulo");
		Queue<K> queue = new Queue<K>();
		keys(raiz, queue, init, end);
		return queue.darElementos().iterator();

	}

	/**
	 * Método que auxilia el método auxiliar keysInRange
	 * @param x Nodo que ingresa por parámetro.
	 * @param queue Cola que ingresa por parámetro.
	 * @param lo Valor inferior que ingresa por parámetro.
	 * @param hi Valor superior que ingresa por parámetro.
	 */
	private void keys(Node x, Queue<K> queue, K lo, K hi) { 
		if (x == null)
		{
			return; 
		}
		int cmplo = lo.compareTo(x.key); 
		int cmphi = hi.compareTo(x.key); 
		if (cmplo < 0)
		{
			keys(x.left, queue, lo, hi); 
		}
		if (cmplo <= 0 && cmphi >= 0)
		{
			queue.enqueue(x.key); 
		}
		if (cmphi > 0)
		{
			keys(x.right, queue, lo, hi); 
		}
	} 
	
	/**
	 * Método que auxilia el método auxiliar keysInRange
	 * @param x Nodo que ingresa por parámetro.
	 * @param queue Cola que ingresa por parámetro.
	 * @param lo Valor inferior que ingresa por parámetro.
	 * @param hi Valor superior que ingresa por parámetro.
	 */
	private void values(Node x, Queue<V> queue, K lo, K hi) { 
		if (x == null)
		{
			return; 
		}
		int cmplo = lo.compareTo(x.key); 
		int cmphi = hi.compareTo(x.key); 
		if (cmplo < 0)
		{
			values(x.left, queue, lo, hi); 
		}
		if (cmplo <= 0 && cmphi >= 0)
		{
			queue.enqueue(x.val); 
		}
		if (cmphi > 0)
		{
			values(x.right, queue, lo, hi); 
		}
	} 

	/**
	 * Clase interna del nodo que será utilizado por el árbol.
	 * @author Sergio Julian Zona Moreno y Juan Andrés Ariza Gacharná.
	 */
	public class Node implements Comparable<Node>
	{
		public K key; 
		public V val; 
		Node left, right; 
		int N; //Número de nodos del subárbol.
		boolean color; 
		Node(K key, V val, int N, boolean color)
		{
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
		@Override
		public int compareTo(RedBlackBST<K, V>.Node o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
	/**
	 * 
	 */
	public Iterator<Node> darHojas() 
	{
		ListaEncadenada<Node> lista = new ListaEncadenada<Node>();
		llenarHojas(raiz,lista);
		return lista.iterator();
	}
	
	/**
	 * Método que llena la lista que contiene las hojas.
	 * @param x Nodo con las hojas
	 * @param lista Lista encadenada con las hojas
	 */
	public void llenarHojas(Node x, ListaEncadenada<Node> lista)
	{
		if(x!=null)
		{
			if(x.left==null&&x.right==null)
			{
				lista.agregarFinal(x);
			}
			else
			{
				if(x.left!=null)
					llenarHojas(x.left,lista);
				if(x.right!=null)
					llenarHojas(x.right,lista);
			}
		}
	}


	
}
