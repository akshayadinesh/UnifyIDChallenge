
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RandomOrgUnifyIDChallenge {
	
	private final int width = 128;
	private final int height = 128;
	
	public int[] array = new int[128*128];

	private final String USER_AGENT = "akshayadinesh19@gmail.com";

	public static void main(String[] args) {

		RandomOrgUnifyIDChallenge http = new RandomOrgUnifyIDChallenge();

		System.out.println("Sending Http GET request");
		try {
			http.sendGet("10000");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		http.createImage();

	}
	
	public void createImage() {
		System.out.println("Creating bitmap...");
		
		try {
			sendGet("6384");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		int index = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				img.setRGB(i, j, array[index]);
				index++;
			}
		}
	}

	// HTTP GET request
	public void sendGet(String num) throws Exception {

		String url = "https://www.random.org/integers/?num="+num+"&min=0&max=255&col=1&base=10&format=plain&rnd=new";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		int index = 0;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			if(index < 128*128) {
				array[index] = Integer.parseInt(inputLine);
				index++;
			}
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
	}

}
