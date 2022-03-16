//package org.eda2.practica1;
//
//public class CodigoMErgeshort {
//	   public static Player[] mergesort(Player jugador[]) {
//	    	if (jugador ==null) {
//	    		System.out.println("Array vacio");
//	    		return null;
//	    	}
//	    	return mergesort (jugador);
//	    }
//	    
//	   public static Player[] mergesort (Player jugador[], int start, int end) {
//		if (start==end) return new Player[] {jugador[start]};
//		int middle = (start+end)/2;
//		Player[] jugador1=mergesort(jugador,start,middle);
//		Player[] jugador2=mergesort(jugador,middle+1,end);
//		Player[] jugadorUnido=merge(jugador1,jugador2);
//		
//		   
//		   return jugadorUnido;
//		   
//	   }
//
//	private static Player[] merge(Player[] jugador1, Player[] jugador2) {
//		// TODO Auto-generated method stub
//		if (jugador1==null || jugador2==null) {
//			System.out.println("Uno de los Arrays esta vacio");
//		
//		return null;
//		}
//		Player jugadorUnido[]=new Player[jugador1.length+jugador2.length];
//		int k=0;
//		int i=0;
//		int j=0;
//		while(i<jugador1.length && j<jugador2.length) {
//			if (jugador1[i] < jugador2[j]) {
//				jugadorUnido[k]=jugador1[i];
//				i++;
//			}else {
//				jugadorUnido[k]=jugador2[j];
//			}
//			k++;
//		}
//		while (i<jugador1.length) {
//			jugadorUnido[k]=jugador1[i];
//			i++;
//			k++;
//		}
//		while (j<jugador2.length) {
//			jugadorUnido[k]=jugador2[j];
//			j++;
//			k++;
//		}
//		return jugadorUnido;
//	}
//	   
//}
