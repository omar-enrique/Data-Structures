#include "BSTNode.h"

Node::Node(char newLetter, string newCode)
{
	mLetter = newLetter;
	mCode = newCode; // will be on heap
	mpLeft = nullptr;
	mpRight = nullptr;
}

Node::~Node()
{
	/*cout << "Inside Node's destructor!" << endl;
	cout << "Deleting node with data: " << mLetter << mCode << endl;*/
}

char Node::getLetter() const // can't call non const functions
{
	return mLetter;
}

string Node::getCode() const // can't call non const functions
{
	return mCode;
}
Node *& Node::getLeft()
{
	return mpLeft;
}
Node *& Node::getRight()
{
	return mpRight;
}

void Node::setLetter(const char newData) // setData (string newData);
{
	mLetter = newData;
}

// setData ("literal string");
void Node::setCode(const string newData) // setData (string newData);
{
	mCode = newData;
}

void Node::setLeft(Node * const newLeft)
{
	mpLeft = newLeft;
}
void Node::setRight(Node * const newRight)
{
	mpRight = newRight;
}