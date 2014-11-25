#include <iostream>

using namespace std;

int a[10000];
int main()
{
	int k, tmp,j=0, s = 0;
	for (;; s++)
	{
		cin >> tmp;
		if ((tmp == 42)&&(j!=1)) k++;
		else if ((tmp==42)&&(j == 1)) break;
		else k = 0;
		a[s] = tmp;
		if (k > 1) j = 1;
	}
	a[s] = tmp;
	for (int i = 0; i <= s; i++)
	{
		cout << a[i] << endl;
	}
}
