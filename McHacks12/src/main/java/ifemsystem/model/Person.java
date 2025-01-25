package ifemsystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 69 "model.ump"
// line 156 "model.ump"
public class Person
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Person Associations
    private List<User> users;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Person()
    {
        users = new ArrayList<User>();
    }

    //------------------------
    // INTERFACE
    //------------------------
    /* Code from template association_GetMany */
    public User getUser(int index)
    {
        User aUser = users.get(index);
        return aUser;
    }

    public List<User> getUsers()
    {
        List<User> newUsers = Collections.unmodifiableList(users);
        return newUsers;
    }

    public int numberOfUsers()
    {
        int number = users.size();
        return number;
    }

    public boolean hasUsers()
    {
        boolean has = users.size() > 0;
        return has;
    }

    public int indexOfUser(User aUser)
    {
        int index = users.indexOf(aUser);
        return index;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfUsers()
    {
        return 0;
    }
    /* Code from template association_AddManyToOne */


    public boolean addUser(User aUser)
    {
        boolean wasAdded = false;
        if (users.contains(aUser)) { return false; }
        Person existingPerson = aUser.getPerson();
        boolean isNewPerson = existingPerson != null && !this.equals(existingPerson);
        if (isNewPerson)
        {
            aUser.setPerson(this);
        }
        else
        {
            users.add(aUser);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeUser(User aUser)
    {
        boolean wasRemoved = false;
        //Unable to remove aUser, as it must always have a person
        if (!this.equals(aUser.getPerson()))
        {
            users.remove(aUser);
            wasRemoved = true;
        }
        return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addUserAt(User aUser, int index)
    {
        boolean wasAdded = false;
        if(addUser(aUser))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
            users.remove(aUser);
            users.add(index, aUser);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveUserAt(User aUser, int index)
    {
        boolean wasAdded = false;
        if(users.contains(aUser))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
            users.remove(aUser);
            users.add(index, aUser);
            wasAdded = true;
        }
        else
        {
            wasAdded = addUserAt(aUser, index);
        }
        return wasAdded;
    }

    public void delete()
    {
        for(int i=users.size(); i > 0; i--)
        {
            User aUser = users.get(i - 1);
            aUser.delete();
        }
    }

}
