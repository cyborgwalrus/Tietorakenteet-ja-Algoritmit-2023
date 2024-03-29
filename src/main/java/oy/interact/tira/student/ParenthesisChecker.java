package oy.interact.tira.student;

import oy.interact.tira.util.StackInterface;

public class ParenthesisChecker {

   private ParenthesisChecker() {
   }

   /**
    * Student: Implement this method which checks if the given string has matching
    * opening and closing
    * parentheses. It should check for matching parentheses:
    * 
    * Lorem ipsum ( dolor sit { amet, [ consectetur adipiscing ] elit, sed } do
    * eiusmod tempor ) incididunt ut...,
    * 
    * that can be found for example in Java source code and JSON structures.
    * 
    * If the string has issues with parentheses, you should throw a
    * {@code ParenthesisException} with a
    * descriptive message and error code. Error codes are already defined for you
    * in the ParenthesesException
    * class to be used.
    * 
    * NOTE: If the string contains quotation marks ("like this"), the text between
    * quotation marks
    * MUST be ignored. Why? In structured text, the rule of the parentheses applies
    * only to the structured
    * text but not the text in quotation marks. It is totally valid to have JSON:
    * 
    * {
    * "somekey": "Some value [ with that opening bracket only"
    * }
    *
    * and that is perfectly ok and acceptable, also in source code like Java.
    *
    * Note that the exception thrown must include correct line and column numbers
    * of the
    * place where it became obvious that there are incorrect parenthesis in the
    * input text.
    *
    * What is to be tested about the incoming string:
    *
    * - If a quotation mark was found, all characters until the next quotation mark
    * must be ignored.
    * And obviously, before and after, all charactes must be checked if they are
    * parenthesis chars.
    * - When an opening parenthesis is found in the string, it is successfully
    * pushed to the stack (push may fail).
    * - When a closing parenthesis is found in the string, chech that a matching
    * opening parenthesis is popped from the stack.
    * - If the stack was empty, this indicates an error, too many opening
    * parentheses (or too few closing ones).
    * Note that you can check if the stack is empty before calling pop() and throw
    * an exception.
    * - When the string has been handled, and if the stack still has parentheses,
    * there are too few closing parentheses.
    * 
    * @param stack      The stack object used in checking the parentheses from the
    *                   string.
    * @param fromString A string containing parentheses to check if it is valid.
    * @return Returns the number of parentheses found from the input in total (both
    *         opening and closing).
    * @throws ParenthesesException     if the parentheses did not match as
    *                                  intended.
    * @throws StackAllocationException If the stack cannot be allocated or
    *                                  reallocated if necessary.
    */
   public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {

      int lineNumber = 1;
      int columnNumber = 0;
      int parenthesisCount = 0;
      char poppedChar = 0;
      boolean betweenQuotes = false;
      boolean parenthesisError = false;
      // for each character in the input string
      for (int i = 0; i < fromString.length(); i++) {
         char c = fromString.charAt(i);
         columnNumber++;
         // if in between of quotes
         // ignore this character (but count column numbers)
         if (c != '"' && betweenQuotes)
            continue;
         if (c == '"' && !betweenQuotes) {
            betweenQuotes = true;
            continue;
         }
         if (c == '"' && betweenQuotes) {
            betweenQuotes = false;
            continue;
         }

         switch (c) {
            case '\n':
               lineNumber++;
               columnNumber = 0;
               break;
            // if character is an opening parenthesis -- one of "([{"
            // push it into the stack (check for failure and throw an exception if so)
            case '(':
            case '[':
            case '{':
               parenthesisCount++;
               try {
                  stack.push(c);
               } catch (Exception OutOfMemoryError) {
                  throw new ParenthesesException("Failed to push to stack", lineNumber, columnNumber,
                        ParenthesesException.STACK_FAILURE);
               }
               break;
            // else if character is a closing parenthesis -- one of ")]}", pop the latest
            // opening parenthesis from the stack
            case ')':
            case ']':
            case '}':
               parenthesisCount++;
               try {
                  poppedChar = stack.pop();
               } catch (Exception e) {
                  throw new ParenthesesException("Failed to pop from the stack", lineNumber, columnNumber,
                        ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
               }
               if (poppedChar == 0)
                  // if the popped item is null, throw an exception, there are too many closing
                  // parentheses
                  throw new ParenthesesException("Parenthesis didn't match.", lineNumber, columnNumber,
                        ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
               // check the popped opening parenthesis against the closing parenthesis read
               // from the string
               if (poppedChar == '(' && c != ')')
                  parenthesisError = true;
               if (poppedChar == '[' && c != ']')
                  parenthesisError = true;
               if (poppedChar == '{' && c != '}')
                  parenthesisError = true;
               // if they do not match -- opening was { but closing was ], for example.
               // throw an exception, wrong kind of parenthesis were in the text (e.g. "asfa
               // (asdf } sadf")
               if (parenthesisError)
                  throw new ParenthesesException("Parenthesis didn't match.", lineNumber, columnNumber,
                        ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
         }
      }
      // if the stack is not empty after all the characters have been handled
      // throw an exception since the string has more opening than closing
      // parentheses.
      if (stack.isEmpty() == false)
         throw new ParenthesesException("Parenthesis didn't match.", lineNumber, columnNumber,
               ParenthesesException.TOO_MANY_OPENING_PARENTHESES);

      return parenthesisCount;
   }
}