#pragma once

#include <iostream>
#include <string>
#include "BSTNode.h"

using std::cin;
using std::cout;
using std::endl;
using std::string;

class BST
{
public:
	BST();
	BST(const BST &rhs);
	~BST();

	Node *getRoot() const;

	void setRoot(Node * const newRoot);

	// will call on private insert (), pass through the data

	void insert(char letter, string code);

	void print();
	void preOrderTraversal();
	string search(char letter);

	void deleteTree();

private:
	Node *mpRoot;


	void print(Node *&pTree);
	void preOrderTraversal(Node *&pTree);
	// hides pointer information for recursive calls
	bool insertInOrder(Node *& pTree, char letter, string code, int height);

	bool BST::search(Node *&pTree, char letter, string &code);

	void deleteTree(Node *&pTree);

	// hides pointer information for recursive calls
};