package main;

public class Lang {

	//Languages
	public static int en = 0;
	public static int pl = 1;
	public static int ru = 2;
	public static int nl = 3;
	
	public static int Languages = 4;
	
	public static String english = "English";
	public static String polish = "Polski";
	public static String russian ="Русский";
	public static String dutch = "Nederlands";

	//Menu stringss
	public static String resume;
	public static String newGame;
	public static String loadGame;
	public static String options;
	public static String credits;
	public static String quit;
	public static String back;
	public static String language;
	public static String loading;
	
	public static int currentLanguage = en;

	
	public static void init() {
		if(currentLanguage == en) {
			resume = "Resume";
			newGame = "New Game";
			loadGame = "Load Game";
			options = "Options";
			credits = "Credits";
			quit = "Quit";
			back = "Back";
			language = "Language";
			loading = "Loading...";
			
			//english = "English";
			//polish = "Polish";
			//russian = "Russian";
			//dutch = "Dutch";
			
		}
		if(currentLanguage == pl) {
			resume = "Wznów grę";
			newGame = "Nowa gra";
			loadGame = "Wczytaj grę";
			options = "Opcje";
			credits = "Autorzy";
			quit = "Wyjdź";
			back = "Powrót";
			language = "Język";
			loading = "Wczytywanie...";
			
			//english = "Angielski";
			//polish = "Polski";
			//russian = "Rosyjski";
			//dutch = "Holenderski";
		}
		if(currentLanguage == ru) {
			resume = "Продолжить";
			newGame = "Новая Игра";
			loadGame = "Загрузить Игру";
			options = "Опции";
			credits = "Авторы";
			quit = "Выйти";
			back = "Назад";
			language = "Язык";
			loading = "Загрузка...";

			//english = "Английский";
			//polish = "Польский";
			//russian = "Русский";
			//dutch = "Голландский";
		}
		if(currentLanguage == nl) {
			resume = "Doorgaan";
			newGame = "Nieuw Spel";
			loadGame = "Laad Spel";
			options = "Instelling";
			credits = "Auteurs";
			quit = "Stoppen";
			back = "Terug";
			language = "Taal";
			loading = "Laden...";
			
			//english = "Engels";
			//polish = "Pools";
			//russian = "Russies";
			//dutch = "Nederlands";
		}
	}
	
	public static String currentLanguage(int a) {
		String currentLanguageString = null;
		if(a == en) {
			currentLanguageString = Lang.english;
		}
		if(a == pl) {
			currentLanguageString = Lang.polish;
		}
		if(a == ru) {
			currentLanguageString = Lang.russian;
		}
		if(a == nl) {
			currentLanguageString = Lang.dutch;
		}
		return currentLanguageString;
	}
	
	public static void changeLanguage(int language) {
		currentLanguage = language;
		init();
		
	}
	
}
