/*
 *   File Name: hw2_1.cpp
 *        Name: Sample Solution
 *      Course: CST 238
 *        Term: Fall 2014
 *  Assignment: HW2
 *    Abstract: Prints a menu of choices to increment, decrement,
 *              multiply or add user specified operands
 *
 */
 
// Includes in alphabetical order
// With #include <...> and #include "..."
// Separated by a blank line into two 
// blocks
#include <iostream>
#include <limits>
#include <string>

// Using declarations
using namespace std;

// Typedefs
enum choice_t { INC = 1, DEC, MUL, ADD, END };

// Constants
const char * MENU =
  "Choose Operation:\n"
  "1. ++\n"
  "2. --\n"
  "3. *\n"
  "4. +\n"
  "5. END\n";

const char * INPUT_ONE = "Input an operand:\n";

const char * INPUT_TWO = "Input two operands:\n";

const char * INVALID =
  "Invalid Operation. Choose again:\n";

// Function prototypes (alphabetical order)
void border();
bool get_choice(unsigned *);
void read_n_ints(int *, unsigned);

// Main (if appropriate)
int main() {
  unsigned * choice = new unsigned;
  int * operands = new int[2];

  while (get_choice(choice)) {
    switch (*choice) {
      default: cout << INVALID;
               break;

      case INC: 
      case DEC: cout << INPUT_ONE;
                read_n_ints(operands, 1);

                cout << operands[0] 
                     << (*choice == INC ? "++ " : "-- ")
                     << "is ";

                if (*choice == INC) {
                  cout << *operands + 1 << endl;
                } else {
                  cout << *operands - 1 << endl;
                }
                break;
      case MUL:
      case ADD: cout << INPUT_TWO;
                read_n_ints(operands, 2);

                cout << operands[0] 
                     << (*choice == MUL ? " * " : " + ")
                     << operands[1]
                     << " is ";

                if (*choice == MUL) {
                  cout << operands[0] * operands[1] << endl;
                } else {
                  cout << operands[0] + operands[1] << endl;
                }
                break;

    }
  }
  cout << "Bye.\n";

  delete choice;
  delete [] operands;

  return 0;
}

// Function definitions (alphabetical order)

/*
 *
 *  Abstract: Prints out the border of * seen in the sample output
 *
 *  Edge Cases: N/A
 *
 *  Arguments: N/A
 *
 *  Return Value: N/A
 *
 */
void border() {
  cout << string(27, '*') << endl;
}

/*
 *
 *  Abstract: Get a choice from the user and stores it in
 *            the unsigned int pointed to by choice.
 *            Return true if choice isn't to end.
 *
 *  Edge Cases: N/A
 *
 *  Arguments: choice (unsigned int pointer to a variable
 *                     used to store the user's choice)
 *
 *  Return Value: true if choice isn't END; false otherwise
 *
 */
bool get_choice(unsigned * choice) {
  if (*choice <= END) {
    border();
    cout << MENU;
  }
  
  while (!(cin >> *choice)) {
    // Clear the fail bit
    cin.clear();

    // Get rid of junk in input buffer
    cin.ignore(numeric_limits<int>::max(), '\n');

    cout << INVALID; 
  }

  return *choice != END;
}

/*
 *
 *  Abstract: Read n integers from the user and print a border.
 *
 *  Edge Cases: N/A
 *
 *  Arguments: iarr (pointer to array of ints for storing user input)
 *             n (unsigned int for how many ints to read in)
 *
 *  Return Value: N/A, but iarr's values are over-written by user input
 *
 */
void read_n_ints(int *iarr, unsigned n) {
  for (int i = 0; i < n; i++) {
    cin >> iarr[i];
  }
  border();
}

