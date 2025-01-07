#include <iostream>
#include <vector>
#include <string>
#include <iomanip>

using namespace std;

struct Book {
    string coverImage, title, authors, publisher, isbn;
    int pages, edition;

    Book(string coverImage, string title, string authors, int pages, string publisher, int edition, string isbn) 
        : coverImage(coverImage), title(title), authors(authors), pages(pages), publisher(publisher), edition(edition), isbn(isbn) {}
};

class BookSystem {
public:
    static void displayDetails(const Book& book) {
        cout << "******************************************************\n";
        cout << "*                                                    *\n";
        cout << "*               Cover image (1:2)                    *\n";
        cout << "*                                                    *\n";
        cout << "******************************************************\n";
        cout << "* Title: " << setw(32) << left << book.title << " Pages: " << setw(3) << right << book.pages << "  *\n";
        cout << "* By: " << setw(47) << left << book.authors << "*\n";
        cout << "* ISBN: " << setw(45) << left << book.isbn << "*\n";
        cout << "* Edition: " << setw(42) << left << book.edition << "*\n";
        cout << "* Published by: " << setw(37) << left << book.publisher << "*\n";
        cout << "*******************************************************\n";
    }

    static void displayTableHeader() {
        cout << "----------------------------------------------------------------------------- \n";
        cout << "| No | Title           | ISBN          | Author(s)      | Edition | Publisher       |\n";
        cout << "----------------------------------------------------------------------------- \n";
    }

    static void displayTableRow(const Book& book, int index) {
        cout << "| " << setw(2) << index << " | " 
             << setw(15) << book.title << " | " 
             << setw(13) << book.isbn << " | " 
             << setw(15) << book.authors << " | " 
             << setw(7) << book.edition << " | " 
             << setw(15) << book.publisher << " |\n";
    }

    static void displayTableFooter() {
        cout << "----------------------------------------------------------------------------- \n";
    }
};

class BookModel {
private:
    vector<Book> books;

public:
    void addBook(const Book& book) {
        books.push_back(book);
    }

    void updateBook(int index, const Book& book) {
        if (index >= 0 && index < books.size()) {
            books[index] = book;
        } else {
            cout << "Invalid book index.\n";
        }
    }

    void deleteBook(int index) {
        if (index >= 0 && index < books.size()) {
            books.erase(books.begin() + index);
        } else {
            cout << "Invalid book index.\n";
        }
    }

    vector<Book> getBooks() const {
        return books;
    }
};

class BookController {
private:
    BookModel model;

public:
    BookController(BookModel model) : model(model) {}

    void addBook(const Book& book) {
        model.addBook(book);
        cout << "Book added successfully!\n";
    }

    void editBook(int index, const Book& book) {
        model.updateBook(index, book);
        cout << "Book updated successfully!\n";
    }

    void deleteBook(int index) {
        model.deleteBook(index);
        cout << "Book deleted successfully!\n";
    }

    void displayBooksAsTable() const {
        vector<Book> books = model.getBooks();
        BookSystem::displayTableHeader();
        for (int i = 0; i < books.size(); ++i) {
            BookSystem::displayTableRow(books[i], i + 1);
        }
        BookSystem::displayTableFooter();
    }

    void displayBookDetails(int index) const {
        vector<Book> books = model.getBooks();
        if (index >= 0 && index < books.size()) {
            BookSystem::displayDetails(books[index]);
        } else {
            cout << "Invalid book index.\n";
        }
    }
};

int main() {
    BookModel model;
    BookController controller(model);
    int choice;

    while (true) {
        cout << "\n=== Book Management System ===\n";
        cout << "1. Add Book\n";
        cout << "2. Edit Book\n";
        cout << "3. Delete Book\n";
        cout << "4. Display All Books (Table)\n";
        cout << "5. Display Book Details (Book Format)\n";
        cout << "6. Exit\n";
        cout << "Choose an option: ";
        cin >> choice;
        cin.ignore(); // Clear input buffer

        switch (choice) {
        case 1: {
            string coverImage, title, authors, publisher, isbn;
            int pages, edition;

            cout << "Enter book details:\n";
            cout << "Cover Image: ";
            getline(cin, coverImage);
            cout << "Title: ";
            getline(cin, title);
            cout << "Authors: ";
            getline(cin, authors);
            cout << "Pages: ";
            cin >> pages;
            cin.ignore();
            cout << "Publisher: ";
            getline(cin, publisher);
            cout << "Edition: ";
            cin >> edition;
            cin.ignore();
            cout << "ISBN: ";
            getline(cin, isbn);

            controller.addBook(Book(coverImage, title, authors, pages, publisher, edition, isbn));
            break;
        }
        case 2: {
            controller.displayBooksAsTable();
            int index;
            cout << "Enter the book number to edit: ";
            cin >> index;
            cin.ignore();

            string coverImage, title, authors, publisher, isbn;
            int pages, edition;

            cout << "Enter new book details:\n";
            cout << "Cover Image: ";
            getline(cin, coverImage);
            cout << "Title: ";
            getline(cin, title);
            cout << "Authors: ";
            getline(cin, authors);
            cout << "Pages: ";
            cin >> pages;
            cin.ignore();
            cout << "Publisher: ";
            getline(cin, publisher);
            cout << "Edition: ";
            cin >> edition;
            cin.ignore();
            cout << "ISBN: ";
            getline(cin, isbn);

            controller.editBook(index - 1, Book(coverImage, title, authors, pages, publisher, edition, isbn));
            break;
        }
        case 3: {
            controller.displayBooksAsTable();
            int index;
            cout << "Enter the book number to delete: ";
            cin >> index;
            controller.deleteBook(index - 1);
            break;
        }
        case 4:
            controller.displayBooksAsTable();
            break;
        case 5: {
            vector<Book> books = model.getBooks();
            if (books.empty()) {
                cout << "No books available to display.\n";
                break;
            }
            cout << "Choose a book to view in detail:\n";
            for (int i = 0; i < books.size(); ++i) {
                cout << i + 1 << ". " << books[i].title << '\n';
            }
            int index;
            cout << "Enter the number of the book: ";
            cin >> index;
            controller.displayBookDetails(index - 1);
            break;
        }
        case 6:
            cout << "Thank you for using this system. :))\n";
            return 0;
        default:
            cout << "Invalid choice! Try again.\n";
        }
    }
}
