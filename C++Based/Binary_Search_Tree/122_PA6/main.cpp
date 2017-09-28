/****************************************************************************************
*	Name: Omar Finol-Evans																*
*	Class: CptS 122, Spring 2017														*
*	Programming Assignment: Project Assignment #6, Lab Section #5						*
*	Date Due: 3/22/2017																	*
*	Description: This program uses a Binary search tree to convert strings given from a *
*                text file to morse code.												*
*																						*
*****************************************************************************************/

#include "BST.h"
#include <vector>
#include <fstream>
using std::fstream;


int main(void)
{
	BST tree;
	char char_holder;
	string holder;

	string conversion;
	string code;

	int i = 0;

	fstream file("MorseTable.txt"); // Open first file for reading

	while (!file.eof())
	{
		file >> char_holder >> holder;

		tree.insert(char_holder, holder); // Create BST containing 39 characters from "MorseTable.txt" and their associated morse code values
	}

	file.close();

	tree.print();

	cout << endl;

	system("pause");

	system("cls");

	file.open("Convert.txt"); // Open new file for reading

	while (!file.eof())
	{
		getline(file, holder);

		cout << holder << endl; // Holder holds the string of the current line being processed from Convert.txt

		for (int i = 0; i < holder.length(); i++)
		{
			code = tree.search(holder[i]);
			conversion = conversion + code + " "; // Conversion holds the string of the morse code for the string being processed
		}

		conversion = conversion + "\n";
	}
	
	cout << endl;
	cout << conversion << endl;

	file.close();

	return 0;
}