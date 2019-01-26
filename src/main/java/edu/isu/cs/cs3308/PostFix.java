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

			// bool to determine if all values needed are ready for calculation
			boolean calcNow = false;
			// int value of the calculated output
			int postNum = -1;

			// temp values for each calculation
			int tempNum1 = -1;
			int tempNum2 = -1;
			String tempSym1 = "";
			String tempSym2 = "";

			// bool to determine if all the calculations are done
			boolean finished = false;

			// iterate through the stack to do the calculations
			while (valueList.size() >= 0 && finished == false) {
				// temporary check values needed
				String tempPeek = "";
				int tempInt = -1;

				// ensure we can get a value from the stack
				if (valueList.size() > 0) {
					// temp peek of the next value
					tempPeek = valueList.peek();

					// int of the temppeek
					tempInt = Integer.parseInt(tempPeek);

					// assigns values if the count is high enough
					if (tempInt > -1) {
						if (countNum == 0) {
							tempNum1 = Integer.parseInt(valueList.pop());
						} else if (countNum == 1) {
							tempNum2 = Integer.parseInt(valueList.pop());
						}
						countNum++;
					} else {
						if (countSym == 0) {
							tempSym1 = valueList.pop();
						} else if (countSym == 1) {
							tempSym2 = valueList.pop();
						}
						countSym++;
					}
				}

				// // FRIST do checks to see if there is an invalid set of arguments:
				// check if needed first value is a number otherwise throw exception
				if (countNum == 0 &&
					tempPeek == "+" ||
					tempPeek == "-" ||
					tempPeek == "*" ||
					tempPeek == "/") {
					throw new IllegalArgumentException("Number was expected, but got a symbol instead.");
				}
				// else if too many numbers throw exception
				else if (countNum > 2) {
					throw new IllegalArgumentException("Too many numbers provided.");
				}
				// else if too many symbols throw exception
				else if (countSym > 2) {
					throw new IllegalArgumentException("Too many symbols provided.");
				}
				// else if there are too many symbols for the starting set of numbers then throw exception
				else if (calcNow == true && postNum == -1 && countNum == 2 && countSym == 2) {
					throw new IllegalArgumentException("Too many symbols for the starting set of numbers.");
				}
				// else if not calculating and already have too many of either then throw exception
				else if (calcNow == false && postNum != -1 && countNum == 2 && countSym == 2) {
					throw new IllegalArgumentException("Too many numbers or symbols provided cannot calculate.");
				}
				// else if there are too many symbols throw exception
				else if (calcNow == true && postNum != -1 && countNum == 1 && countSym == 2) {
					throw new IllegalArgumentException("Too many symbols provided for the amount of numbers.");
				}
				// else if there are too few symbols throw exception
				else if (calcNow == true && postNum != -1 && countNum == 2 && countSym == 1) {
					throw new IllegalArgumentException("Too many numbers provided for the amount of symbols.");
				}
				// // SECOND do checks to see if we are ready to calculate:
				else if ((postNum == -1 && countNum == 2 && countSym == 1 && tempInt != -1) ||
						(postNum != -1 && countNum == 2 && countSym == 2 && tempInt != -1) ||
						(postNum != -1 && countNum == 2 && countSym == 1 && tempInt != -1) ||
						(postNum != -1 && countNum == 1 && countSym == 1 && tempInt != -1)
				) {
					calcNow = true;
				}
				// // THIRD calculate the needed post number
				if (calcNow == true) {
					// number used for the 2 temp number combined
					int comboNum = -1;

					// for calculations with 2 numbers
					if (countNum == 2) {
						// do the appropriate calculate for the
						if (tempSym1 == "+") {
							comboNum = (tempNum1 + tempNum2);
						} else if (tempSym1 == "-") {
							comboNum = (tempNum1 - tempNum2);
						} else if (tempSym1 == "*") {
							comboNum = (tempNum1 * tempNum2);
						} else if (tempSym1 == "/") {
							comboNum = (tempNum1 / tempNum2);
						}

						// determine how to combine it with the final post number
						if (postNum == -1) {
							postNum = comboNum;
						} else if (countSym == 2) {
							// do the appropriate calculate for the
							if (tempSym2 == "+") {
								postNum += comboNum;
							} else if (tempSym2 == "-") {
								postNum -= comboNum;
							} else if (tempSym2 == "*") {
								postNum *= comboNum;
							} else if (tempSym2 == "/") {
								postNum /= comboNum;
							}
						}
					}
					// else we are at the end of the stack
					else if (countNum == 1) {
						// do the appropriate calculate for the
						if (tempSym1 == "+") {
							postNum += tempNum1;
						} else if (tempSym1 == "-") {
							postNum -= tempNum1;
						} else if (tempSym1 == "*") {
							postNum *= tempNum1;
						} else if (tempSym1 == "/") {
							postNum /= tempNum1;
						}
					}

					// reset counter values since we did the calculations
					countNum = 0;
					countSym = 0;
					tempNum1 = -1;
					tempNum2 = -1;
					tempSym1 = "";
					tempSym2 = "";
				}

				// // LASTLY check to see if we are done looping and calculating:
				// if at the end of the stack and did the final calculation then exit the loop
				if (valueList.size() == 0 && calcNow == true) {
					finished = true;
				}
				// else we are not done yet so reset calcnow
				else {
					calcNow = false;
				}
			}

			return postNum;
		}
	}
}
