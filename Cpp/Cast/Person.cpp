
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

int Person::getNumber() {
    return number;
}

bool Person::operator<(int i) {
    return number < i;
}

bool Person::operator<(Person &p1) {
    return number < p1.number;
}

bool operator<(int i, Person p) {
    return i < p.number;
}