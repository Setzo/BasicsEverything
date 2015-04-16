#include <iostream>
#include <iomanip>
#include <stdio.h>
#include <limits.h>

using namespace std;

int main() {

	cout << sizeof(unsigned int) << endl << sizeof(signed int) << endl << sizeof(int) << endl;

	float x = 10.222222;
	double y = 10.222222;
	long double z = 10.222222;

	cout << sizeof(long double) << "\n" << flush;

	cout << setprecision(20) << fixed << x << endl;

	cout << setprecision(20) << fixed << y << endl;

	cout << setprecision(20) << fixed << z << endl;

	cout << setprecision(20) << scientific << x << endl;

	cout << setprecision(20) << scientific << y << endl;

	cout << setprecision(20) << scientific << z << endl;

	int tab[] = {3, 3, 3, 3};

	for(unsigned i = 0; i < sizeof(tab) / sizeof(int); i++) {
		cout << tab[i] << " " << flush;
	}

	return 0;
}
