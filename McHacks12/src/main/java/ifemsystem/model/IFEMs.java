package ifemsystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;
import java.sql.Time;

// line 2 "model.ump"
// line 227 "model.ump"
public class IFEMs
{

    //------------------------
    // ENUMERATIONS
    //------------------------

    public enum Phase { Registered, Triaged, InvestigationPending, Ordered, Pending, Reported }
    public enum Triage { Resuscitation, Emergent, Urgent, LessUrgent, NonUrgent }

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //IFEMs Associations
    private List<User> users;
    private List<HospitalStay> hospitalStaies;
    private List<AssessmentDoc> assessmentDocs;
    private List<AssessmentNurse> assessmentNurses;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public IFEMs()
    {
        users = new ArrayList<User>();
        hospitalStaies = new ArrayList<HospitalStay>();
        assessmentDocs = new ArrayList<AssessmentDoc>();
        assessmentNurses = new ArrayList<AssessmentNurse>();
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
    /* Code from template association_GetMany */
    public HospitalStay getHospitalStay(int index)
    {
        HospitalStay aHospitalStay = hospitalStaies.get(index);
        return aHospitalStay;
    }

    public List<HospitalStay> getHospitalStaies()
    {
        List<HospitalStay> newHospitalStaies = Collections.unmodifiableList(hospitalStaies);
        return newHospitalStaies;
    }

    public int numberOfHospitalStaies()
    {
        int number = hospitalStaies.size();
        return number;
    }

    public boolean hasHospitalStaies()
    {
        boolean has = hospitalStaies.size() > 0;
        return has;
    }

    public int indexOfHospitalStay(HospitalStay aHospitalStay)
    {
        int index = hospitalStaies.indexOf(aHospitalStay);
        return index;
    }
    /* Code from template association_GetMany */
    public AssessmentDoc getAssessmentDoc(int index)
    {
        AssessmentDoc aAssessmentDoc = assessmentDocs.get(index);
        return aAssessmentDoc;
    }

    public List<AssessmentDoc> getAssessmentDocs()
    {
        List<AssessmentDoc> newAssessmentDocs = Collections.unmodifiableList(assessmentDocs);
        return newAssessmentDocs;
    }

    public int numberOfAssessmentDocs()
    {
        int number = assessmentDocs.size();
        return number;
    }

    public boolean hasAssessmentDocs()
    {
        boolean has = assessmentDocs.size() > 0;
        return has;
    }

    public int indexOfAssessmentDoc(AssessmentDoc aAssessmentDoc)
    {
        int index = assessmentDocs.indexOf(aAssessmentDoc);
        return index;
    }
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
    public static int minimumNumberOfUsers()
    {
        return 0;
    }
    /* Code from template association_AddManyToOne */


    public boolean addUser(User aUser)
    {
        boolean wasAdded = false;
        if (users.contains(aUser)) { return false; }
        IFEMs existingIFEMs = aUser.getIFEMs();
        boolean isNewIFEMs = existingIFEMs != null && !this.equals(existingIFEMs);
        if (isNewIFEMs)
        {
            aUser.setIFEMs(this);
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
        //Unable to remove aUser, as it must always have a iFEMs
        if (!this.equals(aUser.getIFEMs()))
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
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfHospitalStaies()
    {
        return 0;
    }
    /* Code from template association_AddManyToOne */
    public HospitalStay addHospitalStay(Time aArrivalTime, Phase aPhase, Patient aPatient, Child aChild)
    {
        return new HospitalStay(aArrivalTime, aPhase, aPatient, this, aChild);
    }

    public boolean addHospitalStay(HospitalStay aHospitalStay)
    {
        boolean wasAdded = false;
        if (hospitalStaies.contains(aHospitalStay)) { return false; }
        IFEMs existingIFEMs = aHospitalStay.getIFEMs();
        boolean isNewIFEMs = existingIFEMs != null && !this.equals(existingIFEMs);
        if (isNewIFEMs)
        {
            aHospitalStay.setIFEMs(this);
        }
        else
        {
            hospitalStaies.add(aHospitalStay);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeHospitalStay(HospitalStay aHospitalStay)
    {
        boolean wasRemoved = false;
        //Unable to remove aHospitalStay, as it must always have a iFEMs
        if (!this.equals(aHospitalStay.getIFEMs()))
        {
            hospitalStaies.remove(aHospitalStay);
            wasRemoved = true;
        }
        return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addHospitalStayAt(HospitalStay aHospitalStay, int index)
    {
        boolean wasAdded = false;
        if(addHospitalStay(aHospitalStay))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfHospitalStaies()) { index = numberOfHospitalStaies() - 1; }
            hospitalStaies.remove(aHospitalStay);
            hospitalStaies.add(index, aHospitalStay);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveHospitalStayAt(HospitalStay aHospitalStay, int index)
    {
        boolean wasAdded = false;
        if(hospitalStaies.contains(aHospitalStay))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfHospitalStaies()) { index = numberOfHospitalStaies() - 1; }
            hospitalStaies.remove(aHospitalStay);
            hospitalStaies.add(index, aHospitalStay);
            wasAdded = true;
        }
        else
        {
            wasAdded = addHospitalStayAt(aHospitalStay, index);
        }
        return wasAdded;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfAssessmentDocs()
    {
        return 0;
    }
    /* Code from template association_AddManyToOne */
    public AssessmentDoc addAssessmentDoc(String aDescription, HospitalStay aHospitalStay, Doctor aDoctor)
    {
        return new AssessmentDoc(aDescription, aHospitalStay, aDoctor, this);
    }

    public boolean addAssessmentDoc(AssessmentDoc aAssessmentDoc)
    {
        boolean wasAdded = false;
        if (assessmentDocs.contains(aAssessmentDoc)) { return false; }
        IFEMs existingIFEMs = aAssessmentDoc.getIFEMs();
        boolean isNewIFEMs = existingIFEMs != null && !this.equals(existingIFEMs);
        if (isNewIFEMs)
        {
            aAssessmentDoc.setIFEMs(this);
        }
        else
        {
            assessmentDocs.add(aAssessmentDoc);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeAssessmentDoc(AssessmentDoc aAssessmentDoc)
    {
        boolean wasRemoved = false;
        //Unable to remove aAssessmentDoc, as it must always have a iFEMs
        if (!this.equals(aAssessmentDoc.getIFEMs()))
        {
            assessmentDocs.remove(aAssessmentDoc);
            wasRemoved = true;
        }
        return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addAssessmentDocAt(AssessmentDoc aAssessmentDoc, int index)
    {
        boolean wasAdded = false;
        if(addAssessmentDoc(aAssessmentDoc))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfAssessmentDocs()) { index = numberOfAssessmentDocs() - 1; }
            assessmentDocs.remove(aAssessmentDoc);
            assessmentDocs.add(index, aAssessmentDoc);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveAssessmentDocAt(AssessmentDoc aAssessmentDoc, int index)
    {
        boolean wasAdded = false;
        if(assessmentDocs.contains(aAssessmentDoc))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfAssessmentDocs()) { index = numberOfAssessmentDocs() - 1; }
            assessmentDocs.remove(aAssessmentDoc);
            assessmentDocs.add(index, aAssessmentDoc);
            wasAdded = true;
        }
        else
        {
            wasAdded = addAssessmentDocAt(aAssessmentDoc, index);
        }
        return wasAdded;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfAssessmentNurses()
    {
        return 0;
    }
    /* Code from template association_AddManyToOne */
    public AssessmentNurse addAssessmentNurse(Triage aTriage, Nurse aNurse, HospitalStay aHospitalStay)
    {
        return new AssessmentNurse(aTriage, aNurse, aHospitalStay, this);
    }

    public boolean addAssessmentNurse(AssessmentNurse aAssessmentNurse)
    {
        boolean wasAdded = false;
        if (assessmentNurses.contains(aAssessmentNurse)) { return false; }
        IFEMs existingIFEMs = aAssessmentNurse.getIFEMs();
        boolean isNewIFEMs = existingIFEMs != null && !this.equals(existingIFEMs);
        if (isNewIFEMs)
        {
            aAssessmentNurse.setIFEMs(this);
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
        //Unable to remove aAssessmentNurse, as it must always have a iFEMs
        if (!this.equals(aAssessmentNurse.getIFEMs()))
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
        while (users.size() > 0)
        {
            User aUser = users.get(users.size() - 1);
            aUser.delete();
            users.remove(aUser);
        }

        while (hospitalStaies.size() > 0)
        {
            HospitalStay aHospitalStay = hospitalStaies.get(hospitalStaies.size() - 1);
            aHospitalStay.delete();
            hospitalStaies.remove(aHospitalStay);
        }

        while (assessmentDocs.size() > 0)
        {
            AssessmentDoc aAssessmentDoc = assessmentDocs.get(assessmentDocs.size() - 1);
            aAssessmentDoc.delete();
            assessmentDocs.remove(aAssessmentDoc);
        }

        while (assessmentNurses.size() > 0)
        {
            AssessmentNurse aAssessmentNurse = assessmentNurses.get(assessmentNurses.size() - 1);
            aAssessmentNurse.delete();
            assessmentNurses.remove(aAssessmentNurse);
        }

    }

}
