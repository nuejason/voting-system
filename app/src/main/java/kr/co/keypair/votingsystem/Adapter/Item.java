package kr.co.keypair.votingsystem.Adapter;

public class Item {
    private String name;
    int image;

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage(){
        return image;
    }

    public String getName() {
        return name;
    }

}
