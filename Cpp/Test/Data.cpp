#include <iostream>
#include "Data.h"

using namespace std;

Data::Data() : x(0) {

	cout << this->x << endl;
}

Data::Data(int x): x(x) {

	cout << this->x << endl;
}

void Data::say() {

	cout << "saying" << endl;
}

Data::~Data() {
	cout << "destroyed;" << endl;
}
