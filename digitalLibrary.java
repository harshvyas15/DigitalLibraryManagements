/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.*;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Library {
    private Map<Integer, Book> books;

    public Library() {
        books = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void removeBook(int id) {
        books.remove(id);
    }

    public Book getBook(int id) {
        return books.get(id);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Admin {
    public void addBook(Library library, int id, String title, String author) {
        Book book = new Book(id, title, author);
        library.addBook(book);
    }

    public void removeBook(Library library, int id) {
        library.removeBook(id);
    }
}

class DigitalLibraryManager {
    Library library;

    public DigitalLibraryManager() {
        library = new Library();
    }

    public void displayBooks() {
        List<Book> books = library.getAllBooks();
        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println(book.getId() + ": " + book.getTitle() + " by " + book.getAuthor() +
                    (book.isAvailable() ? " (Available)" : " (Not Available)"));
        }
    }

    public void searchBook(int id) {
        Book book = library.getBook(id);
        if (book != null) {
            System.out.println("Book found: " + book.getTitle() + " by " + book.getAuthor());
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    public void issueBook(User user, int bookId) {
        Book book = library.getBook(bookId);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Book issued successfully to " + user.getName());
        } else {
            System.out.println("Book not available for issue.");
        }
    }

    public void returnBook(int bookId) {
        Book book = library.getBook(bookId);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Invalid book ID or book is already available.");
        }
    }
}

public class digitalLibrary {
    public static void main(String[] args) {
        DigitalLibraryManager manager = new DigitalLibraryManager();
        Admin admin = new Admin();

        // Adding books by admin
        admin.addBook(manager.library, 1, "Book1", "Author1");
        admin.addBook(manager.library, 2, "Book2", "Author2");

        // Displaying available books
        manager.displayBooks();

        // Searching for a book
        manager.searchBook(1);

        // Issuing a book
        User user = new User("User1");
        manager.issueBook(user, 1);

        // Displaying available books after issuing
        manager.displayBooks();

        // Returning a book
        manager.returnBook(1);

        // Displaying available books after returning
        manager.displayBooks();
    }
}

