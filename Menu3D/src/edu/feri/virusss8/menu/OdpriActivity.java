package edu.feri.virusss8.menu;

import android.content.Intent;

public class OdpriActivity{
	Menu3DActivity d;
	
	OdpriActivity(Menu3DActivity b) {
		d = b;
	}
	
	public void whichActivity(int par) {
		System.out.println("DELUJE!!!");
		switch (par) {
		case 1:
			Intent namen1 = new Intent(d, OdpriPrvi.class);
			d.startActivityForResult(namen1, 1);
			break;
		case 2:
			Intent namen2 = new Intent(d, OdpriDrugi.class);
			d.startActivity(namen2);
			break;
		case 3:
			Intent namen3 = new Intent(d, OdpriTretji.class);
			d.startActivity(namen3);
			break;
		case 4:
			Intent namen4 = new Intent(d, OdpriCetrti.class);
			d.startActivity(namen4);
			break;
		default:
			break;
		}
	}
	
}
