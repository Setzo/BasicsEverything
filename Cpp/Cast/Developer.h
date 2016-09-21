
#pragma once

#include "Person.h"
#include <string>

class Developer : public Person {

private:
    std::string title;
public:
    Developer(std::string firstname,
              std::string lastname,
              int number,
              std::string title);
    ~Developer(void);
};
