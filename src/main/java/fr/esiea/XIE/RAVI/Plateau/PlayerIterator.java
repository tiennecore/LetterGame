package fr.esiea.XIE.RAVI.Plateau;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class PlayerIterator <T> implements Iterator<T> {
	  private final List<T> l1;
	  private Iterator<T> it;

	  public PlayerIterator (List<T> l) {
	    this.l1 = l;
	    this.it = l.iterator();
	  }

	  public boolean hasNext() {
	    return !l1.isEmpty();
	  }

	  public T next() {
	    T ret;

	    if (!hasNext()) {
	      throw new NoSuchElementException();
	    } else {
	    	if (!it.hasNext())
	  	      it = l1.iterator();
		    ret = it.next();

	  }

	    return ret;
	  }

	  public void remove() {
	    it.remove();
	  }

}
