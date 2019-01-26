package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.LinkedStack;

/**
 * Postfix expression evaluator.
 *
 * @author Isaac Griffith
 */
public class PostFix {

    /**
     * Evaluates the provided postfix expression and returns the answer. If the
     * provided string is null, empty, or only contains whitespace then return
     * 0;
     *
     * @param infix A string representing an postfix notation expression where
     * each item is separated by a space.
     * @return value of the postfix express or 0 if the postfix expression is null,
     * empty, or contains only whitespace.
     */
    public static int evalPostFix(String infix) {
        // check if the string is not null or empty
        if (infix == null || infix.trim().isEmpty()) {
            return 0;
        }
        // else the string is usable to evaluate
        else {
            // a stack to store all of the separated string values
            LinkedStack<String> valueList = new LinkedStack<>();

            // int value of the calculated output
            int postNum = 0;
            // may need a temp num too

            // based off of this example will loop and make a stack of the values
            // https://www.tutorialspoint.com/java/java_string_split.htm
            for (String portion: infix.split(" ")) {
                valueList.push(portion);
            }

            // reverse the stack to be the correct order for reading from
            valueList.reverse();

            // counter for how many numbers / symbols in sequence there have been
            int countNum = 0;
            int countSym = 0;

            // TODO number check first

            return postNum;

            // if too few arguments provided for a given operator then throw exception
//            throw new IllegalArgumentException("Not enough correct arguments provided.");
        }
    }
}
