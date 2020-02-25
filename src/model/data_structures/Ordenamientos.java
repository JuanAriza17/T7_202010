package model.data_structures;

public class Ordenamientos 
{

	
	public static boolean menor (Comparable a,Comparable b)
	{
		return a.compareTo(b)<0;
	}
	
	public static void intercambiar(Comparable[] a, int i, int j)
	{
		Comparable swap = a[i];
		a[i]=a[j];
		a[j]=swap;
	}

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
	
	public static void mergeSort(Comparable a[])
	{
		Comparable[] auxiliar = new Comparable[a.length];
		
		ordenarMerge(a,auxiliar,0,a.length - 1);
	}
	
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
	public static void quickSort(Comparable[] a)
	{
		shuffle(a);
		ordenarQuick(a,0,a.length-1);
		
	}
	public static void ordenarQuick(Comparable a[], int lo, int hi)
	{
		if(hi<=lo)
			return;
		
		int j = particion(a,lo,hi);
		
		ordenarQuick(a,lo,j-1);
		ordenarQuick(a,j+1,hi);
		
	}
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

}
