#pragma once

#include <iostream>
#include <string>
#include <cmath>

using std::cin;
using std::cout;
using std::endl;
using std::string;

class Node
{
public:
	Node(char newLetter, string newCode);
	~Node();
	// we will implement a copy constructor and destructor later...

	char getLetter() const;
	string getCode() const; // can't call non const functions
	Node *& getLeft();
	Node *& getRight();


	void setLetter(const char newData);
	void setCode(const string newData);
	void setLeft(Node * const newLeft);
	void setRight(Node * const newRight);

private:
	// mLetter is the english character, number, or punctuation associated with the morse code
	char mLetter;
	// mCode is the morse code associated with the character
	string mCode;

	Node *mpLeft; // Left Node
	Node *mpRight; // Right Node
};