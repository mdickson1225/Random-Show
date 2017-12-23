/* Scott Dickson
 * Mark Dickson
 * 12/21/2017
 * get_data.java
 */

import java.net.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

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
				System.out.println(str);
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
		
		
		
		
		
	    return res;

	}
}
