#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>

bool isDivisible(int divis);

int main(){
	int t,n;
	int tab[100];
	scanf("%d", &t);
	for(int x=0; x<t; x++) {
		scanf("%d", &n);
		for(int i=0; i<n; i++) {
			scanf("%d", &tab[i]);
		}
		for(int i=1; i<n; i+=2) {
			if(!isDivisible(i)) {
				printf("%d ", tab[i]);
			}
		}
		for(int i=0; i<n; i+=2) {
			if(isDivisible(i)) {
				printf("%d ", tab[i]);
			}
		}
		printf("\n");
	}
	return 0;
}

bool isDivisible(int divis) {

	if(divis % 2==0) {
		return true;
	}
	else {
		return false;
	}
}
