
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
};
