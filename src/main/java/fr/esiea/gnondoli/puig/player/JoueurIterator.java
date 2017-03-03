package fr.esiea.gnondoli.puig.player;


import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;



public class JoueurIterator <T> implements Iterator<T> {
	  private final List<T> list;
	  private Iterator<T> it;

	  public JoueurIterator (List<T> l) {
	    this.list = l;
	    this.it = l.iterator();
	  }

	  public boolean hasNext() {
	    return !list.isEmpty();
	  }

	  public T next() {
	    T ret ;

	    if (!hasNext()) {
		      throw new NoSuchElementException();
		    } else {
		    	if (!it.hasNext())
		  	      it = list.iterator();
			    ret = it.next();

		  }

	    return ret;
	  }

	  public void remove() {
	    it.remove();
	  }

}

