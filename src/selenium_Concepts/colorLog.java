package selenium_Concepts;

public class colorLog {

	public static void main(String[] args) {
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
		final String ANSI_RED_BACKGROUND = "\u001B[41m";
		final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
		final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
		final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
		final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
		final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
		final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
		final String ANSI_RESET = "\u001B[0m";

		System.out.println(ANSI_GREEN_BACKGROUND + "This text has a green background but default text!" + ANSI_RESET);
		System.out.println(ANSI_RED + "This text has red text but a default background!" + ANSI_RESET);
		System.out.println(ANSI_GREEN_BACKGROUND + ANSI_RED + "This text has a green background and red text!" + ANSI_RESET);


	}

}
