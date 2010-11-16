package providers.implementations;

import providers.Provider;

public class MNMProvider extends Provider {
	public MNMProvider() {
		super("MNM",
				"http://internetradio.vrt.be/internetradio_master/productiesysteem2/song_noa/noa_55.xml");
	}

	@Override
	public String[] getNOA() {
		String[] result = super.getNOA();
		if (result[1].equals("Let's have a Big Time"))
			result[1] = "";
		return result;
	}
}
