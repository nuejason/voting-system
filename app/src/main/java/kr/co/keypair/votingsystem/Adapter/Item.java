package kr.co.keypair.votingsystem.Adapter;

public class Item {
    private String name;
    int image;


    public Item (int image, String name){
        this.image= image;
        this.name = name;
    }

    public int getImage(){
        return image;
    }

    public String getName() {
        return name;
    }

}
