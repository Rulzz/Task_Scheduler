package com.chatbot.aws;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;




public class test {

	
	 /**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 int[][] multi = new int[5][5];
		 int[][] visit = new int[5][5];
		 Scanner inp = new Scanner(System.in);
		 String x="";
	        for(int i=0;i<5;i++)
	        {
	        	x="";
	        	for( int j=0;j<5;j++)
	        	{
	        		if(Math.random()<=0.3)
	        		{//System.out.println(i+","+j);
	        		multi[i][j]=1;}
	        		else
	        			{multi[i][j]=0;}
	        	}
	        	
	        }
	        System.out.println(x);
	        System.out.println("MAtrix");
	        
	        for(int i=0;i<5;i++)
	        {
	        	x="";
	        	for( int j=0;j<5;j++)
	        	{
	        		x+=(multi[i][j]+"  ");
	        	}
	        	System.out.println(x);
	        }
	        int h=0;
	        int g=0;
	        int f=0;
	        int value=0;
	        System.out.println("Enter a row of start: ");
	        int xs = inp.nextInt();
	        System.out.println("Enter a column of start: ");
	        int ys = inp.nextInt();
	        System.out.println("Enter a row of goal: ");
	        int xg = inp.nextInt();
	        System.out.println("Enter a column of goal: ");
	        int yg = inp.nextInt();
	        Stack<String> stack =new Stack<String>();
	         stack.push(xs+","+ys);
	         ArrayList<String> al = new ArrayList<String>();
	        System.out.println(stack);
	        if(xs-1<0)
			 {}
		 else
			{
			 value=multi[xs-1][ys];
			 System.out.println("value "+value);
			 if(value==0)
			 {
				 
				 if(visit[xs-1][ys]!=1)
				 {
					 visit[xs-1][ys]=1;
					 System.out.println("in");
					 al.add((xs-1)+","+ys);
					 
					
					/* Node<String> childNode1 = new Node<String>("Child 2");
					 childNode1.setParent(parentNode);*/
					 h=Math.abs((xg-xs))+Math.abs((yg-ys));
						
						g++;
						
						
						 f=g+h;
						 g--;
					 
				 }
			 }
			} 
	        System.out.println(xs+","+(ys-1));
		 if((ys-1)<0)
			 {}
		 else
			 {
			 value=multi[xs][ys-1];
			 System.out.println("value "+value);
			 if(value==0)
				 {
				 
				 if(visit[xs][ys-1]!=1)
				 	{
					 visit[xs][ys-1]=1;
					 al.add((xs-1)+","+ys);
					 System.out.println("in");
					 h=Math.abs((xg-xs))+Math.abs((yg-(ys-1)));
						// g=Math.abs((xstart-xs))+Math.abs((ystart-tempy));
						 g++;
						 /*Node<String> childNode2 = new Node<String>((xs-1)+","+ys);
						 childNode2.setParent(parentNode);*/
						 f=g+h;
						 g--;
					 
				 	}
				 }
			 }
		 System.out.println((xs+1)+","+(ys));
		 if(xs+1>4)
			 {/* alert((parseInt(xs)+1)); */}
		 else
			 {
			// alert(document.getElementById(u+","+(parseInt(xs)+1)+","+ys).getAttribute("name"));
			 value=multi[xs+1][ys];
			 System.out.println("value "+value);
			 if(value==0)
				 {
				 	 
					 if(visit[xs+1][ys]!=1)
					 {
						 al.add((xs-1)+","+ys);
						 System.out.println("in");
						 h=Math.abs((xg-((xs)+1)))+Math.abs((yg-ys));
							// g=Math.abs((xstart-(parseInt(xs)+1)))+Math.abs((ystart-ys));
							 g++;
							 /*Node<String> childNode3 = new Node<String>((xs-1)+","+ys);
							 childNode3.setParent(parentNode);*/
						 f=g+h;
						 g--;
						
					 }
				 }
			 }
		System.out.println(xs+","+(ys+1));
		 if(((ys)+1)>4)
			 {}
		 else
			 {
			 //alert(document.getElementById(u+","+xs+","+(parseInt(ys)+1)).getAttribute("name"));
			 value=multi[xs][ys+1];
			 System.out.println("value "+value);
			 if(value==0)
				 {
				 System.out.println("in");
				 	if(visit[xs][ys+1]!=1)
				 	{
				 		al.add((xs-1)+","+ys);
				 		h=Math.abs((xg-xs))+Math.abs((yg-((ys)+1)));
						 //	g=Math.abs((xstart-xs))+Math.abs((ystart-(parseInt(ys)+1)));
						 	g++;
						 	 /*Node<String> childNode4 = new Node<String>((xs-1)+","+ys);
						 	childNode4.setParent(parentNode);*/
						 	 f=g+h;
						 g--;
						 
				 	}
				 }
			 }
		 stack.addAll(al);
		 System.out.println(stack);
	       // System.out.println("parent "+ parentNode.getChildren().size());
	        
	    }
}


