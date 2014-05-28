package org.openspaces.examples;


import java.util.logging.Logger;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component(value="bookDAO")
public class BookDAO {
	
    Logger log = Logger.getLogger(this.getClass().getName());
    
	@Cacheable("books")
	public Book findBook(int id) throws InterruptedException {
		Book book = new Book();
		book.setId(id);
		book.setName("Learning Java, part " + id);
		
		// Simulate heavy database read latency
		Thread.sleep(100);
		
		return book;
	}
	
	@CacheEvict(value = "books", allEntries=true)
	public void loadBooks() {
		
	}
	
	@Cacheable(value="books", key="isbn.rawNumber")
	public Book findBook(ISBN isbn, boolean checkWarehouse, boolean includeUsed) throws InterruptedException {
		Book book = new Book();
		book.setId(1);
		book.setName("Learning Java, part " + isbn.getRawNumber());
		
		// Simulate heavy database read latency
	     Thread.sleep(150);
	     
	     return book;
	}
	
}
