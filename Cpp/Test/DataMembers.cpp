#include <iostream>
#include <sstream>
#include "Data.h"

using namespace std;

int main() {

	{
		Data data;
		data.say();
		Data data2(56);
	}

	stringstream ss;

	ss << "Age :";
	ss << 5;
	ss << " ss";

	string x = ss.str();

	cout << ss.str();

	return 0;
}
