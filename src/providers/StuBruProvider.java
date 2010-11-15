package providers;

public class StuBruProvider extends Provider{
	public StuBruProvider(){
		super("Studio Brussel", "http://internetradio.vrt.be/internetradio_master/productiesysteem2/song_noa/noa_41.xml");
	}
	
	@Override public String[] getNOA(){
		String[] result = super.getNOA();
		if(result[1].equals("Geen muziek(info)"))
			result[1] = "";
		return result;
	}
}
