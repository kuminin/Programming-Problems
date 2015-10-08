#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>


using namespace std;

void paren(int opening, int closing, string parenthesis);

int main(int argc, char const *argv[])
{
    int n;
    cin >> n;
    paren(n, 0, "");
    return 0;
}

// Generate all the possible Complete Parenthesis
void paren(int opening, int closing, string parenthesis) {
    if (opening == 0 && closing == 0) {
        cout << parenthesis << endl;
    }
    if (opening > 0) {
        paren(opening-1, closing+1, parenthesis + "( ");
    }
    if (closing > 0) {
        paren(opening, closing-1, parenthesis + " )");
    }
    
}