// #*** Round 1  QUESTION  ***
// #*** DESCRIPTION ***
// A game has n players all collecting different kinds of animals. Each player has some number of each type of animal. For example:

// // Player   Cows      Pigs      Total
// // Mike       3         2
// // Alex       5         1
// // Tess       4         4

// To calculate each player’s total score for the game, we evaluate each category separately. For each category, the player with the most of that animal gets n points, which is the number of players in the game. 
//Second place gets n-1, down to the player with the fewest of that animal getting one point. Then the total score is the sum of the category scores. For example:

// // Player   Cows      Pigs      Total
// // Mike       3 (1)     2 (2)     3
// // Alex       5 (3)     1 (1)     4
// // Tess       4 (2)     4 (3)     5

// When scores are similar just get average (scores increments that are equal and average with no of same scores players)

// // Player   Cows      Pigs          Total
// // Mike       3 (1)       2 (2)        3
// // Alex       4 (3) -> (2.5)     1 (1)      3.5
// // Tess       4 (2) -> (2.5)     4 (3)      5.5
// fair scoring strategy: (3+2) / 2 = 2.5

// // Player   Cows      Pigs      Total
// // Mike       3 (2)     2 (2)     3
// // Alex       3 (2)     1 (1)     4
// // Tess       3 (2)     4 (3)     5
// (1+2+3) / 3 = 2

// where the numbers in parentheses are the points awarded.

// #*** Round 2  QUESTION ***
// Given the input data above, return the total score for each player. 

// #*** NOTES ***
// The players don’t need to be in any particular order, 
// For output, I don’t need the component scores - just something like “Mike 3, Alex 4, Tess 5”. 
// For input, use whatever data structure you want to represent input data, but note that the score and names of players and categories are defined by the input data.


// Build a tool that when given our domains and a list of other domains, alert the user to all the possible typo squats of our domains present in the list of other domains.

// Part 1:  The first kind of typo we want to detect (there will be more later) are typos such that a single character from one of our domains has been changed into a different character.  

// Ex. Given “Square” as one of our domains, we should detect “Swuare” and “Smuare” but not “Share” or “Google”. 

// Part 2:  Update the tool so that it can restrict the changed character to only be typos that are “likely” given the layout of the keyboard.

// Ex. Given “Square” as our domain, we should detect “Swuare” but not “Smuare” (assuming a standard QWERTY keyboard).  Q -> W is likely while Q -> M is not.

// Part 3:  Update the tool so that it can also detect typos where our domain had an additional character added or removed.

// Ex. Given “Square” as our domain, we should detect “Squares”, “Squbare” and “Suare”

// Note - we still only ever want to detect one kind of typo at a time.  We should not detect something like “Swuares” or “Suate”


// "aquare" "squarz"