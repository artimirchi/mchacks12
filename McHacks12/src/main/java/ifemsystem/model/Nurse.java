package ifemsystem.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 53 "model.ump"
// line 141 "model.ump"
public class Nurse extends Employee
{

    //------------------------
    // ENUMERATIONS
    //------------------------

    public enum Triage { Resuscitation, Emergent, Urgent, LessUrgent, NonUrgent }

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Nurse Associations
    private List<AssessmentNurse> assessmentNurses;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Nurse(String aEmail, String aPassword, IFEMs aIFEMs, Person aPerson, int aEmployeeID)
    {
        super(aEmail, aPassword, aIFEMs, aPerson, aEmployeeID);
        assessmentNurses = new ArrayList<AssessmentNurse>();
    }

    //------------------------
    // INTERFACE
    //------------------------
    /* Code from template association_GetMany */
    public AssessmentNurse getAssessmentNurse(int index)
    {
        AssessmentNurse aAssessmentNurse = assessmentNurses.get(index);
        return aAssessmentNurse;
    }

    public List<AssessmentNurse> getAssessmentNurses()
    {
        List<AssessmentNurse> newAssessmentNurses = Collections.unmodifiableList(assessmentNurses);
        return newAssessmentNurses;
    }

    public int numberOfAssessmentNurses()
    {
        int number = assessmentNurses.size();
        return number;
    }

    public boolean hasAssessmentNurses()
    {
        boolean has = assessmentNurses.size() > 0;
        return has;
    }

    public int indexOfAssessmentNurse(AssessmentNurse aAssessmentNurse)
    {
        int index = assessmentNurses.indexOf(aAssessmentNurse);
        return index;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfAssessmentNurses()
    {
        return 0;
    }
    /* Code from template association_AddManyToOne */
  /*public AssessmentNurse addAssessmentNurse(Triage aTriage, HospitalStay aHospitalStay, IFEMs aIFEMs)
  {
    return new AssessmentNurse(aTriage, aHospitalStay, aIFEMs);
  }*/

    public boolean addAssessmentNurse(AssessmentNurse aAssessmentNurse)
    {
        boolean wasAdded = false;
        if (assessmentNurses.contains(aAssessmentNurse)) { return false; }
        Nurse existingNurse = aAssessmentNurse.getNurse();
        boolean isNewNurse = existingNurse != null && !this.equals(existingNurse);
        if (isNewNurse)
        {
            aAssessmentNurse.setNurse(this);
        }
        else
        {
            assessmentNurses.add(aAssessmentNurse);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeAssessmentNurse(AssessmentNurse aAssessmentNurse)
    {
        boolean wasRemoved = false;
        //Unable to remove aAssessmentNurse, as it must always have a nurse
        if (!this.equals(aAssessmentNurse.getNurse()))
        {
            assessmentNurses.remove(aAssessmentNurse);
            wasRemoved = true;
        }
        return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addAssessmentNurseAt(AssessmentNurse aAssessmentNurse, int index)
    {
        boolean wasAdded = false;
        if(addAssessmentNurse(aAssessmentNurse))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfAssessmentNurses()) { index = numberOfAssessmentNurses() - 1; }
            assessmentNurses.remove(aAssessmentNurse);
            assessmentNurses.add(index, aAssessmentNurse);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveAssessmentNurseAt(AssessmentNurse aAssessmentNurse, int index)
    {
        boolean wasAdded = false;
        if(assessmentNurses.contains(aAssessmentNurse))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfAssessmentNurses()) { index = numberOfAssessmentNurses() - 1; }
            assessmentNurses.remove(aAssessmentNurse);
            assessmentNurses.add(index, aAssessmentNurse);
            wasAdded = true;
        }
        else
        {
            wasAdded = addAssessmentNurseAt(aAssessmentNurse, index);
        }
        return wasAdded;
    }

    public void delete()
    {
        for(int i=assessmentNurses.size(); i > 0; i--)
        {
            AssessmentNurse aAssessmentNurse = assessmentNurses.get(i - 1);
            aAssessmentNurse.delete();
        }
        super.delete();
    }

}
