#pragma once
#include "List.h"

template <class T>
class Queue : private List<T>
{
public:
	Queue();
	~Queue();

	bool enqueue(T &data);
	bool dequeue(T &data);
};

template <class T>
Queue<T>::Queue() : List<T>()
{

}

template <class T>
Queue<T>::~Queue()
{

}

template <class T>
bool Queue<T>::enqueue(T &data)
{
	return this->insertAtEnd(data);
}

template <class T>
bool Queue<T>::dequeue(T &data)
{
	if (this->isEmpty())
		return false;
	else
	{
		data = this->deleteAtFront();

		return true;
	}
}