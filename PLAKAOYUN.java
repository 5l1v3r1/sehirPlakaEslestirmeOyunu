package dosyaIslem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PLAKAOYUN {
	public static final int boyut=81;
	File dosya;
	FileReader fr;
	FileWriter fw;
	BufferedReader oku;
	BufferedWriter yaz;
	File yazDosya;
	ArrayList<String> isimliste;
	ArrayList<String> noliste;
	String[] diziIsim;
	String[] diziPlaka;
	int[] dizi;
	Random rnd;
	int[] sonucDizi;
	
	PLAKAOYUN() throws IOException {
		try {
			dosya = new File("sehirplaka.txt"); 	
			fr=new FileReader(dosya);
			oku = new BufferedReader(fr); 
			isimliste=new ArrayList<String>();
			noliste=new ArrayList<String>();
			diziIsim=new String[2];
			diziPlaka=new String[2];
			dizi=new int[4];	
			rnd=new Random();
			sonucDizi=new int[2];
			
			sehirPlakaAta();
			randomAtamalar();
			sonucYazdir(sonucDizi, 2);
			
		oku.close();
		}
		catch (IOException e) {
			e.toString();
		}  
	}
	
	//ArrayListlere Sehir Ve Plaka Degerlerini Okuyup Atama
	public void sehirPlakaAta() throws IOException {	
		int i=0, j=0;
		
		while(i<boyut) {			
			if(j%2==0) {
				isimliste.add(oku.readLine()); 
				i++;
				noliste.add(oku.readLine());
			}		
		j++;
		}
	}
	
	//Random Say�lar Atayarak Random �ekilde Ekrana Yazd�rma
	public void randomAtamalar() {
		Scanner gir=new Scanner(System.in);
		int i, j, sayac, sayi, dogru=0, tur=0, indis;
		do {	
			
			//4 Tane Random Sayi Atama Ve Bunlar�n Birbirinden Farkl� Olmas�n� Kontrol Etme
			do {
				sayac=0;
				for(i=0; i<4; i++) {
					dizi[i]=rnd.nextInt(boyut);
				}
				for( i=0; i<4; i++){     
					for (j=i+1; j<4; j++) {
						if(dizi[i]==dizi[j]) {      	
							sayac++;
						}
					}
				}
			}while(sayac!=0);
			
			
			//Bir Diziye Atanan Random Say�lar� 
			for(i=0; i<2; i++) {
				diziIsim[i]=isimliste.get(dizi[i]);	
			}
			j=0;
			for(i=2; i<4; i++) {
				diziPlaka[j]=noliste.get(dizi[i]);
				j++;
			}
			indis=rnd.nextInt(2);
			if(indis==0) {
				diziIsim[0]=isimliste.get(dizi[0]);
				diziPlaka[0]=noliste.get(dizi[0]);
			}
			else {
				diziIsim[1]=isimliste.get(dizi[1]);
				diziPlaka[1]=noliste.get(dizi[1]);
			}
			System.out.println("\nPlaka �ehir E�le�tirme Oyununa Ho�geldiniz!"+"\t\tDogru : "+dogru+"\tTur : "+tur);
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("#Oyundan ��kmak i�in (0) giriniz#\n");
			System.out.println("1) "+diziIsim[0]+" = "+diziPlaka[0]+"\t\t2) "+diziIsim[1]+" = "+diziPlaka[1]);
			System.out.print("\nSizce Hangi e�le�me do�rudur? ");
			sayi=gir.nextInt();
			if(sayi==indis+1) {
				dogru++;
			}
			if(sayi==indis+2) {  // En sonda s�f�r girildi�inde bu sat�r� yazmas�n diye kontrol yapt�m. else demedim
				System.err.println("\n-> �ZG�N�Z :( YANLI� CEVAP G�RD�N�Z <-");
			}
			tur++;
		}while(sayi!=0);
		System.out.println("\n***********************************************************************************");
		System.out.println("\nPlaka �ehir E�le�tirme Oyunu �statistikleri: " +
                " \nToplam Tur Say�s�              : "+--tur+
                " \nToplam do�ru e�le�tirme say�s� : "+dogru+
                " \n\n----G�le G�le---- ");
		sonucDizi[0]=tur;
		sonucDizi[1]=dogru;
		gir.close();
	}
	
	//Sonu�lar� Texte Yazd�rma
	public void sonucYazdir(int sonucDizi[], int boyut) throws IOException {
		String str1="Toplam Tur Say�s�:";
		String str2="\nToplam Do�ru E�le�tirme Say�s�:";
		yazDosya=new File("E:\\JAVA\\dosyaIslem\\sonu�lar.txt");
		fw=new FileWriter(yazDosya,false);
		yaz=new BufferedWriter(fw);
		yaz.write(str1+sonucDizi[0]);
		yaz.write(str2+sonucDizi[1]);
		yaz.flush();
		if(!yazDosya.exists()) {
			yazDosya.createNewFile();
		}
		yaz.close();
	}
	
	
	public static void main(String[] args) throws IOException {
		new PLAKAOYUN();
	}
}