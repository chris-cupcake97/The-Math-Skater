/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themathskater.Model;

/**
 *
 * @author Christina.2114893 <christinadeborah458@gmail.com>
 */
public class Players {

    private String name;
    private String email;
    private String password;
    private int Age;
    private int highscores;

    public Players(String name, String email, String password, int age, int highscores) {
        this.name = name;
        this.email = email;
        this.password = password;
        Age = age;
        this.highscores = highscores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getHighscores() {
        return highscores;
    }

    public void setHighscores(int highscores) {
        this.highscores = highscores;
    }
}
