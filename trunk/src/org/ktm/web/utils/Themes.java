package org.ktm.web.utils;

public enum Themes {
	black_tie("black-tie"),
	blitzer("blitzer"),
	cupertino("cupertino"),
	dot_luv("dot-luv"),
	eggplant("eggplant"),
	excite_bike("excite-bike"),
	flick("flick"),
	hot_sneaks("hot-sneaks"),
	humanity("humanity"),
	le_frog("le-frog"),
	mint_choc("mint-choc"),
	overcast("overcast"),
	pepper_grinder("pepper-grinder"),
	redmond("redmond"),
	smoothness("smoothness"),
	south_street("south-street"),
	start("start"),
	sunny("sunny"),
	swanky_purse("swanky-purse"),
	trontastic("trontastic"),
	ui_darkness("ui-darkness"),
	ui_lightness("ui-lightness"),
	vader("vader");
	
	private String name;
	
	private Themes(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
