package com.kodilla.pomiar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.lang.*;
import java.io.*;
// constructor with arguments
class Book {
	private String title;
	private String author;

	public Book(String title, String author){
		this.title = title;
		this.author = author;
	}
	// toString method used for printout the object
	public String toString(){
		return "Title: \"" + title + "\", author: " + author;
	}

	// getters
	public String  getTitle(){
		return title;
	}

	public String getAuthor(){
		return author;
	}
	@Override
	public int hashCode(){
		return Objects.hash(title, author);
	}
	@Override
	public boolean equals(Object o){
		Book e = (Book) o;
		return (title.equals(e.getTitle())) &&
				(author.equals(e.getAuthor())) ;
	}
}

class BookList {

	private LinkedList<Book> list;

	public BookList(LinkedList<Book> list){
		this.list = list;
	}

	public long DeleteTimer(Book book) {
		long begin = System.nanoTime();
		list.remove(book);
		long end = System.nanoTime();

		return end - begin;
	}

	public long addTimer(int n,Book book) {
		long begin = System.nanoTime();
		list.add(n, book);
		long end = System.nanoTime();

		return end - begin;
	}
}

class BookHashMap {

	private HashMap<Integer, Book> hashlist;

	public BookHashMap(HashMap<Integer, Book> hashlist) {
		this.hashlist = hashlist;
	}

	public long findTimer(Integer n) {
		long begin = System.nanoTime();
		Book book = hashlist.get(n);
		long end = System.nanoTime();

		return end - begin;
	}

	public long addTimer(Integer n, Book book) {
		long begin = System.nanoTime();
		hashlist.put(n, book);
		long end = System.nanoTime();

		return end - begin;
	}

	public long deleteTimer(Integer n, Book book) {
		long begin = System.nanoTime();
		hashlist.remove(n, book);
		long end = System.nanoTime();

		return end - begin;
	}
}

@SpringBootApplication
public class PomiarApplication {

	public static void main(String[] args) {
		SpringApplication.run(PomiarApplication.class, args);

		System.out.println("Zadanie: 3.5 Pomiar szybkosci LinkedList oraz HashMap \n");
		Book book1 = new Book("Ksiazka 1", "Au Tor 1");
		Book book2 = new Book("Ksiazka 2", "Au Tor 2");
		Book book3 = new Book("Ksiazka 3", "Au Tor 3");
		Book book4 = new Book("Ksiazka 4", "Au Tor 4");
		Book book5 = new Book("Ksiazka 5", "Au Tor 5");

		// Creating   and filling it with objects
		LinkedList<Book> list = new LinkedList<Book>();
		list.add(0, book1);
		list.add(1, book1);
		list.add(2, book1);
		list.add(3, book1);
		list.add(4, book1);

		BookList bookList = new BookList(list);

		System.out.println("Size of the list <LinkedList>: " + list.size());
		System.out.println("Time to find and remove the first element of the list:(remove(Object o)) " + bookList.DeleteTimer(book1) + " ns");
		System.out.println("Time to find and remove the last element of the list: (remove(Object o)) " + bookList.DeleteTimer(book5) + " ns \n");

		Book book6 = new Book("Ksiazka 6", "Au Tor 6");
		LinkedList<Book> list1 = new LinkedList<Book>();
		BookList bookList1 = new BookList(list1);

		for (int n = 0; n < 4000000; n++) {
			list1.add(n, book1);
		}

		System.out.println("Size of the list <LinkedList>: " + list1.size());
		System.out.println("Time to add the element at the beginning of the list (ist.add): " + bookList1.addTimer(0, book1) + " ns");
		System.out.println("Time to add the element to the end of the list (ist.add): " + bookList1.addTimer(list.size() - 1, book1) + " ns \n");

		HashMap<Integer, Book> hashlist = new HashMap<Integer, Book>();

		for (int i = 0; i < 3500000; i++) {
			hashlist.put(i, book1);
		}

		BookHashMap bookHashMap = new BookHashMap(hashlist);

		System.out.println("Size of the map <HashMap>: " + hashlist.size());
		System.out.println("Time to find  the first element of the map: " + bookHashMap.findTimer(1) + " ns");
		System.out.println("Time to find  the last element of the list: " + bookHashMap.findTimer(hashlist.size() - 1) + " ns");
		System.out.println("Time to add the element at the beginning of the map: " + bookHashMap.addTimer(1, book2) + " ns");
		System.out.println("Time to remove the element at the beginning of the map: " + bookHashMap.deleteTimer(1, book2) + " ns");
	}
}
