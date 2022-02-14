# CHECKERS GAME
Program simulating the game of checkers, implemented in Java.

To print the board on the screen I've used UTF-8 encoded characters in the form of filled and hollow circles.
If the encoding doesn't correspond on the machine you test the code, please subtitute the characters with "1" and "2" in the showBoard() method.
There are some comments placed where the change would be performed.
Alternatively, for a better look, you could paste the characters from this file ("●" and "○").


##### Project Completed in Course 02160 Agile OOP Software Development - Technical University of Denmark 
<img src="https://user-images.githubusercontent.com/65953954/120001846-7f05f180-bfd4-11eb-8c11-2379a547dc9f.jpg" alt="drawing" width="100"/>

Game logs:
####################################################
Welcome to our game! 
 Press Q to quit while running and ENTER to advance!
 x->1 2 3 4 5 6 7 8    
  +-----------------+  
1 |   ●   ●   ●   ● | 1
2 | ●   ●   ●   ●   | 2
3 |   ●   ●   ●   ● | 3
4 |                 | 4
5 |                 | 5
6 | ○   ○   ○   ○   | 6
7 |   ○   ○   ○   ○ | 7
8 | ○   ○   ○   ○   | 8
  +-----------------+  
 x->1 2 3 4 5 6 7 8    
Let's complete turn number: 1

Turn of player: 1
Coordinates of piece to be moved:
    Enter coordinate X --> 2
    Enter coordinate Y --> 3
Coordinates of final position of the piece:
    Enter coordinate X --> 3
    Enter coordinate Y --> 4
Game board updated! Piece moved!☻
 x->1 2 3 4 5 6 7 8    
  +-----------------+  
1 |   ●   ●   ●   ● | 1
2 | ●   ●   ●   ●   | 2
3 |       ●   ●   ● | 3
4 |     ●           | 4
5 |                 | 5
6 | ○   ○   ○   ○   | 6
7 |   ○   ○   ○   ○ | 7
8 | ○   ○   ○   ○   | 8
  +-----------------+  
 x->1 2 3 4 5 6 7 8    
Let's complete turn number: 2
Turn of player: 2
Coordinates of piece to be moved:
    Enter coordinate X --> 1
    Enter coordinate Y --> 6
Coordinates of final position of the piece:
    Enter coordinate X --> 2
    Enter coordinate Y --> 5
Game board updated! Piece moved!☻
 x->1 2 3 4 5 6 7 8    
  +-----------------+  
1 |   ●   ●   ●   ● | 1
2 | ●   ●   ●   ●   | 2
3 |       ●   ●   ● | 3
4 |     ●           | 4
5 |   ○             | 5
6 |     ○   ○   ○   | 6
7 |   ○   ○   ○   ○ | 7
8 | ○   ○   ○   ○   | 8
  +-----------------+  
 x->1 2 3 4 5 6 7 8    
Let's complete turn number: 3
Turn of player: 1
Coordinates of piece to be moved:
    Enter coordinate X --> 3
    Enter coordinate Y --> 4
Coordinates of final position of the piece:
    Enter coordinate X --> 1
    Enter coordinate Y --> 6
Piece at 2:5 is taken!
Game board updated! Piece moved!☻
 x->1 2 3 4 5 6 7 8    
  +-----------------+  
1 |   ●   ●   ●   ● | 1
2 | ●   ●   ●   ●   | 2
3 |       ●   ●   ● | 3
4 |                 | 4
5 |                 | 5
6 | ●   ○   ○   ○   | 6
7 |   ○   ○   ○   ○ | 7
8 | ○   ○   ○   ○   | 8
  +-----------------+  
 x->1 2 3 4 5 6 7 8    
Let's complete turn number: 4
Turn of player: 2
Coordinates of piece to be moved:
    Enter coordinate X --> 3
    Enter coordinate Y --> 6
Coordinates of final position of the piece:
    Enter coordinate X --> 4
    Enter coordinate Y --> 5
Game board updated! Piece moved!☻
 x->1 2 3 4 5 6 7 8    
  +-----------------+  
1 |   ●   ●   ●   ● | 1
2 | ●   ●   ●   ●   | 2
3 |       ●   ●   ● | 3
4 |                 | 4
5 |       ○         | 5
6 | ●       ○   ○   | 6
7 |   ○   ○   ○   ○ | 7
8 | ○   ○   ○   ○   | 8
  +-----------------+  
 x->1 2 3 4 5 6 7 8    
Let's complete turn number: 5
Turn of player: 1
Coordinates of piece to be moved:
    Enter coordinate X --> 4
    Enter coordinate Y --> 3
Coordinates of final position of the piece:
    Enter coordinate X --> 2
    Enter coordinate Y --> 5
Invalid move! Respect the rules!☹
Coordinates of final position of the piece:
    Enter coordinate X --> 3
    Enter coordinate Y --> 4
Game board updated! Piece moved!☻
 x->1 2 3 4 5 6 7 8    
  +-----------------+  
1 |   ●   ●   ●   ● | 1
2 | ●   ●   ●   ●   | 2
3 |           ●   ● | 3
4 |     ●           | 4
5 |       ○         | 5
6 | ●       ○   ○   | 6
7 |   ○   ○   ○   ○ | 7
8 | ○   ○   ○   ○   | 8
  +-----------------+  
 x->1 2 3 4 5 6 7 8    
Let's complete turn number: 6
Turn of player: 2
Coordinates of piece to be moved:
    Enter coordinate X --> 4
    Enter coordinate Y --> 5
Coordinates of final position of the piece:
    Enter coordinate X --> 2
    Enter coordinate Y --> 3
Piece at 3:4 is taken!
Game board updated! Piece moved!☻
