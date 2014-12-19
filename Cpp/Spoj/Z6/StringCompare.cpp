#include <iostream>
#include <iomanip>
#include <utility>
#include <string>
#include <algorithm>

typedef char letter;

#define T_SIZE 1001

int my_strcmp(char s1[], char s2[]);

int main()
{
	int t;
	char S1[T_SIZE], S2[T_SIZE];
	scanf_s("%d", &t);
	std::cin.ignore();
	while (t) {
		std::cin.getline(S1, T_SIZE, ' ');
		std::cin.getline(S2, T_SIZE);
		printf("%d\n", my_strcmp(S1, S2));
		t--;
	}
	system("pause");
	return 0;
}

int my_strcmp(char S1[], char S2[]) {
	for (int i = 0; i < std::min(strlen(S1), strlen(S2)); i++) {
		if (i + 1 == std::min(strlen(S1), strlen(S2))) {
			if (strlen(S1) > strlen(S2)) {
				return 1;
			}
			else if (strlen(S1) < strlen(S2)) {
				return -1;
			}
			else if (strlen(S1) == strlen(S2)) {
				return 0;
			}
		}
		if (S1[i] == S2[i]) {
			continue;
		}
		else if (S1[i] == 'c') {
			return -1;
		}
		else if (S2[i] == 'c') {
			return 1;
		}
		else if (S1[i] == 'a') {
			return -1;
		}
		else if (S2[i] == 'a') {
			return 1;
		}
		else if (S1[i] == 'b') {
			return -1;
		}
		else if (S2[i] == 'b') {
			return 1;
		}
	}
}

/*Z jakiegoś powodu, na spoju jest błędna odpowiedź.*/
