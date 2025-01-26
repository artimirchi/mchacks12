package main.java.ifemsystem.model;

import java.util.HashMap;
import java.util.Map;
import java.util.*;


    /*PLEASE DO NOT EDIT THIS CODE*/
    /*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/




    // line 63 "model.ump"
// line 151 "model.ump"
    public abstract class User
    {

        //------------------------
        // STATIC VARIABLES
        //------------------------

        private static Map<String, User> usersByEmail = new HashMap<String, User>();

        //------------------------
        // MEMBER VARIABLES
        //------------------------

        //User Attributes
        private String email;
        private String password;

        //User Associations
        private IFEMs iFEMs;
        private Person person;

        //------------------------
        // CONSTRUCTOR
        //------------------------

        public User(String aEmail, String aPassword, IFEMs aIFEMs, Person aPerson)
        {
            password = aPassword;
            if (!setEmail(aEmail))
            {
                throw new RuntimeException("Cannot create due to duplicate email. See https://manual.umple.org?RE003ViolationofUniqueness.html");
            }
            boolean didAddIFEMs = setIFEMs(aIFEMs);
            if (!didAddIFEMs)
            {
                throw new RuntimeException("Unable to create user due to iFEMs. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
            }
            boolean didAddPerson = setPerson(aPerson);
            if (!didAddPerson)
            {
                throw new RuntimeException("Unable to create user due to person. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
            }
        }

        //------------------------
        // INTERFACE
        //------------------------

        public boolean setEmail(String aEmail)
        {
            boolean wasSet = false;
            String anOldEmail = getEmail();
            if (anOldEmail != null && anOldEmail.equals(aEmail)) {
                return true;
            }
            if (hasWithEmail(aEmail)) {
                return wasSet;
            }
            email = aEmail;
            wasSet = true;
            if (anOldEmail != null) {
                usersByEmail.remove(anOldEmail);
            }
            usersByEmail.put(aEmail, this);
            return wasSet;
        }

        public boolean setPassword(String aPassword)
        {
            boolean wasSet = false;
            password = aPassword;
            wasSet = true;
            return wasSet;
        }

        public String getEmail()
        {
            return email;
        }
        /* Code from template attribute_GetUnique */
        public static User getWithEmail(String aEmail)
        {
            return usersByEmail.get(aEmail);
        }
        /* Code from template attribute_HasUnique */
        public static boolean hasWithEmail(String aEmail)
        {
            return getWithEmail(aEmail) != null;
        }

        public String getPassword()
        {
            return password;
        }
        /* Code from template association_GetOne */
        public IFEMs getIFEMs()
        {
            return iFEMs;
        }
        /* Code from template association_GetOne */
        public Person getPerson()
        {
            return person;
        }
        /* Code from template association_SetOneToMany */
        public boolean setIFEMs(IFEMs aIFEMs)
        {
            boolean wasSet = false;
            if (aIFEMs == null)
            {
                return wasSet;
            }

            IFEMs existingIFEMs = iFEMs;
            iFEMs = aIFEMs;
            if (existingIFEMs != null && !existingIFEMs.equals(aIFEMs))
            {
                existingIFEMs.removeUser(this);
            }
            iFEMs.addUser(this);
            wasSet = true;
            return wasSet;
        }
        /* Code from template association_SetOneToMany */
        public boolean setPerson(Person aPerson)
        {
            boolean wasSet = false;
            if (aPerson == null)
            {
                return wasSet;
            }

            Person existingPerson = person;
            person = aPerson;
            if (existingPerson != null && !existingPerson.equals(aPerson))
            {
                existingPerson.removeUser(this);
            }
            person.addUser(this);
            wasSet = true;
            return wasSet;
        }

        public void delete()
        {
            usersByEmail.remove(getEmail());
            IFEMs placeholderIFEMs = iFEMs;
            this.iFEMs = null;
            if(placeholderIFEMs != null)
            {
                placeholderIFEMs.removeUser(this);
            }
            Person placeholderPerson = person;
            this.person = null;
            if(placeholderPerson != null)
            {
                placeholderPerson.removeUser(this);
            }
        }


        public String toString()
        {
            return super.toString() + "["+
                    "email" + ":" + getEmail()+ "," +
                    "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
                    "  " + "iFEMs = "+(getIFEMs()!=null?Integer.toHexString(System.identityHashCode(getIFEMs())):"null") + System.getProperties().getProperty("line.separator") +
                    "  " + "person = "+(getPerson()!=null?Integer.toHexString(System.identityHashCode(getPerson())):"null");
        }
    }

