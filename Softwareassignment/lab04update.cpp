#include <iostream>
#include <thread>
#include <iomanip>
#include <vector> // To use dynamic array
#include <algorithm>
using namespace std;

struct Book // adaptee
{
    string Cover;
    string Title;
    string Isbn;
    string Authors;
    int Pages;
    string Publisher;
};

// Adaptor 1
void adaptor1(const vector<Book>& books) {
    int size = books.size();
    for (int i = 0; i < size; i += 2) {
        Book b = books[i];
        if ((i + 1) < size) {
            Book b1 = books[i + 1];
            cout << R"(
-------------------------   -------------------------
|   )";
            cout << setw(11) << b.Cover;
            cout << R"( (2:1)   |   |   )";
            cout << setw(11) << b1.Cover;
            cout << R"( (2:1)   |
| )";
            cout << setw(10) << b.Title;
            cout << R"(        )" << b.Pages << R"( |   | )";
            cout << setw(10) << b1.Title;
            cout << R"(        )" << b1.Pages << R"( |
-------------------------   -------------------------)";
        } else {
            cout << R"(
-------------------------
|   )";
            cout << setw(11) << b.Cover;
            cout << R"( (2:1)   |
| )";
            cout << setw(10) << b.Title;
            cout << R"(        )" << b.Pages << R"( |
-------------------------)";
        }
    }
}

// Adaptor 2: Table row
void adaptor2(const vector<Book>& books) {
    cout << "\n-----------------------------------------------------------------";
    for (size_t i = 0; i < books.size(); i++) {
        Book b = books[i];
        cout << R"(
|)" << setw(3) << (i + 1) << R"(| )" << setw(10) << b.Title
             << R"( | )" << setw(10) << b.Isbn << R"( | )" << setw(10) << b.Authors << R"( | )" << setw(3) << "1" << R"( | )" << setw(10) << b.Publisher << R"( |)";
    }
    cout << "\n-----------------------------------------------------------------";
}

// Function to add a book
void addBook(vector<Book>& books) {
    Book newBook;
    cout << "Enter cover: "; cin >> newBook.Cover;
    cout << "Enter title: "; cin >> newBook.Title;
    cout << "Enter ISBN: "; cin >> newBook.Isbn;
    cout << "Enter authors: "; cin >> newBook.Authors;
    cout << "Enter pages: "; cin >> newBook.Pages;
    cout << "Enter publisher: "; cin >> newBook.Publisher;
    books.push_back(newBook);
    cout << "Book added successfully!\n";
}

// Function to delete a book by ISBN
void deleteBook(vector<Book>& books) {
    string isbn;
    cout << "Enter ISBN of the book to delete: "; cin >> isbn;
    auto it = remove_if(books.begin(), books.end(), [&isbn](Book& b) { return b.Isbn == isbn; });
    if (it != books.end()) {
        books.erase(it, books.end());
        cout << "Book deleted successfully!\n";
    } else {
        cout << "Book not found!\n";
    }
}

int main() {
    vector<Book> books = {
        {"Cover", "Title5", "0000012345", "Author5", 235, "Publisher5"},
        {"Cover1", "Title1", "0000012346", "Author1", 235, "Publisher1"},
        {"Cover2", "Title2", "0000012347", "Author2", 235, "Publisher2"},
        {"Cover3", "Title3", "0000012348", "Author3", 235, "Publisher3"},
        {"Cover4", "Title4", "0000012349", "Author4", 235, "Publisher4"}
    };

    void (*p[])(const vector<Book>&) = {adaptor1, adaptor2};
    int option;

    while (true) {
        cout << "\n\nChoose an option:\n1. Display Grid\n2. Display Table\n3. Add Book\n4. Delete Book\n5. Exit\n";
        cin >> option;

        if (option == 1 || option == 2) {
            p[option - 1](books);
        } else if (option == 3) {
            addBook(books);
        } else if (option == 4) {
            deleteBook(books);
        } else if (option == 5) {
            break;
        } else {
            cout << "Invalid option. Please try again.\n";
        }
    }

    return 0;
}