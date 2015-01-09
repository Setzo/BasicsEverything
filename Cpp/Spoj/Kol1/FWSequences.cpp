#include <stdlib.h>
#include <stdarg.h>
#include <cstdlib>
#include <cmath>
#include <stdio.h>


int main() {

	int n, x;
	int tab1[101];
	int tab2[101];

	scanf("%d %d", &n, &x);

	for(int i=0; i<n ;i++) {
		scanf("%d", &tab1[i]);
	}

	for(int i=0; i<n ;i++) {
		scanf("%d", &tab2[i]);
	}

	for(int i=0; i<n; i++) {
		int tmp = -x;

		for(int j=0; j < 2*x+1; j++) {

			if((i + tmp >= 0)&&(i + tmp < n)) {
				if(tab1[i] == tab2[i+tmp]) {
					printf("%d ", i+1);
					tab1[i] = 1000;
				}
			}
			tmp++;
		}

	}

	system("pause");
	return 0;
}
