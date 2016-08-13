#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>

float count(int ta[100], int n);
float wsp(float x, int inte);

int main(){
	int t,n,bls;
	int tab[100];
	float c;
	scanf("%d", &t);
	for(int x=0; x<t; x++) {
		scanf("%d", &n);
		for(int i=0; i<n; i++) {
			scanf("%d", &tab[i]);
		}
		c=count(tab, n);
		printf("\n");
		for(int i=0; i<n; i++) {
			if(i==0) {
				bls=tab[i];
			}
			if(wsp(c,tab[i]) < wsp(c,bls)) {
				bls=tab[i];
			}
		}
		printf("%d", bls);
		printf("\n");
	}
	system("pause");
	return 0;
}

float count(int ta[100], int n) {

	float d;
	int sum=0;
	for(int i=0; i<n; i++) {
		sum=sum+ta[i];
	}
	d=n;
	return (sum / d);
}

float wsp(float x, int inte) {
	if(x >= inte) {
		return (x-inte);
	}
	else {
		return (inte-x);
	}
}
