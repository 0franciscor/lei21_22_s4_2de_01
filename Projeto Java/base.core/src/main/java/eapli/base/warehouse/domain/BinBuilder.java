package eapli.base.warehouse.domain;

public class BinBuilder {

    private Bin bin;

    private BinId Id;

    public BinBuilder(){}

    public BinBuilder withId(BinId Id){
        this.Id = Id;
        return this;
    }

    private Bin buildBin(){
        if(bin != null)
            return bin;
        else if(Id != null) {
            this.bin = new Bin(Id);
            return bin;
        }
        return null;
    }

    public Bin build(){
        final Bin returnBin = buildBin();
        return returnBin;
    }
}
