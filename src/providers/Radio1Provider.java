package providers;

public class Radio1Provider extends Provider{
	public Radio1Provider(){
		super("Radio 1", "http://internetradio.vrt.be/internetradio_master/productiesysteem2/song_noa/noa_11.xml");
	}
	
	@Override public String[] getNOA(){
		String[] result = super.getNOA();
		if(result[1].equals("Radio 1")){
			result[1] = "";
		}
		return result;
	}
}
