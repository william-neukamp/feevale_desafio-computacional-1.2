package br.feevale.freqmax;

public class HeartRate {
    private String name;
    private int age;

    public HeartRate(String name, int age){
        this.name = name;
        this.age = age;
    }

    public HeartRate(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFrequency(){
        return 220 - this.age;
    }

    @Override
    public String toString() {
        return "FCM: " + this.getFrequency();
    }
}
