import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.*;
//Gerekli kütüphaneler import edildi.
public class Client {
	
public static void main(String args[]) throws UnknownHostException, IOException
{
	int number,sonuc;
	  boolean correctness=true;
	  Scanner sc=new Scanner(System.in);
		Socket s = new Socket("localhost",7985);
		Scanner sc1= new Scanner (s.getInputStream());
		InputStreamReader inppp = new InputStreamReader(s.getInputStream());
		BufferedReader bf = new BufferedReader(inppp);
		String sayi = bf.readLine(); //Serverdan gelen veriyi sayi'değişkenine atadı.
	  while(correctness)
	  {
	
	System.out.println("1-10 arasinda bir sayi giriniz.");
	number = sc.nextInt();
	
	
	PrintWriter pout = new PrintWriter(s.getOutputStream(),true);
	pout.println(number);     //number degiskeni servera gönderildi.
	
	InputStream in=s.getInputStream(); 
	BufferedReader bin = new BufferedReader(new InputStreamReader(in));
	sonuc=Integer.parseInt( bin.readLine());	 //Serverdan veri alındı, alınan veri sonuc degiskenine assign edildi.
	if(sonuc==1)
	{
		System.out.println("Dogru"); correctness= false; //correctness'a yapılan atamayla döngü yani oyun biter.
	} 
	else if(sonuc==2)
	{
		System.out.println("Yukselt!"); //Sayı küçük durumu
	}
	else if(sonuc==3)
	{
		System.out.println("Dusur!"); //Sayı büyük durumu
	} 
	 if(sonuc==4)
	{
		System.out.println("Yanlis- Olmasi gereken rakam : "+ sayi ); correctness=false;
	} //correctness'a yapılan atamayla döngü yani oyun biter.
}
s.close(); //soket kapatılır.
}}