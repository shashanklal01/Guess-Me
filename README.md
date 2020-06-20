# Guess-Me

The goal of this game is to guess a 4-digit number according to the game rules. The rules are:
1. The host picks a 4-digit number (i.e. any number between 1000 and 9999). Let’s call this the groundtruth number. In the following example, let’s pick 5432 as the groundtruth.
2. The player takes a guess, for example, 1234.
3. The host tells the player how many digits of the guess number match the groundtruth. A match is defined as the same digit in the same position. For example, 1234 and 5432 have 1 match (at position 3); 2345 and 5432 have 0 match – even though it’s the same set of digits, none matches in the exact same position.
4. Based on the number of matches, the player performs some update and takes another guess, say 5533.
5. The host again tells the player the number of matches. This time, 5533 and 5432 have 2 matches.
6. Repeat steps 4 and 5 until the game is over. The game is over when the host tells the player that all 4 digits are matched. Therefore the player has found the groundtruth number.
