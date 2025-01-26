package main.java.ifemsystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 59 "model.ump"
// line 146 "model.ump"
public abstract class Employee extends User
{

    //------------------------
    // STATIC VARIABLES
    //------------------------

    private static Map<Integer, Employee> employeesByEmployeeID = new HashMap<Integer, Employee>();

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Employee Attributes
    private int employeeID;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Employee(String aEmail, String aPassword, IFEMs aIFEMs, Person aPerson, int aEmployeeID)
    {
        super(aEmail, aPassword, aIFEMs, aPerson);
        if (!setEmployeeID(aEmployeeID))
        {
            throw new RuntimeException("Cannot create due to duplicate employeeID. See https://manual.umple.org?RE003ViolationofUniqueness.html");
        }
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setEmployeeID(int aEmployeeID)
    {
        boolean wasSet = false;
        Integer anOldEmployeeID = getEmployeeID();
        if (anOldEmployeeID != null && anOldEmployeeID.equals(aEmployeeID)) {
            return true;
        }
        if (hasWithEmployeeID(aEmployeeID)) {
            return wasSet;
        }
        employeeID = aEmployeeID;
        wasSet = true;
        if (anOldEmployeeID != null) {
            employeesByEmployeeID.remove(anOldEmployeeID);
        }
        employeesByEmployeeID.put(aEmployeeID, this);
        return wasSet;
    }

    public int getEmployeeID()
    {
        return employeeID;
    }
    /* Code from template attribute_GetUnique */
    public static Employee getWithEmployeeID(int aEmployeeID)
    {
        return employeesByEmployeeID.get(aEmployeeID);
    }
    /* Code from template attribute_HasUnique */
    public static boolean hasWithEmployeeID(int aEmployeeID)
    {
        return getWithEmployeeID(aEmployeeID) != null;
    }

    public void delete()
    {
        employeesByEmployeeID.remove(getEmployeeID());
        super.delete();
    }


    public String toString()
    {
        return super.toString() + "["+
                "employeeID" + ":" + getEmployeeID()+ "]";
    }
}
