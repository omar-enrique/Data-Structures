#ifndef AVL_TREE_H
#define AVL_TREE_H

#include "dsexceptions.h"
#include <iostream>    // For NULL
#include <queue>  // For level order printout
#include <vector>
#include <algorithm> // For max() function
#include <cmath>
using namespace std;

// AvlTree class
//
// CONSTRUCTION: with ITEM_NOT_FOUND object used to signal failed finds
//
// ******************PUBLIC OPERATIONS*********************
// int size( )            --> Quantity of elements in tree
// int height( )          --> Height of the tree (null == -1)
// void insert( x )       --> Insert x
// void insert( vector<T> ) --> Insert whole vector of values
// void remove( x )       --> Remove x (unimplemented)
// bool contains( x )     --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted (in) order
// void printPreOrder( )  --> Print tree in pre order
// void printPostOrder( ) --> Print tree in post order
// void printInOrder( )   --> Print tree in *in* order
// ******************ERRORS********************************
// Throws UnderflowException as warranted

template <typename Comparable>
class AvlTree
{
  public:
    AvlTree( ) : root( NULL )
      { treeSize = 0; }

    AvlTree( const AvlTree & rhs ) : root( NULL )
    {
        *this = rhs;
    }

    ~AvlTree( )
    {
       cout << " [!] Destructor called." << endl;
       makeEmpty( );
    }

    /**
     * Find the smallest item in the tree.
     * Throw UnderflowException if empty.
     */
    const Comparable & findMin( ) const
    {
        if( isEmpty( ) )
            throw UnderflowException( );
        return findMin( root )->element;
    }

    /**
     * Find the largest item in the tree.
     * Throw UnderflowException if empty.
     */
    const Comparable & findMax( ) const
    {
        if( isEmpty( ) )
            throw UnderflowException( );
        return findMax( root )->element;
    }

    /**
     * Returns true if x is found in the tree.
     */
    bool contains( const Comparable & x ) const
    {
        return contains( x, root );
    }

	bool isEmpty() const
	{
		if (root == NULL)
			return true;
		else
			return false;  // so not correct
	}

	/**
	* Return number of elements in tree.
	*/
	int size()
	{
		return size(root);
	}

	/**
	* Return height of tree.
	*  Null nodes are height -1
	*/
	int height()
	{
		return height(root);
	}

	/**
	* Print the tree contents in sorted order.
	*/
	void printTree() const
	{
		if (isEmpty())
			cout << "Empty tree" << endl;
		else
			printInOrder(root);
	}

	/**
	* Print the tree contents in sorted order.
	*/
	void printInOrder() const
	{
		if (isEmpty())
			cout << "Empty tree" << endl;
		else
			printInOrder(root);
	}

	/**
	* Print the tree contents in pre order.
	*/
	void printPreOrder() const
	{
		if (isEmpty())
			cout << "Empty tree" << endl;
		else
			printPreOrder(root);
	}

	/**
	* Print the tree contents in post order.
	*/
	void printPostOrder() const
	{
		if (isEmpty())
			cout << "Empty tree" << endl;
		else
			printPostOrder(root);
	}

	/**
	* Make the tree logically empty.
	*/
	void makeEmpty()
	{
		makeEmpty(root);
	}

	/**
	* Insert x into the tree; duplicates are ignored.
	*/
	void insert(const Comparable & x)
	{
		insert(x, root);
	}

	/**
	* Insert vector of x's into the tree; duplicates are ignored.
	*/
	void insert(vector<Comparable> vals)
	{
		for (auto x : vals) {
			insert(x, root);
		}
	}


	/**
	* Remove x from the tree. Nothing is done if x is not found.
	*  TODO: Implement
	*/
	void remove(const Comparable & x) // Unfortunately will have to be less efficient since I am not allowed to use recursion
	{
		root = remove(x, root);
	}


	/**
	* Deep copy. - or copy assignment operator
	*  Will be in part II
	*/
	const AvlTree & operator=(const AvlTree & rhs)
	{
		cout << " [!] Copy *assignment* operator called." << endl;
		return *this;
	}


	/*****************************************************************************/
private:
	struct AvlNode
	{
		Comparable element;
		AvlNode   *left;
		AvlNode   *right;
		int       height;

		AvlNode(const Comparable & theElement, AvlNode *lt,
			AvlNode *rt, int h = 0)
			: element(theElement), left(lt), right(rt), height(h) { }
	};

	AvlNode *root;

	int treeSize;

	/**
	* Internal method to count nodes in tree
	*  TODO: Implement
	*/
	int size(AvlNode * & t)
	{
		return treeSize;
	}

	AvlNode * remove(const Comparable & x, AvlNode * & t)
	{
		AvlNode *pTemp;
		int difference = 0;

		if (t == NULL)
			return t;
		else if (x < t->element)
			t = remove(x, t->left);
		else if (x > t->element)
			t = remove(x, t->right);
		else
		{
			if (t->left == NULL && t->right == NULL)
			{
				delete t;
				t = NULL;
				treeSize -= 1;
			}
			else if (t->left == NULL)
			{
				pTemp = t;
				t = t->right;
				delete pTemp;
				treeSize -= 1;
			}
			else if (root->right == NULL)
			{
				pTemp = t;
				t = t->left;
				delete pTemp;
				treeSize -= 1;
			}
			else
			{
				pTemp = findMin(t->right);
				t->element = pTemp->element;
				t->right = remove(pTemp->element, t->right);
			}
		}

		if (t != NULL)
		{
			t->height = max(height(t->left), height(t->right)) + 1;

			difference = height(t->left) - height(t->right);
		}

		if (difference > 1)
		{
			if (height(t->left->left) > height(t->left->right)) // If second rotation is left
			{
				rotateWithLeftChild(t);
			}
			else // If second rotation is right
			{
				doubleWithLeftChild(t);
			}
		}
		else if (difference < -1)
		{
			if (height(t->right->left) > height(t->right->right)) // If second rotation is left
			{
				doubleWithRightChild(t);
			}
			else // If second rotation is right
			{
				rotateWithRightChild(t);
			}
		}

		return t;
	}

	/**
	* Internal method to insert into a subtree.
	* x is the item to insert.
	* t is the node that roots the subtree.
	* Set the new root of the subtree.
	*  TODO: Implement
	*/
	void insert(const Comparable & x, AvlNode * & t)
	{
		int difference = 0;

		if (t == NULL) // base case - we found spot in tree to insert
		{
			t = new AvlNode(x, NULL, NULL, 0);
			treeSize += 1;
		}
		else if (x <= t->element)
		{
			insert(x, t->left);
			t->height = t->left->height + 1;
		}
		else
		{
			insert(x, t->right);
			t->height = t->right->height + 1;
		}

		difference = height(t->left) - height(t->right);

		if (difference > 1)
		{
			if (height(t->left->left) > height(t->left->right)) // If second rotation is left
			{
				rotateWithLeftChild(t);
			}
			else // If second rotation is right
			{
				doubleWithLeftChild(t);
			}
		}
		else if (difference < -1)
		{
			if (height(t->right->left) > height(t->right->right)) // If second rotation is left
			{
				doubleWithRightChild(t);
			}
			else // If second rotation is right
			{
				rotateWithRightChild(t);
			}
		}
	}

	/**
	* Internal method to find the smallest item in a subtree t.
	* Return node containing the smallest item.
	*  You'll need this for deletes
	*  TODO: Implement
	*/
	AvlNode * findMin(AvlNode *t) const
	{
		AvlNode *pTemp = t;

		if (t != NULL)
		{
			if (t->left != NULL)
				pTemp = findMin(t->left);
			else
				return pTemp;
		}

		return t; // placeholder
	}

	/**
	* Internal method to find the largest item in a subtree t.
	* Return node containing the largest item.
	*  TODO: Implement
	*/
	AvlNode * findMax(AvlNode *t) const
	{
		AvlNode *pTemp = t;

		if (t != NULL)
		{
			if (t->right != NULL)
				pTemp = findMax(t->right);
			else
				return pTemp;
		}

		return t; // placeholder
	}


	/**
	* Internal method to test if an item is in a subtree.
	* x is item to search for.
	* t is the node that roots the tree.
	*  TODO: Implement
	*/
	bool contains(const Comparable & x, AvlNode *t) const
	{
		bool found = false;

		if (t == NULL) // base case - we found spot in tree to insert
		{
			found = false;
		}
		else if (x < t->element)
		{
			found = contains(x, t->left);
		}
		else if (x > t->element)
		{
			found = contains(x, t->right);
		}
		else
		{
			found = true;
		}

		return found;    // Lolz
	}

	/******************************************************/

	/**
	* Internal method to make subtree empty.
	*  TODO: implement for destructor
	*
	*/
	void makeEmpty(AvlNode * & t)
	{
		AvlNode *pLeft, *pRight;

		if (t != nullptr)
		{
			pLeft = t->left;
			pRight = t->right;

			delete t;

			//Deletes every node in the tree, splitting off to the left and right of the current node
			makeEmpty(pLeft);
			makeEmpty(pRight);
		}
	}

	/**
	* Internal method to print a subtree rooted at t in sorted order.
	*  TODO: Implement
	*/
	void printInOrder(AvlNode *t) const
	{
		if (t != nullptr)
		{
			printInOrder(t->left);
			cout << t->element << " ";
			printInOrder(t->right);
		}
	}

	/**
	* Internal method to print a subtree rooted at t in pre order.
	*  TODO: Implement
	*/
	void printPreOrder(AvlNode *t) const
	{

		if (t != nullptr)
		{
			cout << t->element << " ";
			printPreOrder(t->left);
			printPreOrder(t->right);
		}
	}

	/**
	* Internal method to print a subtree rooted at t in post order.
	*  TODO: Implement
	*/
	void printPostOrder(AvlNode *t) const
	{
		if (t != nullptr)
		{
			printPreOrder(t->left);
			cout << t->element << " ";
			printPreOrder(t->right);
		}
	}

	/**
	* Internal method to clone subtree.
	*/
	AvlNode * clone(AvlNode *t) const
	{
		if (t == NULL)
			return NULL;
		else
			return new AvlNode(t->element, clone(t->left), clone(t->right), t->height);
	}


	// Avl manipulations
	/**

	* Return the height of node t or -1 if NULL.
	*  TODO: Implement
	*/
	int height(AvlNode *t) const
	{
		if (t != NULL)
		{
			return t->height;
		}
		else
			return(-1);  // DEFINITELY not true
	}


	int max(int lhs, int rhs) const
	{
		return lhs > rhs ? lhs : rhs;
	}

	/**
	* Rotate binary tree node with left child.
	* For AVL trees, this is a single rotation for case 1.
	* Update heights, then set new root.
	*  TODO: Implement
	*/
	void rotateWithLeftChild(AvlNode * & k2)
	{
		AvlNode *newRoot; // New Root of SubTree

		newRoot = k2->left;

		k2->left = newRoot->right;

		newRoot->right = k2;

		k2->height = max(height(k2->left), height(k2->right)) + 1;


		newRoot->height = max(height(newRoot->left), height(newRoot->right)) + 1;

		k2 = clone(newRoot);
	}

	/**
	* Rotate binary tree node with right child.
	* For AVL trees, this is a single rotation for case 4.
	* Update heights, then set new root.
	*  TODO: Implement
	*/
	void rotateWithRightChild(AvlNode * & k1)
	{
		AvlNode *newRoot; // New Root of SubTree

		newRoot = k1->right;

		k1->right = newRoot->left;

		newRoot->left = k1;


		k1->height = max(height(k1->left), height(k1->right)) + 1;

		newRoot->height = max(height(newRoot->left), height(newRoot->right)) + 1;

		k1 = clone(newRoot);
	}

	/**
	* Double rotate binary tree node: first left child.
	* with its right child; then node k3 with new left child.
	* For AVL trees, this is a double rotation for case 2.
	* Update heights, then set new root.
	*  TODO: Implement
	*/
	void doubleWithLeftChild(AvlNode * & k3)
	{
		AvlNode *newRoot = k3->left->right;
		newRoot->left = k3->left;
		k3->left = newRoot;
		newRoot->left->right = nullptr;

		newRoot->left->height = max(height(newRoot->left->left), height(newRoot->left->right)) + 1;

		newRoot = k3->left;

		k3->left = newRoot->right;

		newRoot->right = k3;

		k3->height = max(height(k3->left), height(k3->right)) + 1;

		newRoot->height = max(height(newRoot->left), height(newRoot->right)) + 1;

		k3 = clone(newRoot);
	}

	/**
	* Double rotate binary tree node: first right child.
	* with its left child; then node k1 with new right child.
	* For AVL trees, this is a double rotation for case 3.
	* Update heights, then set new root.
	*  TODO: Implement
	*/
	void doubleWithRightChild(AvlNode * & k1)
	{
		AvlNode *newRoot = k1->right->left;
		newRoot->right = k1->right;
		k1->right = newRoot;
		newRoot->right->left = nullptr;

		newRoot->right->height = max(height(newRoot->right->left), height(newRoot->right->right)) + 1;

		newRoot = k1->right;

		k1->right = newRoot->left;

		newRoot->left = k1;

		k1->height = max(height(k1->left), height(k1->right)) + 1;

		newRoot->height = max(height(newRoot->left), height(newRoot->right)) + 1;

		k1 = clone(newRoot);
	}
};

#endif