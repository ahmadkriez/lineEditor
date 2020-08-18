# lineEditor

This project is about building line editor for text files, for using this java app you should follow the following commandes:

-----------------------------
-----------------------------

Syntax for running the line editor

   ```java edit input_file_name output_file_name```
   
   ```java edit input_file_name```
   
   ```java edit```

The command that user can used to control the editor.

   `A`   New line to the end of file.


   `D`   Delete the current line and move to the next line


   `E`   Starting character editing mode
   
          L     Move cursor one character left
          
          R     Move cursor one character right
          
          A s   Appand a string, s, to the end of line
          
          I s   Insert a string, s, after the curor
          
          C s   Change to string, s, from the cursor to length of, s,
          
          D    Delete the character after the cursor
          
          E     Exit editing mode

   `F`   Goes to and displays the first line.

   `L`   Goes to and displays the last line.

   `G`   Goes to and displays a user-specified line number.

   `S`   Goes to and displays the line having the first occurrence of
         a user-specified word.

   `H` or `?`  Prints out help messages explaining all the commands.

   `I`   Inserts a single new line typed-in by the user at the current line.

   `P`   Prints all the lines at the terminal.

   `N`   Advances one line.

   `B`   Goes back one line.

   `Q`   Quits the editor without saving.

   `R`   Prompts for a file name and reads the text file.

   `W`   Save changes to the output file if it specified.
       if not, save it to input file.
       if both not specified, prompt the user to specify th file name.

   `X`   Save as -W- command and Quit the editor.

-----------------------------
-----------------------------

![lineEditor](https://github.com/ahmadkriez/lineEditor/blob/master/presenting/lineEditor.png?raw=true)
