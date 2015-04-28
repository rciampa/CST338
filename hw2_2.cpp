/*
 *   File Name: hw2_2.cpp
 *        Name: Sample Solution
 *      Course: CST 238
 *        Term: Fall 2014
 *  Assignment: HW2
 *    Abstract: Read in an unsigned int and print out a diamond of stars
 *              based on user input
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
using std::cerr;
using std::cin;
using std::cout;
using std::endl;
using std::string;

// Typedefs

// Constants
const char * PROMPT = "Enter a number: ";

// Function prototypes (alphabetical order)

// Main (if appropriate)
int main() {
  // Allocate some memory
  unsigned *n = new unsigned;
  int *i = new int;
  int *j = new int;

  // Error correction technique taken from:
  // http://ow.ly/BuGL5
  cout << PROMPT;
  while (!(cin >> *n)) {
    // Clear the fail bit
    cin.clear();

    // Get rid of junk in input buffer
    // Can also do: while (std::getchar() != '\n') ;
    cin.ignore(std::numeric_limits<int>::max(), '\n');

    // Print error and try again
    cerr << "Invalid input.\n";
    cout << PROMPT;
  }

  // Store each line of the diamond in an array of strings
  string *lines = new string[2 * (*n) - 1];

  // Fill in the array of strings with the lines to print
  for (*i = 0, *j = 2 * (*n) - 2; *i < *n; (*i)++, (*j)--) {
    // Number of spaces = | n - i + 1 |
    // Number of stars = 2 * (i + 1) - 1
    lines[*i] = string(*n - *i + 1, ' ') + string(2 * (*i + 1) - 1, '*');
    lines[*j] = lines[*i];
  }

  // Print out the array of strings
  for (*i = 0; *i < (*n) * 2 - 1; (*i)++) {
    cout << lines[*i] << endl;
  }

  // Deallocate memory
  delete n;
  delete i;
  delete j;
  delete [] lines; 
}

