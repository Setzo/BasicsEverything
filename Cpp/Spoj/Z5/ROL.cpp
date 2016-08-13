#define ANSI

#include <cstring>
#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>

#define T_SIZE 1000

void read_table(int T[], int n);
void print_table(int T[], int n);
void rol(int T[], int n);


int main(){
	int t, n;
	int T[T_SIZE];

	scanf("%d", &t);
	while (t){
		scanf("%d", &n);
		read_table(T, n);
		rol(T, n);
		printf("\n");
		print_table(T, n);
		t--;
		printf("\n");
	}
	system("pause");
	return 0;
}

void read_table(int T[], int n) {
	for (int i = 0; i < n; i++) {
		scanf("%d", &T[i]);
	}
	return;
}

void rol(int T[], int n) {
	int T2[T_SIZE];
	for (int i = 0; i < n; i++) {
		T2[i] = T[i];
	}
	for (int i = 0; i < n-1; i++) {
		T[i] = T2[i + 1];
	}
	T[n - 1] = T2[0];
}

void print_table(int T[], int n) {
	for (int i = 0; i < n; i++) {
		printf("%d ", T[i]);
	}
}
