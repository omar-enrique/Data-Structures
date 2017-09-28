#include "BST.h"

BST::BST()
{
	mpRoot = nullptr;
}

BST::BST(const BST &rhs)
{
	this->mpRoot = rhs.mpRoot;
}

BST::~BST()
{
	deleteTree(); // Frees the memory allocated for every node in the tree
}

Node * BST::getRoot() const
{
	return mpRoot;
}

// read the parameter type from right-to-left;
// newRoot is a constant pointer to a Node. This means
// the contents of newRoot can't be modified, but
// the Node can be modified!
void BST::setRoot(Node * const newRoot)
{
	mpRoot = newRoot;
}


// public
void BST::insert(char letter, string code)
{
	bool done;
	// call private insert ()
	done = insertInOrder(this->mpRoot, letter, code, 0);
}

// private
bool BST::insertInOrder(Node *& pTree, char letter, string code, int height)
{
	/* Code below inserts the nodes in a way that the whole tree will be balanced out perfectly, with the same amount of nodes
		on one side as on another side of the tree */
	bool inserted = false;

	if (pTree == nullptr) // base case - we found spot in tree to insert
	{
		pTree = new Node(letter, code);

		return true;
	}
	//The algorithm h >= log2(n+1)-1 >= log2(n) has been used to balance Binary Search Trees, with
	// h being the vertical maximum height of a balanced Binary Tree and n being the number of nodes this tree will have.
	// So, since log2(39+1)-1 approximates to 4.32... its ceiling is 5, which is h.
	// I use this algorithm for balancing out my binary tree in the following if statement
	else if ((height + 1) <= (int) (log2(39+1))) //n is 39 because there will always be 39 morse character translations in "MorseTable.txt"
	{
		if(inserted == false)
			inserted = insertInOrder(pTree->getLeft(), letter, code, height + 1);
			
		if (inserted == false)
			inserted = insertInOrder(pTree->getRight(), letter, code, height + 1);
	}
	else
	{
		//cout << "duplicate" << endl;
	}

	/* Code below inserts the nodes in order, whereas code above does a balanced tree */

	//if (pTree == nullptr) // base case - we found spot in tree to insert
	//{
	//	pTree = new Node(letter, code);
	//	return true;
	//}
	//else if (letter < pTree->getLetter())
	//{
	//	inserted = insertInOrder(pTree->getLeft(), letter, code, 0);
	//}
	//else if (letter > pTree->getLetter())
	//{
	//	inserted = insertInOrder(pTree->getRight(), letter, code, 0);
	//}
	//else
	//{
	//	cout << "duplicate" << endl;
	//}

	return inserted;
}

void BST::preOrderTraversal()
{
	preOrderTraversal(this->mpRoot);
}


void BST::preOrderTraversal(Node *&pTree)
{
	if (pTree != nullptr)
	{
		cout << pTree->getLetter() << "  " << pTree->getCode() << endl;
		preOrderTraversal(pTree->getLeft());
		preOrderTraversal(pTree->getRight());
	}
}

void BST::print()
{
	print(this->mpRoot);
}

void BST::print(Node *&pTree)
{
	if (pTree != nullptr)
	{
		cout << pTree->getLetter() << "  " << pTree->getCode() << endl;
		print(pTree->getLeft());
		print(pTree->getRight());
	}
}

string BST::search(char letter)
{
	string code;
	bool found;

	found = search(this->mpRoot, letter, code);

	return code;
}

bool BST::search(Node *&pTree, char letter, string &code)
{
	bool found = false;

	if (pTree != nullptr)
	{
		if ((int)letter < 97) // If the character is not a lowercase character
		{
			if (letter == pTree->getLetter()) // If matching character to current letter we are searching has been found
			{
				code = pTree->getCode(); // Get code of matching character
				found = true;
			}
		}
		else // If the character is a lower case character
		{
			if (((int)letter - 32) == (int)pTree->getLetter()) // Reduce ascii value of lower case character to its upper case value
			{
				code = pTree->getCode(); // Then get the matching code of the character
				found = true;
			}
		}

	}

	if (pTree != nullptr && found == false) // If the matching character was not found in this node, keep searching
	{
		found = search(pTree->getLeft(), letter, code);
	}

	if (pTree != nullptr && found == false) // If the matching character was not found in this node, keep searching
	{
		found = search(pTree->getRight(), letter, code);
	}

	return found;
}

void BST::deleteTree()
{
	deleteTree(this->mpRoot);
}

void BST::deleteTree(Node *&pTree)
{
	Node *pNextLeft, *pNextRight;

	if (pTree != nullptr)
	{
		pNextLeft = pTree->getLeft();
		pNextRight = pTree->getRight();

		delete pTree;

		//Deletes every node in the tree, splitting off to the left and right of the current node
		deleteTree(pNextLeft);
		deleteTree(pNextRight);
	}
}