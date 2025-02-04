package product_shop.models.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;
    @Expose
    private List<UserDto> friends;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<UserDto> getFriends() {
        return friends;
    }

    public void setFriends(List<UserDto> friends) {
        this.friends = friends;
    }
}
