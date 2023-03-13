/**
 * Responsible for the creation of the actors and in writing it we enforce the
 * "Principle of Single Responsibility".
 */
public class PlayerFactory {

    /**
     * Responsible for mapping the string from the command line to an actual player object.
     * @param type name player
     * @return player
     */
    public Player buildPlayer(String type){

        switch (type){
            case "human":
                return new HumanPlayer();

            case  "clever":
                return new CleverPlayer();

            case "whatever":
                return new WhateverPlayer();

            case "genius":
                return new GeniusPlayer();

            default:
                return null;

        }
    }
}
