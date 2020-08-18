import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class edit {

	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_RESET = "\u001B[0m";

	static ArrayList<ArrayList<Character>> lines = new ArrayList<ArrayList<Character>>();
	static LinkedList<Character> cLine = new LinkedList<Character>();

	static int currentLine;
	static Scanner in = new Scanner(System.in);
	static FileWriter fWriter;
	static boolean run;
	static char cursorC = '^';
	static int cursorIndex;
	static String inFileName;
	static String outFileName;
	static String path = "C:\\Users\\ahmad_000\\eclipse-workspace\\LineEditor\\src\\";

	public static void main(final String[] args) throws IOException {
		// TODO Auto-generated method stub

		if (args.length == 1)
			inFileName = args[0];

		if (args.length == 2) {
			inFileName = args[0];
			outFileName = args[1];
		}

		// System.out.println(inFileName);
		final FileReader fileReader = fRead(path, inFileName);
		// s = line.get(3);
		//
		run = true;
		// int g = 0;
		// int k;
		currentLine = 0;
		cursorIndex = 0;
		String cmd;
		linePrinter();
		do {
			// lines.remove(currentLine + 1);
			// System.out.print(cursorC);
			System.out.println();
			System.out.print(":");
			cmd = in.next();
			System.out.println();
			runCMD(cmd);
			// clearConsole(g);

			// run = false;
		} while (run);

		if (fileReader != null)
			fileReader.close();
	}

	public static FileReader fRead(final String path, final String inFileName) throws IOException {
		final File inFile = new File(path + inFileName);
		// inFile.createNewFile();
		FileReader fileReader;
		try {
			fileReader = new FileReader(inFile);

			char sc;
			int ss;
			int index = 0;
			lines.add(new ArrayList<Character>());
			do {
				ss = fileReader.read();
				sc = (char) ss;
				if (sc != '\n') {
					if (sc != '\r')
						lines.get(index).add(sc);
					// System.out.print(s);

				}

				if (sc == '\n') {

					// lines.add(line);
					lines.add(new ArrayList<Character>());
					index++;
					// line.clear();
					// System.out.println();
				}

			} while (sc != 65535);
		} catch (final FileNotFoundException e) {
			// TODO: handle exception
			fileReader = null;
			lines.add(new ArrayList<Character>());
			lines.get(0).add(' ');
		}

		return fileReader;
	}

	public static void linePrinter() {
		System.out.print((currentLine + 1));
		System.out.print(">");
		System.out.print(" ");
		// k = (Integer.toString(currentLine)).length() + 2;
		// g = k + lines.get(currentLine).size();
		for (int i = 0; i < lines.get(currentLine).size(); i++) {
			System.out.print(lines.get(currentLine).get(i));
		}

		System.out.println();
		for (int i = 0; i < (Integer.toString(currentLine)).length() + 2; i++) {
			System.out.print(" ");
		}
		// lines.add(currentLine + 1, new LinkedList<Character>());
		for (int i = 0; i < cursorIndex; i++) {
			cLine.add(' ');
		}
		cLine.add(cursorC);

		for (int i = 0; i < cLine.size(); i++) {
			System.out.print(cLine.get(i));
		}
		cLine.clear();

	}

	public static void runCMD(final String cmd) throws IOException {
		switch (cmd) {
			case "A":
				lines.add(new ArrayList<Character>());
				break;
			case "D":
				// lines.remove(currentLine);
				if (lines.size() == 0) {
					lines.add(new ArrayList<Character>());
				}
				lines.remove(currentLine);
				if (currentLine >= lines.size()) {
					currentLine--;
				}
				linePrinter();
				break;
			case "E":
				// System.out.print('\b');

				String s;
				String cmd1;
				boolean runEditMode = true;
				do {
					System.out.println();
					System.out.print("E:");
					cmd1 = in.next();
					switch (cmd1) {
						case "L":
							if (cursorIndex > 0) {
								// lines.get(currentLine + 1).remove(cursorIndex - 1);
								cursorIndex--;
							} else {
								// System.err.println("The cursor can not move left at the begining.");
							}

							break;
						case "R":
							// System.out.println("Iam Here.");
							if (cursorIndex < lines.get(currentLine).size() - 1) {
								// lines.get(currentLine + 1).add(cursorIndex + 1,'^');
								cursorIndex++;
							} else {
								// System.err.println("The cursor can not move right at the end.");
							}
							break;
						case "A":
							s = in.next();
							for (int i = 0; i < s.length(); i++) {
								lines.get(currentLine).add(s.charAt(i));
							}
							break;
						case "I":
							s = in.next();

							for (int i = s.length() - 1; i >= 0; i--) {
								lines.get(currentLine).add(cursorIndex + 1, s.charAt(i));
							}
							break;
						case "C":
							s = in.next();
							for (int i = 0; i < s.length(); i++) {
								lines.get(currentLine).remove(cursorIndex);
							}
							for (int i = s.length() - 1; i >= 0; i--) {
								lines.get(currentLine).add(cursorIndex, s.charAt(i));
							}
							break;
						case "D":
							lines.get(currentLine).remove(cursorIndex + 1);
							break;
						case "E":
							runEditMode = false;
							break;
						default:
							System.out.println("------------------------");
							System.err.println("Wronge Command!");
							System.err.println("Insert H or ? for help.");
							break;
					}
					linePrinter();
				} while (runEditMode);
				// lines.remove(currentLine + 1);
				break;
			case "F":
				// lines.remove(currentLine + 1);
				currentLine = 0;
				linePrinter();
				break;
			case "L":
				// lines.remove(currentLine + 1);
				currentLine = lines.size() - 1;
				linePrinter();
				break;
			case "G":
				// lines.remove(currentLine + 1);
				System.out.print("N:");
				currentLine = in.nextInt() - 1;
				linePrinter();
				break;
			case "S":
				System.out.print("W:");
				final String cmd2 = in.next();
				int sInd;
				lines.remove(currentLine + 1);
				for (int i = 0; i < lines.size(); i++) {
					sInd = LinkedListToString(lines.get(i)).indexOf(cmd2);
					if (sInd != -1) {
						cursorIndex = sInd;
						currentLine = i;
						break;
					}
				}
				linePrinter();
				break;
			case "?":
				help();
				break;
			case "H":
				help();
				break;
			case "I":
				System.out.print("L:");
				in.nextLine();
				final String strLine = in.nextLine();
				System.out.println();
				lines.add(currentLine, new ArrayList<Character>());
				for (int i = 0; i < strLine.length(); i++) {
					lines.get(currentLine).add(strLine.charAt(i));
				}
				linePrinter();
				break;
			case "P":
				System.out.println();
				// lines.remove(currentLine + 1);
				for (int i = 0; i < lines.size(); i++) {
					System.out.print((i + 1) + ": ");
					for (int j = 0; j < lines.get(i).size(); j++) {
						System.out.print(lines.get(i).get(j));
					}
					System.out.println();
				}
				System.out.println();
				break;
			case "N":
				// lines.remove(currentLine + 1);
				if (currentLine < lines.size()) {
					currentLine++;
				}
				linePrinter();
				break;
			case "B":
				// lines.remove(currentLine + 1);
				if (currentLine > 0) {
					currentLine--;
				}
				linePrinter();
				break;
			case "Q":
				run = false;
				break;
			case "R":
				System.out.print("F:");
				final String filename = in.next();
				lines.clear();
				final FileReader fileReader = fRead("", filename);
				fileReader.close();
				linePrinter();
				break;
			case "W":
				saveAction();
				break;
			case "X":
				saveAction();
				run = false;
				break;
			default:
				System.out.println("------------------------");
				System.err.println("Wronge Command!");
				System.err.println("Insert H or ? for help.");
				break;
		}
	}

	public static void saveAction() throws IOException {
		// TODO Auto-generated method stub
		File outFile;

		if (outFileName != null) {
			outFile = new File(outFileName);
			fWriter = new FileWriter(outFile);

			for (int i = 0; i < lines.size(); i++) {
				for (int j = 0; j < lines.get(i).size(); j++) {
					fWriter.append(lines.get(i).get(j));
				}
				if (i < lines.size() - 1)
					fWriter.append('\n');
			}
		} else {
			if (inFileName != null) {
				outFile = new File(inFileName);
				fWriter = new FileWriter(outFile);

				for (int i = 0; i < lines.size(); i++) {
					for (int j = 0; j < lines.get(i).size(); j++) {
						fWriter.append(lines.get(i).get(j));
					}
					if (i < lines.size() - 1)
						fWriter.append('\n');
				}

			} else {
				System.out.print("F:");
				inFileName = in.next() + ".txt";
				outFile = new File(inFileName);
				fWriter = new FileWriter(outFile);

				for (int i = 0; i < lines.size(); i++) {
					for (int j = 0; j < lines.get(i).size(); j++) {
						fWriter.append(lines.get(i).get(j));
					}
					if (i < lines.size() - 1)
						fWriter.append('\n');
				}
				System.out.println();
			}
		}
		
	}

	public static String LinkedListToString(ArrayList<Character> list) {
		String str;
		
		StringBuilder sb = new StringBuilder();
		for (Character ch : list) {
			sb.append(ch);
		}
		str = sb.toString();
		return str;
	}

	public static void help() {
		System.out.println("-----------------------------");
		System.out.println("-----------------------------");
		System.out.println();
		System.out.println("Syntax for running the line editor");
		System.out.println();
		System.out.println("   java edit input_file_name output_file_name    ");
		System.out.println("   java edit input_file_name    ");
		System.out.println("   java edit    ");
		System.out.println();
		System.out.println("The command that user can used to control the editor.");
		System.out.println();
		System.out.println("   A   " + "New line to the end of file.");
		System.out.println();
		System.out.println("   D   " + "Delete the current line and move to the next line");
		System.out.println();
		System.out.println("   E   " + "Starting character editing mode");
		System.out.println("       " + "   L     Move cursor one character left");
		System.out.println("       " + "   R     Move cursor one character right");
		System.out.println("       " + "   A s   Appand a string, s, to the end of line");
		System.out.println("       " + "   I s   Insert a string, s, after the curor");
		System.out.println("       " + "   C s   Change to string, s, from the cursor to length of, s,");
		System.out.println("       " + "   D     Delete the character after the cursor");
		System.out.println("       " + "   E     Exit editing mode");
		System.out.println();
		System.out.println("   F   " + "Goes to and displays the first line.");
		System.out.println();
		System.out.println("   L   " + "Goes to and displays the last line.");
		System.out.println();
		System.out.println("   G   " + "Goes to and displays a user-specified line number.");
		System.out.println();
		System.out.println("   S   " + "Goes to and displays the line having the first occurrence of");
		System.out.println("       " + "a user-specified word.");
		System.out.println();
		System.out.println("   H or ?  " + "Prints out help messages explaining all the commands.");
		System.out.println();
		System.out.println("   I   " + "Inserts a single new line typed-in by the user at the current line.");
		System.out.println();
		System.out.println("   P   " + "Prints all the lines at the terminal.");
		System.out.println();
		System.out.println("   N   " + "Advances one line.");
		System.out.println();
		System.out.println("   B   " + "Goes back one line.");
		System.out.println();
		System.out.println("   Q   " + "Quits the editor without saving.");
		System.out.println();
		System.out.println("   R   " + "Prompts for a file name and reads the text file.");
		System.out.println();
		System.out.println("   W   " + "Save changes to the output file if it specified.");
		System.out.println("       " + "if not, save it to input file.");
		System.out.println("       " + "if both not specified, prompt the user to specify th file name.");
		System.out.println();
		System.out.println("   X   " + "Save as -W- command and Quit the editor.");
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println("-----------------------------");
	}

}
