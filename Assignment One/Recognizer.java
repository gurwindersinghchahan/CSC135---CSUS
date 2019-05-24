import java.io.*;
import java.util.Scanner;
import java.util.Scanner;
//--------------------------------------------
// Recognizer for Assignment 1
// Written by Gurwinder Singh  on (03/02/2019)

// to run on Athena (linux) -
//    save as:  Recognizer.java
//    compile:  javac Recognizer.java
//    execute:  java Recognizer
// ------------------------------------------   
public class Recognizer
{
  static String inputString;
  static int index = 0;
  static int errorflag = 0;

  private char token()
  { return(inputString.charAt(index)); }

  private void advancePtr()
  { if (index < (inputString.length()-1)) index++; }

  private void match(char T)// Match method which was already provided by professor
  { 
   if (T == token()) 
         advancePtr(); 
    else 
         error(); }

  private void error()// error method which is provided by professor
  {
    System.out.println("error at position: " + index);
    errorflag = 1;
    advancePtr();
  }

  private void program()// Programm method which checks the S token then have the option to run their statement 0 or more times.
  { 
     match ('S'); // Executing the first command in syntax diagram
     
     while ((token()=='W')|| (token()=='X')|| (token()=='Y')|| (token()=='Z')|| (token()=='I')||(token()=='D')||(token()=='R')||(token()=='O')||(token()=='C'))// Checking if any of these tokens are in input then call statemt function
     {
      statemt(); 
     }
     
  }
  
  private void statemt()// statemt methods 
  { 
      if ((token()=='W')|| (token()=='X')|| (token()=='Y')|| (token()=='Z'))
      {
         assnmt();
      }
      else if ((token()=='I'))
      {
         ifstmt();
      }
      else if ((token()=='D'))
      {
         doo();
      }
      else if ((token()=='R')|| (token()=='O'))
      {
         inout();
       }
      else if ((token()=='C'))
      {
         progcall();
      }
      
  }
  private void assnmt()// assnmt method inwhich we call ident method and match ~ token and call exprsn method
 { 
   ident();
   match( '~');
   exprsn();
   match(';');
  }
  private void ifstmt()// if statemnt method which is first invoked by statemnt
 {
   match ('I');
   comprsn();
   match ('@');
   while ((token()=='W')|| (token()=='X')|| (token()=='Y')|| (token()=='Z')|| (token()=='I')||(token()=='D')||(token()=='R')||(token()=='O')||(token()=='C'))// Checking if any of these tokens are in input then call statemt function
   {
      statemt();
    }
   if (token()=='%')
   {
      match ('%');
      while ((token()=='W')|| (token()=='X')|| (token()=='Y')|| (token()=='Z')|| (token()=='I')||(token()=='D')||(token()=='R')||(token()=='O')||(token()=='C'))// Checking if any of these tokens are in input then call statemt function
      {
         statemt();
      }
    }
     match('&');
 }
  private void doo()// do methoed which is firstly invoked by statemt method
 {
   match('D');
   while ((token()=='W')|| (token()=='X')|| (token()=='Y')|| (token()=='Z')|| (token()=='I')||(token()=='D')||(token()=='R')||(token()=='O')||(token()=='C'))// Checking if any of these tokens are in input then call statemt function
   {
      statemt();
   }
   match ('U');
   comprsn();
   match ('E');
 }
  private void inout()// inout method, here we make sure ident method is invoked by one or more times 
  {
   iosym();
   ident();
   while (token() == ',')//loops represent the decision points
   {
       match(',');
       ident();
    }
    match (';');
  }
  private void progcall ()//progcall method
  {
   match('C');
   program();
   match ('G');
  }
 private void exprsn()//exprsn method
 {
   factor();
   while (token()=='+')// 0 or more, loops represent the decision point
   {
            match('+');
            factor();
    }
 }
 private void comprsn()// in this method there are no decision points. 
 {
   
   match('(');
   oprnd();
   opratr();
   oprnd();
   match(')'); 
 }
 private void iosym()// iosym method
 {
   if (token()=='R')
   {
      match('R'); 
   }
   else if (token() =='O')
   {
      match('O');
   }
    else 
    {
      error();
    } 
 }
 private void factor()//factor method
 {
   oprnd();
   while (token()=='*')//loop represent the decision points
      {
        match('*');
        oprnd();
     }
 }
 
 private void oprnd()//operand method
 	{
   if ((token()=='0')||(token()=='1'))
   {
      integer();
   }
   else if ((token()=='W')||(token()=='X') || (token()=='Y')||(token()=='Z'))
   {
      ident();
    }
   else if ((token()=='T')||(token()=='F'))
   {
      bool();
   }
   else if ((token()=='('))  
   {
      match('(');
      exprsn();
      match(')');
    }
 }
    
 private void opratr()//opratr method, matching the tokens
 {
    if ((token()=='<'))
    {
      match('<');
    }
    else if ((token()=='='))
    {
      match('=');
    }
    else if ((token()=='>'))
    {
      match('>');
    }
    else if ((token()=='!'))
    {
      match('!');
    }
    else if (token()=='^')
    {
      match('^');
    }
 }
 private void ident()
	{
    letter();//calling letter method
    while ((token()=='W')|| (token()=='X') || (token()=='Y') || (token()=='Z') || (token()=='0') || (token()=='1'))
    {
      charc();//calling charc method
    }
	} 
 private void charc()
 	{
      if ((token()=='0') || (token()=='1'))//call digit method if input has 0 or 1 otherwise call letter method
      {
         digit();
      }
     else 
         {
            letter();
         }        
 }
 private void integer()//integer function
 {
    do 
    {
      digit();
    }
    while ((token()=='0') || (token()=='1')); 
 } 
 
 private void letter()//letter function
 {
    if ((token()=='W'))
    {
      match('W');
    }
    else if (token()=='X')
    {
      match('X');
    }
    else if (token()=='Y')
    {
      match('Y');
    }
    else if (token()=='Z')
    {
      match('Z');
    }
   
 }
 private void digit()//digit function
 {  
    if (token()=='0')
    {
      match('0');
    }
   
    else if ((token()=='1'))
    {
      match('1');
    }
   
 }
 private void bool()//boolean method
 {
    if ((token()=='T'))
    {
      match('T');
    }
    else if ((token()=='F'))
    {
      match('F');
    }  
 }

  private void start()//start method which calls the programs and match the $ which represent the end of the string
  {
    program();//call to program method 
    match('$');// terminator of the string

    if (errorflag == 0)
      System.out.println("legal." + "\n");
    else
      System.out.println("errors found." + "\n");
  }
//Main function  gets the iput from the user and calls the start method
  public static void main (String[] args) throws IOException
  {
    Recognizer rec = new Recognizer();
    Scanner input = new Scanner(System.in);
    System.out.print("\n" + "Please enter an expression: ");
    
    inputString = input.nextLine();
    rec.start();
  }
}

