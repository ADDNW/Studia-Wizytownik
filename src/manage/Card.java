package manage;

import exeptions.BadDataFormatExeption;

public class Card {
	//Card Version data
	private static double version = 1.0;
	private static String[] dataFormat = {"NAME","ADRESS","PHONE","EMAIL","WEB"};

	//Single Card data
	private String name;
	private String adress;
	private String phone;
	private String email;
	private String web;
	
	
	public Card(String[] card) throws BadDataFormatExeption {
		setCard(card);
	}
	
	public Card() {
	}

	public static double getVersion(){
		return version;
	}

	public static String[] getDataFormat(){
		return dataFormat;
	}

	public String[] getCard() {		
		String[] card = {name,adress,phone,email,web};
		return card;
	}

	
	public void setCard(String[] card) throws BadDataFormatExeption {
		if(	
				card[0].matches("[a-zA-Z0-9 ]+") && 
				card[1].matches("[a-zA-Z0-9 ]+") &&
				card[2].matches("[0-9]+") &&
				card[3].matches("[a-zA-Z0-9@.]+") &&
				card[4].matches("[a-zA-Z0-9./:]+"))
			{
				this.name = card[0];
				this.adress = card[1];
				this.phone = card[2];
				this.email = card[3];
				this.web = card[4];
			}
			else {
				throw new BadDataFormatExeption();
			}
		
	}
}