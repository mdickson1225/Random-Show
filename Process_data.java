/* Scott Dickson
 * Mark Dickson
 * 12/21/2017
 * process_data.java
 */

import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;

/**
 *
 * @author Mark
 */
public class Process_data {
    
    /*Urls to webpages of lists of each type of trick */
    String base_url;
	String rope_url;
    String coin_url;
    String silk_url;
    String card_url;
    /* Arrays to store data from last call of "gettricks" 
     * to be later stored in a text file */
    
    String[][] rope_data;
    String[][] coin_data;
    String[][] silk_data;
    String[][] card_data;
    
	public Process_data(){
		base_url = "https://fabmagic.com";
		card_url = "https://fabmagic.com/t/card-tricks";
		coin_url = "https://fabmagic.com/t/coin--money-magic";
		rope_url = "https://fabmagic.com/t/rope-magic";
		silk_url = "https://fabmagic.com/t/silk-magic";	
	}
	
	/* Given a TrickType return the associated URL as set in the constructor */
	private String getUrl(TrickType type){
		return (type == TrickType.CARD) ? card_url : (type == TrickType.COIN) ? coin_url : (type == TrickType.SILK) ? silk_url : rope_url;
	}

	
	/* Given a data array and a type update the instance variable corresponding
	 * to the most recent data for that type */
	private void updateData(TrickType type, String[][] data) {
		switch(type) {
		case CARD:
			card_data = data;
			break;
		case COIN:
			coin_data = data;
			break;
		case SILK:
			silk_data = data;
			break;
		case ROPE:
			rope_data = data;
			break;
		default:
			break;
		}
	}
	
	/* Given html of a specific item parse out its name and purchase URL
	 * and return a 1 by 2 array of this information */
	private String[] getInfo(String html) {
		String[] lst = new String[2];
		
		//Offset to strip newlines and spaces 
		int nameStart = html.indexOf('>') + 3;
		int nameEnd = html.indexOf('<', nameStart) - 1;
		
		int fstEq = html.indexOf('=');
		int sndEq = html.indexOf('=',fstEq+1);
		
		
		int urlStart = sndEq + 2;
		int urlEnd = nameStart - 4;
		
		
		lst[0] = html.substring(nameStart, nameEnd);
		lst[1] = base_url + html.substring(urlStart, urlEnd);
		
		//System.out.printf("Entered html parse with html:\n%s\n",html);
	
		//System.out.printf("Parsed trick name: %s\n", lst[0]);
		//System.out.printf("Parsed trick url: %s\n", lst[1]);
		
		return lst;
	}
	
	
	
	/* Returns a random array of trick names and urls of size [n] by 2 for trick type [type]. */
	public void getTricks(TrickType type, int n){
		
		String[][] res = new String[n][2];
		
		String pageHTML = ""; //Create giant string of the webpage 	
		try {	
			URL url = new URL(getUrl(type));
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream() ) );
			String str;
			while((str = in.readLine()) != null) {
				System.out.println((str = in.readLine()) != null);
				str = in.readLine();
				//if(str == null) {
			
					//System.out.println("Reached a null in the html parsing");
					//break;
				//} WTF is going on here and why does only this work with fabmagic?
				//System.out.println(str);
				pageHTML += str; //Build up the html
			}
			in.close();
	    } catch(MalformedURLException e){
	    	System.out.println("Bad URL");
	    } catch(IOException e){
	    	System.out.println("IO Exception");
	    }

		///JSoup parsing
			
		Document doc = Jsoup.parse(pageHTML);
		//System.out.print(pageHTML);
		Elements items = doc.select("div[class=product-title]");
		//System.out.println(items.toString());
		ArrayList<String> lst = new ArrayList<String>();
		HashSet<Integer> used = new HashSet<Integer>();
				
		for(Element e : items) {
			lst.add(e.toString());
		}
		
		
		Random rnd = new Random();
		int x;
		int i = 0;
		
		//If too many tricks were requested
		if(lst.size() < n) {
			System.out.println("Too many tricks requested");
			return;
		}
		
		//Generate n random tricks then parse them 
		while(used.size() < n) {
			x = rnd.nextInt(lst.size());
			if(!used.contains(x)) {
				res[i] = getInfo(lst.get(x));
				used.add(x);
				i++;
			}
		}
		
		System.out.println("Finished");
		
		updateData(type,res);
		
	    return;

	}
	
	
	/* Given a filename store the text contents of the most
	 * recent calls of getTricks into that file in a readable format. */
	public void storeData(String filename) {
		try {
			PrintWriter print = new PrintWriter(new FileWriter(filename, false));
			
			print.printf("***Randomly Generated Magic Show!***\n");
			
			
			if(rope_data != null) {
				print.printf("Rope Tricks:\n");
				print.printf(formatted(rope_data));
			}
			
			if(silk_data != null) {
				print.printf("Silk Tricks:\n");
				print.printf(formatted(silk_data));
			}
			
			if(coin_data != null) {
				print.printf("Coin Tricks:\n");
				print.printf(formatted(coin_data));
			}
			
			if(card_data != null) {
				print.printf("Card Tricks:\n");
				print.printf(formatted(card_data));
			}
			
			print.close();
		} catch (IOException e) {
			System.out.println("IO Exception in print text");
		}
	}
	
	/* Write the same data to a well formatted Latex document */
	public void storeLatex(String filename) {
		try {
			PrintWriter pr = new PrintWriter(new FileWriter(filename, false));
			
			/* Set up the document header */
			pr.printf(		
			"\\documentclass{article}\r\n" + 
			"\\usepackage{hyperref}\r\n" + 
			"\\hypersetup{colorlinks=true, linkcolor=blue,filecolor=magenta, urlcolor=blue}\r\n" + 
			"\\usepackage{amsfonts}\r\n" + 
			"\\begin{document}\r\n" + 
			"\\begin{center}\r\n" + 
			"\\huge{\\textbf{Randomly Generated Show}}\r\n" + 
			"\\end{center}\r\n");
			
			/* Print all available data */
			if(rope_data != null) {
				pr.printf("\\Large{Rope Tricks}\r\n");
				pr.printf("\\begin{enumerate}\r\n");
				pr.printf(latex_formatted(rope_data));
				pr.printf("\\end{enumerate}\r\n");
			}
			
			if(silk_data != null) {
				pr.printf("\\Large{Silk Tricks}\r\n");
				pr.printf("\\begin{enumerate}\r\n");
				pr.printf(latex_formatted(silk_data));
				pr.printf("\\end{enumerate}\r\n");
			}
			
			if(coin_data != null) {
				pr.printf("\\Large{Coin Tricks}\r\n");
				pr.printf("\\begin{enumerate}\r\n");
				pr.printf(latex_formatted(coin_data));
				pr.printf("\\end{enumerate}\r\n");
			}
			
			if(card_data != null) {
				pr.printf("\\Large{Card Tricks}\r\n");
				pr.printf("\\begin{enumerate}\r\n");
				pr.printf(latex_formatted(card_data));
				pr.printf("\\end{enumerate}\r\n");
			}
						
			/* Print the footer */
			pr.printf("\\end{document}");
			pr.close();
			
		} catch (IOException e) {
			System.out.println("IO Exception in print Latex");
		}
		
		
	}
	

	/* Given an array of data return it as a well formatted single string */
	private String formatted(String[][] data) {
		String ret = "";
		for(int i = 0; i < data.length; i++) {
			ret += "-" + data[i][0] + ", URL: " + data[i][1] + "\n";
		}
		return ret;
	}

	/* Given an array of data a string of latex items representing it */
	private String latex_formatted(String[][] data) {
		String ret = "";
		for(int i = 0; i < data.length; i++) {
		  ret += "\\item \\large{" + data[i][0] + ", \\href{" + data[i][1] + "}{Item Link}}\r\n";
		}
		return ret;
	}
	
	
	}//End class

