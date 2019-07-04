package main;
import exeptions.BadCardFormatExeption;
import manage.Card;
import window.AplicationView;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			AplicationView App = new AplicationView();
		} catch (BadCardFormatExeption e) {
			System.out.println("Unalbe to prepare Table");
		}
		

	}

}
