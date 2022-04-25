package eapli.base.warehouse.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Bin implements ValueObject {
    private int size;

    public Bin(){}

    public Bin(final int size){
        setSize(size);
    }

    private void setSize(final int size){
        if(size <= 0) throw new IllegalArgumentException("A bin's size must be bigger than 0.");
        this.size = size;
    }

    public boolean equals(Object obj){
        if(obj == null) return false;

        if(this == obj) return true;

        return size == ((Bin) obj).size;
    }

    public String toString(){
        return "Bin sized at " + size;
    }
}
