#include <iostream>
using namespace std;
int main(int argc, char *args[]) {
	double a, b, c, x;
	cin >> a >> b >> c;
	x = (c - b) / a;
	if ((a * x + b - c == 0) && (a != 0))
		printf("%.2f", x);
	else if (b != c)
		cout << "BR";
	else
		cout << "NWR";
	return 0;
}
