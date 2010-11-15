package providers;

public class Radio2Provider extends Provider{
	public Radio2Provider(){
		super("Radio 2", "http://internetradio.vrt.be/internetradio_master/productiesysteem2/song_noa/noa_22.xml");
	}
	
	@Override public String[] getNOA(){
		String[] result = super.getNOA();
		if(result[1].equals("geen plaatinfo beschikbaar")){
			result[1] = "";
		}
		return result;
	}
}
