package oy.interact.tira.student;

import oy.interact.tira.model.Coder;
import java.util.Comparator;

public class CoderFullNameComparator implements Comparator<Coder> {
    
    @Override
    public int compare(Coder coder1, Coder coder2){
        return coder1.compareTo(coder2);
    }
}
