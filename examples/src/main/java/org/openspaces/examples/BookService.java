package org.openspaces.examples;

import java.util.logging.Logger;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService implements InitializingBean, DisposableBean {

    Logger log= Logger.getLogger(this.getClass().getName());
    
    @Autowired
    private BookDAO bookDAO;

    public void afterPropertiesSet() throws Exception {
        log.info("--- Starting Sample DAO Calls");
        int id = 1;
        while(true) {
        	Thread.sleep(900);
        	long start = System.currentTimeMillis();
        	Book b = bookDAO.findBook(id);
        	long stop = System.currentTimeMillis() - start;
        	
        	System.out.println("Found book: " + b.getName() + " in " + stop + " ms");
        	
        	id++;
        	
        	if(id > 10) {
        		id = 1;
        	}
        }
    }
    
   
    public void destroy() throws Exception {
    	
    }
    
}
