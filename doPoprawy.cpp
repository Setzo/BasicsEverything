#include <stdio.h>
#include <stdlib.h>
#include <cstdlib>
#include <iostream>
#include <cstring>
#include <cassert>

using namespace std;

typedef char letter;

#define T_SIZE 1001
#define MAX_ZNAK 256

int my_strcmp(char S1[], char S2[]);

int main(){
  int t; 
  char S1[T_SIZE], S2[T_SIZE];
  scanf_s("%d", &t);

  while(t) { 

    cin.getline(S1,T_SIZE,' ');
    cin.getline(S2,T_SIZE);

	printf("%d\n", my_strcmp(S1, S2));
    
	t--;   
  } 
  return 0;
}

int my_strcmp(char S1[], char S2[]) {

	
	for(int i=0; (i<max(strlen(S1),strlen(S2))); i++) {

		if (S1[i]==S2[i]) {
			if(i+1==(strlen(S1),strlen(S2))) {
				return 0;
			}
			continue;
		}
		else {
			if (S1[i]=='a') {
				return 1;
			}
			else if(S1[i]=='b' && S2[i]!='a') {
				return 1;
			}
			else if(strlen(S1)>strlen(S2)) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
}
