// This example illustrates how to define a Stack class template.

#include "TestStack.h"

//#include <vector>

int main(void)
{
	int item1 = -89, item2 = 104;
	char item = '\0';
	string end;

	bool take;

	Stack<int> toPost(0);
	Stack<char> toIn(0);

	string str = "123*+4-";

	take = toPost.infixToPost(str);

	take = toPost.peek(item1);

	cout << item1 << endl;

	system("pause");
	system("cls");

	str = "(6+2)*5-8/4";

	end = toIn.postfixToIn(str);

	cout << "Postfix: "<< str << " Infix: " << end << endl;

	system("pause");

	str = "(1+2)*3-4";

	end = toIn.postfixToIn(str);

	cout << "\nPostfix: " << str << " Infix: " << end << endl;

	system("pause");


	//	vector<int> my_vector; // vector<> is from the Standard Template Library (STL)

	TestStack<int> tester;

	tester.Test(item1, item2);

	return 0;
}