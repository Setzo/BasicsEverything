#!/usr/bin/env bash

declare -i testVariable

testVariable="2+2"
declare -p testVariable

echo $testVariable

declare +i testVariable
declare -p testVariable