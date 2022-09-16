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

// #*** QUESTION ***
// Given the input data above, return the total score for each player. 

// #*** NOTES ***
// The players don’t need to be in any particular order, 
// For output, I don’t need the component scores - just something like “Mike 3, Alex 4, Tess 5”. 
// For input, use whatever data structure you want to represent input data, but note that the score and names of players and categories are defined by the input data.
