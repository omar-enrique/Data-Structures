// This file contains a stack class template. The underlying data structure for the 
// stack is an array allocated from the heap.

#pragma once

#include <iostream>
#include <string>

using std::cout;
using std::cin;
using std::endl;
using std::string;

template <class T>
class Stack
{
public:
	Stack(int newSize = 0);
	~Stack();

	bool push(T &newItem);
	bool pop(T &poppedItem);
	bool peek(T &item);

	bool isEmpty();

	bool infixToPost(string &line);
	string postfixToIn(string &line);

private:
	int mSize; // represents the current number of items in the stack
	int mMaxSize; // must not exceed the max size of our allocated array
	T *mTop; // will point to contigous memory on the heap (an array)
};

template <class T>
Stack<T>::Stack(int newSize)
{
	mSize = newSize; // this can also be used as the index to the top of the stack
	mMaxSize = 100;
	mTop = new T[100]; // allocating an array of 100 items of type T on the heap
}

template <class T>
Stack<T>::~Stack()
{
	delete[] mTop; // this is how we free up an array on the heap in C++
}

// Places the newItem at the top of the stack
template <class T>
bool Stack<T>::push(T &newItem)
{
	if (mSize < mMaxSize)
	{
		mTop[mSize] = newItem;
		mSize++;
		return true;
	}

	return false;
}

// In this implementation you will apply defensive design. You must check to 
// see if the stack is empty or not before you pop. Places the popped item in 
// the parameter referred to as "poppedItem". Returns true if the item was popped; false
// otherwise.
template <class T>
bool Stack<T>::pop(T &poppedItem)
{
	if (this->isEmpty() == false)
	{
		poppedItem = mTop[mSize - 1];
		mSize--;
		return true;
	}

	return false;
}

// In this implementation you will apply defensive design. You must check to 
// see if the stack is empty or not before you peek. Places the item at the top of the
// stack in the parameter referred to as "item". Returns true if there
// is an item at the top; false otherwise.
template <class T>
bool Stack<T>::peek(T &item)
{
	if (isEmpty() == false)
	{
		item = mTop[mSize - 1];
		return true;
	}

	return false;
}

// Returns true if the stack is empty; false otherwise
template <class T>
bool Stack<T>::isEmpty()
{
	if (mSize == 0)
		return true;

	return false;
}

template <class T>
bool Stack<T>::infixToPost(string &line)
{
	int length = line.length();
	char temp;
	int current;
	int add1 = 0, add2 = 0, end = 0;

	bool check1, check2;

	for (int i = 0; i < length; i++)
	{
		temp = line[i];

		if ((int)temp > 47 && (int)temp < 58)
		{
			current = (int)temp - 48;
			push(current);
		}
		else
		{
			check1 = pop(add1);
			check2 = pop(add2);

			if (check1 && check2)
			{
				if (temp == '+')
				{
					end = add2 + add1;
				}
				else if (temp == '-')
					end = add2 - add1;
				else if (temp == '/')
					end = add2 / add1;
				else if (temp == '*')
					end = add2 * add1;
			}

			push(end);
		}
	}

	return true;
}

template <class T>
string Stack<T>::postfixToIn(string &line)
{
	int length = line.length();
	string str = "";
	char temp = '\0';
	char current;
	bool check1, check2;

	for (int i = 0; i < length; i++)
	{
		if (line[i] == '(')
		{
			i++;
			temp = line[i];
			while (temp != ')')
			{
				temp = line[i];
				if ((int)temp <= 47 || (int)temp >= 58)
				{
					current = temp;
					push(current);
				}
				else
				{
					str += temp;
				}

				if (temp == '*' || temp == '/')
				{
					i += 1;
					str += line[i];
					while (isEmpty() == false)
					{
						pop(current);

						str += current;
					}
				}
				i++;
				temp = line[i];
			}

			while (isEmpty() == false)
			{
				pop(current);

				str += current;
			}
		}
		else
		{
			temp = line[i];
			if ((int)temp <= 47 || (int)temp >= 58)
			{
				current = temp;
				push(current);
			}
			else
			{
				str += temp;
			}

			if (temp == '*' || temp == '/')
			{
				i += 1;
				str += line[i];
				while (isEmpty() == false)
				{
					pop(current);

					str += current;
				}
			}
		}
	}

	while (isEmpty() == false)
	{
		pop(current);

		str += current;
	}

	return str;
}