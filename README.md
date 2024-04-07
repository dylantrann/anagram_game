# ___agram
Created by Benny Xie and Dylan Tran

## Game Concept
A word is displayed as an anagram and the user attempts to guess the original word in as little tries as possible.

The player can either choose for the word to come from a predetermined word bank associated with a category, or they can input their own word.

## Running
After compiling, use the following to run the program:

```
java AnagramGenerator.java <category>
```

The argument `<category>` isn't required, but will allow the player to access words from a determined category. Categories themselves are text files that can be created by the user. The text file's name determines the category and each line in the text file represents a word that can become the chosen anagram.

* If the player decides to pick a category, a random word will be chosen from the category's word base to become an anagram.
* If the player decides to input their own word to play (i.e. they choose not include any arguments), the program will automatically prompt the user to input their own word of choice. 
