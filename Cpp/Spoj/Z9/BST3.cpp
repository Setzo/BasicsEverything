#include <cstdlib>
#include <stdio.h>

struct node* binaryTree = NULL;

int counter = -1;

struct node {

	int value;
	int level;
	struct node* left;
	struct node* right;
};

struct node* newNode(int value) {

	struct node* binaryTree = new(struct node);

	binaryTree->value = value;
	binaryTree->left = NULL;
	binaryTree->right = NULL;
	binaryTree->level = counter;

	counter = -1;

	return(binaryTree);
}

struct node* insert(struct node* binaryTree, int value) {

	counter++;

	if (binaryTree == NULL) {
		return(newNode(value));
	}
	else {
		if (value <= binaryTree->value) {
			binaryTree->left = insert(binaryTree->left, value);
		}
		else {
			binaryTree->right = insert(binaryTree->right, value);
		}
		return(binaryTree);
	}
}

void printBinaryTree(struct node* binaryTree) {

	if (binaryTree == NULL) return;

	printBinaryTree(binaryTree->left);
	printf("%d : %d\n", binaryTree->level, binaryTree->value);
	printBinaryTree(binaryTree->right);
}

int main() {

	int j, t;

	scanf("%d", &t);

	for (int i = 0; i < t; i++) {

		scanf("%d", &j);
		binaryTree = insert(binaryTree, j);
	}

	printBinaryTree(binaryTree);

	system("pause");
	return 0;
}
