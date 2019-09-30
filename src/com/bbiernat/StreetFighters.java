package com.bbiernat;

import org.junit.Test;
import static org.junit.Assert.*;


public class StreetFighters {

    public static String[] fighterSelection(String[][] fighters, int[] position, String[] moves) {
        for(int currentMove = 0; currentMove<moves.length; currentMove++) {
            switch (moves[currentMove]) {
                case "right":
                    position[1]++;

                    if(position[1] == fighters[position[0]].length) position[1] = 0;
                    break;

                case "left":
                    position[1]--;

                    if(position[1] < 0) position[1] = fighters[position[0]].length -1;
                    break;

                case "down": position[0] = 1; break;
                case "up": position[0] = 0;
            }
            moves[currentMove] = fighters[position[0]][position[1]];
        }

        return moves;
    }


        private String[][] fighters = new String[][]{
                new String[] { "Ryu", "E.Honda", "Blanka", "Guile", "Balrog", "Vega" },
                new String[] { "Ken", "Chun Li", "Zangief", "Dhalsim", "Sagat", "M.Bison" },
        };



        @Test
        public void shouldWorkWithNoMoves() {
            String[] solution = new String[]{};
            assertEquals(solution, StreetFighters.fighterSelection(fighters, new int[]{0,0}, new String[]{}));
        }

        @Test
        public void simpleMoveRight(){
            String[] moves = new String[] { "right" };
            String[] solution = new String[] { "E.Honda" };
            assertEquals(solution, StreetFighters.fighterSelection(fighters, new int[] {0,0}, moves));
        }

        @Test
        public void loopBackRightTooManyMoves(){
            String[] moves = new String[] { "right", "right", "right", "right", "right", "right" };
            String[] solution = new String[] { "E.Honda", "Blanka", "Guile", "Balrog", "Vega", "Ryu" };
            assertEquals(solution, StreetFighters.fighterSelection(fighters, new int[] {0,0}, moves));
        }

        @Test
        public void simpleMoveLeft(){
            String[] moves = new String[] { "left" };
            String[] solution = new String[] { "Ryu" };
            assertEquals(solution, StreetFighters.fighterSelection(fighters, new int[] {0,1}, moves));
        }

        @Test
        public void loopBackLeftTooManyMoves(){
            String[] moves = new String[] { "left", "left" };
            String[] solution = new String[] { "Vega", "Balrog" };
            assertEquals(solution, StreetFighters.fighterSelection(fighters, new int[] {0,0}, moves));
        }

        @Test
        public void shouldWorkWithFewMoves(){
            String[] moves = new String[] { "up", "left", "right", "left", "left" };
            String[] solution = new String[] { "Ryu", "Vega", "Ryu", "Vega", "Balrog" };
            assertEquals(solution, StreetFighters.fighterSelection(fighters, new int[] {0,0}, moves));
        }

        @Test
        public void shouldWorkWhenAlwaysMovingLeft(){
            String[] moves = new String[] { "left", "left", "left", "left", "left", "left", "left", "left" };
            String[] solution = new String[] { "Vega", "Balrog", "Guile", "Blanka", "E.Honda", "Ryu", "Vega", "Balrog" };
            assertEquals(solution, StreetFighters.fighterSelection(fighters, new int[] {0,0}, moves));
        }

        @Test
        public void shouldWorkWhenAlwaysMovingRight(){
            String[] moves = new String[] { "right", "right", "right", "right", "right", "right", "right", "right" };
            String[] solution = new String[] { "E.Honda", "Blanka", "Guile", "Balrog", "Vega", "Ryu", "E.Honda", "Blanka" };
            assertEquals(solution, StreetFighters.fighterSelection(fighters, new int[] {0,0}, moves));
        }

        @Test
        public void shouldUseAll4DirectionsClockwiseTwice(){
            String[] moves = new String[] { "up", "left", "down", "right", "up", "left", "down", "right" };
            String[] solution = new String[] { "Ryu", "Vega", "M.Bison", "Ken", "Ryu", "Vega", "M.Bison", "Ken" };
            assertEquals(solution, StreetFighters.fighterSelection(fighters, new int[] {0,0}, moves));
        }

        @Test
        public void shouldWorkWhenAlwaysMovingDown(){
            String[] moves = new String[] { "down", "down", "down", "down" };
            String[] solution = new String[] { "Ken", "Ken", "Ken", "Ken" };
            assertEquals(solution, StreetFighters.fighterSelection(fighters, new int[] {0,0}, moves));
        }

        @Test
        public void shouldWorkWhenAlwaysMovingUp(){
            String[] moves = new String[] { "up", "up", "up", "up" };
            String[] solution = new String[] { "Ryu", "Ryu", "Ryu", "Ryu" };
            assertEquals(solution, StreetFighters.fighterSelection(fighters, new int[] {0,0}, moves));
        }
}


//** Short Intro**
//
//        Some of you might remember spending afternoons playing Street Fighter 2 in some Arcade back in the 90s or emulating it nowadays with the numerous emulators for retro consoles.
//
//        Can you solve this kata? Suuure-You-Can!
//
//        UPDATE: a new kata's harder version is available here.
//
//        ** The Kata **
//
//        You'll have to simulate the video game's character selection screen behaviour, more specifically the selection grid. Such screen looks like this:
//
//        ![alt text][screen] [screen]: https://images.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.fightersgeneration.com%2Fnp5%2Fgm%2Fsf2ce-s2.jpg&f=1 "Character Selection Screen for Street Fighter 2"
//
//        Selection Grid Layout in text:
//
//        | Ryu  | E.Honda | Blanka  | Guile   | Balrog | Vega    |
//        | Ken  | Chun Li | Zangief | Dhalsim | Sagat  | M.Bison |
//
//        ** Input **
//
//        the list of game characters in a 2x6 grid;
//        the initial position of the selection cursor (top-left is (0,0));
//        a list of moves of the selection cursor (which are up, down, left, right);
//
//        ** Output **
//
//        the list of characters who have been hovered by the selection cursor after all the moves (ordered and with repetition, all the ones after a move, wether successful or not, see tests);
//
//        ** Rules **
//
//        Selection cursor is circular horizontally but not vertically!
//
//        As you might remember from the game, the selection cursor rotates horizontally but not vertically; that means that if I'm in the leftmost and I try to go left again I'll get to the rightmost (examples: from Ryu to Vega, from Ken to M.Bison) and vice versa from rightmost to leftmost.
//
//        Instead, if I try to go further up from the upmost or further down from the downmost, I'll just stay where I am located (examples: you can't go lower than lowest row: Ken, Chun Li, Zangief, Dhalsim, Sagat and M.Bison in the above image; you can't go upper than highest row: Ryu, E.Honda, Blanka, Guile, Balrog and Vega in the above image).
//
//        ** Test **
//
//        For this easy version the fighters grid layout and the initial position will always be the same in all tests, only the list of moves change.
//
//        ** Notice **: changing some of the input data might not help you.
//
//        ** Examples **
//
//        1.
//
//        fighters = [
//        ["Ryu", "E.Honda", "Blanka", "Guile", "Balrog", "Vega"],
//        ["Ken", "Chun Li", "Zangief", "Dhalsim", "Sagat", "M.Bison"]
//        ]
//        initial_position = (0,0)
//        moves = ['up', 'left', 'right', 'left', 'left']
//
//        then I should get:
//
//        ['Ryu', 'Vega', 'Ryu', 'Vega', 'Balrog']
//
//        as the characters I've been hovering with the selection cursor during my moves. Notice: Ryu is the first just because it "fails" the first up See test cases for more examples.
//
//        2.
//
//        fighters = [
//        ["Ryu", "E.Honda", "Blanka", "Guile", "Balrog", "Vega"],
//        ["Ken", "Chun Li", "Zangief", "Dhalsim", "Sagat", "M.Bison"]
//        ]
//        initial_position = (0,0)
//        moves = ['right', 'down', 'left', 'left', 'left', 'left', 'right']
//
//        Result:
//
//        ['E.Honda', 'Chun Li', 'Ken', 'M.Bison', 'Sagat', 'Dhalsim', 'Sagat']
