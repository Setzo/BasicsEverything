/*
 * Multithread.cpp
 *
 *  Created on: 27 kwi 2015
 *      Author: Setzo
 */

#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <fstream>
#include <cstdlib>

#define WORD_LEN_MAX 16

#define SKIP_MIN 1
#define SKIP_MAX 100

std::string text;

std::vector<std::string> dictionary;

void showSyntax() {

	std::cout << "showSyntax() quad-core" << std::endl;
}

void findWords(std::string &text, std::vector<std::string> &dictionary) {

	std::cout << "findWords() quad-core" << std::endl;

#pragma omp parallel for

	for(unsigned int textId = 0; textId < text.size() - SKIP_MAX * WORD_LEN_MAX; textId++) {

		for(unsigned int dictionaryId = 0; dictionaryId < dictionary.size(); dictionaryId++) {

			for(unsigned int skipValue = SKIP_MIN; skipValue <= SKIP_MAX; skipValue++) {

				bool done = false;
				bool match = true;

				for(unsigned int letterNumber = 0; !done && letterNumber < dictionary[dictionaryId].size(); letterNumber++) {

					char c1 = text[textId + letterNumber * skipValue];
					char c2 = dictionary[dictionaryId][letterNumber];

					if(c1 != c2) {

						done = true;
						match = false;
					}
				}

				if(match) {

					std::cout << dictionary[dictionaryId] << " at " << textId << " skip " << skipValue << std::endl;
				}
			}
		}
	}
}

int main(int argc, const char *argv[]) {

	if(argc != 3) {

		showSyntax();
		exit(1);
	}

	std::string textFilename(argv[1]);
	std::cout << "Text Filename: " << textFilename << std::endl;

	std::string dictionaryFilename(argv[2]);
	std::cout << "Dictionary Filename: " << dictionaryFilename << std::endl;

	std::cout << "Skips: " << SKIP_MIN << " : " << SKIP_MAX << std::endl;

	std::ifstream ifs(dictionaryFilename.c_str());
	std::string tmp;

	while(std::getline(ifs, tmp)) {

		for(unsigned int i = 0; i < tmp.size(); i++) {

			tmp[i] = std::tolower(tmp[i]);
		}

		dictionary.push_back(tmp);
	}

	std::cout << "Words in dictionary: " << dictionary.size() << std::endl;

	ifs.close();

	std::ifstream cin(textFilename.c_str());
	std::cin >> text;
	std::cout << "Input: " << text.size() << std::endl;

	for(int i = 0; i < SKIP_MAX * WORD_LEN_MAX; i++) {

		text += " ";
	}

	findWords(text, dictionary);

	return 0;
}
