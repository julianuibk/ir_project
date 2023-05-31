CXX := g++

CXXFLAGS := -std=c++17 -Wall -Wextra -pedantic

all: main

main: main.o
	$(CXX) $(CXXFLAGS) -o $@ $<

main.o: main.cpp
	$(CXX) $(CXXFLAGS) -c $<

clean:
	rm -f main main.o