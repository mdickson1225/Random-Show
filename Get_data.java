/* Scott Dickson
 * Mark Dickson
 * 12/21/2017
 * get_data.java
 */

import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;

public class Get_data {
    
    //Urls to webpages of lists of each type of trick
    String stage_url;
    String coin_url;
    String silk_url;
    String card_url;

    /* Blank constructor */
	public Get_data(){
		stage_url = "https://www.magictricks.com/stage-magic.html";
		coin_url = "https://www.magictricks.com/coin-magic-tricks.html";
		silk_url = "";
		card_url = "https://www.magictricks.com/card-magic.html";
	}
	
	private String getUrl(TrickType type){
		return (type == TrickType.STAGE) ? stage_url : (type == TrickType.COIN) ? coin_url : (type == TrickType.SILK) ? silk_url : card_url;
	}

	
	//Given html of a specific item parse out its name and purchase URL
	//and return a 1 by 2 array of this information 
	private String[] getInfo(String html) {
		String[] lst = new String[2];
	
		String nameHtml = Jsoup.parse(html).select("a[href]").toString();
		int start = nameHtml.indexOf('>') + 1;
		int end = nameHtml.indexOf('<',start);
		
		String name = nameHtml.substring(start,end);
		
		System.out.printf("Entered html parse with html:\n%s\n",html);
	
		System.out.printf("Parsed trick name: %s\n", name);
		
		return lst;
	}
	
	
	
	/* Returns list of tricks urls, names of length [n] and trick type [type] 
	 * For each trick store the name of the trick and the purchase link of the trick
	*/
	public String[][] getTricks(TrickType type, int n){
		
		String[][] res = new String[n][2];
		
		String pageHTML = ""; //Create giant string of the webpage 	
		try {	
			URL url = new URL(getUrl(type));
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream() ) );
			String str;
			while((str = in.readLine()) != null) {
				str = in.readLine().toString();
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
			
		Elements items = doc.select("div[class*=name]");
		
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
			return res;
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
		
		System.out.print("Finished");
				
	    return res;

	}
}

