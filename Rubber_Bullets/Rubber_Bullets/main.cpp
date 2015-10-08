//
//  main.cpp
//  Rubber_Bullets
//
//  Created by Kumin In on 12/19/14.
//  Copyright (c) 2014 kumin In. All rights reserved.
//

#include <iostream>
#include <math.h>
using namespace std;
int main(int argc, const char * argv[]) {
   bool done = false;
   int radius, testRig = 1, distance, distanceTraveled = 0;
   double angle;
   
   while (!done) {
      cin >> radius;
      if (radius == 0) {
         break;
      } else {
         cout << "Test Rig " << testRig << endl;
         testRig++;
      }
      while (!done) {
         cin >> distance >> angle;
         if (distance == 0 && angle == 0) {
            done = true;
         } else {
            angle = ((angle-90)/180) * M_PI;
            distanceTraveled = distance / (radius * cos(angle) * 2);
            cout << distanceTraveled << endl;
         }
      }
   }
}
