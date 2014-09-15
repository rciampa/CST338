/*
 *   File Name: lab3_2.cpp
 *        Name: Solution
 *      Course: CST 238
 *        Term: Fall 2014
 *  Assignment: Lab 3
 *    Abstract: Print all primes up to a user defined value using the Sieve
 *              of Eratosthenes
 *
 */
#include <cstring>    // memset
#include <iostream>

using namespace std;

void sieve(unsigned);

int main() {
  unsigned n;

  cout << "Find primes up to what number: ";
  cin >> n;

  sieve(n);

  return 0;
}

/*
 *
 *  Abstract: Print to stdout all primes up to a certain number
 *            using the Sieve of Eratosthenes
 *
 *  Edge Cases: N/A
 *
 *  Arguments: n (number to find primes to up--inclusive, i.e.,
 *                primes from [2, n])
 *             
 *  Return Value: N/A
 *
 */
void sieve(unsigned n) {
  bool *barr = new bool[++n];
  memset(barr, true, sizeof(bool) * n);

  for (int i = 2; i < n; i++) {
    if (!barr[i]) {
      continue;
    }

    for (int j = 2 * i; j < n; j += i) {
      barr[j] = false;
    }
  }

  cout << "Primes from 2 to " << n - 1 << ": ";
  for (int i = 2; i < n; i++) {
    if (barr[i]) {
      cout << i << " ";
    }
  }
  cout << endl;

  delete [] barr;
}

