
#include "Person.h"
#include <iostream>

Person::Person(
        std::string firstname,
        std::string lastname,
        int number) : firstname(firstname), lastname(lastname), number(number) {

    std::cout << "Creating: " << this->firstname << std::endl;
}

Person::~Person(void) {
    std::cout << "Destroying: " << firstname << std::endl;
}
