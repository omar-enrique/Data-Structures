#pragma once

#include <iostream>

#include "ListNode.h"

using std::cin;
using std::cout;
using std::endl;

// This class defines a container for a list; it's a singly linked list
template <class T> class List
{
public:
	List();                     // default constructor; will always set mpHead to NULL
	List(const List<T> &copyList); // copy constructor - implicitly invoked copying a List object during construction of 
								// another List object, or when a List object is passed-by-value - must perform a deep copy, 
								// which means create new memory for each node copied!
	~List();                    // destructor - implicitly invoked when a List object leaves scope

	List<T> & operator= (const List<T> &rhs); // overloaded assignment operator - must perform a deep copy, which means
										// create new memory (from the heap) for each node copied!

										// getter
	ListNode<T> * getHeadPtr() const;     // returns a copy of the address stored in mpHead

									   // setter
	void setHeadPtr(ListNode<T> * const pNewHead); // modifies mpHead

	bool insertAtFront(const T newData);     // inserts newData at the beginning or front of the list
	bool insertInOrder(const T newData);     // inserts newData in ascending (smallest to largest) order
	bool insertAtEnd(const T newData);       // inserts newData at the end of the list

	bool isEmpty();                            // determines if the list is empty  

	T deleteAtFront();                       // deletes the node at the front of the list
	bool deleteNode(T &searchValue);    // deletes the node with data == searchValue
	T deleteAtEnd();                         // deletes the node at the end of the list

	void printList();                          // visits each node, print the node's data - we could also overload the stream insertion << operator to print the list

private:
	ListNode<T> *firstNode; // pointer to the start or head of the singly linked list
	ListNode<T> *lastNode;

					  // yes, we can make member functions private as well
	ListNode<T> * makeNode(const T newData);    // will only call within insert functions
	void destroyList();                        // deletes each node to free memory; will call in the destructor
	void destroyListHelper(ListNode<T> * const pMem);    // we will use recursion to solve this problem!
};

// default constructor; will always set mpHead to NULL or nullptr
template <class T>
List<T>::List()
{
	firstNode = nullptr;
	lastNode = nullptr;
}

// copy constructor - implicitly invoked copying a List object during construction of 
// another List object, or when a List object is passed-by-value - must perform a deep copy, 
// which means create new memory for each node copied!
template <class T>
List<T>::List(const List &copyList)
{
	ListNode<T> *pCopy = copyList.firstNode;

	//this->mpHead
	if (copyList.firstNode != nullptr)
	{
		ListNode<T> *nNode = new ListNode<T>(pCopy->getData());
		firstNode = nNode;
		ListNode<T> *pWalk = firstNode;
		pCopy = pCopy->getNextPtr();

		while (pCopy->getNextPtr() != nullptr)
		{
			nNode = new ListNode<T>(pCopy->getData());
			pWalk->setNextPtr(nNode);

			pWalk = pWalk->getNextPtr();
			pCopy = pCopy->getNextPtr();
		}

		if (pCopy->getNextPtr() == nullptr)
			lastNode = pCopy;
	}
	else
		firstNode = lastNode = nullptr;
}

template <class T>
List<T>::~List()                   // destructor - implicitly invoked when a List object leaves scope
{
	cout << "Inside List's destructor! Freeing each ListNode in the List!" << endl;
	destroyList();
}

// this function must allocate new memory for each of the nodes in rhs to construct "this" object
template <class T>
List<T> & List<T>::operator= (const List<T> &rhs) // overloaded assignment operator - must perform a deep copy
{
	if (firstNode != nullptr)
	{
		while (_size > 0)
		{
			removeElementAt(0);
		}
	}
	ListNode<T> *pCopy = rhs.firstNode;

	if (rhs.firstNode != nullptr)
	{
		while (pCopy != nullptr)
		{
			addElement(pCopy->getData());

			pCopy = pCopy->getNextPtr();
		}
	}
	else
		firstNode = nullptr;

	return (*this);
}

// getter

template <class T>
ListNode<T> * List<T>::getHeadPtr() const     // returns a copy of the address stored in mpHead
{
	return firstNode;
}

template <class T>
// setter
void List<T>::setHeadPtr(ListNode<T> * const pNewHead) // modifies mpHead
{
	firstNode = pNewHead;
}

template <class T>
// This function creates a new ListNode on the heap, and uses the ListNode constructor to initialize the node!
bool List<T>::insertAtFront(const T newData)     // inserts newData at the beginning or front of the list
{
	//ListNode<T> *pMem = makeNode(newData);
	//bool success = false;                   // C++ has built in bool types - false, true


	//if (pMem != nullptr)
	//{
	//	success = true; // allocated memory successfully
	//					// pMem is pointing to a ListNode object, but a List object does not have direct access to it; must use a setter!
	//	pMem->setNextPtr(mpHead);
	//	mpHead = pMem;
	//}

	//return success;
}

template <class T>
// inserts newData at the end of the list
bool List<T>::insertAtEnd(const T newData)
{
	ListNode<T> *pMem = makeNode(newData);
	ListNode<T> *pCur = firstNode;

	bool success = false;

	if (pMem != nullptr)
	{
		if (this->isEmpty())
			firstNode = lastNode = pMem;
		else
		{
			lastNode->setNextPtr(pMem);

			lastNode = pMem;
		}

		success = true;
	}

	return success;
}

template <class T>
// determines if the list is empty;  
// returns true if the list is empty; false otherwise
bool List<T>::isEmpty()
{
	return (firstNode == nullptr);
}

template <class T>
// deletes the node at the front of the list and returns a copy of the data
// precondition: list must not be empty
T List<T>::deleteAtFront()
{
	T data;
	ListNode<T> *pTemp;
	pTemp = firstNode;

	if (firstNode != nullptr)
	{
		firstNode = firstNode->getNextPtr();

		data = pTemp->getData();

		delete pTemp;
	}

	return data;
}

template <class T>
// deletes the node at the end of the list and returns a copy of the data
// precondition: list must not be empty
T List<T>::deleteAtEnd()
{
	//T data;
	//ListNode<T> *pPrev;
	//ListNode<T> *pCur = mpHead;

	//while (pCur->getNextPtr() != nullptr)
	//{
	//	pPrev = pCur;
	//	pCur = pCur->getNextPtr();
	//}

	//data = pCur->getData();
	//pPrev->setNextPtr(nullptr);
	//delete pCur;

	//return data;
}

template <class T>
// visits each node, print the node's data
void List<T>::printList()
{
	ListNode<T> *pCur = firstNode;

	while (pCur != nullptr)
	{
		cout << pCur->getData() << endl;
		pCur = pCur->getNextPtr();
	}
}

//////////// private member functions! //////////////

template <class T>
// allocates memory for a Listnode; initializes the memory with newData
ListNode<T> * List<T>::makeNode(const T newData)    // will only call within insert functions
{
	ListNode<T> *pMem = new ListNode<T>(newData);  // ListNode constructor is invoked!

	return pMem;
}

template <class T>
// we created this function so that we could use recursion to delete the nodes!
void List<T>::destroyListHelper(ListNode<T> * const pMem)
{
	if (pMem != nullptr)
	{
		destroyListHelper(pMem->getNextPtr());
		delete pMem;    // delete from the back of list to the front!
	}
}

template <class T>
// deletes each node to free memory; will call in the destructor
void List<T>::destroyList()
{
	destroyListHelper(firstNode);
}