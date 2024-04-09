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

## Anagram Development
Uses Anagramica API to generate sensible anagrams. Anagramica doesn't just scramble letters, it looks for a valid word given it's dictionary. 

Anagramica has a terrible flaw where it will often return nothing due to it's simplistic query handling. To have a better success rate and allow for more query options, we developed a randomizer that is able to split the single query into two unique queries and scramble the letter orders. This allows for previously failed queries to now produce multiple unique anagrams while still using all the characters and keeping it sensible.

For example, the query `lewishamilton` would be split into two queries, `lewish` and  `amilton`, and each query would be processed through the API. If no valid result returns, we would scramble the letters and split the query once more, generating two more unique queries. This process would repeat until either an anagram is found or a set number of attempts is reached.

The implementation allows for more valid queries and produces an anagram at 50x the speed of the naive implementation.

---

## Q&E
#### Inspiration
Dylan just took CSE 100 last quarter, and he couldn't stop yapping about Burrows–Wheeler transformation, the result of which is an anagram of the original string. Benny had seen videos in which people try to guess the original word by looking at its anagram, and we thought it would be fun.
#### What it does
A word is displayed as an anagram and the user attempts to guess the original word in as little tries as possible.
The player can either choose for the word to come from a predetermined word bank associated with a category, or they can input their own word.
#### How we built it
We used Java, plus an API which finds anagram for a word.
#### Challenges we ran into
The API we use cannot take words longer than 9 characters, so we have to find a workaround.
#### Accomplishments that we're proud of
Benny used a random permutation generator instead of a sequential permutation generator, which increased the speed of finding a valid anagram by 50x.
Dylan was able to implement the category system, using file I/O rather than just scanner. 
#### What we learned
The structure of the code is the most important thing, and we should not head right into coding before determining what components and methods the class should have.
#### What's next for ___agram
Adding a GUI instead of using console for the users;
Allowing users to play with their friends remotely.
