import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
//Gerekli kütüphaneler import edildi.
public class Server {
public static void main(String args[]) throws IOException
{
	boolean boolDetect = true;
	Random rand = new Random();
	int number,result,number2,counter=0;
    number2 = rand.nextInt(10)+1;
	ServerSocket s1 = new ServerSocket(7985);
	Socket ss = s1.accept();                            //ödevde belirtildiği gibi accept ile bekliyor.
    Scanner sc = new Scanner(ss.getInputStream());
    PrintWriter pr = new PrintWriter(ss.getOutputStream());
    pr.println(number2); //number 2 değişkenini client'a gönderdi
    pr.flush();
	while(boolDetect)
	{
	InputStream in=ss.getInputStream();
	BufferedReader bin = new BufferedReader(new InputStreamReader(in)); 
		
	number = Integer.parseInt( bin.readLine()); //clienttan number alındı ve readLine ile değer okundu
	if(number > 10 || number < 0 )
	{
		System.out.println("girdiginiz sayi 0-10 arasinda degil program kapatiliyor.");
		break;
	}
	if(counter == 2 && number != number2) // clienttan gelen değer ile rand edilen değerin kıyaslanması.
	{
	result = 4;
	PrintWriter pout = new PrintWriter(ss.getOutputStream(),true);
	pout.println(result); 
	boolDetect = false;
	}
	
	if(number == number2) //Doğru durumu (doğru olduğu için counter'ın arttırılmasına gerek yok program bu noktada sonlanacak.)
	{
	result = 1;
	PrintWriter pout = new PrintWriter(ss.getOutputStream(),true);
	pout.println(result); boolDetect=false;
	}

	else if(number>number2) //büyük durumu
	{
	result= 3;
	PrintWriter pout = new PrintWriter(ss.getOutputStream(),true);
	pout.println(result);
	counter++;					   //Counter arttırılıyor böylece 3 clienta 3 şans tanınmış oluyor.
	}

	else if(number < number2)      //küçük durumu                 
	{
	result = 2;	
	PrintWriter pout = new PrintWriter(ss.getOutputStream(),true);
	pout.println(result);
	counter++;					   //Counter arttırılıyor böylece 3 clienta 3 şans tanınmış oluyor.
	}
	}
	ss.close();
	
}
}