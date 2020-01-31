/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Brandstrup
 */
public class Animal
{

    private String type;
    private int birthYear;
    private String sound;
    //Too lazy to make ENUM classes for the type/sound

    public Animal()
    {
    }

    public Animal(String type, int birthYear, String sound)
    {
        this.type = type;
        this.birthYear = birthYear;
        this.sound = sound;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getBirthYear()
    {
        return birthYear;
    }

    public void setBirthYear(int birthYear)
    {
        this.birthYear = birthYear;
    }

    public String getSound()
    {
        return sound;
    }

    public void setSound(String sound)
    {
        this.sound = sound;
    }

    @Override
    public String toString()
    {
        return "Animal{" + "type=" + type + ", birthYear=" + birthYear + ", sound=" + sound + '}';
    }

}
