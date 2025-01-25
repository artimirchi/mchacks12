package ifemsystem.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;
import java.sql.Time;

// line 117 "model.ump"
// line 217 "model.ump"
public class Child
{

    //------------------------
    // ENUMERATIONS
    //------------------------

    public enum Phase { Registered, Triaged, InvestigationPending, Ordered, Pending, Reported }

    //------------------------
    // STATIC VARIABLES
    //------------------------

    private static Map<String, Child> childsById = new HashMap<String, Child>();

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Child Attributes
    private String id;

    //Child Associations
    private List<Patient> patients;
    private List<HospitalStay> hospitalStaies;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Child(String aId, Patient... allPatients)
    {
        if (!setId(aId))
        {
            throw new RuntimeException("Cannot create due to duplicate id. See https://manual.umple.org?RE003ViolationofUniqueness.html");
        }
        patients = new ArrayList<Patient>();
        boolean didAddPatients = setPatients(allPatients);
        if (!didAddPatients)
        {
            throw new RuntimeException("Unable to create Child, must have at least 1 patients. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
        hospitalStaies = new ArrayList<HospitalStay>();
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setId(String aId)
    {
        boolean wasSet = false;
        String anOldId = getId();
        if (anOldId != null && anOldId.equals(aId)) {
            return true;
        }
        if (hasWithId(aId)) {
            return wasSet;
        }
        id = aId;
        wasSet = true;
        if (anOldId != null) {
            childsById.remove(anOldId);
        }
        childsById.put(aId, this);
        return wasSet;
    }

    public String getId()
    {
        return id;
    }
    /* Code from template attribute_GetUnique */
    public static Child getWithId(String aId)
    {
        return childsById.get(aId);
    }
    /* Code from template attribute_HasUnique */
    public static boolean hasWithId(String aId)
    {
        return getWithId(aId) != null;
    }
    /* Code from template association_GetMany */
    public Patient getPatient(int index)
    {
        Patient aPatient = patients.get(index);
        return aPatient;
    }

    public List<Patient> getPatients()
    {
        List<Patient> newPatients = Collections.unmodifiableList(patients);
        return newPatients;
    }

    public int numberOfPatients()
    {
        int number = patients.size();
        return number;
    }

    public boolean hasPatients()
    {
        boolean has = patients.size() > 0;
        return has;
    }

    public int indexOfPatient(Patient aPatient)
    {
        int index = patients.indexOf(aPatient);
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
    /* Code from template association_IsNumberOfValidMethod */
    public boolean isNumberOfPatientsValid()
    {
        boolean isValid = numberOfPatients() >= minimumNumberOfPatients();
        return isValid;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfPatients()
    {
        return 1;
    }
    /* Code from template association_AddManyToManyMethod */
    public boolean addPatient(Patient aPatient)
    {
        boolean wasAdded = false;
        if (patients.contains(aPatient)) { return false; }
        patients.add(aPatient);
        if (aPatient.indexOfChild(this) != -1)
        {
            wasAdded = true;
        }
        else
        {
            wasAdded = aPatient.addChild(this);
            if (!wasAdded)
            {
                patients.remove(aPatient);
            }
        }
        return wasAdded;
    }
    /* Code from template association_AddMStarToMany */
    public boolean removePatient(Patient aPatient)
    {
        boolean wasRemoved = false;
        if (!patients.contains(aPatient))
        {
            return wasRemoved;
        }

        if (numberOfPatients() <= minimumNumberOfPatients())
        {
            return wasRemoved;
        }

        int oldIndex = patients.indexOf(aPatient);
        patients.remove(oldIndex);
        if (aPatient.indexOfChild(this) == -1)
        {
            wasRemoved = true;
        }
        else
        {
            wasRemoved = aPatient.removeChild(this);
            if (!wasRemoved)
            {
                patients.add(oldIndex,aPatient);
            }
        }
        return wasRemoved;
    }
    /* Code from template association_SetMStarToMany */
    public boolean setPatients(Patient... newPatients)
    {
        boolean wasSet = false;
        ArrayList<Patient> verifiedPatients = new ArrayList<Patient>();
        for (Patient aPatient : newPatients)
        {
            if (verifiedPatients.contains(aPatient))
            {
                continue;
            }
            verifiedPatients.add(aPatient);
        }

        if (verifiedPatients.size() != newPatients.length || verifiedPatients.size() < minimumNumberOfPatients())
        {
            return wasSet;
        }

        ArrayList<Patient> oldPatients = new ArrayList<Patient>(patients);
        patients.clear();
        for (Patient aNewPatient : verifiedPatients)
        {
            patients.add(aNewPatient);
            if (oldPatients.contains(aNewPatient))
            {
                oldPatients.remove(aNewPatient);
            }
            else
            {
                aNewPatient.addChild(this);
            }
        }

        for (Patient anOldPatient : oldPatients)
        {
            anOldPatient.removeChild(this);
        }
        wasSet = true;
        return wasSet;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addPatientAt(Patient aPatient, int index)
    {
        boolean wasAdded = false;
        if(addPatient(aPatient))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfPatients()) { index = numberOfPatients() - 1; }
            patients.remove(aPatient);
            patients.add(index, aPatient);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMovePatientAt(Patient aPatient, int index)
    {
        boolean wasAdded = false;
        if(patients.contains(aPatient))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfPatients()) { index = numberOfPatients() - 1; }
            patients.remove(aPatient);
            patients.add(index, aPatient);
            wasAdded = true;
        }
        else
        {
            wasAdded = addPatientAt(aPatient, index);
        }
        return wasAdded;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfHospitalStaies()
    {
        return 0;
    }
    /* Code from template association_AddManyToOne */
    public HospitalStay addHospitalStay(Time aArrivalTime, Phase aPhase, Patient aPatient, IFEMs aIFEMs)
    {
        return new HospitalStay(aArrivalTime, aPhase, aPatient, aIFEMs, this);
    }

    public boolean addHospitalStay(HospitalStay aHospitalStay)
    {
        boolean wasAdded = false;
        if (hospitalStaies.contains(aHospitalStay)) { return false; }
        Child existingChild = aHospitalStay.getChild();
        boolean isNewChild = existingChild != null && !this.equals(existingChild);
        if (isNewChild)
        {
            aHospitalStay.setChild(this);
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
        //Unable to remove aHospitalStay, as it must always have a child
        if (!this.equals(aHospitalStay.getChild()))
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

    public void delete()
    {
        childsById.remove(getId());
        ArrayList<Patient> copyOfPatients = new ArrayList<Patient>(patients);
        patients.clear();
        for(Patient aPatient : copyOfPatients)
        {
            aPatient.removeChild(this);
        }
        for(int i=hospitalStaies.size(); i > 0; i--)
        {
            HospitalStay aHospitalStay = hospitalStaies.get(i - 1);
            aHospitalStay.delete();
        }
    }


    public String toString()
    {
        return super.toString() + "["+
                "id" + ":" + getId()+ "]";
    }
}
