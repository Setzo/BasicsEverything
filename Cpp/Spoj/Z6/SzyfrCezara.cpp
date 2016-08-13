#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <cstdlib>
#include <stdarg.h>
#include <string>

void doWork(int k, char tab[])
{
	int len = strlen(tab);
	if (k >= 0) {
		for (int i = 0; i < len; i++) {
			if (isspace(tab[i])) {
			}
			else {
				tab[i] = (tab[i] - 65 + k) % 26 + 65;
			}
		}
	}
	else {
		for (int i = 0; i < len; i++) {
			if (isspace(tab[i])) {
			}
			else {
				tab[i] = (tab[i] + 65 + k) % 26 + 65;
			}
		}
	}
}

int main()
{
	char tab[100];
	std::string c;
	while(std::getline(std::cin, c)) {
		strcpy(tab, c.c_str());
		doWork(3, tab);
		for (int i = 0; i < strlen(tab); i++) {
			printf("%c", tab[i]);
		}
		printf("\n");
	}
	system("pause");
	return 0;
}
