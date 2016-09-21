cmake_minimum_required(VERSION 3.6)
project(Cast)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp Person.cpp Person.h Developer.cpp Developer.h)
add_executable(Cast ${SOURCE_FILES})