package me.ethan.rps;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameEvent {

    public static String strategyUsed;
    /*
    Returns the results of a round of play as an integer.
    Returns 0 if the Computer wins, 1 if the Player wins, and 2 if the round results in a tie.
    The string pChoice must be either "r", "p", or "s" for Rock, Paper, or Scissors.

    @param pChoice  a String that represents the move that the player decided to play
    @return     an integer that represents the results of the round. A 0 is output for a Computer win, a 1 for a Player win, and a 2 for a tie.
     */
    public static int results(String pChoice) throws InterruptedException {
        Random compRandom = new Random();
        String cChoice = "";
        int cInt = stratLogic(pChoice);
        if (cInt == 0 ) {
            cChoice = "r";
            setGameIcon(RPSFrame.cMove, RPSFrame.rockIcon);
        } else if (cInt == 1) {
            cChoice = "p";
            setGameIcon(RPSFrame.cMove, RPSFrame.paperIcon);
        } else if (cInt == 2) {
            cChoice = "s";
            setGameIcon(RPSFrame.cMove, RPSFrame.scissorsIcon);
        }
        RPSFrame.lastUsed = pChoice;
        if ((pChoice.equalsIgnoreCase("r") && cChoice.equalsIgnoreCase("s")) || (pChoice.equalsIgnoreCase("s") && cChoice.equalsIgnoreCase("p")) || (pChoice.equalsIgnoreCase("p") && cChoice.equalsIgnoreCase("r"))) {
            resultLog(pChoice, cChoice, 1);

            return 1;
        } else if ((cChoice.equalsIgnoreCase("r") && pChoice.equalsIgnoreCase("s")) || (cChoice.equalsIgnoreCase("s") && pChoice.equalsIgnoreCase("p")) || (cChoice.equalsIgnoreCase("p") && pChoice.equalsIgnoreCase("r"))) {
            resultLog(pChoice, cChoice, 0);
            return 0;
        } else {
            resultLog(pChoice, cChoice, 2);
            return 2;
        }
    }
    /*
    Appends text to the move log of the text area with the results of the round, as well the
    strategy the computer used in that round. The parameter strings pMove and cMove must be
    either "r", "p", or "s" for Rock, Paper, or Scissors. The parameter int result must be either 1,
    2, or 0. 0 Represents a computer win, 1 represents a Player win, and 2 represents a tie.

    @param pMove    A string (either r, p, or s) that is the move the player performed
    @param cMove    A string (either r, p, or s) that is the move the computer performed
    @param result   An integer (either 0, 1, or 2) that represents the result of the match. Results are computer win, player win, or tie.
     */
    private static void resultLog(String pMove, String cMove, int result) {
            pMove = fullMove(pMove);
            cMove = fullMove(cMove);
        if (result == 1) {
            RPSFrame.logText.append(pMove + " beats " + cMove + " (Player Wins)\n");
            RPSFrame.logText.append("Computer Used " + strategyUsed + "\n");
        } else if (result == 0) {
            RPSFrame.logText.append(cMove + " beats " + pMove + " (Computer Wins)\n");
            RPSFrame.logText.append("Computer Used " + strategyUsed + "\n");
        } else if (result == 2) {
            RPSFrame.logText.append(pMove + " and " + cMove + " are the same (Tie)\n");
            RPSFrame.logText.append("Computer Used " + strategyUsed + "\n");
        }
    }
    /*
    Converts the shorthand strings of "r", "s", and "p" to their full move names, being "Rock", "Paper", and "Scissors", and returns that value.

    @param  move    A string that is the shorthand form of one of the moves. Must be either "r", "s", or "p".
    @return         A string that is the full word of the move entered.
     */
    public static String fullMove(String move) {
        String retString = "";
        if (move.equalsIgnoreCase("r")) {
            retString = "Rock";
            return retString;
        } else if (move.equalsIgnoreCase("p")) {
            retString = "Paper";
            return retString;
        } else if (move.equalsIgnoreCase("s")) {
            retString = "Scissors";
            return retString;
        }
        return retString;
    }
    /*
    Sets a JLabels Icon to the provided ImageIcon.

    @param  label   The JLabel to change the Icon for.
    @param icon     The ImageIcon to set as the Icon for the JLabel.
    @see            ImageIcon, JLabel
     */
    public static void setGameIcon(JLabel label, ImageIcon icon) {
        label.setIcon(icon);
    }
    /*
    Returns an integer (either 0, 1, or 2) to determine what move the Computer will use.
    This method randomly selects one of five strategies to use, and will make most of those
    moves based on the pChoice (player's move) string.

    @param  pChoice     String that is the move the player has used this round. Must be either r, p, or s
    @return             An integer determining what the computer's move will be. Will be 0 for rock, 1 for paper, and 2 for scissors.

     */
    public static int stratLogic(String pChoice) {
        Random stratRandom = new Random();
        int rocksUsed = RPSFrame.rocksUsed;
        int paperUsed = RPSFrame.paperUsed;
        int scissorsUsed = RPSFrame.scissorsUsed;
        int stratInt = 1 + stratRandom.nextInt(10);
        if (stratInt == 1 || stratInt == 2) {
            strategyUsed = "Least Used";
            Random tieRandom = new Random();
            if (rocksUsed < paperUsed && rocksUsed < scissorsUsed) {
                return 1;
            } else if (paperUsed < rocksUsed && paperUsed < scissorsUsed) {
                return 2;
            } else if (scissorsUsed < rocksUsed && scissorsUsed < paperUsed){
                return 0;
            } else if (rocksUsed == paperUsed && rocksUsed != scissorsUsed) {
                int choice = tieRandom.nextInt(2);
                if (choice == 1) {
                    return 1;
                } else if (choice == 2) {
                    return 2;
                }
            } else if (rocksUsed == scissorsUsed && rocksUsed != paperUsed) {
                int choice = tieRandom.nextInt(2);
                if (choice == 1) {
                    return 1;
                } else if (choice == 2) {
                    return 0;
                }
            } else if (scissorsUsed == paperUsed && rocksUsed != scissorsUsed) {
                int choice = tieRandom.nextInt(2);
                if (choice == 1) {
                    return 0;
                } else if (choice == 2) {
                    return 2;
                }
            } else {
                int choice = tieRandom.nextInt(3);
                if (choice == 1) {
                    return 0;
                } else if (choice==2) {
                    return 1;
                } else if (choice == 3) {
                    return 2;
                }
            }

        } else if (stratInt == 3 || stratInt ==4) {
            strategyUsed = "Most Used";
            Random tieRandom = new Random();
            if (rocksUsed > paperUsed && rocksUsed > scissorsUsed) {
                return 1;
            } else if (paperUsed > rocksUsed && paperUsed > scissorsUsed) {
                return 2;
            } else if (scissorsUsed > rocksUsed && scissorsUsed > paperUsed){
                return 0;
            } else if (rocksUsed == paperUsed && rocksUsed != scissorsUsed) {
                int choice = tieRandom.nextInt(2);
                if (choice == 1) {
                    return 1;
                } else if (choice == 2) {
                    return 2;
                }
            } else if (rocksUsed == scissorsUsed && rocksUsed != paperUsed) {
                int choice = tieRandom.nextInt(2);
                if (choice == 1) {
                    return 1;
                } else if (choice == 2) {
                    return 0;
                }
            } else if (scissorsUsed == paperUsed && rocksUsed != scissorsUsed) {
                int choice = tieRandom.nextInt(2);
                if (choice == 1) {
                    return 0;
                } else if (choice == 2) {
                    return 2;
                }
            } else {
                int choice = tieRandom.nextInt(3);
                if (choice == 1) {
                    return 0;
                } else if (choice==2) {
                    return 1;
                } else if (choice == 3) {
                    return 2;
                }
            }
        } else if (stratInt == 5 || stratInt == 6) {
            strategyUsed = "Last Used";
            if (RPSFrame.lastUsed == null) {
                Random random = new Random();
                int choice = random.nextInt(3);
                if (choice == 1) {
                    return 0;
                } else if (choice==2) {
                    return 1;
                } else if (choice==3) {
                    return 2;
                }
            } else {
                if (RPSFrame.lastUsed.equalsIgnoreCase("r")) {
                    return 1;
                } else if (RPSFrame.lastUsed.equalsIgnoreCase("s")) {
                    return 2;
                } else if (RPSFrame.lastUsed.equalsIgnoreCase("p")) {
                    return 0;
                }
            }
        } else if (stratInt == 7 || stratInt ==8 || stratInt ==9) {
            strategyUsed = "Random";
            Random choiceRandom = new Random();
            int choice = choiceRandom.nextInt(3);
                if (choice==1) {
                    return 0;
                } else if (choice == 2) {
                    return 1;
                } else if (choice==3) {
                    return 2;
                }
            } else if (stratInt == 10) {
            strategyUsed = "Cheat";
            if (pChoice.equalsIgnoreCase("r")) {
                return 1;
            } else if (pChoice.equalsIgnoreCase("p")) {
                return 2;
            } else if (pChoice.equalsIgnoreCase("s")) {
                return 0;
            }
        }
            return 0;


    }

}
