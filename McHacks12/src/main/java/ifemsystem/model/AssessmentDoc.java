package ifemsystem.model;

import java.util.*;

// line 75 "model.ump"
// line 163 "model.ump"
public class AssessmentDoc
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //AssessmentDoc Attributes
    private String description;

    //AssessmentDoc Associations
    private HospitalStay hospitalStay;
    private Doctor doctor;
    private TreatmentPlan treatmentPlan;
    private List<Test> tests;
    private IFEMs iFEMs;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public AssessmentDoc(String aDescription, HospitalStay aHospitalStay, Doctor aDoctor, IFEMs aIFEMs)
    {
        description = aDescription;
        boolean didAddHospitalStay = setHospitalStay(aHospitalStay);
        if (!didAddHospitalStay)
        {
            throw new RuntimeException("Unable to create assessmentDoc due to hospitalStay. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
        boolean didAddDoctor = setDoctor(aDoctor);
        if (!didAddDoctor)
        {
            throw new RuntimeException("Unable to create assessmentDoc due to doctor. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
        tests = new ArrayList<Test>();
        boolean didAddIFEMs = setIFEMs(aIFEMs);
        if (!didAddIFEMs)
        {
            throw new RuntimeException("Unable to create assessmentDoc due to iFEMs. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setDescription(String aDescription)
    {
        boolean wasSet = false;
        description = aDescription;
        wasSet = true;
        return wasSet;
    }

    public String getDescription()
    {
        return description;
    }
    /* Code from template association_GetOne */
    public HospitalStay getHospitalStay()
    {
        return hospitalStay;
    }
    /* Code from template association_GetOne */
    public Doctor getDoctor()
    {
        return doctor;
    }
    /* Code from template association_GetOne */
    public TreatmentPlan getTreatmentPlan()
    {
        return treatmentPlan;
    }

    public boolean hasTreatmentPlan()
    {
        boolean has = treatmentPlan != null;
        return has;
    }
    /* Code from template association_GetMany */
    public Test getTest(int index)
    {
        Test aTest = tests.get(index);
        return aTest;
    }

    public List<Test> getTests()
    {
        List<Test> newTests = Collections.unmodifiableList(tests);
        return newTests;
    }

    public int numberOfTests()
    {
        int number = tests.size();
        return number;
    }

    public boolean hasTests()
    {
        boolean has = tests.size() > 0;
        return has;
    }

    public int indexOfTest(Test aTest)
    {
        int index = tests.indexOf(aTest);
        return index;
    }
    /* Code from template association_GetOne */
    public IFEMs getIFEMs()
    {
        return iFEMs;
    }
    /* Code from template association_SetOneToOptionalOne */
    public boolean setHospitalStay(HospitalStay aNewHospitalStay)
    {
        boolean wasSet = false;
        if (aNewHospitalStay == null)
        {
            //Unable to setHospitalStay to null, as assessmentDoc must always be associated to a hospitalStay
            return wasSet;
        }

        AssessmentDoc existingAssessmentDoc = aNewHospitalStay.getAssessmentDoc();
        if (existingAssessmentDoc != null && !equals(existingAssessmentDoc))
        {
            //Unable to setHospitalStay, the current hospitalStay already has a assessmentDoc, which would be orphaned if it were re-assigned
            return wasSet;
        }

        HospitalStay anOldHospitalStay = hospitalStay;
        hospitalStay = aNewHospitalStay;
        hospitalStay.setAssessmentDoc(this);

        if (anOldHospitalStay != null)
        {
            anOldHospitalStay.setAssessmentDoc(null);
        }
        wasSet = true;
        return wasSet;
    }
    /* Code from template association_SetOneToMany */
    public boolean setDoctor(Doctor aDoctor)
    {
        boolean wasSet = false;
        if (aDoctor == null)
        {
            return wasSet;
        }

        Doctor existingDoctor = doctor;
        doctor = aDoctor;
        if (existingDoctor != null && !existingDoctor.equals(aDoctor))
        {
            existingDoctor.removeAssessmentDoc(this);
        }
        doctor.addAssessmentDoc(this);
        wasSet = true;
        return wasSet;
    }
    /* Code from template association_SetOptionalOneToMany */
    public boolean setTreatmentPlan(TreatmentPlan aTreatmentPlan)
    {
        boolean wasSet = false;
        TreatmentPlan existingTreatmentPlan = treatmentPlan;
        treatmentPlan = aTreatmentPlan;
        if (existingTreatmentPlan != null && !existingTreatmentPlan.equals(aTreatmentPlan))
        {
            existingTreatmentPlan.removeAssessmentDoc(this);
        }
        if (aTreatmentPlan != null)
        {
            aTreatmentPlan.addAssessmentDoc(this);
        }
        wasSet = true;
        return wasSet;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfTests()
    {
        return 0;
    }
    /* Code from template association_AddManyToManyMethod */
    public boolean addTest(Test aTest)
    {
        boolean wasAdded = false;
        if (tests.contains(aTest)) { return false; }
        tests.add(aTest);
        if (aTest.indexOfAssessmentDoc(this) != -1)
        {
            wasAdded = true;
        }
        else
        {
            wasAdded = aTest.addAssessmentDoc(this);
            if (!wasAdded)
            {
                tests.remove(aTest);
            }
        }
        return wasAdded;
    }
    /* Code from template association_RemoveMany */
    public boolean removeTest(Test aTest)
    {
        boolean wasRemoved = false;
        if (!tests.contains(aTest))
        {
            return wasRemoved;
        }

        int oldIndex = tests.indexOf(aTest);
        tests.remove(oldIndex);
        if (aTest.indexOfAssessmentDoc(this) == -1)
        {
            wasRemoved = true;
        }
        else
        {
            wasRemoved = aTest.removeAssessmentDoc(this);
            if (!wasRemoved)
            {
                tests.add(oldIndex,aTest);
            }
        }
        return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addTestAt(Test aTest, int index)
    {
        boolean wasAdded = false;
        if(addTest(aTest))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfTests()) { index = numberOfTests() - 1; }
            tests.remove(aTest);
            tests.add(index, aTest);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveTestAt(Test aTest, int index)
    {
        boolean wasAdded = false;
        if(tests.contains(aTest))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfTests()) { index = numberOfTests() - 1; }
            tests.remove(aTest);
            tests.add(index, aTest);
            wasAdded = true;
        }
        else
        {
            wasAdded = addTestAt(aTest, index);
        }
        return wasAdded;
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
            existingIFEMs.removeAssessmentDoc(this);
        }
        iFEMs.addAssessmentDoc(this);
        wasSet = true;
        return wasSet;
    }

    public void delete()
    {
        HospitalStay existingHospitalStay = hospitalStay;
        hospitalStay = null;
        if (existingHospitalStay != null)
        {
            existingHospitalStay.setAssessmentDoc(null);
        }
        Doctor placeholderDoctor = doctor;
        this.doctor = null;
        if(placeholderDoctor != null)
        {
            placeholderDoctor.removeAssessmentDoc(this);
        }
        if (treatmentPlan != null)
        {
            TreatmentPlan placeholderTreatmentPlan = treatmentPlan;
            this.treatmentPlan = null;
            placeholderTreatmentPlan.removeAssessmentDoc(this);
        }
        ArrayList<Test> copyOfTests = new ArrayList<Test>(tests);
        tests.clear();
        for(Test aTest : copyOfTests)
        {
            aTest.removeAssessmentDoc(this);
        }
        IFEMs placeholderIFEMs = iFEMs;
        this.iFEMs = null;
        if(placeholderIFEMs != null)
        {
            placeholderIFEMs.removeAssessmentDoc(this);
        }
    }


    public String toString()
    {
        return super.toString() + "["+
                "description" + ":" + getDescription()+ "]" + System.getProperties().getProperty("line.separator") +
                "  " + "hospitalStay = "+(getHospitalStay()!=null?Integer.toHexString(System.identityHashCode(getHospitalStay())):"null") + System.getProperties().getProperty("line.separator") +
                "  " + "doctor = "+(getDoctor()!=null?Integer.toHexString(System.identityHashCode(getDoctor())):"null") + System.getProperties().getProperty("line.separator") +
                "  " + "treatmentPlan = "+(getTreatmentPlan()!=null?Integer.toHexString(System.identityHashCode(getTreatmentPlan())):"null") + System.getProperties().getProperty("line.separator") +
                "  " + "iFEMs = "+(getIFEMs()!=null?Integer.toHexString(System.identityHashCode(getIFEMs())):"null");
    }
}
