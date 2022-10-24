Wordle Clone by Diego Feliz Gonzalez

############ GAME DESCRIPTION ##############

The purpose of the game is to find the correct word.
This word will be selected randomly and with every attempt the program will check each character
and return a coloured coded feedback that will be added to the previous attempts if any. 
The aim of the game os to find the correct word using the information given by the system.
There is also a scoring system based on the number of attempts. 

Players can at any given time type :giveup to stop the game and find out the misterious word. 
Also they can use :help to get a cheeky advice from the computer.

################## HOW TO USE #####################

Please be aware the file for the dictionary should be included within the ZIP file 
and is called thtough a relative path: "dic.txt". So both files should be in the same directory. 

Methods available in the program are: 

gameColor - Process the String given as input against the misterious choosen by the system and
colour codes the input; Green for character in the same place, Yellow for character in anoother
place but within the word, and Red for non existing character in the misterious word. 

isLonger - Checks that the guesse word is the right lenght, if it;s longer, it will chop the word up to the maximum 

isShorter - Checks that the guessed word is the right lenght, if it's shorter it will ask again.

isExisting - Checks that the guessed word has no numbers or special characters, otherwise it asks again.

isWithInDictionary - Checks that the guessed word is in the provided dictionary otherwise it asks again. 

###################EXTRA FUNCTIONALITIES################

:help
:giveup