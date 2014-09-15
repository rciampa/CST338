#ifndef _POINT_H_
#define _POINT_H_

struct point {
  double x;
  double y;
};

// point_dist stores the distance
// between two points along with
// pointers to the points themselves
struct point_dist {
  double dist;
  const point *p1;
  const point *p2;
};

#endif

