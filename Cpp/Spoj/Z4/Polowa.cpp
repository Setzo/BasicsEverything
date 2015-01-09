#include <iostream>
#include <cstdio>
#include <string>

using namespace std;

int main()
{
	int l;
	string c;
	scanf("%d\n", &l);
	while (l) {
		cin >> c;
		for (int i = 0; i < c.length() / 2; i++) {
			printf("%c", c[i]);
		}
		puts("");

		l--;
	}
	return 0;
}
