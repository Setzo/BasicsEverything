
#include <iostream>

using namespace std;

void manipulate(double val) {

	val = 10.0;
	cout << val << endl;
}

void manipulate(double* val) {

	*val = 10.0;
	cout << *val << endl;
}

int main() {

	int val = 8;

	int* p_Val = &val;

	cout << val << endl;
	cout << &val << endl;
	cout << p_Val << endl;
	cout << *p_Val << endl << endl << endl;


	double dVal = 241.0;
	double* p_dVal = &dVal;

	cout << dVal << endl;
	manipulate(dVal);
	cout << dVal << endl;

	cout << "------------------------" << endl;
	dVal = 241.0;

	cout << *p_dVal << endl;
	manipulate(p_dVal);
	cout << *p_dVal << endl;

	cout << "------------------------" << endl;
	dVal = 241.0;

	cout << dVal << endl;
	manipulate(&dVal);
	cout << dVal << endl;


	return 0;
}
