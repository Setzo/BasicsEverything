
#pragma once

#include <string>

class Person {

private:
    std::string firstname;
    std::string lastname;
    int number;

public:
    Person(
        std::string firstname,
        std::string lastname,
        int number);
    ~Person(void);
    int getNumber();
    bool operator<(Person &p1);
    bool operator<(int i);
    friend bool operator<(int i, Person p);
};

bool operator<(int i, Person p);
