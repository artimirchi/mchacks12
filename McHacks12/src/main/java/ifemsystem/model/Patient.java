package main.java.ifemsystem.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;
import java.sql.Time;

// line 11 "model.ump"
// line 124 "model.ump"
public class Patient extends User
{

    //------------------------
    // ENUMERATIONS
    //------------------------

    public enum Phase { Registered, Triaged, InvestigationPending, Ordered, Pending, Reported }

    //------------------------
    // STATIC VARIABLES
    //------------------------

    private static Map<String, Patient> patientsById = new HashMap<String, Patient>();

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Patient Attributes
    private String id;

    //Patient Associations
    private List<HospitalStay> hospitalStaies;
    private MedicalRecord medicalRecord;
    private List<Child> children;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Patient(String aEmail, String aPassword, IFEMs aIFEMs, Person aPerson, String aId)
    {
        super(aEmail, aPassword, aIFEMs, aPerson);
        if (!setId(aId))
        {
            throw new RuntimeException("Cannot create due to duplicate id. See https://manual.umple.org?RE003ViolationofUniqueness.html");
        }
        hospitalStaies = new ArrayList<HospitalStay>();
        children = new ArrayList<Child>();
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
            patientsById.remove(anOldId);
        }
        patientsById.put(aId, this);
        return wasSet;
    }

    public String getId()
    {
        return id;
    }
    /* Code from template attribute_GetUnique */
    public static Patient getWithId(String aId)
    {
        return patientsById.get(aId);
    }
    /* Code from template attribute_HasUnique */
    public static boolean hasWithId(String aId)
    {
        return getWithId(aId) != null;
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
    /* Code from template association_GetOne */
    public MedicalRecord getMedicalRecord()
    {
        return medicalRecord;
    }

    public boolean hasMedicalRecord()
    {
        boolean has = medicalRecord != null;
        return has;
    }
    /* Code from template association_GetMany */
    public Child getChild(int index)
    {
        Child aChild = children.get(index);
        return aChild;
    }

    public List<Child> getChildren()
    {
        List<Child> newChildren = Collections.unmodifiableList(children);
        return newChildren;
    }

    public int numberOfChildren()
    {
        int number = children.size();
        return number;
    }

    public boolean hasChildren()
    {
        boolean has = children.size() > 0;
        return has;
    }

    public int indexOfChild(Child aChild)
    {
        int index = children.indexOf(aChild);
        return index;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfHospitalStaies()
    {
        return 0;
    }
    /* Code from template association_AddManyToOne */
    public HospitalStay addHospitalStay(Time aArrivalTime, Phase aPhase, IFEMs aIFEMs, Child aChild)
    {
        return new HospitalStay(aArrivalTime, aPhase, this, aIFEMs, aChild);
    }

    public boolean addHospitalStay(HospitalStay aHospitalStay)
    {
        boolean wasAdded = false;
        if (hospitalStaies.contains(aHospitalStay)) { return false; }
        Patient existingPatient = aHospitalStay.getPatient();
        boolean isNewPatient = existingPatient != null && !this.equals(existingPatient);
        if (isNewPatient)
        {
            aHospitalStay.setPatient(this);
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
        //Unable to remove aHospitalStay, as it must always have a patient
        if (!this.equals(aHospitalStay.getPatient()))
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
    /* Code from template association_SetOptionalOneToOne */
    public boolean setMedicalRecord(MedicalRecord aNewMedicalRecord)
    {
        boolean wasSet = false;
        if (medicalRecord != null && !medicalRecord.equals(aNewMedicalRecord) && equals(medicalRecord.getPatient()))
        {
            //Unable to setMedicalRecord, as existing medicalRecord would become an orphan
            return wasSet;
        }

        medicalRecord = aNewMedicalRecord;
        Patient anOldPatient = aNewMedicalRecord != null ? aNewMedicalRecord.getPatient() : null;

        if (!this.equals(anOldPatient))
        {
            if (anOldPatient != null)
            {
                anOldPatient.medicalRecord = null;
            }
            if (medicalRecord != null)
            {
                medicalRecord.setPatient(this);
            }
        }
        wasSet = true;
        return wasSet;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfChildren()
    {
        return 0;
    }
    /* Code from template association_AddManyToManyMethod */
    public boolean addChild(Child aChild)
    {
        boolean wasAdded = false;
        if (children.contains(aChild)) { return false; }
        children.add(aChild);
        if (aChild.indexOfPatient(this) != -1)
        {
            wasAdded = true;
        }
        else
        {
            wasAdded = aChild.addPatient(this);
            if (!wasAdded)
            {
                children.remove(aChild);
            }
        }
        return wasAdded;
    }
    /* Code from template association_RemoveMany */
    public boolean removeChild(Child aChild)
    {
        boolean wasRemoved = false;
        if (!children.contains(aChild))
        {
            return wasRemoved;
        }

        int oldIndex = children.indexOf(aChild);
        children.remove(oldIndex);
        if (aChild.indexOfPatient(this) == -1)
        {
            wasRemoved = true;
        }
        else
        {
            wasRemoved = aChild.removePatient(this);
            if (!wasRemoved)
            {
                children.add(oldIndex,aChild);
            }
        }
        return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addChildAt(Child aChild, int index)
    {
        boolean wasAdded = false;
        if(addChild(aChild))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfChildren()) { index = numberOfChildren() - 1; }
            children.remove(aChild);
            children.add(index, aChild);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveChildAt(Child aChild, int index)
    {
        boolean wasAdded = false;
        if(children.contains(aChild))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfChildren()) { index = numberOfChildren() - 1; }
            children.remove(aChild);
            children.add(index, aChild);
            wasAdded = true;
        }
        else
        {
            wasAdded = addChildAt(aChild, index);
        }
        return wasAdded;
    }

    public void delete()
    {
        patientsById.remove(getId());
        for(int i=hospitalStaies.size(); i > 0; i--)
        {
            HospitalStay aHospitalStay = hospitalStaies.get(i - 1);
            aHospitalStay.delete();
        }
        MedicalRecord existingMedicalRecord = medicalRecord;
        medicalRecord = null;
        if (existingMedicalRecord != null)
        {
            existingMedicalRecord.delete();
        }
        ArrayList<Child> copyOfChildren = new ArrayList<Child>(children);
        children.clear();
        for(Child aChild : copyOfChildren)
        {
            if (aChild.numberOfPatients() <= Child.minimumNumberOfPatients())
            {
                aChild.delete();
            }
            else
            {
                aChild.removePatient(this);
            }
        }
        super.delete();
    }


    public String toString()
    {
        return super.toString() + "["+
                "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
                "  " + "medicalRecord = "+(getMedicalRecord()!=null?Integer.toHexString(System.identityHashCode(getMedicalRecord())):"null");
    }
}
