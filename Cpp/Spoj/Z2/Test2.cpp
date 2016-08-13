#include <iostream>

using namespace std;

int a[10000];
int main()
{
	int j, tmp, s = 0;
	for (;; s++)
	{
		cin >> tmp;
		if (tmp == 42) j++;
		else j = 0;
		a[s] = tmp;
		if (j>1) break;
	}

	for (int i = 0; i <= s; i++)
	{
		cout << a[i] << endl;
	}
}
