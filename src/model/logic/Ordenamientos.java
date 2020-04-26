package model.logic;

import java.util.Comparator;

public class Ordenamientos
{
	/**
	 * M�todo que compara dos elementos por comparable.
	 * @param a Elemento a que llega por par�metro.
	 * @param b Elemento b que llega por par�metro.
	 * @param comparador Indica por que comparar los objetos
	 * @return True si a es menor que b, o False en caso contrario.
	 */
	public static boolean menor (Comparable a, Comparable b, Comparator comparador)
	{
		return comparador.compare(a, b)<0;
	}

	/**
	 * M�todo que cambia de posici�n en el arreglo un elemento comparable que llega por par�metro.
	 * @param a Arreglo de elementos comparable que llega por par�metro.
	 * @param i Posici�n i entera que llega por par�metro.
	 * @param j Posici�n j entera que llega por par�metro.
	 */
	public static void intercambiar(Comparable[] a, int i, int j)
	{
		Comparable swap = a[i];
		a[i]=a[j];
		a[j]=swap;
	}

	/**
	 * M�todo que organiza por el algoritmo shellSort el arreglo comparable que llega por par�metro.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 259, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param comparador Indica por que comparar los objetos
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 */
	public static void shellSort(Comparable[] a,Comparator comparador)
	{
		int n = a.length;

		int h = 1;

		while(h<n/3)
		{
			h = 3*h+1;
		}

		while(h>=1)
		{
			for (int i = 0; i < n; i++) 
			{
				for (int j = i;j>=h&&menor(a[j],a[j-h],comparador); j-=h) 
				{
					intercambiar(a, j, j-h);
				}
			}

			h=h/3;
		}
	}

	/**
	 * M�todo que organiza por el algoritmo mergeSort el arreglo comparable que llega por par�metro.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 271, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 * @param comparador Indica por que comparar los objetos
	 */
	public static void mergeSort(Comparable a[],Comparator comparador)
	{
		Comparable[] auxiliar = new Comparable[a.length];

		ordenarMerge(a,auxiliar,0,a.length - 1,comparador);
	}

	/**
	 * M�todo que organiza por el algoritmo MergeSort el arreglo comparable que llega por par�metro.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 271, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 * @param comparador Indica por que comparar los objetos
	 */
	public static void ordenarMerge(Comparable[] a, Comparable[] auxiliar, int lo, int hi,Comparator comparador)
	{
		if(hi<=lo)
		{
			return;
		}

		int mid = lo+(hi-lo)/2;
		ordenarMerge(a, auxiliar, lo, mid,comparador);
		ordenarMerge(a, auxiliar, mid+1, hi,comparador);
		merge(a,auxiliar,lo,mid,hi,comparador);

	}

	/**
	 * M�todo auxiliar que organiza por el algoritmo MergeSort el arreglo comparable que llega por par�metro.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 271, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 * @param comparador Indica por que comparar los objetos
	 */
	public static void merge(Comparable[] a, Comparable[] auxiliar, int lo, int mid, int hi,Comparator comparador)
	{
		for (int k = lo; k<=hi; k++) 
		{
			auxiliar[k]=a[k];
		}

		int i = lo;
		int j = mid +1;

		for (int k = lo; k <=hi; k++) 
		{
			if(i>mid)
				a[k]=auxiliar[j++];
			else if(j>hi)
				a[k]=auxiliar[i++];
			else if(menor(auxiliar[j],auxiliar[i],comparador))
				a[k]=auxiliar[j++];
			else
				a[k]=auxiliar[i++];
		}
	}

	/**
	 * M�todo que organiza por el algoritmo QuickSort el arreglo comparable que llega por par�metro.
	 * @post Se bajara aleatoriamente el arreglo antes de aplicar el ordenamiento.
	 * 		 Posteriormente se ordena el arreglo.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 289, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 * @param comparador Indica por que comparar los objetos
	 */
	public static void quickSort(Comparable[] a,Comparator<Comparable> comparador)
	{
		shuffle(a);
		ordenarQuick(a,0,a.length-1,comparador);

	}

	/**
	 * M�todo auxiliar que organiza por el algoritmo QuickSort el arreglo comparable que llega por par�metro.
	 * @post Se bajara aleatoriamente el arreglo antes de aplicar el ordenamiento.
	 * 		 Posteriormente se ordena el arreglo.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 289, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 * @param comparador Indica por que comparar los objetos
	 */
	public static void ordenarQuick(Comparable a[], int lo, int hi,Comparator comparador)
	{
		if(hi<=lo)
			return;

		int j = particion(a,lo,hi,comparador);

		ordenarQuick(a,lo,j-1,comparador);
		ordenarQuick(a,j+1,hi,comparador);

	}

	/**
	 * M�todo auxiliar que organiza por el algoritmo QuickSort el arreglo comparable que llega por par�metro.
	 * @post Se bajara aleatoriamente el arreglo antes de aplicar el ordenamiento.
	 * 		 Posteriormente se ordena el arreglo.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 289, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 * @param comparador Indica por que comparar los objetos
	 */
	public static int particion(Comparable a[], int lo, int hi,Comparator comparador)
	{
		int i = lo;
		int j = hi+1;
		while(true)
		{
			while(menor(a[++i],a[lo],comparador))
				if(i==hi)
					break;
			while(menor(a[lo],a[--j],comparador))
				if(j==lo)
					break;
			if(i>=j)
				break;
			intercambiar(a, i, j);	
		}
		intercambiar(a,lo,j);
		return j;
	}

	/**
	 * M�todo que baraja aleatoriamente el arreglo.
	 * @param a Arreglo de elementos comparables que llega por par�metro.
	 */
	public static void shuffle(Comparable[] a)
	{
		int n = a.length;

		for (int i = 0; i < a.length; i++)
		{
			int r = (int) Math.random()*n;

			Comparable b = a[i];

			a[i] = a[r];
			a[r]= b;
		}
	}

	/**
	 * M�todo que compara dos elementos por comparable.
	 * @param a Elemento a que llega por par�metro.
	 * @param b Elemento b que llega por par�metro.
	 * @return True si a es menor que b, o False en caso contrario.
	 */
	public static boolean menor (Comparable a, Comparable b)
	{
		return a.compareTo(b)<0;
	}


	/**
	 * M�todo que organiza por el algoritmo shellSort el arreglo comparable que llega por par�metro.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 259, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 */
	public static void shellSort(Comparable[] a)
	{
		int n = a.length;

		int h = 1;

		while(h<n/3)
		{
			h = 3*h+1;
		}

		while(h>=1)
		{
			for (int i = 0; i < n; i++) 
			{
				for (int j = i;j>=h&&menor(a[j],a[j-h]); j-=h) 
				{
					intercambiar(a, j, j-h);
				}
			}

			h=h/3;
		}
	}

	/**
	 * M�todo que organiza por el algoritmo mergeSort el arreglo comparable que llega por par�metro.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 271, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 */
	public static void mergeSort(Comparable a[])
	{
		Comparable[] auxiliar = new Comparable[a.length];

		ordenarMerge(a,auxiliar,0,a.length - 1);
	}

	/**
	 * M�todo que organiza por el algoritmo MergeSort el arreglo comparable que llega por par�metro.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 271, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 */
	public static void ordenarMerge(Comparable[] a, Comparable[] auxiliar, int lo, int hi)
	{
		if(hi<=lo)
		{
			return;
		}

		int mid = lo+(hi-lo)/2;
		ordenarMerge(a, auxiliar, lo, mid);
		ordenarMerge(a, auxiliar, mid+1, hi);
		merge(a,auxiliar,lo,mid,hi);

	}

	/**
	 * M�todo auxiliar que organiza por el algoritmo MergeSort el arreglo comparable que llega por par�metro.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 271, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 */
	public static void merge(Comparable[] a, Comparable[] auxiliar, int lo, int mid, int hi)
	{
		for (int k = lo; k<=hi; k++) 
		{
			auxiliar[k]=a[k];
		}

		int i = lo;
		int j = mid +1;

		for (int k = lo; k <=hi; k++) 
		{
			if(i>mid)
				a[k]=auxiliar[j++];
			else if(j>hi)
				a[k]=auxiliar[i++];
			else if(menor(auxiliar[j],auxiliar[i]))
				a[k]=auxiliar[j++];
			else
				a[k]=auxiliar[i++];
		}
	}

	/**
	 * M�todo que organiza por el algoritmo QuickSort el arreglo comparable que llega por par�metro.
	 * @post Se bajara aleatoriamente el arreglo antes de aplicar el ordenamiento.
	 * 		 Posteriormente se ordena el arreglo.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 289, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 */
	public static void quickSort(Comparable[] a)
	{
		shuffle(a);
		ordenarQuick(a,0,a.length-1);

	}

	/**
	 * M�todo auxiliar que organiza por el algoritmo QuickSort el arreglo comparable que llega por par�metro.
	 * @post Se bajara aleatoriamente el arreglo antes de aplicar el ordenamiento.
	 * 		 Posteriormente se ordena el arreglo.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 289, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 */
	public static void ordenarQuick(Comparable a[], int lo, int hi)
	{
		if(hi<=lo)
			return;

		int j = particion(a,lo,hi);

		ordenarQuick(a,lo,j-1);
		ordenarQuick(a,j+1,hi);

	}

	/**
	 * M�todo auxiliar que organiza por el algoritmo QuickSort el arreglo comparable que llega por par�metro.
	 * @post Se bajara aleatoriamente el arreglo antes de aplicar el ordenamiento.
	 * 		 Posteriormente se ordena el arreglo.
	 * ACLARACI�N: Este algoritmo fu� tomado textualmente de la p�gina del libro. P�gina 289, Cap�tulo 2, Algorithms 4th edition by Robert Sedgewick and Kevin Wayne
	 * @param a Arreglo que ser� organizado por el algoritmo y que es comparable.
	 */
	public static int particion(Comparable a[], int lo, int hi)
	{
		int i = lo;
		int j = hi+1;
		while(true)
		{
			while(menor(a[++i],a[lo]))
				if(i==hi)
					break;
			while(menor(a[lo],a[--j]))
				if(j==lo)
					break;
			if(i>=j)
				break;
			intercambiar(a, i, j);	
		}
		intercambiar(a,lo,j);
		return j;
	}



}