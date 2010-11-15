package providers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import util.Introspection;


public abstract class Provider {
	
	private final String name;
	private final String url;
	
	protected Provider(String name, String url){
		this.name = name;
		this.url = url;
	}
	
	public String[] getNOA(){
		Random rm = new Random();
		HttpClient h = new HttpClient();
		HttpMethod m = new GetMethod(url+"?"+rm.nextDouble());
		String Artiest="";
		String Titel="";
		try {
			h.executeMethod(m);
			String respons = m.getResponseBodyAsString();
			int start = respons.indexOf("<item index=\"0\">");
			int startTitel = respons.indexOf("<titlename>", start)+11;
			int stopTitel = respons.indexOf("</titlename>", startTitel);
			int startArtiest = respons.indexOf("<artistname>", start)+12;
			int stopArtiest = respons.indexOf("</artistname>", startArtiest);
			Titel = respons.substring(startTitel, stopTitel);
			Artiest = respons.substring(startArtiest, stopArtiest).toLowerCase().replaceAll("&amp;", "&");
		} catch (Exception e) {
			e.printStackTrace();
			Titel="";
		}
		String[] ret = {Artiest, Titel};
		return ret;
	}
	
	public String getName(){
		return name;
	}
	
	public static List<Provider> getProviders(){
		List<Provider> providers = new ArrayList<Provider>();
		try {
			Class[] klassen = Introspection.getClasses("providers");
			for (Class klasse : klassen) {
				if(!klasse.getName().equals("providers.Provider")){
					providers.add((Provider)klasse.newInstance());
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return providers;
	}

}
