/*
 *   File Name: lab3_1.cpp
 *        Name: Solution
 *      Course: CST 238
 *        Term: Fall 2014
 *  Assignment: Lab 3
 *    Abstract: Generate a user defined number of points, i.e., (X, Y) coordinates,
 *              and display the min and max distance for all pairs of points.
 *
 */
#include <algorithm>  // sort
#include <cmath>      // pow, sqrt
#include <iomanip>
#include <iostream>
#include <random>
#include <vector>

#include "point.h"

using namespace std;

bool cmp_point_dists(const point_dist &, const point_dist &);
double distance(const point *, const point *);
void find_point_dists(const point *, unsigned, vector<point_dist> &);
void gen_rand_points(point *, unsigned);
void min_max_dists(vector<point_dist> &);
void print_point(const point *);

int main() {
  // Allocate variables
  unsigned n;
  cout << "Enter the number of points to generate: ";
  cin >> n;
  point *parr = new point[n];
  
  // Generate random points
  gen_rand_points(parr, n);

  // Find the distances between all pairs of points
  vector<point_dist> vpd;
  find_point_dists(parr, n, vpd);

  // Display the max and min distance b/w any two points
  min_max_dists(vpd);

  // Deallocate variables
  delete [] parr;

  return 0;
}

/*
 *
 *  Abstract: Comparison function for sort. Defines how to compare
 *            two point_dist structs for ascending order sorting.
 *
 *  Edge Cases: If either of the dist members are not set the resulting
 *              sort is undefined.
 *
 *  Arguments: Two constant references to point_dist structs
 *
 *  Return Value: True if pd1's dist is <= pd2's dist
 *
 */
bool cmp_point_dists(const point_dist &pd1, const point_dist &pd2) {
  return pd1.dist <= pd2.dist;
}

/*
 *
 *  Abstract: Calculate the distance between two point structs
 *
 *  Edge Cases: If a == b || (a == NULL || b == NULL), errors will
 *              result and won't be handled by the function
 *
 *  Arguments: Constant pointers to point structs
 *
 *  Return Value: The distance between the points using the standard
 *                distance formula for two Cartesian coordinates
 *
 */
double distance(const point *a, const point *b) {
  double inner = pow(a->x - b->x, 2) + pow(a->y - b->y, 2);
  return sqrt(inner);
}

/*
 *
 *  Abstract: Calculate the distance between every pair of points in
 *            an array of points and store the results in a vector of
 *            point_dist structs.
 *
 *  Edge Cases: If more memory is required to store the point_dist structs
 *              the program will crash 
 *
 *  Arguments: parr (const pointer to an array of point structs)
 *             n (number of point structs pointed to by parr)
 *             vpd (reference to a vector of point_dists)
 *
 *  Return Value: N/A, but vpd will be modified by the function if n > 0
 *
 */
void find_point_dists(const point *parr, unsigned n, vector<point_dist> &vpd) {
  point_dist pd;
  for (int i = 0; i < n; i++) {
    for (int j = i+1; j < n; j++) {
      // Fill in the point_dist struct
      pd.p1 = parr + i;
      pd.p2 = parr + j;
      pd.dist = distance(pd.p1, pd.p2);

      // Add it to the vector
      vpd.push_back(pd);
    }
  }
}

/*
 *
 *  Abstract: Generate n random point structs. All x and y coordinates
 *            are between -1000.0 and 1000.0, inclusive
 *
 *  Edge Cases: If more points are required than the machine has
 *              memory, the function will crash
 *
 *  Arguments: parr (pointer to an array of point structs)
 *             n (number of point structs to generate)
 *
 *  Return Value: N/A, but the array parr points to will be overwritten
 *                with randomly generated doubles      
 *
 */
void gen_rand_points(point *parr, unsigned n) {
  uniform_real_distribution<double> unid(-1000.0, 1000.0);
  random_device rd;
  // Without seeding the default random engine
  // with a new value, the sequence of random
  // numbers will always be the same
  default_random_engine dre(rd());

  for (int i = 0; i < n; i++) {
    parr[i].x = unid(dre);
    parr[i].y = unid(dre);
  }
}

/*
 *
 *  Abstract: Find and print the min and max dist between any two pairs of points
 *
 *  Edge Cases: N/A
 *
 *  Arguments: vpd (array of point_dist structs)
 *
 *  Return Value: N/A, but vpd is modified.  vpd is sorted before the min and max
 *                are determined.
 *
 */
void min_max_dists(vector<point_dist> &vpd) {
  // Sort to find the max and min distances
  sort(vpd.begin(), vpd.end(), cmp_point_dists);
  double min_dist = vpd.front().dist;
  double max_dist = vpd.back().dist;

  cout << "Min dist: " << fixed << showpoint << setprecision(2) 
       << min_dist << endl;
  cout << " Points: ";
  for (int i = 0; vpd[i].dist == min_dist && i < vpd.size(); i++) {
    print_point(vpd[i].p1);
    cout << " & ";
    print_point(vpd[i].p2);

    if (i > 0) {
      cout << "; ";
    }
  }
  cout << endl; 

  cout << "Max dist: " << max_dist << endl;
  cout << " Points: ";
  for (int i = vpd.size() - 1; vpd[i].dist == max_dist && i >= 0; i--) {
    print_point(vpd[i].p1);
    cout << " & ";
    print_point(vpd[i].p2);

    if (i < vpd.size() - 1) {
      cout << "; ";
    }
  }
  cout << endl; 
}

/*
 *
 *  Abstract: Print out the contents of a point struct p like so:
 *            (p->x, p->y).  No newline is printed.
 *
 *  Edge Cases: If p is NULL the program will crash
 *
 *  Arguments: p (const point struct)
 *
 *  Return Value: N/A
 *
 */
void print_point(const point *p) {
  cout << "(" << p->x << ", " << p->y << ")";
}

