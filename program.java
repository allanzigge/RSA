package cryptographi;
import java.util.Scanner;

public class program {

	public static void main(String[] args) {
		//krypter
		int p = 419; //419 	11 
		int q = 809; //809	17
		int n = p*q;
		int k = 7; 
		Scanner scan = new Scanner(System.in); //Scanner for scanning inputs 
		
		System.out.println("Insert your message (m): "); //Message input from user 
		String message = scan.nextLine();	//Scanning input 
		int m =Integer.parseInt(message); //Convert from string to integer 

		
		long c = fakto(m,k,n); //m^k %n Se Formel 1
		
		
		System.out.println("p "+p+ "\nq "+q+"\nn "+n+"\nk "+k+"\nc "+c);
		
		//dekrypter
		int A = k;
		int B = (p-1)*(q-1);
		
		int D1 = A/B; //Udregninger på divisorer og rester ses i udregning 6
		int R1 = A%B;
		int D2 = B/R1;
		int R2 = B%R1;
		int D3 = R1/R2;
		int R3 = R1%R2;
		long u1 = 1;
		long v1 = 1;
		
		
		if (R2 == 1) { //hvis resten er 1 efter kun 2 omgange
			u1 = (long) -D2;//I sætning 4 har jeg vist hvordan jeg kom frem til disse resultat
			v1 = (long) -(1-D1*D2); 
			
		} else if (R3 == 1){ //3 omgange
			u1 = (long) 1+(D2*D3);//I sætning 3 har jeg vist hvordan jeg kom frem til disse resultat
			v1 = (long) D1+D3+D1*D2*D3;
		}
		else {
			System.out.println("Error 40");
		}
	
		
		if (u1 <1 |v1<1) { //Hvis u eller v er negative, skal jeg addere henholdsvis b og a
			u1 = u1+B;
			v1 = v1+A; 
		}
		
		long d = fakto(c,u1, n); // c^u1 %n Se Formel 2
		
		System.out.println("\nDekryptering:\nA " + A + "\nB " +B+"\nD1 "+D1+"\nR1 "+R1+"\nD2 "+D2+"\nR2 "+R2+"\nD3 "+D3+"\nR3 "+R3);
		System.out.print("\nU "+u1+"\nV "+v1+"\nDekrypteret: "+ d);
	}
	
	static long fakto(long c, long u1, int n) { //c = tekst; u1 = potens ; n = produkt af p&q (modolu) 
		long rest = 1; //definerer variablen
		if (u1%2 == 1) { //Se udregning 7
			rest = (long) c%n; //Se udregning 8 (c^1 = c)
			u1 = u1-1;
		}
		
		while (u1>=2) {
			rest = (long) (rest*((c*c)%n)%n); //Se udregning 9
			u1 = u1-2;
		}
		return rest;	
	}
}