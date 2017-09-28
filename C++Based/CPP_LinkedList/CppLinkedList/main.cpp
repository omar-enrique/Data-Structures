#include "ListApp.h"

int main(void)
{
	Queue<int> q;
	int num = 21;
	bool success;

	success = q.enqueue(num);

	num = 1;

	success = q.dequeue(num);

	cout << num << endl;

	return 0;
}