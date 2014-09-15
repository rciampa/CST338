/*
 *   File Name: lab3_4.cpp
 *        Name: Solution
 *      Course: CST 238
 *        Term: Fall 2014
 *  Assignment: Lab 3
 *    Abstract: Fill in two arrays whose size is specified by the user with
 *              randomly generated ints, sort the arrays, merge them, and
 *              display the results.
 *
 */
#include <algorithm>
#include <cassert>
#include <chrono>
#include <iostream>
#include <random>

using namespace std;

void display_result(const int *, unsigned);
void merge(const int *, unsigned, const int *, unsigned, int *);
void randomly_fill(int *, unsigned);

int main() {
  unsigned n1, n2;
  cout << "Enter the size of array 1: ";
  cin >> n1;

  int *arr1 = new int[n1];
  randomly_fill(arr1, n1);
  sort(arr1, arr1 + n1);

  cout << "Enter the size of array 2: ";
  cin >> n2;

  int *arr2 = new int[n2];
  randomly_fill(arr2, n2);
  sort(arr2, arr2 + n2);

  int *result = new int[n1 + n2];

  merge(arr1, n1, arr2, n2, result);
  assert(is_sorted(result, result + n1 + n2));

  display_result(result, n1 + n2);

  delete [] arr1;
  delete [] arr2;
  delete [] result;

  return 0;
}

/*
 *
 *  Abstract: Display all values of an array of ints
 *
 *  Edge Cases: N/A
 *
 *  Arguments: result (pointer to an array of ints)
 *             n (number of ints in result)
 *             
 *  Return Value: N/A
 *
 */
void display_result(const int *result, unsigned n) {
  const int * end = result + n;

  while (result != end) {
    cout << *result++ << " ";
  }
  cout << endl;
}

/*
 *
 *  Abstract: Merge together two sorted arrays of ints
 *
 *  Edge Cases: The array pointed to by result must have enough
 *              space to hold n1 + n2 ints.
 *
 *  Arguments: arr1 (pointer to the first array of ints)
 *             n1 (number of ints in arr1)
 *             arr2 (pointer to the second array of ints)
 *             n2 (number of ints in arr2)
 *             result (pointer to the array where the result of
 *                     the merging should go)
 *             
 *  Return Value: N/A, but result is modified and contains the merged
 *                arrays when the function returns
 *
 */
void merge(const int *arr1, unsigned n1, 
           const int *arr2, unsigned n2, 
           int *result) {
  assert(is_sorted(arr1, arr1 + n1) &&
         is_sorted(arr2, arr2 + n2));

  const int * last1 = arr1 + n1;
  const int * last2 = arr2 + n2;

  while (true) {
    if (arr1 == last1) {
      copy(arr2, last2, result);
      break;
    }

    if (arr2 == last2) {
      copy(arr1, last1, result);
      break;
    }

    if (*arr1 < *arr2) {
      *result++ = *arr1++;
    } else {
      *result++ = *arr2++;
    }
  }

}

/*
 *
 *  Abstract: Populate an array of ints with random values
 *
 *  Edge Cases: If arr is NULL, the program will crash
 *
 *  Arguments: arr (pointer to an arry of ints where the randomly
 *                  generated values will go)
 *             n (number of ints to generate and store in arr)
 *             
 *  Return Value: N/A, but n ints in arr are over-written with randomly
 *                generated values
 *
 */
void randomly_fill(int *arr, unsigned n) {
  unsigned seed = chrono::system_clock::now().time_since_epoch().count();
  mt19937 int_gen(seed);

  int *last = arr + n;
  while (arr != last) {
    *arr++ = int_gen();
  }
}

