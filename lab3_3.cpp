/*
 *   File Name: lab3_3.cpp
 *        Name: Solution
 *      Course: CST 238
 *        Term: Fall 2014
 *  Assignment: Lab 3
 *    Abstract: Use the Fisher-Yates method of shuffling an array of unsigned ints
 *              up to a user specified value.
 *
 */
#include <algorithm>  // generate
#include <chrono>
#include <iostream>
#include <random>

using namespace std;

void display_uarr(const unsigned *, unsigned);
void shuffle(unsigned *, unsigned);
unsigned ugen();

int main() {
  unsigned n;

  cout << "Shuffle unsigned ints up to: ";
  cin >> n;

  unsigned *uarr = new unsigned[n];
  generate(uarr, uarr + n, ugen);
  shuffle(uarr, n);
  display_uarr(uarr, n);

  delete [] uarr;

  return 0;
}

/*
 *
 *  Abstract: Display all values of an array of unsigned ints
 *
 *  Edge Cases: N/A
 *
 *  Arguments: uarr (pointer to an array of unsigned ints)
 *             n (number of unsigned ints in uarr)
 *             
 *  Return Value: N/A
 *
 */
void display_uarr(const unsigned *uarr, unsigned n) {
  for (int i = 0; i < n; i++) {
    cout << uarr[i] << " ";
  }
  cout << endl;
}


/*
 *
 *  Abstract: Use the Fisher-Yates shuffling algorithm to shuffle
 *            an array of unsigned ints
 *
 *  Edge Cases: N/A
 *
 *  Arguments: uarr (pointer to an array of unsigned ints)
 *             n (number of unsigned ints in uarr)
 *             
 *  Return Value: N/A, but uarr is modified by the shuffling
 *
 */
void shuffle(unsigned *uarr, unsigned n) {
  unsigned seed = chrono::system_clock::now().time_since_epoch().count();
  mt19937 int_gen(seed);

  int j;
  for (int i = n - 1; i > 0; i--) {
    j = int_gen() % (i + 1);
    swap(uarr[i], uarr[j]);
  }
}

/*
 *
 *  Abstract: Generator function used by the generate function in <algorithm>
 *            Starts at zero and goes up by 1 each time the function is called
 *
 *  Edge Cases: N/A
 *
 *  Arguments: N/A
 *             
 *  Return Value: Unsigned int starting at 0 and going up by 1 for each invocation
 *
 */
unsigned ugen() {
  static unsigned n = 0;

  return n++;
}

