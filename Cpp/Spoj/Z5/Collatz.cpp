#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>
#include <cstring>

int collatz(int s);

int main(){
	int t, s;

	scanf("%d", &t);
	while (t){
		scanf("%d", &s);
		printf("%d\n", collatz(s));
		t--;
	}
	system("pause");
	return 0;
}

int collatz(int s) {
	int c = 0;
	if (s == 1) {
		return 0;
	}
	while (s != 1) {
		c++;
		if (s % 2 == 0) {
			s = s / 2;
		}
		else {
			s = 3 * s + 1;
		}
	}
	return c;
}
